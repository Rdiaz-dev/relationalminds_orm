package relationalminds.vista;

import relationalminds.servicio.DatosJPA;
import relationalminds.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaClientes extends JDialog {

    private DatosJPA datos;
    private JTextArea areaListado;

    public VentanaClientes(JFrame parent, DatosJPA datos) {
        super(parent, "Gestión de Clientes", true);
        this.datos = datos;

        setSize(600, 500);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());

        // Área de listado
        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        panel.add(scroll, BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel();

        JButton btnListar = new JButton("Listar Clientes");
        JButton btnNuevo = new JButton("Nuevo Cliente");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnListar);
        panelBotones.add(btnNuevo);
        panelBotones.add(btnCerrar);

        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        // Eventos
        btnCerrar.addActionListener(e -> dispose());

        btnListar.addActionListener(e -> listarClientes());

        btnNuevo.addActionListener(e -> nuevoCliente());
    }

    private void listarClientes() {
        List<Cliente> lista = datos.listarClientes();

        areaListado.setText("");

        if (lista.isEmpty()) {
            areaListado.append("No hay clientes registrados.\n");
        } else {
            for (Cliente c : lista) {
                areaListado.append(
                        c.getEmail() + " | " + c.getNombre() +
                                " | Tipo: " + c.getTipo() + "\n"
                );
            }
        }
    }

    private void nuevoCliente() {
        JTextField email = new JTextField();
        JTextField nombre = new JTextField();
        JTextField domicilio = new JTextField();
        JTextField nif = new JTextField();

        String[] opciones = {"Estándar", "Premium"};
        JComboBox<String> tipoBox = new JComboBox<>(opciones);

        JTextField cuotaBox = new JTextField();
        JTextField descuentoBox = new JTextField();

        JPanel formulario = new JPanel(new GridLayout(7, 2));

        formulario.add(new JLabel("Email:"));
        formulario.add(email);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(nombre);
        formulario.add(new JLabel("Domicilio:"));
        formulario.add(domicilio);
        formulario.add(new JLabel("NIF:"));
        formulario.add(nif);
        formulario.add(new JLabel("Tipo:"));
        formulario.add(tipoBox);
        formulario.add(new JLabel("Cuota anual (solo premium):"));
        formulario.add(cuotaBox);
        formulario.add(new JLabel("Descuento envío (solo premium):"));
        formulario.add(descuentoBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                formulario,
                "Nuevo Cliente",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {
                Cliente c;

                if (tipoBox.getSelectedItem().equals("Estándar")) {
                    c = new Cliente(
                            email.getText(),
                            nombre.getText(),
                            domicilio.getText(),
                            nif.getText()
                    );
                } else {
                    c = new Cliente(
                            email.getText(),
                            nombre.getText(),
                            domicilio.getText(),
                            nif.getText(),
                            Double.valueOf(cuotaBox.getText()),
                            Double.valueOf(descuentoBox.getText())
                    );
                }

                datos.addCliente(c);
                JOptionPane.showMessageDialog(this,
                        "Cliente añadido correctamente.");

                listarClientes();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al crear cliente: " + ex.getMessage());
            }
        }
    }
}
