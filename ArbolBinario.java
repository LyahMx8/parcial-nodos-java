/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nodos;


/**
 *
 * @author LyahMotta
 */
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class ArbolBinario {
    Node root;

    // Metodo para insertar un valor en el arbol
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insertRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data);
        }
        return root;
    }

    // Muestra los nodos de menor a mayor
    public void showInOrder(Node node) {
        if (node != null) {
            showInOrder(node.left);
            System.out.print(node.data + " ");
            showInOrder(node.right);
        }
    }

    // Cuenta cuantos nodos no tienen hijos
    public int countLeaves(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Elimina un valor del arbol
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node root, int data) {
        if (root == null) return null;

        if (data < root.data) {
            root.left = deleteRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRecursive(root.right, data);
        } else {
            // Caso 1: Sin hijos o solo uno
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Caso 2: Dos hijos (buscamos el valor minimo a la derecha)
            root.data = findMin(root.right);
            root.right = deleteRecursive(root.right, root.data);
        }
        return root;
    }

    private int findMin(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] valores = {55,25,75,15,35,65,8555,25,75,15,35,65,85};

        // Agregando los valores del arreglo
        for (int v : valores) {
            tree.insert(v);
        }

        System.out.print("Secuencia In-Order: ");
        tree.showInOrder(tree.root);
        System.out.println();

        System.out.println("Total de hojas: " + tree.countLeaves(tree.root));

        System.out.println("Eliminando el 75...");
        tree.delete(75);
        
        System.out.print("Secuencia final: ");
        tree.showInOrder(tree.root);
        System.out.println();
    }
}