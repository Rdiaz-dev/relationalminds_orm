package relationalminds;

import relationalminds.servicio.DatosJPA;
import relationalminds.modelo.Cliente;

public class test {

    public static void main(String[] args) {

        DatosJPA datos = new DatosJPA();

        Cliente c = new Cliente(
                "test@central.com",
                "Cliente Central",
                "Calle Central 123",
                "10000000X"
        );

        datos.addCliente(c);

        datos.listarClientes().forEach(System.out::println);
    }
}
