package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private List<ItemPedido> items;

    public Pedido() {
        this.id = contadorId++;
        this.items = new ArrayList<>();
    }

    public void agregarItem(Producto producto, int cantidad) {
        items.add(new ItemPedido(producto, cantidad));
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void mostrarPedido() {
        System.out.println("=== Pedido #" + id + " ===");
        for (ItemPedido item : items) {
            System.out.printf("- %s x%d = $%.2f\n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.getSubtotal());
        }
        System.out.println("Total: $" + String.format("%.2f", getTotal()) + "\n");
    }

    // ✅ Agregar este método:
    public List<ItemPedido> getItems() {
        return items;
    }
}

