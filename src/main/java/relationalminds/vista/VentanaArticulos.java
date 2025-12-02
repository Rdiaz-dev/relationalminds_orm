package relationalminds.vista;

import relationalminds.modelo.Articulo;
import relationalminds.servicio.DatosJPA;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaArticulos extends JDialog {

    private final DatosJPA datos;
    private final JTextArea areaListado;

    public VentanaArticulos(JFrame parent, DatosJPA datos) {
        super(parent, "Gestión de Artículos", true);
        this.datos = datos;

        setSize(600, 500);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());

        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnListar = new JButton("Listar Artículos");
        JButton btnNuevo = new JButton("Nuevo Artículo");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnListar);
        panelBotones.add(btnNuevo);
        panelBotones.add(btnCerrar);

        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        // Eventos
        btnCerrar.addActionListener(e -> dispose());
        btnListar.addActionListener(e -> listarArticulos());
        btnNuevo.addActionListener(e -> nuevoArticulo());
    }

    private void listarArticulos() {
        List<Articulo> lista = datos.listarArticulos();
        areaListado.setText("");

        if (lista.isEmpty()) {
            areaListado.append("No hay artículos registrados.\n");
        } else {
            for (Articulo a : lista) {
                areaListado.append(
                        a.getCodigo() + " | " +
                                a.getDescripcion() + " | PVP=" +
                                a.getPrecioVenta() + " | ENV=" +
                                a.getGastosEnvio() + " | Prep=" +
                                a.getTiempoPreparacionMin() + " min\n"
                );
            }
        }
    }

    private void nuevoArticulo() {
        JTextField codigo = new JTextField();
        JTextField descripcion = new JTextField();
        JTextField precio = new JTextField();
        JTextField gastos = new JTextField();
        JTextField prep = new JTextField();

        JPanel formulario = new JPanel(new GridLayout(5, 2));

        formulario.add(new JLabel("Código:"));
        formulario.add(codigo);
        formulario.add(new JLabel("Descripción:"));
        formulario.add(descripcion);
        formulario.add(new JLabel("Precio venta:"));
        formulario.add(precio);
        formulario.add(new JLabel("Gastos envío:"));
        formulario.add(gastos);
        formulario.add(new JLabel("Tiempo preparación (min):"));
        formulario.add(prep);

        int result = JOptionPane.showConfirmDialog(
                this,
                formulario,
                "Nuevo Artículo",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Articulo a = new Articulo(
                        codigo.getText(),
                        descripcion.getText(),
                        Double.parseDouble(precio.getText()),
                        Double.parseDouble(gastos.getText()),
                        Integer.parseInt(prep.getText())
                );
                datos.addArticulo(a);
                JOptionPane.showMessageDialog(this,
                        "Artículo añadido correctamente.");
                listarArticulos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al crear artículo: " + ex.getMessage());
            }
        }
    }
}
