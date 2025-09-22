import java.util.Scanner;

// Node class representing each node of the BST
class Node {
    int data;
    Node left, right;

    // Constructor to create a new node
    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class bst_operation {
    static Scanner sc = new Scanner(System.in);

    // Function to insert a node into the BST
    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data); // Create new node if root is null
        }
        if (data < root.data) {
            root.left = insert(root.left, data); // Go to left subtree
        } else if (data > root.data) {
            root.right = insert(root.right, data); // Go to right subtree
        }
        return root; // Return updated root
    }

    // Function to search a value in the BST
    public static boolean search(Node root, int key) {
        if (root == null)
            return false; // Key not found
        if (key == root.data)
            return true; // Key found
        if (key < root.data) {
            return search(root.left, key); // Search in left subtree
        } else {
            return search(root.right, key); // Search in right subtree
        }
    }

    // Function to find the minimum value node in right subtree (used in deletion)
    public static Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Function to delete a node from the BST
    public static Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data) {
            root.left = delete(root.left, key); // Recurse to left
        } else if (key > root.data) {
            root.right = delete(root.right, key); // Recurse to right
        } else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in right)
            Node minNode = findMin(root.right);
            root.data = minNode.data; // Copy inorder successor's data to this node
            root.right = delete(root.right, minNode.data); // Delete inorder successor
        }
        return root;
    }

    // Function to perform in-order traversal (Left, Root, Right)
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);       // Visit left
        System.out.print(root.data + " "); // Visit root
        inorder(root.right);      // Visit right
    }

    public static void main(String[] args) {
        Node root = null; // Start with an empty tree
        int choice, data;

        // User-driven program
        while (true) {
            System.out.println("\n--- Binary Search Tree Operations ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. In-order Traversal");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    data = sc.nextInt();
                    root = insert(root, data);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    data = sc.nextInt();
                    if (search(root, data)) {
                        System.out.println(data + " is found in the BST.");
                    } else {
                        System.out.println(data + " is NOT found in the BST.");
                    }
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    data = sc.nextInt();
                    root = delete(root, data);
                    break;
                case 4:
                    System.out.print("In-order Traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}