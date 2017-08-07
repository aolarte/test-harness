package com.andresolarte.harness.lang.structures;

import com.andresolarte.harness.lang.algo.BinarySearchTree;

public class MaxHeap {

    class Node {
        final int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node root;


}
