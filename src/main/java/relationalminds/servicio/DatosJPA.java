package relationalminds.servicio;

import relationalminds.modelo.Articulo;
import relationalminds.modelo.Cliente;
import relationalminds.modelo.Pedido;
import relationalminds.central.Utilidades;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class DatosJPA {

   /* Apartado de clientes */

    public void addCliente(Cliente c) {
        EntityManager em = Utilidades.getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    public Cliente getCliente(String email) {
        EntityManager em = Utilidades.getEntityManager();
        Cliente c = em.find(Cliente.class, email);
        em.close();
        return c;
    }

    public List<Cliente> listarClientes() {
        EntityManager em = Utilidades.getEntityManager();
        TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        List<Cliente> lista = q.getResultList();
        em.close();
        return lista;
    }


   /* Apartado de articulos */

    public void addArticulo(Articulo a) {
        EntityManager em = Utilidades.getEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    public Articulo getArticulo(String codigo) {
        EntityManager em = Utilidades.getEntityManager();
        Articulo art = em.find(Articulo.class, codigo);
        em.close();
        return art;
    }

    public List<Articulo> listarArticulos() {
        EntityManager em = Utilidades.getEntityManager();
        TypedQuery<Articulo> q = em.createQuery("SELECT a FROM Articulo a", Articulo.class);
        List<Articulo> lista = q.getResultList();
        em.close();
        return lista;
    }


   /* Apartado de pedidos */

    public void addPedido(Pedido p) {
        EntityManager em = Utilidades.getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public Pedido getPedido(String num) {
        EntityManager em = Utilidades.getEntityManager();
        Pedido p = em.find(Pedido.class, num);
        em.close();
        return p;
    }

    public List<Pedido> listarPedidos() {
        EntityManager em = Utilidades.getEntityManager();
        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
        List<Pedido> lista = q.getResultList();
        em.close();
        return lista;
    }

    public List<Pedido> listarPendientes() {
        EntityManager em = Utilidades.getEntityManager();
        TypedQuery<Pedido> q =
                em.createQuery("SELECT p FROM Pedido p WHERE p.cancelado = false", Pedido.class);
        List<Pedido> lista = q.getResultList();
        em.close();
        return lista;
    }

    public List<Pedido> listarEnviados() {
        EntityManager em = Utilidades.getEntityManager();
        TypedQuery<Pedido> q =
                em.createQuery("SELECT p FROM Pedido p WHERE p.cancelado = true", Pedido.class);
        List<Pedido> lista = q.getResultList();
        em.close();
        return lista;
    }


    /* bloque para crear pedido */

    public Pedido crearPedido(String numero, String emailCliente, String codArticulo, int cantidad) {

        EntityManager em = Utilidades.getEntityManager();
        em.getTransaction().begin();

        Cliente c = em.find(Cliente.class, emailCliente);
        Articulo a = em.find(Articulo.class, codArticulo);

        Pedido p = new Pedido(
                numero,
                c,
                a,
                cantidad,
                LocalDateTime.now(),
                false
        );

        em.persist(p);
        em.getTransaction().commit();
        em.close();

        return p;
    }
}
