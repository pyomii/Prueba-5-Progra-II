package prueba5;

import controlador.ControladorPrincipal;
import javax.swing.SwingUtilities;
import vista.Menu;

/** Punto de entrada del programa. */
public class Prueba5 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            new ControladorPrincipal(menu);
            menu.setSize(1100, 720);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}
