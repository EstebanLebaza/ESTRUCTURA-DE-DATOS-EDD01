import java.util.ArrayList;
import java.util.Scanner;

class Producto implements Comparable<Producto> {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public double getValorTotal() { return precio * cantidad; }

    public void mostrar() {
        System.out.println("Código: " + codigo + " | Producto: " + nombre + 
            " | Precio: $" + precio + " | Cantidad: " + cantidad + 
            " | Valor Total: $" + getValorTotal());
    }

    @Override
    public int compareTo(Producto otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }
}

public class OrdenarBurbuja {
    private static ArrayList<Producto> productos = new ArrayList<>();

    public static void main(String[] args) {
        // Cargar productos iniciales
        productos.add(new Producto(1001, "Leche", 4500, 15));
        productos.add(new Producto(1002, "Pan", 3800, 10));
        productos.add(new Producto(1003, "Huevos", 16500, 8));
        productos.add(new Producto(1004, "Arroz", 5200, 20));
        productos.add(new Producto(1005, "Aceite", 7800, 12));
        productos.add(new Producto(1006, "Papel Higiénico", 10500, 7));
        productos.add(new Producto(1007, "Jabón", 3200, 25));
        productos.add(new Producto(1008, "Cepillo de dientes", 5600, 18));
        productos.add(new Producto(1009, "Detergente", 9600, 5));
        productos.add(new Producto(1010, "Sal", 1800, 30));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ordenar productos por código (Burbuja)");
            System.out.println("2. Ordenar por precio * cantidad (Burbuja)");
            System.out.println("3. Buscar producto por código (Lineal)");
            System.out.println("4. Buscar producto por nombre (Lineal)");
            System.out.println("5. Agregar nuevo producto");
            System.out.println("6. Mostrar productos");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ordenarPorCodigoBurbuja();
                    System.out.println("Productos ordenados por código:");
                    mostrarProductos();
                    break;

                case 2:
                    ordenarPorPrecioCantidadBurbuja();
                    System.out.println("Productos ordenados por Valor Total:");
                    mostrarProductos();
                    break;

                case 3:
                    System.out.println("Ingrese el código del producto a buscar:");
                    int codigoBuscado = scanner.nextInt();
                    scanner.nextLine();
                    int indiceCodigo = busquedaLinealPorCodigo(codigoBuscado);
                    if (indiceCodigo != -1) {
                        System.out.println("Producto encontrado:");
                        productos.get(indiceCodigo).mostrar();
                    } else {
                        System.out.println("Producto con código " + codigoBuscado + " no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el nombre del producto a buscar:");
                    String nombreBuscado = scanner.nextLine();
                    int indiceNombre = busquedaLinealPorNombre(nombreBuscado);
                    if (indiceNombre != -1) {
                        System.out.println("Producto encontrado:");
                        productos.get(indiceNombre).mostrar();
                    } else {
                        System.out.println("Producto con nombre '" + nombreBuscado + "' no encontrado.");
                    }
                    break;

                case 5:
                    agregarProducto(scanner);
                    break;

                case 6:
                    mostrarProductos();
                    break;

                case 7:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void mostrarProductos() {
        for (Producto p : productos) {
            p.mostrar();
        }
    }

    public static int busquedaLinealPorCodigo(int codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public static int busquedaLinealPorNombre(String nombre) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public static void agregarProducto(Scanner scanner) {
        System.out.println("Ingrese el código del nuevo producto:");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del nuevo producto:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del nuevo producto:");
        double precio = scanner.nextDouble();

        System.out.println("Ingrese la cantidad del nuevo producto:");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        productos.add(new Producto(codigo, nombre, precio, cantidad));
        System.out.println("Producto agregado exitosamente.");
    }

    public static void ordenarPorCodigoBurbuja() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (productos.get(j).compareTo(productos.get(j + 1)) > 0) {
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }

    public static void ordenarPorPrecioCantidadBurbuja() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (productos.get(j).getValorTotal() > productos.get(j + 1).getValorTotal()) {
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }
}
