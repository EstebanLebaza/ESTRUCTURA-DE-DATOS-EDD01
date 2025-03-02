import java.util.*;

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

    public void mostrar() {
        System.out.println("Código: " + codigo + " | Producto: " + nombre + " | Precio: $" + precio + " | Cantidad: " + cantidad);
    }

    @Override
    public int compareTo(Producto otroProducto) {
        return Integer.compare(this.codigo, otroProducto.codigo);
    }

    // Comparadores para otros atributos
    public static Comparator<Producto> porPrecio = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            return Double.compare(p1.precio, p2.precio);
        }
    };

    public static Comparator<Producto> porCantidad = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            return Integer.compare(p1.cantidad, p2.cantidad);
        }
    };

    public static Comparator<Producto> porNombre = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            return p1.nombre.compareToIgnoreCase(p2.nombre);
        }
    };
}

public class OrdenarProductos {

    // Método para ordenar usando Insertion Sort
    public static void insertSort(Producto[] productos, Comparator<Producto> comparador) {
        for (int i = 1; i < productos.length; i++) {
            Producto productoActual = productos[i];
            int j = i - 1;

            // Desplazamiento de elementos mayores al productoActual
            while (j >= 0 && comparador.compare(productos[j], productoActual) > 0) {
                productos[j + 1] = productos[j];
                j--;
            }
            productos[j + 1] = productoActual;
        }
    }

    // Búsqueda binaria por código
    public static int busquedaBinariaPorCodigo(Producto[] productos, int codigo) {
        int inicio = 0, fin = productos.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (productos[medio].getCodigo() == codigo) {
                return medio;
            } else if (productos[medio].getCodigo() < codigo) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;  // Producto no encontrado
    }

    // Búsqueda binaria por nombre
    public static int busquedaBinariaPorNombre(Producto[] productos, String nombre) {
        int inicio = 0, fin = productos.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comparacion = productos[medio].getNombre().compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;  // Producto no encontrado
    }

    public static void main(String[] args) {
        // Inicialización de productos
        Producto[] productos = new Producto[10];
        productos[0] = new Producto(1001, "Bolsa de leche", 4500, 15);
        productos[1] = new Producto(1002, "Pan tajado", 3800, 10);
        productos[2] = new Producto(1003, "Huevos", 16500, 8);
        productos[3] = new Producto(1004, "Arroz", 5200, 20);
        productos[4] = new Producto(1005, "Aceite", 7800, 12);
        productos[5] = new Producto(1006, "Papel Higiénico", 10500, 7);
        productos[6] = new Producto(1007, "Jabón en barra", 3200, 25);
        productos[7] = new Producto(1008, "Cepillo de dientes", 5600, 18);
        productos[8] = new Producto(1009, "Detergente", 9600, 5);
        productos[9] = new Producto(1010, "Sal", 1800, 30);

        // Interfaz de usuario
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ordenar por código");
            System.out.println("2. Ordenar por precio");
            System.out.println("3. Ordenar por cantidad");
            System.out.println("4. Ordenar por nombre");
            System.out.println("5. Buscar producto por código");
            System.out.println("6. Buscar producto por nombre");
            System.out.println("7. Agregar producto");
            System.out.println("8. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    insertSort(productos, Producto::compareTo);  // Ordenar por código
                    System.out.println("Productos ordenados por código:");
                    for (Producto p : productos) {
                        p.mostrar();
                    }
                    break;
                case 2:
                    insertSort(productos, Producto.porPrecio);  // Ordenar por precio
                    System.out.println("Productos ordenados por precio:");
                    for (Producto p : productos) {
                        p.mostrar();
                    }
                    break;
                case 3:
                    insertSort(productos, Producto.porCantidad);  // Ordenar por cantidad
                    System.out.println("Productos ordenados por cantidad:");
                    for (Producto p : productos) {
                        p.mostrar();
                    }
                    break;
                case 4:
                    insertSort(productos, Producto.porNombre);  // Ordenar por nombre
                    System.out.println("Productos ordenados por nombre:");
                    for (Producto p : productos) {
                        p.mostrar();
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el código del producto a buscar:");
                    int codigoBuscado = scanner.nextInt();
                    int indiceCodigo = busquedaBinariaPorCodigo(productos, codigoBuscado);
                    if (indiceCodigo != -1) {
                        System.out.println("Producto encontrado:");
                        productos[indiceCodigo].mostrar();
                    } else {
                        System.out.println("Producto con código " + codigoBuscado + " no encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el nombre del producto a buscar:");
                    String nombreBuscado = scanner.nextLine();
                    int indiceNombre = busquedaBinariaPorNombre(productos, nombreBuscado);
                    if (indiceNombre != -1) {
                        System.out.println("Producto encontrado:");
                        productos[indiceNombre].mostrar();
                    } else {
                        System.out.println("Producto con nombre " + nombreBuscado + " no encontrado.");
                    }
                    break;
                case 7:
                    // Agregar un nuevo producto
                    System.out.println("Ingrese el código del nuevo producto:");
                    int nuevoCodigo = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    System.out.println("Ingrese el nombre del nuevo producto:");
                    String nuevoNombre = scanner.nextLine();
                    System.out.println("Ingrese el precio del nuevo producto:");
                    double nuevoPrecio = scanner.nextDouble();
                    System.out.println("Ingrese la cantidad del nuevo producto:");
                    int nuevaCantidad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer

                    // Crear el nuevo producto y agregarlo al arreglo
                    Producto nuevoProducto = new Producto(nuevoCodigo, nuevoNombre, nuevoPrecio, nuevaCantidad);
                    Producto[] nuevoArreglo = new Producto[productos.length + 1];
                    System.arraycopy(productos, 0, nuevoArreglo, 0, productos.length);
                    nuevoArreglo[productos.length] = nuevoProducto;
                    productos = nuevoArreglo;

                    System.out.println("Producto agregado exitosamente.");
                    break;
                case 8:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
