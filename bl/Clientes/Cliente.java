package InventarioAvance1.bl.Clientes;

import InventarioAvance1.bl.Productos.ListaProductos;
import InventarioAvance1.bl.Productos.Producto;

public class Cliente {

    //Atributos
    private String         nombre;
    private int            prioridad; // 1 basico, 2 afiliado, 3 Preferencial
    private ListaProductos carrito;

    //Metodos
    //Contructor
    public Cliente (String pNombre, int pPrioridad){

        nombre    = pNombre;
        prioridad = pPrioridad;
        carrito   = new ListaProductos();

    }

    public void agregarCarrito (Producto producto, int cantidad){

        if (producto.getCantidad() >= cantidad) {

            producto.setCantidad(producto.getCantidad() - cantidad);
            Producto comprado = new Producto(producto);
            comprado.setCantidad(cantidad);

            carrito.insertarFin(comprado);
        }else {

            System.out.println("No hay suficiente Stock");

        }
    }

    public double calcularTotalCarrito() {
        return carrito.imprimirReporteCostos();
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarAlCarrito(){}
}
