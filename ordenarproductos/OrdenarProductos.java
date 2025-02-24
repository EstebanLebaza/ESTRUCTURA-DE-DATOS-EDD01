package com.mycompany.ordenarproductos;

/**
 *
 * @author dlebaza
 */
class Producto {
    String nombre;
    double precio;
    int stock;

    // Constructor
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Método para mostrar los productos
    public void mostrar() {
        System.out.println("Producto: " + nombre + " | Precio: $" + precio + " COP | Stock: " + stock);
    }
}

public class OrdenarProductos {

    public static void main(String[] args) {
        // Arreglo de productos con precios más baratos en pesos colombianos
        Producto[] productos = {
            new Producto("Bolsa de leche", 4500, 15),
            new Producto("Pan tajado", 3800, 10),
            new Producto("Huevos", 16500, 8),
            new Producto("Arroz", 5200, 20),
            new Producto("Aceite", 7800, 12),
            new Producto("Papel Higiénico", 10500, 7),
            new Producto("Jabón en barra", 3200, 25),
            new Producto("Cepillo de dientes", 5600, 18),
            new Producto("Detergente", 9600, 5),
            new Producto("Sal", 1800, 30)
        };

        // Ordenar por precio con Bubble Sort
        bubbleSort(productos);
        System.out.println("Productos ordenados por precio (Bubble Sort):");
        for (Producto p : productos) {
            p.mostrar();
        }

        // Volver a desordenar para probar Insertion Sort
        Producto[] productos2 = {
            new Producto("Bolsa de leche", 4500, 15),
            new Producto("Pan tajado", 3800, 10),
            new Producto("Huevos (30 und)", 16500, 8),
            new Producto("Arroz (1kg)", 5200, 20),
            new Producto("Aceite (500ml)", 7800, 12),
            new Producto("Papel Higiénico (4 rollos)", 10500, 7),
            new Producto("Jabón en barra", 3200, 25),
            new Producto("Cepillo de dientes", 5600, 18),
            new Producto("Detergente (1kg)", 9600, 5),
            new Producto("Sal (500g)", 1800, 30)
        };

        // Ordenar por precio con Insertion Sort
        insertionSort(productos2);
        System.out.println("\nProductos ordenados por precio (Insertion Sort):");
        for (Producto p : productos2) {
            p.mostrar();
        }
    }

    // Bubble Sort para ordenar productos por precio
    public static void bubbleSort(Producto[] productos) {
        int n = productos.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (productos[j].precio > productos[j + 1].precio) {
                    // Intercambiar si el precio actual es mayor que el siguiente
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort para ordenar productos por precio
    public static void insertionSort(Producto[] productos) {
        int n = productos.length;
        for (int i = 1; i < n; i++) {
            Producto key = productos[i];
            int j = i - 1;
            // Mover productos mayores que `key` una posición adelante
            while (j >= 0 && productos[j].precio > key.precio) {
                productos[j + 1] = productos[j];
                j--;
            }
            productos[j + 1] = key;
        }
    }
}
