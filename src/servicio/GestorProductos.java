package servicio;

import modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class GestorProductos {
    private List<Producto> productos;
    private static int contadorId = 1;

    public GestorProductos() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precio, int cantidad) {
        Producto nuevo = new Producto(contadorId++, nombre, precio, cantidad);
        productos.add(nuevo);
        System.out.println("Producto agregado con ID: " + nuevo.getId() + "\n");
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.\n");
        } else {
            System.out.println("=== Lista de Productos ===");
            for (Producto p : productos) {
                System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d\n",
                        p.getId(), p.getNombre(), p.getPrecio(), p.getCantidadEnStock());
            }
            System.out.println();
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // ✅ Buscar producto por ID
    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // ✅ Actualizar producto
    public boolean actualizarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            producto.setCantidadEnStock(nuevaCantidad);
            return true;
        }
        return false;
    }

    // ✅ Eliminar producto
    public boolean eliminarProducto(int id) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            productos.remove(producto);
            return true;
        }
        return false;
    }
}
