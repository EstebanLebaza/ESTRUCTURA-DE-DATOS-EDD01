import java.util.*;

class Producto implements Comparable<Producto> {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor
    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    // Setters
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // Método para mostrar detalles del producto
    public void mostrar() {
        System.out.println("Código: " + codigo + " | Producto: " + nombre + " | Precio: $" + precio + " | Cantidad: " + cantidad);
    }

    @Override
    public int compareTo(Producto otroProducto) {
        return Integer.compare(this.codigo, otroProducto.codigo);
    }

    // Comparadores para ordenar productos
    public static Comparator<Producto> porPrecio = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            return Double.compare(p1.precio, p2.precio);
        }
    };

    public static Comparator<Producto> porCodigo = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            return Integer.compare(p1.codigo, p2.codigo);
        }
    };
}

public class Inventario {

    private LinkedList<Producto> inventario;

    public Inventario() {
        inventario = new LinkedList<>();
        inicializarInventario();
    }

    // Inicializa el inventario con 10 productos
    private void inicializarInventario() {
        inventario.add(new Producto(1001, "Bolsa de leche", 4500, 15));
        inventario.add(new Producto(1002, "Pan tajado", 3800, 10));
        inventario.add(new Producto(1003, "Huevos", 16500, 8));
        inventario.add(new Producto(1004, "Arroz", 5200, 20));
        inventario.add(new Producto(1005, "Aceite", 7800, 12));
        inventario.add(new Producto(1006, "Papel Higiénico", 10500, 7));
        inventario.add(new Producto(1007, "Jabón en barra", 3200, 25));
        inventario.add(new Producto(1008, "Cepillo de dientes", 5600, 18));
        inventario.add(new Producto(1009, "Detergente", 9600, 5));
        inventario.add(new Producto(1010, "Sal", 1800, 30));
    }

    // Método para agregar un producto al final del inventario (lista enlazada)
    public void add(Producto producto) {
        inventario.addLast(producto);
        System.out.println("Producto agregado exitosamente.");
    }

    // Método para eliminar un producto si su cantidad es mayor a 0
    public void remove(int codigo) {
        Producto productoAEliminar = buscarPorCodigo(codigo);
        
        if (productoAEliminar != null) {
            if (productoAEliminar.getCantidad() > 0) {
                productoAEliminar.setCantidad(productoAEliminar.getCantidad() - 1); // Decrementar la cantidad
                System.out.println("Producto eliminado: " + productoAEliminar.getNombre() + " | Nueva cantidad: " + productoAEliminar.getCantidad());
                
                // Si la cantidad llega a 0, se elimina el producto de la lista
                if (productoAEliminar.getCantidad() == 0) {
                    inventario.remove(productoAEliminar);
                    System.out.println("Producto completamente agotado y eliminado del inventario.");
                }
            } else {
                System.out.println("No se puede eliminar el producto porque su cantidad es 0.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Mostrar todo el inventario
    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Producto p : inventario) {
                p.mostrar();
            }
        }
    }

    // Buscar producto por código
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : inventario) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Buscar producto por nombre
    public Producto buscarPorNombre(String nombre) {
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Método para vender un producto
    public void venderProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Quieres vender el producto por código o nombre?");
        System.out.println("1. Código");
        System.out.println("2. Nombre");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Producto productoVendido = null;

        if (opcion == 1) {
            System.out.println("Ingrese el código del producto a vender:");
            int codigo = scanner.nextInt();
            productoVendido = buscarPorCodigo(codigo);
        } else if (opcion == 2) {
            System.out.println("Ingrese el nombre del producto a vender:");
            String nombre = scanner.nextLine();
            productoVendido = buscarPorNombre(nombre);
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        // Si el producto fue encontrado
        if (productoVendido != null) {
            System.out.println("¿Cuántos productos deseas vender?");
            int cantidad = scanner.nextInt();

            if (cantidad <= productoVendido.getCantidad() && cantidad > 0) {
                productoVendido.setCantidad(productoVendido.getCantidad() - cantidad);
                System.out.println("Producto vendido: " + productoVendido.getNombre() + " | Cantidad vendida: " + cantidad);

                if (productoVendido.getCantidad() == 0) {
                    System.out.println("La cantidad del producto llegó a 0, se elimina del inventario.");
                    inventario.remove(productoVendido);  // Eliminar producto si su cantidad es 0
                }
            } else {
                System.out.println("No hay suficiente stock para vender esa cantidad.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Ordenar inventario por código
    public void ordenarPorCodigo() {
        Producto[] productosArray = inventario.toArray(new Producto[0]);
        Arrays.sort(productosArray, Producto.porCodigo);
        System.out.println("Inventario ordenado por código:");
        for (Producto p : productosArray) {
            p.mostrar();
        }
    }

    // Ordenar inventario por precio
    public void ordenarPorPrecio() {
        Producto[] productosArray = inventario.toArray(new Producto[0]);
        Arrays.sort(productosArray, Producto.porPrecio);
        System.out.println("Inventario ordenado por precio:");
        for (Producto p : productosArray) {
            p.mostrar();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Mostrar inventario");
            System.out.println("2. Agregar producto");
            System.out.println("3. Vender producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Ordenar por código");
            System.out.println("6. Ordenar por precio");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    inventario.mostrarInventario();
                    break;
                case 2:
                    System.out.println("Ingrese el código del nuevo producto:");
                    int nuevoCodigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nombre del nuevo producto:");
                    String nuevoNombre = scanner.nextLine();
                    System.out.println("Ingrese el precio del nuevo producto:");
                    double nuevoPrecio = scanner.nextDouble();
                    System.out.println("Ingrese la cantidad del nuevo producto:");
                    int nuevaCantidad = scanner.nextInt();

                    Producto nuevoProducto = new Producto(nuevoCodigo, nuevoNombre, nuevoPrecio, nuevaCantidad);
                    inventario.add(nuevoProducto); // Agregar el producto al inventario
                    break;
                case 3:
                    inventario.venderProducto();
                    break;
                case 4:
                    System.out.println("Ingrese el código del producto a eliminar:");
                    int codigoEliminar = scanner.nextInt();
                    inventario.remove(codigoEliminar); // Eliminar el producto del inventario
                    break;
                case 5:
                    inventario.ordenarPorCodigo();
                    break;
                case 6:
                    inventario.ordenarPorPrecio();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
