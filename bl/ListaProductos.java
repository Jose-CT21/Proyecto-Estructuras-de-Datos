package InventarioAvance1.bl;

/**
 * Implementación de una lista enlazada simple para Productos.
 */
public class ListaProductos {
    private NodoProducto primero;
    private int size;

    //CONSTRUCTOR
    public ListaProductos() {
        primero = null;
        size = 0;
    }

    //METODOS ESTANDAR

    /// GETTERS
    public NodoProducto getPrimero() {
        return primero;
    }

    public int getSize() {
        return size;
    }

    /// SETTERS
    public void setHead(NodoProducto otroHead) {
        primero = otroHead;
    }
    /// No hice setter de size ya que no lo considero necesario.

    //METODOS
    public boolean isEmpty() {
        return primero == null;
    }

    public int size() {
        return size;
    }

    public void insertarInicio(Producto p) {
        NodoProducto nuevo = new NodoProducto(p);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        size++;
    }

    public void insertarFin(Producto p) {
        NodoProducto nuevo = new NodoProducto(p);
        if (isEmpty()) {
            primero = nuevo;
        } else {
            NodoProducto actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        size++;
    }

    private NodoProducto buscarNodoPorNombre(String nombre) {
        NodoProducto actual = primero;
        while (actual != null) {
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre.trim())) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean modificarProducto(String nombreBusqueda,
                                     String nuevoNombre,
                                     Double nuevoPrecio,
                                     String nuevaCategoria,
                                     String nuevaFechaVenc,
                                     Integer nuevaCantidad) {
        NodoProducto nodo = buscarNodoPorNombre(nombreBusqueda);
        if (nodo == null) return false;
        Producto p = nodo.getProducto();

        if (nuevoNombre != null && !nuevoNombre.isBlank()) p.setNombre(nuevoNombre);
        if (nuevoPrecio != null) p.setPrecio(nuevoPrecio);
        if (nuevaCategoria != null && !nuevaCategoria.isBlank()) p.setCategoria(nuevaCategoria);
        if (nuevaFechaVenc != null) p.setFechaVencimiento(nuevaFechaVenc);
        if (nuevaCantidad != null) p.setCantidad(nuevaCantidad);

        return true;
    }

    public boolean agregarImagenAProducto(String nombreBusqueda, String rutaImagen) {
        NodoProducto nodo = buscarNodoPorNombre(nombreBusqueda);
        if (nodo == null) return false;
        nodo.getProducto().agregarImagen(rutaImagen);
        return true;
    }

    public double imprimirReporteCostos() {
        if (isEmpty()) {
            System.out.println("La lista de productos está vacía.");
            return 0.0;
        }
        System.out.println("----- REPORTE DE INVENTARIO -----");
        NodoProducto actual = primero;
        double acumulado = 0.0;
        int index = 1;
        while (actual != null) {
            Producto p = actual.getProducto();
            double costo = p.costoTotal();
            acumulado += costo;
            System.out.printf("%d) %s%n    Costo total del producto: %.2f%n", index, p.toString(), costo);
            actual = actual.getSiguiente();
            index++;
        }
        System.out.printf("Costo TOTAL acumulado de la lista: %.2f%n", acumulado);
        System.out.println("----- FIN DEL REPORTE -----");
        return acumulado;
    }

    public void listarProductos() {
        if (isEmpty()) {
            System.out.println("[Lista vacía]");
            return;
        }
        NodoProducto actual = primero;
        int index = 1;
        while (actual != null) {
            System.out.printf("%d) %s%n", index, actual.getProducto().toString());
            actual = actual.getSiguiente();
            index++;
        }
    }
}
