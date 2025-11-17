package InventarioAvance1.bl.Productos;

public class ArbolProductos {

    //Atributos
    //private Producto raiz;
    private NodoArbol raiz;

    //Metodos
    // Constructor
    public ArbolProductos() {
        raiz = null;
    }

    // Metodo público para insertar
    public void insertar(Producto p) {
        raiz = insertarRec(raiz, p);
    }

    // Inserción recursiva
    private NodoArbol insertarRec(NodoArbol n, Producto p) {
        if (n == null) return new NodoArbol(p);
        int cmp = p.getNombre().compareToIgnoreCase(n.producto.getNombre());
        if (cmp < 0) n.izquierdo = insertarRec(n.izquierdo, p);
        else if (cmp > 0) n.derecho = insertarRec(n.derecho, p);
        else n.producto.setCantidad(n.producto.getCantidad() + p.getCantidad());
        return n;
    }

    // Buscar producto por nombre
    public Producto buscar(String nombre) {
        NodoArbol n = buscarRec(raiz, nombre);
        return n == null ? null : n.producto;
    }

    private NodoArbol buscarRec(NodoArbol n, String nombre) {
        if (n == null) return null;
        int cmp = nombre.compareToIgnoreCase(n.producto.getNombre());
        if (cmp == 0) return n;
        if (cmp < 0) return buscarRec(n.izquierdo, nombre);
        return buscarRec(n.derecho, nombre);
    }

    // Recorrido in-order para mostrar inventario ordenado
    public void inOrden() {
        if (raiz == null) {
            System.out.println("[Inventario vacío]");
        } else {
            inOrdenRecursivo(raiz);
        }
    }

    private void inOrdenRecursivo(NodoArbol actual) {
        if (actual != null) {
            inOrdenRecursivo(actual.izquierdo);
            System.out.println(actual.producto.toString());
            inOrdenRecursivo(actual.derecho);
        }
    }

    public boolean descontar(String nombre, int cant) {
        Producto p = buscar(nombre);
        if (p == null || cant > p.getCantidad()) return false;
        p.setCantidad(p.getCantidad() - cant);
        return true;
    }

    public void mostrar() {
        mostrarRec(raiz);
    }

    private void mostrarRec(NodoArbol n) {
        if (n == null) return;
        mostrarRec(n.izquierdo);
        System.out.println(n.producto);
        mostrarRec(n.derecho);
    }
}
