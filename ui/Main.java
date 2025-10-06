package InventarioAvance1.ui;

import InventarioAvance1.bl.ListaProductos;
import InventarioAvance1.bl.Producto;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ListaProductos lista = new ListaProductos();

    public static void main(String[] args) throws IOException {
        System.out.println("=== Aplicación de Gestión de Inventarios ===");
        boolean corriendo = true;
        while (corriendo) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1" -> insertarProductoMenu();
                case "2" -> insertarProductoAlInicioMenu();
                case "3" -> modificarProductoMenu();
                case "4" -> agregarImagenMenu();
                case "5" -> lista.imprimirReporteCostos();
                case "6" -> lista.listarProductos();
                case "0" -> {
                    System.out.println("Saliendo...");
                    corriendo = false;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1 - Insertar producto al final");
        System.out.println("2 - Insertar producto al inicio");
        System.out.println("3 - Modificar producto (por nombre)");
        System.out.println("4 - Agregar imagen a un producto");
        System.out.println("5 - Mostrar reporte de costos");
        System.out.println("6 - Listar productos (resumen)");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void insertarProductoMenu() {
        System.out.println("--- Insertar producto al final ---");
        Producto nuevoProducto = leerProductoDesdeConsola();
        lista.insertarFin(nuevoProducto);
        System.out.println("Producto insertado al final.");
    }

    private static void insertarProductoAlInicioMenu() {
        System.out.println("--- Insertar producto al inicio ---");
        Producto p = leerProductoDesdeConsola();
        lista.insertarInicio(p);
        System.out.println("Producto insertado al inicio.");
    }

    private static Producto leerProductoDesdeConsola() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        double precio = leerDouble("Precio (ej: 12.50): ");
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine().trim();
        System.out.print("Fecha de vencimiento (yyyy-MM-dd) o ENTER si no aplica: ");
        String fecha = scanner.nextLine().trim();
        int cantidad = leerInt("Cantidad (entera): ");
        Producto p = new Producto(nombre, precio, categoria, fecha, cantidad);

        System.out.print("¿Desea añadir rutas de imágenes ahora? (s/n): ");
        String r = scanner.nextLine().trim().toLowerCase();
        while (r.equals("s")) {
            System.out.print("Ruta relativa de imagen (ej: images/producto1.jpg): ");
            String ruta = scanner.nextLine().trim();
            if (!ruta.isBlank()) p.agregarImagen(ruta);
            System.out.print("Agregar otra imagen? (s/n): ");
            r = scanner.nextLine().trim().toLowerCase();
        }
        return p;
    }

    private static void modificarProductoMenu() {
        System.out.println("--- Modificar producto ---");
        System.out.print("Ingrese el nombre del producto a modificar: ");
        String nombreBusqueda = scanner.nextLine().trim();
        System.out.println("Deje vacíos los campos que no desea modificar.");

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine().trim();
        Double nuevoPrecio = leerDoubleOpcional("Nuevo precio (ENTER para omitir): ");
        System.out.print("Nueva categoría: ");
        String nuevaCategoria = scanner.nextLine().trim();
        System.out.print("Nueva fecha de vencimiento (yyyy-MM-dd) o ENTER: ");
        String nuevaFecha = scanner.nextLine().trim();
        Integer nuevaCantidad = leerIntOpcional("Nueva cantidad (ENTER para omitir): ");

        boolean ok = lista.modificarProducto(
                nombreBusqueda,
                nuevoNombre.isBlank() ? null : nuevoNombre,
                nuevoPrecio,
                nuevaCategoria.isBlank() ? null : nuevaCategoria,
                nuevaFecha.isBlank() ? null : nuevaFecha,
                nuevaCantidad
        );
        System.out.println(ok ? "Producto modificado correctamente." : "Producto no encontrado.");
    }

    private static void agregarImagenMenu() {
        System.out.println("--- Agregar imagen a producto ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ruta relativa de la imagen (ej: images/imagen.jpg): ");
        String ruta = scanner.nextLine().trim();
        boolean ok = lista.agregarImagenAProducto(nombre, ruta);
        System.out.println(ok ? "Imagen agregada." : "Producto no encontrado.");
    }

    // Helpers
    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente con un número (ej: 12.50).");
            }
        }
    }

    private static Double leerDoubleOpcional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isBlank()) return null;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, se omitirá la modificación de precio.");
            return null;
        }
    }

    private static int leerInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente con un entero.");
            }
        }
    }

    private static Integer leerIntOpcional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        if (s.isBlank()) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, se omitirá la modificación de cantidad.");
            return null;
        }
    }
}
