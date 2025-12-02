package relationalminds.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String domicilio;

    @Column(nullable = false, length = 15)
    private String nif;

    @Column(nullable = false, length = 20)
    private String tipo; // ESTANDAR o PREMIUM

    @Column(name = "cuota_anual")
    private Double cuotaAnual; // Solo premium

    @Column(name = "descuento_envio")
    private Double descuentoEnvio; // Solo premium

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Pedido> pedidos = new ArrayList<>();

    /* Bloque de constructores */
    public Cliente() { }

    /*Constructor para cliente ESTÁNDAR*/
    public Cliente(String email, String nombre, String domicilio, String nif) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.tipo = "ESTANDAR";
        this.cuotaAnual = null;
        this.descuentoEnvio = null;
    }

    /*Constructor para cliente PREMIUM*/
    public Cliente(String email, String nombre, String domicilio, String nif,
                   Double cuotaAnual, Double descuentoEnvio) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.tipo = "PREMIUM";
        this.cuotaAnual = cuotaAnual;
        this.descuentoEnvio = descuentoEnvio;
    }

    /* constructor principal*/
    public Cliente(String email, String nombre, String domicilio, String nif,
                   String tipo, Double cuotaAnual, Double descuentoEnvio) {
        this.email = email;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.tipo = tipo;
        this.cuotaAnual = cuotaAnual;
        this.descuentoEnvio = descuentoEnvio;
    }

   /* Bloque de setters y getters */

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getCuotaAnual() { return cuotaAnual; }
    public void setCuotaAnual(Double cuotaAnual) { this.cuotaAnual = cuotaAnual; }

    public Double getDescuentoEnvio() { return descuentoEnvio; }
    public void setDescuentoEnvio(Double descuentoEnvio) { this.descuentoEnvio = descuentoEnvio; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }



    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cuotaAnual=" + cuotaAnual +
                ", descuentoEnvio=" + descuentoEnvio +
                '}';
    }
}
