package InventarioAvance1.bl;

import java.util.ArrayList;
import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento; // puede ser null si no aplica
    private int cantidad; // unidades en inventario
    private ArrayList<String> listaImagenes;
    private Producto siguiente;

    public Producto(String pNombre, double pPrecio, String pCategoria, String pFechaVencimiento, int pCantidad) {
        nombre = pNombre;
        precio = pPrecio;
        categoria = pCategoria;
        fechaVencimiento = pFechaVencimiento;
        cantidad = pCantidad;
        listaImagenes = new ArrayList<>();
        siguiente = null;
    }

    /* Cuando tengamos un poco mas de tiempo ponemos la fecha con LocalDate, ATT: Jose :P
    private LocalDate parseFecha(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(s.trim(), DTF);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    */

    /// GETTERS

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    /// SETTERS

    public void setNombre(String otroNombre) {
        nombre = otroNombre;
    }

    public void setPrecio(double otroPrecio) {
        precio = otroPrecio;
    }

    public void setCategoria(String otraCategoria) {
        categoria = otraCategoria;
    }

    public void setFechaVencimiento(String otraFechaVencimiento) {
        fechaVencimiento = otraFechaVencimiento;
    }

    public void setCantidad(int otraCantidad) {
        cantidad = otraCantidad;
    }

    public ArrayList<String> getListaImagenes() { return listaImagenes; }

    public void agregarImagen(String rutaImagen) {
        if (rutaImagen != null && !rutaImagen.isBlank()) {
            listaImagenes.add(rutaImagen);
        }
    }

    public double costoTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s \n Precio: %.2f \n Categoria: %s \n FechaVenc: %s \n Cantidad: %d \n #Imágenes: %d",
                nombre, precio, categoria, fechaVencimiento, cantidad, listaImagenes.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
