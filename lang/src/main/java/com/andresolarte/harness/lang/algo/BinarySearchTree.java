package com.andresolarte.harness.lang.algo;

import java.util.Arrays;

public class BinarySearchTree {
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


    public Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean find(int id) {
        return findNode(id) != null;
    }

    public Node[] findNode(int id) {
        Node currentNode = null;
        Node nextNode = root;

        while (nextNode != null) {
            if (nextNode.data == id) {
                return Arrays.asList(currentNode, nextNode).toArray(new Node[2]);
            }
            if (nextNode.data > id) {
                currentNode = nextNode;
                nextNode = nextNode.left;
            } else {
                currentNode = nextNode;
                nextNode = nextNode.right;
            }
        }
        return null;
    }

    public boolean delete(int id) {
        Node[] node = findNode(id);
        Node parent = node[0];
        Node nodeToDelete = node[1];


        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            //Leaf node
            if (parent.left == nodeToDelete) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (nodeToDelete.left == null || nodeToDelete.right == null) {
            //One child, graft child onto parent
            if (parent.left == nodeToDelete) {
                parent.left = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;
            } else {
                parent.right = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;
            }
        } else {
            //Two children
            //Successor is the node which will replace the deleted node.
            //Successor is the smaller node in the right sub tree of the node to be deleted.
            Node successor = getSuccessor(nodeToDelete);
            if (nodeToDelete == root) {
                root = successor;
            } else if (parent.left == nodeToDelete) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = nodeToDelete.left;
        }
        return false;
    }


    public void insert(int id) {
        if (root == null) {
            root = new Node(id);
        } else {
            Node currentNode = root;
            boolean done = false;
            while (!done) {
                if (currentNode.data > id) {
                    //go left
                    if (currentNode.left == null) {
                        currentNode.left = new Node(id);
                        done = true;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    //go right
                    if (currentNode.right == null) {
                        currentNode.right = new Node(id);
                        done = true;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }

    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.

        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }


    public void display() {

        if (root != null) {
            System.out.print("[ ");
            display(root);
            System.out.println(" ]");
        }
    }

    public void display(Node root) {

        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }

    public static void main(String arg[]) {
        BinarySearchTree b = new BinarySearchTree();
        verify(b.find(5) == false);

        b.insert(5);
        verify(b.find(5) == true);

        b.insert(3);
        verify(b.find(3) == true);

        b.display();


        b.insert(4);
        b.display();


        b.insert(6);
        b.insert(9);
        b.display();
        b.delete(6);
        b.display();

        b.insert(8);
        b.insert(15);
        b.insert(12);
        b.insert(16);
        b.display();


        b.delete(9);
        b.display();

    }

    private static void verify(boolean condition) {
        if (!condition) {
            throw new RuntimeException();
        }
    }

}
