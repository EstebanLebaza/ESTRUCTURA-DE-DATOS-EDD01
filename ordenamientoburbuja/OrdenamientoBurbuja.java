/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordenamientoburbuja;

/**
 *
 * @author dlebaza
 */
public class OrdenamientoBurbuja {

    public static void main(String[] args) {
        // Declarar arreglo desordenado:
        int[] desordenado = {15, 8, 7, 9, 5, 1, 0, 4, 10, 3};

        // Ordenar utilizando el algoritmo de burbuja.
        bubbleSort(desordenado);

        // Mostrar el arreglo ordenado
        System.out.println("Arreglo ordenado por Burbuja:");
        for (int i = 0; i < desordenado.length; i++) {
            System.out.printf("%d,", desordenado[i]);
        }
    }

    // Implementación del metodo Bubble Sort
    // Recibe un arreglo arr de números enteros como parámetro
    public static void bubbleSort(int[] arr) {
        // len almacena el tamaño del arreglo.
        int len = arr.length;
        // swapped es una bandera que nos dirá si hubo intercambios en una iteración.
        boolean swapped;
        //Bucle interno es el que compara los elementos
        for (int i = 0; i < len - 1; i++) {
            swapped = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar si el elemento anterior es mayor
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
           // Es el encargado de decirnos si el arrglo ya esta ordenado
            if (!swapped) break;
        }
    }
}
