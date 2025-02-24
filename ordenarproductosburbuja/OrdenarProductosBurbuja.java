/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordenarproductosburbuja;

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

public class OrdenarProductosBurbuja {

    public static void main(String[] args) {
        // Arreglo de productos con precios colombianos
        Producto[] productos = {
            new Producto("Bolsa de leche", 4500, 15),
            new Producto("Pan tajado", 3800, 10),
            new Producto("Panal de Huevos", 16500, 8),
            new Producto("Arroz", 5200, 20),
            new Producto("Aceite", 7800, 12),
            new Producto("Papel Higienico", 10500, 7),
            new Producto("Jabón en barra", 3200, 25),
            new Producto("Cepillo de dientes", 5600, 18),
            new Producto("Detergente", 9600, 5),
            new Producto("Sal", 1800, 30)
        };

        // Ordenar por precio con Bubble Sort
        bubbleSort(productos);

        // Mostrar los productos ordenados
        System.out.println("Productos ordenados por precio (Bubble Sort):");
        for (Producto p : productos) {
            p.mostrar();
        }
    }

    // Método Bubble Sort para ordenar productos por precio
    public static void bubbleSort(Producto[] productos) {
        int n = productos.length;
        boolean intercambiado;

        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (productos[j].precio > productos[j + 1].precio) {
                    // Intercambiar si el precio actual es mayor que el siguiente
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                    intercambiado = true;
                }
            }
            // Si no hubo intercambios, el arreglo ya está ordenado
            if (!intercambiado) break;
        }
    }
}
