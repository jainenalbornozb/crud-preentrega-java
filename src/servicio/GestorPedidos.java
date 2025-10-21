package servicio;

import modelo.Producto;
import modelo.Pedido;
import modelo.ItemPedido;
import excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPedidos {
    private List<Pedido> pedidos;
    private List<Producto> productos;
    private Scanner sc;

    public GestorPedidos(List<Producto> productos, Scanner sc) {
        this.productos = productos;
        this.pedidos = new ArrayList<>();
        this.sc = sc;
    }

    public void crearPedido() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para crear un pedido.\n");
            return;
        }

        Pedido pedido = new Pedido();
        boolean seguir = true;

        while (seguir) {
            mostrarProductosDisponibles();

            try {
                System.out.print("Ingrese ID del producto a agregar al pedido: ");
                int id = Integer.parseInt(sc.nextLine());

                Producto productoSeleccionado = buscarProductoPorId(id);
                if (productoSeleccionado == null) {
                    System.out.println("Producto no encontrado.\n");
                    continue;
                }

                System.out.print("Ingrese cantidad deseada: ");
                int cantidad = Integer.parseInt(sc.nextLine());

                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor que cero.\n");
                    continue;
                }

                if (cantidad > productoSeleccionado.getCantidadEnStock()) {
                    throw new StockInsuficienteException("No hay suficiente stock para '" + productoSeleccionado.getNombre() + "'");
                }

                pedido.agregarItem(productoSeleccionado, cantidad);
                System.out.println("Producto agregado al pedido.\n");

                System.out.print("¿Desea agregar otro producto al pedido? (s/n): ");
                String respuesta = sc.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    seguir = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Asegúrese de ingresar números válidos.\n");
            } catch (StockInsuficienteException e) {
                System.out.println("⚠️ " + e.getMessage() + "\n");
            }
        }

        if (!pedido.estaVacio()) {
            pedidos.add(pedido);

            // Disminuir stock de cada producto
            for (ItemPedido item : pedido.getItems()) {
                Producto p = item.getProducto();
                p.setCantidadEnStock(p.getCantidadEnStock() - item.getCantidad());
            }

            System.out.println("✅ Pedido creado con éxito. Total: $" + String.format("%.2f", pedido.getTotal()) + "\n");
        } else {
            System.out.println("El pedido está vacío. No se creó ningún pedido.\n");
        }
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.\n");
        } else {
            for (Pedido pedido : pedidos) {
                pedido.mostrarPedido();
            }
        }
    }

    private void mostrarProductosDisponibles() {
        System.out.println("=== Productos Disponibles ===");
        for (Producto p : productos) {
            System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d\n",
                    p.getId(), p.getNombre(), p.getPrecio(), p.getCantidadEnStock());
        }
        System.out.println();
    }

    private Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
