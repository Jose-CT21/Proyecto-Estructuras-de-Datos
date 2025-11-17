package InventarioAvance1.bl;

import InventarioAvance1.bl.Clientes.Cliente;
import InventarioAvance1.bl.Clientes.ColaClientes;
import InventarioAvance1.bl.Productos.ArbolProductos;
import InventarioAvance1.bl.Productos.Producto;

public class Tienda {

    //Atributos
    private ArbolProductos inventario;
    private ColaClientes   cola;

    //Metodos
    //Constructor
    public Tienda (){
        inventario = new ArbolProductos();
        cola       = new ColaClientes();
    }

    public void agregarProducto(Producto nuevoProducto){
        inventario.insertar(nuevoProducto);
    }

    public void agregarCliente(Cliente nuevoCliente) {
        cola.encolar(nuevoCliente);
    }

    public void procesarCliente(){
        Cliente c = cola.desencolar();
        if (c == null) {
            System.out.println("No hay clientes en la cola");
            return;
        }
        System.out.println("Atendiendo cliente: " + c.getNombre());
        double total = c.calcularTotalCarrito();
        System.out.printf("Factura total: %.2f%n", total);
    }

    public void mostrarInventario(){
        inventario.inOrden();
    }

    public void mostrarCola() {
        cola.mostrar();
    }

    public ArbolProductos getInventario() {
        return inventario;
    }
}
