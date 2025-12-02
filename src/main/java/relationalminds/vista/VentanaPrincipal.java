package relationalminds.vista;

import relationalminds.servicio.DatosJPA;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private DatosJPA datos;

    public VentanaPrincipal() {
        super("Relational Minds - ORM");
        this.datos = new DatosJPA();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 20, 20));

        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnArticulos = new JButton("Gestión de Artículos");
        JButton btnPedidos = new JButton("Gestión de Pedidos");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnClientes);
        panel.add(btnArticulos);
        panel.add(btnPedidos);
        panel.add(btnSalir);

        add(panel);

        // Eventos
        btnClientes.addActionListener(e ->
                new VentanaClientes(this, datos).setVisible(true));

        btnArticulos.addActionListener(e ->
                new VentanaArticulos(this, datos).setVisible(true));

        btnPedidos.addActionListener(e ->
                new VentanaPedidos(this, datos).setVisible(true));

        btnSalir.addActionListener(e -> System.exit(0));
    }
}
