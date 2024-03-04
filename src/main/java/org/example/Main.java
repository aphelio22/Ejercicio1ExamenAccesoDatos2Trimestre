package org.example;

import org.example.domain.Cliente.Cliente;
import org.example.domain.Cliente.ClienteDAO;
import org.example.enums.Estado;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Cliente jorge = new Cliente();
        jorge.setNombre("Jorge");
        jorge.setEstado(Estado.activo);
        jorge.setTotal(3500l);

        Cliente maria = new Cliente();
        maria.setNombre("Maria");
        maria.setEstado(Estado.inactivo);
        maria.setTotal(3000l);

        Cliente pepita = new Cliente();
        pepita.setNombre("Pepita");
        pepita.setEstado(Estado.activo);
        pepita.setTotal(1200l);

        Cliente juan = new Cliente();
        juan.setNombre("Juan");
        juan.setEstado(Estado.inactivo);
        juan.setTotal(100l);

        System.out.println("Inserción de clientes:");
        ClienteDAO clienteDAO = new ClienteDAO();
        System.out.println(clienteDAO.save(jorge));
        System.out.println(clienteDAO.save(maria));
        System.out.println(clienteDAO.save(pepita));
        System.out.println(clienteDAO.save(juan));

        System.out.println("Cliente por id:");
        System.out.println(clienteDAO.get(2));

        System.out.println("Clientes activos con una cantidad de ventas mayor a una cantidad concreta:");
        System.out.println(clienteDAO.listarMejoresClientes(2000l));

        System.out.println("Total de ventas entre todos los clientes:");
        System.out.println(clienteDAO.calcularTotalVentas());

        System.out.println("Promedio de ventas de los clientes activos:");
        System.out.println(clienteDAO.calcularPromedioVentas());

        System.out.println("Cantidad de clientes inactivos que tienen total de ventas mayor a 0:");
        System.out.println(clienteDAO.contarClientesInactivosConVentasMayorQueCero());

    }
}