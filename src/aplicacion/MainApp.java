package aplicacion;

import servicio.GestorProductos;
import servicio.GestorPedidos;
import modelo.Producto;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos(gestorProductos.getProductos(), sc);

        boolean salir = false;

        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver productos");
            System.out.println("3. Buscar/Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear pedido");
            System.out.println("6. Ver pedidos realizados");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    try {
                        System.out.print("Nombre del producto: ");
                        String nombre = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine());

                        System.out.print("Cantidad en stock: ");
                        int cantidad = Integer.parseInt(sc.nextLine());

                        gestorProductos.agregarProducto(nombre, precio, cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Precio o cantidad inválida.\n");
                    }
                    break;

                case "2":
                    gestorProductos.mostrarProductos();
                    break;

                case "3":
                    System.out.print("Ingrese ID del producto a buscar/actualizar: ");
                    int idActualizar = Integer.parseInt(sc.nextLine());
                    Producto producto = gestorProductos.buscarProductoPorId(idActualizar);
                    if (producto != null) {
                        System.out.println("Producto encontrado: ");
                        System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d\n",
                                producto.getId(), producto.getNombre(),
                                producto.getPrecio(), producto.getCantidadEnStock());

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = Double.parseDouble(sc.nextLine());
                        System.out.print("Nueva cantidad en stock: ");
                        int nuevaCantidad = Integer.parseInt(sc.nextLine());

                        if (gestorProductos.actualizarProducto(idActualizar, nuevoNombre, nuevoPrecio, nuevaCantidad)) {
                            System.out.println("Producto actualizado.\n");
                        } else {
                            System.out.println("Error al actualizar producto.\n");
                        }
                    } else {
                        System.out.println("Producto no encontrado.\n");
                    }
                    break;

                case "4":
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    if (gestorProductos.eliminarProducto(idEliminar)) {
                        System.out.println("Producto eliminado.\n");
                    } else {
                        System.out.println("Producto no encontrado.\n");
                    }
                    break;

                case "5":
                    gestorPedidos.crearPedido();
                    break;

                case "6":
                    gestorPedidos.mostrarPedidos();
                    break;

                case "7":
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida.\n");
            }
        }

        sc.close();
    }
}
