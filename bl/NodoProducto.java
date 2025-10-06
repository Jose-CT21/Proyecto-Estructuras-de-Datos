package InventarioAvance1.bl;

/**
 * Nodo que contiene un Producto para la lista enlazada simple.
 */
public class NodoProducto {
    private Producto producto;
    private NodoProducto siguiente;

    public NodoProducto(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    public Producto getProducto() {
        return producto;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProducto nuevoSiguiente) {
        siguiente = nuevoSiguiente;
    }
}
