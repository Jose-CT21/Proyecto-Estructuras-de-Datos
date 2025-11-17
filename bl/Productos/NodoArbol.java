package InventarioAvance1.bl.Productos;

public class NodoArbol {

    Producto producto;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Producto pProducto) {
        producto = pProducto;
    }
}
