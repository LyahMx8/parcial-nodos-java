/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nodos;


/**
 *
 * @author LyahMotta
 */

public class ArbolBinario {

    public static class Nodo {
        int data;
        Nodo left, right;

        public Nodo(int item) {
            data = item;
            left = right = null;
        }
    }

    public static Nodo insert(Nodo root, int value) {
        if (root == null) return new Nodo(value);
        
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    // Mostrar la secuencia de nodos In-Order
    public static void showInOrder(Nodo root) {
        if (root != null) {
            showInOrder(root.left);
            System.out.print(root.data + " ");
            showInOrder(root.right);
        }
    }

    // Contar las hojas del árbol
    public static int countLeaves(Nodo root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    // Ejercicio 2. Eliminar manteniendo la propiedad de BST
    public static Nodo delete(Nodo root, int deleteValue) {
        if (root == null) return null;

        if (deleteValue < root.data) {
            root.left = delete(root.left, deleteValue);
        } else if (deleteValue > root.data) {
            root.right = delete(root.right, deleteValue);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            root.data = findMin(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    private static int findMin(Nodo root) {
        int rootData = root.data;
        while (root.left != null) {
            rootData = root.left.data;
            root = root.left;
        }
        return rootData;
    }

    public static void main(String[] args) {
        // En lugar de una clase Arbol, solo manejamos la raiz
        Nodo root = null;
        int[] valores = {55,25,75,15,35,65,8555,25,75,15,35,65,85};

        // Llenamos el arbol
        for (int v : valores) {
            root = insert(root, v);
        }

        System.out.print("Recorrido del arbol: ");
        showInOrder(root);
        System.out.println();

        System.out.println("Cantidad de hojas de árbol: " + countLeaves(root));

        System.out.println("Borrando el valor 65");
        root = delete(root, 65);

        System.out.print("Árbol final: ");
        showInOrder(root);
        System.out.println();
    }
}