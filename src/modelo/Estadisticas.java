package modelo;

import java.io.File;

public class Estadisticas {

    private String[] nombres = {"TXT", "JAVA", "PDF", "OTROS"};
    private long[] cantidad = new long[4];
    private long[] bytes = new long[4];

    private long totalArchivos;
    private long totalBytes;
    private long totalCarpetas;
    private long carpetasVacias;
    private long sinPermiso;
    private int profundidadMaxima;

    private File masGrande;
    private File masReciente;
    private long fechaReciente = -1;

    public void agregarArchivo(File archivo, int nivel) {
        totalArchivos++;
        long tamano = archivo.length();
        totalBytes += tamano;

        int categoria = obtenerCategoria(archivo);
        cantidad[categoria]++;
        bytes[categoria] += tamano;

        if (masGrande == null || tamano > masGrande.length()) {
            masGrande = archivo;
        }
        if (masReciente == null || archivo.lastModified() > fechaReciente) {
            masReciente = archivo;
            fechaReciente = archivo.lastModified();
        }
        if (nivel > profundidadMaxima) {
            profundidadMaxima = nivel;
        }
    }

    public void agregarCarpeta(File carpeta, boolean vacia, int nivel) {
        totalCarpetas++;
        if (vacia) {
            carpetasVacias++;
        }
        if (nivel > profundidadMaxima) {
            profundidadMaxima = nivel;
        }
    }

    public void agregarSinPermiso() {
        sinPermiso++;
    }

    private int obtenerCategoria(File archivo) {
        String extension = ModeloArchivos.obtenerExtension(archivo);
        if (extension.equals("txt")) {
            return 0;
        }
        if (extension.equals("java")) {
            return 1;
        }
        if (extension.equals("pdf")) {
            return 2;
        }
        return 3;
    }
    
    public static String tamanoLegible(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        if (bytes < 1024 * 1024) {
            return (bytes / 1024.0) + " KB";
        }
        if (bytes < 1024L * 1024L * 1024L) {
            return (bytes / (1024.0 * 1024.0)) + " MB";
        }
        return (bytes / (1024.0 * 1024.0 * 1024.0)) + " GB";
    }

    public String getNombreCategoria(int i) {
        return nombres[i];
    }

    public long getCantidad(int i) {
        return cantidad[i];
    }

    public long getBytesCategoria(int i) {
        return bytes[i];
    }

    public double getPorcentajeCantidad(int i) {
        return totalArchivos == 0 ? 0 : 100.0 * cantidad[i] / totalArchivos;
    }

    public double getPorcentajeBytes(int i) {
        return totalBytes == 0 ? 0 : 100.0 * bytes[i] / totalBytes;
    }

    public long getTotalArchivos() {
        return totalArchivos;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public long getTotalCarpetas() {
        return totalCarpetas;
    }

    public long getCarpetasVacias() {
        return carpetasVacias;
    }

    public long getSinPermiso() {
        return sinPermiso;
    }

    public int getProfundidadMaxima() {
        return profundidadMaxima;
    }

    public File getMasGrande() {
        return masGrande;
    }

    public File getMasReciente() {
        return masReciente;
    }
}
