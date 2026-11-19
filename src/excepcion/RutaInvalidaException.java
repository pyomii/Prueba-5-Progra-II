package excepcion;

public class RutaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public RutaInvalidaException(String mensaje) {
        super(mensaje);
    }

    public RutaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
