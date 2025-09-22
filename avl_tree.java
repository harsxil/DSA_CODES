import java.util.Scanner;

// Node class represents each node of the AVL tree
class Node {
    int key, height;
    Node left, right;

    // Constructor to initialize a new node with a given key
    Node(int key) {
        this.key = key;
        this.height = 1; // new node is initially a leaf, so height is 1
    }
}

// Main class containing AVL tree methods
public class avl_tree {
    Node root; // Root of the AVL tree

    // Function to get the height of a node
    int height(Node node) {
        if (node == null) { // null nodes have height 0
            return 0;
        }
        return node.height;
    }

    // Utility function to get the maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right rotation for LL imbalance
    Node rightRotate(Node y) {
        System.out.println("Performing Right Rotation at node " + y.key); // debug message
        Node x = y.left; // x = left child of y
        Node T2 = x.right; // T2 will become left child of y after rotation

        // Perform rotation
        x.right = y;
        y.left = T2; // Reassign left child

        // Update heights after rotation
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x; // return new root of rotated subtree
    }

    // Left rotation for RR imbalance
    Node leftRotate(Node x) {
        System.out.println("Performing Left Rotation at node " + x.key); // debug message
        Node y = x.right; // y = right child of x
        Node T2 = y.left; // T2 will become right child of x after rotation

        // Perform rotation
        y.left = x;
        x.right = T2; // Reassign right child

        // Update heights after rotation
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y; // return new root of rotated subtree
    }

    // Get balance factor of a node
    int getBalance(Node node) {
        if (node == null) { // null node has balance 0
            return 0;
        }
        return height(node.left) - height(node.right); // balance = height(left) - height(right)
    }

    // Insert a node into the AVL tree
    Node insert(Node node, int key) {
        // Base case: found position for new node
        if (node == null) {
            System.out.println("Inserting " + key); // debug message
            return new Node(key);
        }

        // Standard BST insertion
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        // Update height of ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // Get balance factor to check if this node became unbalanced
        int balance = getBalance(node);
        System.out.println("Balance at node " + node.key + " after inserting " + key + " = " + balance);

        // Check four cases of imbalance
        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            System.out.println("LL Rotation");
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            System.out.println("RR Rotation");
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            System.out.println("LR Rotation");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            System.out.println("RL Rotation");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // return unchanged node if balanced
    }

    // Find the node with minimum value in subtree (helper for delete)
    // Always finds inorder successor (min in right subtree)
    Node minValueNode(Node node) {
        Node current = node;
        // In right sub tree leftmost node has minimum value
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Delete a node from AVL tree
    Node deleteNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        // Standard BST delete
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else { // Found the node to delete
            System.out.println("Deleting " + key); // debug message

            // node has one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) { // no child
                    return null;
                } else { // one child
                    return temp;
                }
            } else { // node has two children
                Node temp = minValueNode(root.right); // get inorder successor
                root.key = temp.key; // copy successor's value
                root.right = deleteNode(root.right, temp.key); // delete successor
            }
        }

        // Update height of current node
        root.height = 1 + max(height(root.left), height(root.right));

        // Get balance factor
        int balance = getBalance(root);
        System.out.println("Balance at node " + root.key + " after deletion = " + balance);

        // Balance the tree if needed
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            System.out.println("LL Rotation");
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            System.out.println("LR Rotation");
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            System.out.println("RR Rotation");
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            System.out.println("RL Rotation");
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root; // return updated node
    }

    // Main driver
    public static void main(String[] args) {
        AVLTree tree = new AVLTree(); // Create AVL tree instance
        Scanner sc = new Scanner(System.in); // scanner for input

        // Insertion
        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            tree.root = tree.insert(tree.root, key); // insert each key
        }

        // Deletion
        System.out.print("Enter number of elements to delete: ");
        int m = sc.nextInt();
        System.out.println("Enter " + m + " integers to delete:");
        for (int i = 0; i < m; i++) {
            int key = sc.nextInt();
            tree.root = tree.deleteNode(tree.root, key); // delete each key
        }

        sc.close(); // close scanner
    }
}