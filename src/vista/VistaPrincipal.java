package vista;

import controlador.ControladorPrincipal;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import modelo.Estadisticas;
import modelo.NodoArchivo;

public interface VistaPrincipal {

    void setControlador(ControladorPrincipal controlador);

    String[] getCampos();
    void setCampos(String[] datos);

    String getRuta();
    String getTexto();
    String getExtension();
    String getMin();
    String getMax();
    String getDesde();
    String getHasta();
    String getNivel();
    boolean getOcultos();

    File getArchivoSeleccionado();
    File getCarpetaBase();

    void mostrarArbol(NodoArchivo raiz);
    void mostrarResultados(ArrayList<File> archivos);
    void mostrarDuplicados(Map<String, ArrayList<File>> grupos);
    void mostrarEstadisticas(Estadisticas e, File raiz);

    void iniciarProgreso(String mensaje);
    void terminarProgreso();
    void setEstado(String texto);

    void mostrarError(String texto);
    void mostrarInfo(String texto);
    boolean confirmar(String texto);
    String pedirTexto(String texto);
    String pedirContenido(String titulo, String contenido);
    File pedirCarpeta(String titulo);
    File pedirArchivo(String titulo);
    void mostrarTexto(String titulo, String contenido);

    void cerrarVentana();
}
