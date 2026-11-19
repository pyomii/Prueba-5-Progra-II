package controlador;

import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import modelo.GestorArchivos;
import vista.VistaPrincipal;

public class ControladorGestion {

    private final VistaPrincipal vista;
    private final GestorArchivos gestor = new GestorArchivos();
    private final BiConsumer<String, String> registro;
    private final ControladorAnalisis analisis;

    public ControladorGestion(VistaPrincipal vista, BiConsumer<String, String> registro,
            ControladorAnalisis analisis) {
        this.vista = vista;
        this.registro = registro;
        this.analisis = analisis;
    }

    public void operacion(int opcion) {
        switch (opcion) {
            case 0: crearCarpeta(); break;
            case 1: crearArchivo(); break;
            case 2: renombrar(); break;
            case 3: copiar(); break;
            case 4: mover(); break;
            case 5: eliminar(); break;
            case 6: editarTexto(); break;
            case 7: verBitacora(); break;
            case 8: exportar(); break;
            default: break;
        }
    }

    private File base() {
        File carpeta = vista.getCarpetaBase();
        if (carpeta == null) {
            vista.mostrarError("Seleccione una carpeta base o escriba una ruta válida.");
        }
        return carpeta;
    }

    private File seleccion() {
        File archivo = vista.getArchivoSeleccionado();
        if (archivo == null) {
            vista.mostrarError("Seleccione un archivo o carpeta.");
        }
        return archivo;
    }

    private void crearCarpeta() {
        File base = base();
        if (base == null) {
            return;
        }
        String nombre = vista.pedirTexto("Nombre de carpeta (se admite proyectos/src):");
        if (nombre == null) {
            return;
        }
        try {
            gestor.crearCarpeta(base, nombre);
            vista.setEstado("Carpeta creada.");
            analisis.analizar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void crearArchivo() {
        File base = base();
        if (base == null) {
            return;
        }
        String nombre = vista.pedirTexto("Nombre del archivo:");
        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }
        String contenido = vista.pedirContenido("Contenido del archivo", "");
        if (contenido == null) {
            return;
        }
        String nombreFinal = nombre.contains(".") ? nombre : nombre + ".txt";
        File destino = new File(base, nombreFinal);
        boolean sobrescribir = destino.exists() && vista.confirmar("El archivo ya existe. ¿Sobrescribir?");
        if (destino.exists() && !sobrescribir) {
            return;
        }
        try {
            gestor.crearArchivoTexto(base, nombre, contenido, sobrescribir);
            vista.setEstado("Archivo creado.");
            analisis.analizar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void renombrar() {
        File seleccion = seleccion();
        if (seleccion == null) {
            return;
        }
        String nuevo = vista.pedirTexto("Nuevo nombre:");
        if (nuevo == null || nuevo.trim().isEmpty()) {
            return;
        }
        try {
            gestor.renombrar(seleccion, nuevo);
            analisis.analizar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void copiar() {
        moverOCopiar(true);
    }

    private void mover() {
        moverOCopiar(false);
    }

    private void moverOCopiar(boolean copiar) {
        File seleccion = seleccion();
        if (seleccion == null) {
            return;
        }
        File destino = vista.pedirCarpeta("Carpeta de destino");
        if (destino == null) {
            return;
        }
        File finalPath = new File(destino, seleccion.getName());
        boolean sobrescribir = finalPath.exists() && vista.confirmar(
                "Ya existe:\n" + finalPath.getAbsolutePath() + "\n¿Sobrescribir?");
        try {
            if (copiar) {
                gestor.copiar(seleccion, destino, sobrescribir);
            } else {
                gestor.mover(seleccion, destino, sobrescribir);
            }
            analisis.analizar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void eliminar() {
        File seleccion = seleccion();
        if (seleccion == null) {
            return;
        }
        if (!vista.confirmar("¿Eliminar definitivamente?\n" + seleccion.getAbsolutePath())) {
            return;
        }
        try {
            gestor.eliminar(seleccion);
            analisis.analizar();
        } catch (Exception e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void editarTexto() {
        File seleccion = seleccion();
        if (seleccion == null || seleccion.isDirectory()) {
            vista.mostrarError("Seleccione un archivo de texto.");
            return;
        }
        String contenido;
        try {
            contenido = gestor.leerTexto(seleccion, false);
        } catch (IOException e) {
            if (!vista.confirmar(e.getMessage() + "\n¿Intentar abrirlo de todos modos?")) {
                return;
            }
            try {
                contenido = gestor.leerTexto(seleccion, true);
            } catch (IOException e2) {
                vista.mostrarError(e2.getMessage());
                return;
            }
        }
        registro.accept("ABRIR TEXTO", seleccion.getAbsolutePath());
        String editado = vista.pedirContenido("Editar " + seleccion.getName(), contenido);
        if (editado == null || !vista.confirmar("¿Guardar los cambios?")) {
            return;
        }
        try {
            gestor.guardarTexto(seleccion, editado);
            vista.setEstado("Archivo guardado.");
        } catch (IOException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void verBitacora() {
        try {
            vista.mostrarTexto("Bitácora", String.join("\n", gestor.leerBitacora()));
        } catch (IOException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void exportar() {
        if (analisis.getEstadisticas() == null) {
            vista.mostrarError("Primero realice un análisis.");
            return;
        }
        boolean csv = vista.confirmar("¿Exportar a CSV? (No = TXT)");
        File destino = vista.pedirArchivo("Guardar reporte");
        if (destino == null) {
            return;
        }
        try {
            gestor.exportarReporte(analisis.getEstadisticas(), analisis.getRaizActual(), destino, csv);
            vista.setEstado("Reporte exportado.");
        } catch (IOException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}
