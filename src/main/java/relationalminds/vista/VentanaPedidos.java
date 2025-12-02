package relationalminds.vista;

import relationalminds.modelo.Pedido;
import relationalminds.servicio.DatosJPA;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPedidos extends JDialog {

    private final DatosJPA datos;
    private final JTextArea areaListado;

    public VentanaPedidos(JFrame parent, DatosJPA datos) {
        super(parent, "Gestión de Pedidos", true);
        this.datos = datos;

        setSize(700, 500);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());

        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnListarTodos = new JButton("Listar todos");
        JButton btnListarPend = new JButton("Pendientes");
        JButton btnListarEnv = new JButton("Enviados / Cancelados");
        JButton btnNuevo = new JButton("Nuevo Pedido");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnListarTodos);
        panelBotones.add(btnListarPend);
        panelBotones.add(btnListarEnv);
        panelBotones.add(btnNuevo);
        panelBotones.add(btnCerrar);

        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        // Eventos
        btnCerrar.addActionListener(e -> dispose());
        btnListarTodos.addActionListener(e -> listarTodos());
        btnListarPend.addActionListener(e -> listarPendientes());
        btnListarEnv.addActionListener(e -> listarEnviados());
        btnNuevo.addActionListener(e -> nuevoPedido());
    }

    private void listarTodos() {
        List<Pedido> lista = datos.listarPedidos();
        mostrarPedidos("TODOS LOS PEDIDOS", lista);
    }

    private void listarPendientes() {
        List<Pedido> lista = datos.listarPendientes();
        mostrarPedidos("PEDIDOS PENDIENTES (no cancelados)", lista);
    }

    private void listarEnviados() {
        List<Pedido> lista = datos.listarEnviados();
        mostrarPedidos("PEDIDOS ENVIADOS/CANCELADOS", lista);
    }

    private void mostrarPedidos(String titulo, List<Pedido> lista) {
        areaListado.setText("");
        areaListado.append(titulo + "\n\n");

        if (lista.isEmpty()) {
            areaListado.append("No hay pedidos.\n");
        } else {
            for (Pedido p : lista) {
                areaListado.append(
                        p.getNumPedido() + " | " +
                                (p.getCliente() != null ? p.getCliente().getEmail() : "¿?") + " | " +
                                (p.getArticulo() != null ? p.getArticulo().getCodigo() : "¿?") + " | " +
                                "Cant=" + p.getCantidad() + " | " +
                                "Fecha=" + p.getFechaHora() + " | " +
                                "Cancelado=" + p.isCancelado() + "\n"
                );
            }
        }
    }

    private void nuevoPedido() {
        JTextField numPedido = new JTextField();
        JTextField emailCliente = new JTextField();
        JTextField codArticulo = new JTextField();
        JTextField cantidad = new JTextField();

        JPanel formulario = new JPanel(new GridLayout(4, 2));

        formulario.add(new JLabel("Número de pedido:"));
        formulario.add(numPedido);
        formulario.add(new JLabel("Email cliente:"));
        formulario.add(emailCliente);
        formulario.add(new JLabel("Código artículo:"));
        formulario.add(codArticulo);
        formulario.add(new JLabel("Cantidad:"));
        formulario.add(cantidad);

        int result = JOptionPane.showConfirmDialog(
                this,
                formulario,
                "Nuevo Pedido",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int cant = Integer.parseInt(cantidad.getText());
                datos.crearPedido(
                        numPedido.getText(),
                        emailCliente.getText(),
                        codArticulo.getText(),
                        cant
                );
                JOptionPane.showMessageDialog(this,
                        "Pedido creado correctamente.");
                listarTodos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al crear pedido: " + ex.getMessage());
            }
        }
    }
}
