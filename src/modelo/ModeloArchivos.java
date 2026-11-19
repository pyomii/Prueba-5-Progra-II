package modelo;

import excepcion.RutaInvalidaException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ModeloArchivos {

    public boolean cancelado;
    public int visitados;
    public int sinPermiso;

    public File validarRuta(String texto) throws RutaInvalidaException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RutaInvalidaException("La ruta no puede estar vacía.");
        }
        File ruta = new File(texto.trim());
        if (!ruta.exists()) {
            throw new RutaInvalidaException("La ruta no existe.");
        }
        if (!ruta.isDirectory()) {
            throw new RutaInvalidaException("La ruta no es un directorio.");
        }
        return ruta;
    }

    public static String obtenerExtension(File archivo) {
        String nombre = archivo.getName();
        int punto = nombre.lastIndexOf('.');
        return punto <= 0 || punto == nombre.length() - 1
                ? "" : nombre.substring(punto + 1).toLowerCase();
    }

    public static String rutaCanonica(File archivo) {
        try {
            return archivo.getCanonicalPath();
        } catch (Exception e) {
            return archivo.getAbsolutePath();
        }
    }

    public NodoArchivo analizar(File raiz, boolean ocultos, int maxNivel,
            Estadisticas stats) throws RutaInvalidaException {
        validarRuta(raiz.getAbsolutePath());
        reiniciar();
        return recorrerAnalisis(raiz, 0, ocultos, maxNivel, stats, new HashSet<>());
    }

    private NodoArchivo recorrerAnalisis(File f, int nivel, boolean ocultos, int maxNivel,
            Estadisticas stats, HashSet<String> vistos) {
        if (cancelado || (maxNivel > 0 && nivel > maxNivel)
                || (nivel > 0 && !ocultos && f.isHidden())) {
            return null;
        }
        if (!f.isDirectory()) {
            stats.agregarArchivo(f, nivel);
            visitados++;
            return new NodoArchivo(f, false);
        }
        if (!vistos.add(rutaCanonica(f))) {
            return null;
        }
        NodoArchivo nodo = new NodoArchivo(f, true);
        File[] hijos = f.listFiles();
        if (hijos == null) {
            sinPermiso++;
            return nodo;
        }
        Arrays.sort(hijos);
        for (File hijo : hijos) {
            NodoArchivo hijoNodo = recorrerAnalisis(hijo, nivel + 1, ocultos, maxNivel,
                    stats, vistos);
            if (hijoNodo != null) {
                nodo.agregarHijo(hijoNodo);
            }
        }
        stats.agregarCarpeta(f, nodo.getHijos().isEmpty(), nivel);
        visitados++;
        return nodo;
    }

    public ArrayList<File> buscar(File raiz, String texto, String extension,
            double minKB, double maxKB, long desde, long hasta,
            boolean ocultos, int maxNivel) throws RutaInvalidaException {
        validarRuta(raiz.getAbsolutePath());
        reiniciar();
        ArrayList<File> resultado = new ArrayList<>();
        recorrerBusqueda(raiz, 0, texto, extension, minKB, maxKB, desde, hasta,
                ocultos, maxNivel, resultado, new HashSet<>());
        return resultado;
    }

    private void recorrerBusqueda(File carpeta, int nivel, String texto, String extension,
            double minKB, double maxKB, long desde, long hasta, boolean ocultos,
            int maxNivel, ArrayList<File> resultado, HashSet<String> vistos) {
        if (cancelado || (maxNivel > 0 && nivel > maxNivel)
                || (!ocultos && carpeta.isHidden()) || !vistos.add(rutaCanonica(carpeta))) {
            return;
        }
        File[] hijos = carpeta.listFiles();
        if (hijos == null) {
            sinPermiso++;
            return;
        }
        for (File hijo : hijos) {
            if (cancelado) {
                return;
            }
            if (!hijo.isHidden() || ocultos) {
                if (hijo.isDirectory()) {
                    recorrerBusqueda(hijo, nivel + 1, texto, extension, minKB, maxKB,
                            desde, hasta, ocultos, maxNivel, resultado, vistos);
                } else if (cumple(hijo, texto, extension, minKB, maxKB, desde, hasta)) {
                    resultado.add(hijo);
                }
            }
            visitados++;
        }
    }

    private boolean cumple(File f, String texto, String extension, double min,
            double max, long desde, long hasta) {
        if (!f.getName().toLowerCase().contains(texto.toLowerCase())) {
            return false;
        }
        String ext = extension.toLowerCase().replaceFirst("^\\.+", "");
        String real = obtenerExtension(f);
        boolean sinExt = ext.equals("sin extension") || ext.equals("sin extensión");
        if (sinExt && !real.isEmpty()) {
            return false;
        }
        if (!sinExt && !ext.isEmpty() && !ext.equals(real)) {
            return false;
        }
        return !(min >= 0 && f.length() < min * 1024)
                && !(max >= 0 && f.length() > max * 1024)
                && !(desde >= 0 && f.lastModified() < desde)
                && !(hasta >= 0 && f.lastModified() > hasta);
    }

    public Map<String, ArrayList<File>> duplicados(File raiz, boolean ocultos, int maxNivel)
            throws RutaInvalidaException {
        validarRuta(raiz.getAbsolutePath());
        reiniciar();
        HashMap<String, ArrayList<File>> grupos = new HashMap<>();
        recorrerDuplicados(raiz, 0, ocultos, maxNivel, grupos, new HashSet<>());

        ArrayList<String> borrar = new ArrayList<>();
        for (Map.Entry<String, ArrayList<File>> entrada : grupos.entrySet()) {
            HashSet<String> carpetas = new HashSet<>();
            for (File f : entrada.getValue()) {
                carpetas.add(rutaCanonica(f.getParentFile()));
            }
            if (entrada.getValue().size() < 2 || carpetas.size() < 2) {
                borrar.add(entrada.getKey());
            }
        }
        for (String clave : borrar) {
            grupos.remove(clave);
        }
        return grupos;
    }

    private void recorrerDuplicados(File carpeta, int nivel, boolean ocultos, int maxNivel,
            HashMap<String, ArrayList<File>> grupos, HashSet<String> vistos) {
        if (cancelado || (maxNivel > 0 && nivel > maxNivel)
                || (!ocultos && carpeta.isHidden()) || !vistos.add(rutaCanonica(carpeta))) {
            return;
        }
        File[] hijos = carpeta.listFiles();
        if (hijos == null) {
            sinPermiso++;
            return;
        }
        for (File hijo : hijos) {
            if (!hijo.isHidden() || ocultos) {
                if (hijo.isDirectory()) {
                    recorrerDuplicados(hijo, nivel + 1, ocultos, maxNivel, grupos, vistos);
                } else {
                    String clave = hijo.getName().toLowerCase() + " | "
                            + hijo.length() + " B";
                    if (!grupos.containsKey(clave)) {
                        grupos.put(clave, new ArrayList<>());
                    }
                    grupos.get(clave).add(hijo);
                }
            }
            visitados++;
        }
    }

    private void reiniciar() {
        cancelado = false;
        visitados = 0;
        sinPermiso = 0;
    }
}
