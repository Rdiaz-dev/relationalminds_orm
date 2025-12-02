package relationalminds;

import relationalminds.vista.VentanaPrincipal;

public class App {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });

    }
}
