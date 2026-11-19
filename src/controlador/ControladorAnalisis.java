package controlador;

import java.io.File;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.function.BiConsumer;
import javax.swing.SwingWorker;
import modelo.Estadisticas;
import modelo.ModeloArchivos;
import modelo.NodoArchivo;
import vista.VistaPrincipal;

public class ControladorAnalisis {

    private final VistaPrincipal vista;
    private final ModeloArchivos modelo = new ModeloArchivos();
    private final BiConsumer<String, String> registro;

    private SwingWorker<Object, Void> tarea;
    private Estadisticas estadisticas;
    private File raizActual;

    public ControladorAnalisis(VistaPrincipal vista, BiConsumer<String, String> registro) {
        this.vista = vista;
        this.registro = registro;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public File getRaizActual() {
        return raizActual;
    }

    public void analizar() {
        File raiz = validar();
        if (raiz == null) {
            return;
        }
        int nivel = leerNivel();
        registro.accept("ANALIZAR", raiz.getAbsolutePath());
        ejecutar("analizar", raiz, nivel, null, null, -1, -1, -1, -1);
    }

    public void buscar() {
        File raiz = validar();
        if (raiz == null) {
            return;
        }
        int nivel = leerNivel();
        double min = leerKB(vista.getMin(), "mínimo");
        double max = leerKB(vista.getMax(), "máximo");
        if (min >= 0 && max >= 0 && min > max) {
            throw new IllegalArgumentException("El mínimo no puede ser mayor que el máximo.");
        }
        long desde = leerFecha(vista.getDesde(), false);
        long hasta = leerFecha(vista.getHasta(), true);
        if (desde >= 0 && hasta >= 0 && desde > hasta) {
            throw new IllegalArgumentException("Las fechas no son coherentes.");
        }
        registro.accept("BUSCAR", raiz.getAbsolutePath() + " | " + vista.getTexto());
        ejecutar("buscar", raiz, nivel, vista.getTexto(), vista.getExtension(),
                min, max, desde, hasta);
    }

    public void duplicados() {
        File raiz = validar();
        if (raiz == null) {
            return;
        }
        int nivel = leerNivel();
        registro.accept("DETECTAR DUPLICADOS", raiz.getAbsolutePath());
        ejecutar("duplicados", raiz, nivel, null, null, -1, -1, -1, -1);
    }

    public void cancelar() {
        modelo.cancelado = true;
        cancelarTarea();
        vista.setEstado("Cancelando...");
    }

    public void cancelarTarea() {
        if (tarea != null && !tarea.isDone()) {
            tarea.cancel(true);
        }
    }

    private File validar() {
        try {
            return modelo.validarRuta(vista.getRuta());
        } catch (Exception e) {
            vista.mostrarError(mensaje(e));
            return null;
        }
    }

    private void ejecutar(final String tipo, final File raiz, final int nivel,
            final String texto, final String extension, final double min, final double max,
            final long desde, final long hasta) {
        if (raiz == null) {
            return;
        }
        cancelarTarea();
        modelo.cancelado = false;
        modelo.sinPermiso = 0;

        tarea = new SwingWorker<Object, Void>() {
            @Override
            protected Object doInBackground() throws Exception {
                if (tipo.equals("analizar")) {
                    Estadisticas e = new Estadisticas();
                    NodoArchivo arbol = modelo.analizar(raiz, vista.getOcultos(), nivel, e);
                    return new Object[]{arbol, e};
                }
                if (tipo.equals("buscar")) {
                    return modelo.buscar(raiz, texto, extension, min, max, desde, hasta,
                            vista.getOcultos(), nivel);
                }
                return modelo.duplicados(raiz, vista.getOcultos(), nivel);
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void done() {
                try {
                    Object resultado = get();
                    if (tipo.equals("analizar")) {
                        Object[] datos = (Object[]) resultado;
                        estadisticas = (Estadisticas) datos[1];
                        raizActual = raiz;
                        vista.mostrarArbol((NodoArchivo) datos[0]);
                        vista.mostrarEstadisticas(estadisticas, raiz);
                        vista.setEstado("Análisis terminado. Visitados: " + modelo.visitados);
                    } else if (tipo.equals("buscar")) {
                        ArrayList<File> archivos = (ArrayList<File>) resultado;
                        vista.mostrarResultados(archivos);
                        vista.setEstado("Resultados: " + archivos.size());
                    } else {
                        Map<String, ArrayList<File>> grupos =
                                (Map<String, ArrayList<File>>) resultado;
                        vista.mostrarDuplicados(grupos);
                        vista.setEstado("Grupos de duplicados: " + grupos.size());
                    }
                } catch (CancellationException e) {
                    vista.setEstado("Operación cancelada.");
                } catch (Exception e) {
                    vista.mostrarError(mensaje(e));
                } finally {
                    if (modelo.sinPermiso > 0) {
                        registro.accept("SIN PERMISO", "omitidos=" + modelo.sinPermiso);
                    }
                    vista.terminarProgreso();
                    tarea = null;
                }
            }
        };
        vista.iniciarProgreso("Trabajando...");
        tarea.execute();
    }

    private int leerNivel() {
        String valor = vista.getNivel();
        if (valor.isEmpty()) {
            return 0;
        }
        try {
            int nivel = Integer.parseInt(valor);
            if (nivel < 0) {
                throw new IllegalArgumentException("La profundidad no puede ser negativa.");
            }
            return nivel;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La profundidad debe ser un número entero.");
        }
    }

    private double leerKB(String valor, String nombre) {
        if (valor.isEmpty()) {
            return -1;
        }
        try {
            double numero = Double.parseDouble(valor.replace(',', '.'));
            if (numero < 0) {
                throw new IllegalArgumentException("El tamaño " + nombre + " no puede ser negativo.");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Tamaño " + nombre + " no válido.");
        }
    }

    private long leerFecha(String valor, boolean finDelDia) {
        if (valor.isEmpty()) {
            return -1;
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        formato.setLenient(false);
        ParsePosition posicion = new ParsePosition(0);
        Date fecha = formato.parse(valor, posicion);
        if (fecha == null || posicion.getIndex() != valor.length()) {
            throw new IllegalArgumentException("Fecha no válida. Use yyyy-MM-dd.");
        }
        return finDelDia ? fecha.getTime() + 86400000L - 1 : fecha.getTime();
    }

    private String mensaje(Exception e) {
        return e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
    }
}
