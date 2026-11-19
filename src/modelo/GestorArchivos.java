package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GestorArchivos {

    private static final String LOG = "bitacora.log";
    private static final String CONFIG = "config.txt";

    public void crearCarpeta(File base, String nombre) throws IOException {
        File destino = base;
        for (String parte : nombre.replace('\\', '/').split("/")) {
            if (!nombreValido(parte)) {
                throw new IOException("Nombre no válido: " + parte);
            }
            destino = new File(destino, parte.trim());
        }
        if (destino.exists() || !destino.mkdirs()) {
            throw new IOException("No se pudo crear: " + destino.getAbsolutePath());
        }
        registrar("CREAR CARPETA", destino.getAbsolutePath());
    }

    public void crearArchivoTexto(File base, String nombre, String contenido,
            boolean sobrescribir) throws IOException {
        String nombreFinal = nombre.contains(".") ? nombre : nombre + ".txt";
        if (!nombreValido(nombreFinal)) {
            throw new IOException("Nombre no válido.");
        }
        File destino = new File(base, nombreFinal);
        if (destino.exists() && !sobrescribir) {
            throw new IOException("Ya existe: " + destino.getAbsolutePath());
        }
        FileWriter w = new FileWriter(destino, false);
        try {
            w.write(contenido == null ? "" : contenido);
        } finally {
            w.close();
        }
        registrar("CREAR ARCHIVO", destino.getAbsolutePath());
    }

    public void renombrar(File origen, String nuevo) throws IOException {
        if (!nombreValido(nuevo)) {
            throw new IOException("Nombre no válido.");
        }
        File destino = new File(origen.getParentFile(), nuevo.trim());
        if (destino.exists() || !origen.renameTo(destino)) {
            throw new IOException("No se pudo renombrar.");
        }
        registrar("RENOMBRAR", origen + " -> " + destino);
    }

    public void copiar(File origen, File carpetaDestino, boolean sobrescribir)
            throws IOException {
        if (origen.isDirectory()) {
            throw new IOException("Esta versión copia solo archivos.");
        }
        File destino = new File(carpetaDestino, origen.getName());
        if (ModeloArchivos.rutaCanonica(origen).equals(ModeloArchivos.rutaCanonica(destino))) {
            throw new IOException("Origen y destino son iguales.");
        }
        if (destino.exists() && !sobrescribir) {
            throw new IOException("Ya existe: " + destino.getAbsolutePath());
        }
        FileInputStream entrada = new FileInputStream(origen);
        FileOutputStream salida = new FileOutputStream(destino);
        try {
            byte[] buffer = new byte[8192];
            int n;
            while ((n = entrada.read(buffer)) != -1) {
                salida.write(buffer, 0, n);
            }
        } finally {
            entrada.close();
            salida.close();
        }
        registrar("COPIAR", origen + " -> " + destino);
    }

    public void mover(File origen, File carpetaDestino, boolean sobrescribir)
            throws IOException {
        if (origen.isDirectory()) {
            throw new IOException("Esta versión mueve solo archivos.");
        }
        File destino = new File(carpetaDestino, origen.getName());
        if (destino.exists()) {
            if (!sobrescribir) {
                throw new IOException("Ya existe: " + destino.getAbsolutePath());
            }
            if (!destino.delete()) {
                throw new IOException("No se pudo reemplazar el destino.");
            }
        }
        if (!origen.renameTo(destino)) {
            copiar(origen, carpetaDestino, true);
            origen.delete();
        }
        registrar("MOVER", origen + " -> " + destino);
    }

    public void eliminar(File objetivo) throws IOException {
        if (!borrarRecursivo(objetivo)) {
            throw new IOException("No se pudo eliminar: " + objetivo.getAbsolutePath());
        }
        registrar("ELIMINAR", objetivo.getAbsolutePath());
    }

    private boolean borrarRecursivo(File f) {
        if (f.isDirectory()) {
            File[] hijos = f.listFiles();
            if (hijos == null) {
                return false;
            }
            for (File hijo : hijos) {
                if (!borrarRecursivo(hijo)) {
                    return false;
                }
            }
        }
        return f.delete();
    }

    public String leerTexto(File archivo, boolean forzar) throws IOException {
        long limite = forzar ? 20L * 1024 * 1024 : 5L * 1024 * 1024;
        if (archivo.length() > limite) {
            throw new IOException("El archivo es demasiado grande.");
        }
        if (!forzar && !esTexto(archivo)) {
            throw new IOException("El archivo no parece de texto.");
        }
        StringBuilder texto = new StringBuilder();
        FileReader lector = new FileReader(archivo);
        try {
            char[] buffer = new char[4096];
            int n;
            while ((n = lector.read(buffer)) != -1) {
                texto.append(buffer, 0, n);
            }
        } finally {
            lector.close();
        }
        return texto.toString();
    }

    private boolean esTexto(File f) {
        String ext = ModeloArchivos.obtenerExtension(f);
        return ext.isEmpty() || ext.equals("txt") || ext.equals("java")
                || ext.equals("csv") || ext.equals("md") || ext.equals("html");
    }

    public void guardarTexto(File archivo, String contenido) throws IOException {
        FileWriter w = new FileWriter(archivo, false);
        try {
            w.write(contenido);
        } finally {
            w.close();
        }
        registrar("GUARDAR TEXTO", archivo.getAbsolutePath());
    }

    private boolean nombreValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        String valor = nombre.trim();
        if (valor.equals(".") || valor.equals("..")) {
            return false;
        }
        return valor.indexOf('\\') < 0 && valor.indexOf('/') < 0
                && valor.indexOf(':') < 0 && valor.indexOf('*') < 0
                && valor.indexOf('?') < 0 && valor.indexOf('"') < 0
                && valor.indexOf('<') < 0 && valor.indexOf('>') < 0
                && valor.indexOf('|') < 0;
    }

    public void registrar(String accion, String ruta) throws IOException {
        FileWriter w = new FileWriter(LOG, true);
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            w.write("[" + fecha + "] " + accion + " | " + ruta + System.lineSeparator());
        } finally {
            w.close();
        }
    }

    public ArrayList<String> leerBitacora() throws IOException {
        ArrayList<String> lineas = new ArrayList<String>();
        File archivo = new File(LOG);
        if (!archivo.exists()) {
            return lineas;
        }
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        try {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } finally {
            lector.close();
        }
        return lineas;
    }

    public void guardarConfiguracion(String[] datos) throws IOException {
        String[] claves = {"ruta", "texto", "extension", "min", "max",
            "desde", "hasta", "profundidad", "ocultos"};
        BufferedWriter w = new BufferedWriter(new FileWriter(CONFIG, false));
        try {
            for (int i = 0; i < claves.length; i++) {
                w.write(claves[i] + "=" + datos[i]);
                w.newLine();
            }
        } finally {
            w.close();
        }
    }

    public String[] leerConfiguracion() throws IOException {
        String[] datos = {"", "", "", "", "", "", "", "0", "false"};
        File archivo = new File(CONFIG);
        if (!archivo.exists()) {
            return datos;
        }
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        try {
            String linea;
            int i = 0;
            while ((linea = lector.readLine()) != null && i < datos.length) {
                int p = linea.indexOf('=');
                if (p > 0) {
                    datos[i++] = linea.substring(p + 1).trim();
                }
            }
        } finally {
            lector.close();
        }
        return datos;
    }

    public void exportarReporte(Estadisticas e, File raiz, File destino, boolean csv)
            throws IOException {
        if (!destino.getName().toLowerCase().endsWith(csv ? ".csv" : ".txt")) {
            destino = new File(destino.getAbsolutePath() + (csv ? ".csv" : ".txt"));
        }
        BufferedWriter w = new BufferedWriter(new FileWriter(destino, false));
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            w.write((csv ? "fecha," : "Fecha y hora: ") + fecha);
            w.newLine();
            w.write((csv ? "ruta," : "Ruta: ") + raiz.getAbsolutePath());
            w.newLine();
            for (int i = 0; i < 4; i++) {
                w.write(e.getNombreCategoria(i) + "," + e.getCantidad(i) + ","
                        + e.getBytesCategoria(i) + ","
                        + String.format("%.2f", e.getPorcentajeCantidad(i)) + ","
                        + String.format("%.2f", e.getPorcentajeBytes(i)));
                w.newLine();
            }
        } finally {
            w.close();
        }
        registrar("EXPORTAR REPORTE", destino.getAbsolutePath());
    }
}
