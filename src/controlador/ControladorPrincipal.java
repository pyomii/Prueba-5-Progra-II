package controlador;

import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import modelo.GestorArchivos;
import vista.VistaPrincipal;

public class ControladorPrincipal {

    private final VistaPrincipal vista;
    private final GestorArchivos gestor = new GestorArchivos();
    private final ControladorAnalisis analisis;
    private final ControladorGestion gestion;
    private final BiConsumer<String, String> registro;

    public ControladorPrincipal(VistaPrincipal vista) {
        this.vista = vista;
        this.registro = this::registrar;
        this.analisis = new ControladorAnalisis(vista, registro);
        this.gestion = new ControladorGestion(vista, registro, analisis);
        vista.setControlador(this);
        try {
            vista.setCampos(gestor.leerConfiguracion());
        } catch (IOException e) {
            vista.mostrarError("No se pudo leer config.txt: " + e.getMessage());
        }
    }

    public void examinar() {
        File carpeta = vista.pedirCarpeta("Seleccionar directorio raíz");
        if (carpeta == null) {
            return;
        }
        String[] datos = vista.getCampos();
        datos[0] = carpeta.getAbsolutePath();
        vista.setCampos(datos);
        guardarConfiguracion();
        registro.accept("SELECCIONAR RUTA", datos[0]);
    }

    public void analizar() {
        guardarConfiguracion();
        analisis.analizar();
    }

    public void buscar() {
        guardarConfiguracion();
        analisis.buscar();
    }

    public void duplicados() {
        analisis.duplicados();
    }

    public void cancelar() {
        analisis.cancelar();
    }

    public void operacion(int opcion) {
        gestion.operacion(opcion);
    }

    public void salir() {
        analisis.cancelarTarea();
        guardarConfiguracion();
        File raiz = analisis.getRaizActual();
        registro.accept("SALIR", raiz == null ? "-" : raiz.getAbsolutePath());
        vista.cerrarVentana();
    }

    private void registrar(String accion, String ruta) {
        try {
            gestor.registrar(accion, ruta);
        } catch (IOException e) {
            vista.setEstado("No se pudo escribir en la bitácora.");
        }
    }

    private void guardarConfiguracion() {
        try {
            gestor.guardarConfiguracion(vista.getCampos());
        } catch (IOException e) {
            registro.accept("ERROR CONFIG", e.getMessage());
        }
    }
}
