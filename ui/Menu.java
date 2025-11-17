package InventarioAvance1.ui;

import InventarioAvance1.bl.Tienda;
import InventarioAvance1.bl.Clientes.Cliente;
import InventarioAvance1.bl.Productos.Producto;

import java.util.Scanner;

public class Menu {
    private Tienda tienda;
    private Scanner sc;

    public Menu() {
        tienda = new Tienda();
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ TIENDA ---");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Agregar cliente (llenar carrito)");
            System.out.println("3. Atender siguiente cliente");
            System.out.println("4. Ver inventario");
            System.out.println("5. Ver cola de clientes");
            System.out.println("6. Salir");
            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> agregarCliente();
                case 3 -> tienda.procesarCliente();
                case 4 -> tienda.mostrarInventario();
                case 5 -> tienda.mostrarCola();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Categoría: ");
        String categoria = sc.nextLine();
        System.out.print("Fecha vencimiento (puede ser vacío): ");
        String fecha = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        Producto p = new Producto(nombre, precio, categoria, fecha, null, cantidad);
        tienda.agregarProducto(p);
        System.out.println("Producto agregado al inventario.");
    }

    private void agregarCliente() {
        System.out.print("Nombre cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Prioridad (1= Básico, 2= Afiliado, 3= Premium): ");
        int prioridad = sc.nextInt();
        sc.nextLine();

        Cliente c = new Cliente(nombre, prioridad);

        System.out.print("¿Cuántos productos desea agregar al carrito? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Nombre producto: ");
            String prod = sc.nextLine();
            System.out.print("Cantidad: ");
            int cant = sc.nextInt();
            sc.nextLine();

            Producto encontrado = tienda.getInventario().buscar(prod);
            if (encontrado != null) {
                c.agregarCarrito(encontrado, cant);
            } else {
                System.out.println("Producto no encontrado en inventario.");
            }
        }

        tienda.agregarCliente(c);
        System.out.println("Cliente agregado a la cola.");
    }
}
