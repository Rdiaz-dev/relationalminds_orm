package relationalminds.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "num_pedido", length = 30)
    private String numPedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "email_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cod_articulo", nullable = false)
    private Articulo articulo;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private boolean cancelado;

    public Pedido() {
    }

    public Pedido(String numPedido, Cliente cliente, Articulo articulo,
                  int cantidad, LocalDateTime fechaHora, boolean cancelado) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
        this.cancelado = cancelado;
    }

    /* Bloque de setters y getters */

    public String getNumPedido() { return numPedido; }
    public void setNumPedido(String numPedido) { this.numPedido = numPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public boolean isCancelado() { return cancelado; }
    public void setCancelado(boolean cancelado) { this.cancelado = cancelado; }

    @Override
    public String toString() {
        return "Pedido{" +
                "numPedido='" + numPedido + '\'' +
                ", cliente=" + (cliente != null ? cliente.getEmail() : null) +
                ", articulo=" + (articulo != null ? articulo.getCodigo() : null) +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", cancelado=" + cancelado +
                '}';
    }
}
