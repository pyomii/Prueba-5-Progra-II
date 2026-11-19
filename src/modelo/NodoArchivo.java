package modelo;

import java.io.File;
import java.util.ArrayList;

public class NodoArchivo {

    private File archivo;
    private boolean carpeta;
    private ArrayList<NodoArchivo> hijos;

    public NodoArchivo(File archivo, boolean carpeta) {
        this.archivo = archivo;
        this.carpeta = carpeta;
        this.hijos = new ArrayList<NodoArchivo>();
    }

    public File getArchivo() {
        return archivo;
    }

    public boolean esCarpeta() {
        return carpeta;
    }

    public ArrayList<NodoArchivo> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoArchivo hijo) {
        hijos.add(hijo);
    }

    @Override
    public String toString() {
        return (carpeta ? "[Carpeta] " : "[Archivo] ") + archivo.getName();
    }
}
