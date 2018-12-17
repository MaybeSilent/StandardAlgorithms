package algcollection.tree;

import java.util.*;

public class BSTTree {
    private Node root;

    public BSTTree(int key) {
        root.Key = key;
        root.size++;
        root.left = root.right = null;
    }

    public void insert(int key) {
        insert(root, key);
    }

    private void insert(Node node, int key) {
        if (key < node.Key) {
            if (node.left != null)
                insert(node.left, key);
            else {
                Node insert = new Node(key);
                node.left = insert;
            }
        }

        else if (key > node.Key) {
            if (node.right != null)
                insert(node.right, key);
            else {
                Node insert = new Node(key);
                node.right = insert;
            }
        }

        else
            return; // just return for duplicated insert
        node.size = node.left.size + node.right.size + 1;
    }

    // return node for search
    public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node node, int key) {
        if (node == null)
            return null; // null means not found
        if (key < node.Key)
            return search(node.left, key);
        else if (key > node.Key)
            return search(node.right, key);
        else
            return node;
    }

    // other find method min , max , floor , ceil
    // delete function , range , select k
    // query data in array
    public Node min() {
        return min(root);
    }

    private Node min(Node node) {
        if (node.left != null)
            return min(node.left);
        else
            return node;
    }

    public Node max() {
        return max(root);
    }

    private Node max(Node node) {
        if (node.right != null) {
            return max(node.right);
        } else {
            return node;
        }
    }

    // floor means to find max value in tree <= key
    public Node floor(int key) {
        return floor(root, key);
    }

    private Node floor(Node node, int key) {
        if (node == null)
            return null;
        if (key < node.Key)
            return floor(node.left, key);
        else if (key > node.Key) {
            Node rightNode = floor(node.right, key); // don't konw wheather node is match
            return rightNode == null ? node : rightNode;
        } else {
            return node;
        }
    }

    // key means to find min value in tree >= key , similiar to floor
    public Node ceil(int key) {
        return ceil(root, key);
    }

    private Node ceil(Node node, int key) {
        if (node == null)
            return null;
        if (key > node.Key)
            return ceil(node.right, key);
        else if (key < node.Key) {
            Node leftNode = ceil(node.left, key);
            return leftNode == null ? node : leftNode;
        } else {
            return node;
        }

    }

    // delete min , delete max in tree
    // delete operation should consider the size of the bst tree
    public Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        else {
            node.left = deleteMin(node.left);
        }
        node.size = node.left.size + node.right.size + 1;
        return node;
    }

    public Node deleteMax(Node node) {
        if (node.right == null)
            return node.left;
        else
            node.right = deleteMax(node.right);
        node.size = node.left.size + node.right.size + 1;
        return node;
    }

    // delete node in BST
    public void delete(int key) {
        delete(root, key);
    }

    // delete should reutrn the new node deleted in BST
    // delete may change the struction of the BST tree
    private Node delete(Node node, int key) {
        if (node == null)
            return null; // return null at here means no match value in bst tree to delete
        if (key < node.Key)
            node.left = delete(node.left, key);
        else if (key > node.Key)
            node.right = delete(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                // find the min node in right tree
                // delete tht min node in right
                // return new node
                Node minRight = min(node.right);
                minRight.right = deleteMin(node.right);
                minRight.left = node.left;
                node = minRight;
            }
        }
        node.size = node.left.size + node.right.size + 1;
        return node;
    }

    // rank means find the node's rank in bst tree
    public int rank(Node node) {
        return rank(node, node.Key);
    }

    // rank means the nums of key in bst tree less than input key
    private int rank(Node node, int key) {
        if (node == null)
            return 0;
        if (key < node.Key)
            return rank(node.left, key);
        else if (key > node.Key)
            return node.left.size + 1 + rank(node.right, key);
        else
            return node.left.size; // key should than the input key
    }

    // select means find the k-th key in bst tree
    public Node select(int ranks) {
        if (ranks > root.size || ranks < 0)
            return null;
        else
            return select(root, ranks);
    }

    private Node select(Node node, int ranks) {
        if (node == null)
            return null;
        if (node.left.size > ranks)
            return select(node.left, ranks);
        else if (node.left.size < ranks)
            return select(node.right, ranks - node.left.size - 1);
        else
            return node;
    }

    // query data position
    // return a Iterable list
    public Iterable<Integer> getkeys(int min , int max) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        getkeys(root , min, max, array);
        return array;
    }

    private void getkeys(Node node, int min, int max, ArrayList<Integer> array) {
        if (min < node.Key)
            getkeys(node.left, min, max, array);
        if (node.Key >= min && node.Key <= max)
            array.add(node.Key);
        if (node.Key < max)
            getkeys(node.right, min, max, array);
    }

    // inorder traversing
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.println(node.Key);
        inorder(node.right);
    }

    public static void main(String[] args) {

    }

}

class Node {
    int Key; // use for compare

    int size; // sizeof(left) + sizeof(right) + 1
    // size should be thought in insert , build and delete
    Node left, right;

    public Node(int key) {
        this.Key = key;
        this.size = 1;
    }
}