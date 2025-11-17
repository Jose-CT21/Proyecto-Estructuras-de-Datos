package InventarioAvance1.bl.Clientes;

public class ColaClientes {

    private NodoCliente frente;

    public boolean estaVacia() { return frente == null; }

    public void encolar(Cliente c) {
        NodoCliente n = new NodoCliente(c);
        if (estaVacia() || c.getPrioridad() > frente.cliente.getPrioridad()) {
            n.siguiente = frente;
            frente = n;
            return;
        }
        NodoCliente a = frente, ant = null;
        while (a != null && a.cliente.getPrioridad() >= c.getPrioridad()) {
            ant = a;
            a = a.siguiente;
        }
        ant.siguiente = n;
        n.siguiente = a;
    }

    public Cliente desencolar() {
        if (estaVacia()) return null;
        Cliente c = frente.cliente;
        frente = frente.siguiente;
        return c;
    }

    public void mostrar() {
        NodoCliente a = frente;
        while (a != null) {
            System.out.println(a.cliente);
            a = a.siguiente;
        }
    }
}
