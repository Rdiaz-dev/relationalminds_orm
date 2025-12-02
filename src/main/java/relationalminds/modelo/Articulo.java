package relationalminds.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @Column(length = 30)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(name = "precio_venta", nullable = false)
    private double precioVenta;

    @Column(name = "gastos_envio", nullable = false)
    private double gastosEnvio;

    @Column(name = "tiempo_preparacion_min", nullable = false)
    private int tiempoPreparacionMin;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Pedido> pedidos = new ArrayList<>();

    public Articulo() {
    }

    public Articulo(String codigo, String descripcion, double precioVenta,
                    double gastosEnvio, int tiempoPreparacionMin) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    /* bloque de setters y getters */

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public double getGastosEnvio() { return gastosEnvio; }
    public void setGastosEnvio(double gastosEnvio) { this.gastosEnvio = gastosEnvio; }

    public int getTiempoPreparacionMin() { return tiempoPreparacionMin; }
    public void setTiempoPreparacionMin(int tiempoPreparacionMin) { this.tiempoPreparacionMin = tiempoPreparacionMin; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", gastosEnvio=" + gastosEnvio +
                ", tiempoPreparacionMin=" + tiempoPreparacionMin +
                '}';
    }
}
