import java.util.Scanner;

// Node class to represent each node in the binary tree
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

// BinaryTree class to handle tree operations
class BinaryTree {
    Node root; // Root node of the binary tree
    Scanner sc = new Scanner(System.in);

    // Recursive function to find a node in the tree by value
    private Node findNode(Node current, int value) {
        if (current == null) return null;
        if (current.data == value) return current;

        // Search left subtree
        Node leftSearch = findNode(current.left, value);
        if (leftSearch != null) return leftSearch;

        // Search right subtree
        return findNode(current.right, value);
    }

    // Method to build the tree from user input
    public void buildTree() {
        // Step 1: Create the root node
        System.out.print("Enter the root node value: ");
        int rootData = sc.nextInt();
        root = new Node(rootData);

        // Step 2: Insert more nodes based on user input
        while (true) {
            System.out.print("Do you want to insert another node? (yes/no): ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("no")) break;

            // Get new node data
            System.out.print("Enter the new node value: ");
            int newData = sc.nextInt();

            // Get parent node data
            System.out.print("Enter the parent node value: ");
            int parentData = sc.nextInt();

            // Find parent node in the tree
            Node parentNode = findNode(root, parentData);
            if (parentNode == null) {
                System.out.println("Parent node not found! Try again.");
                continue;
            }

            // Ask left or right
            System.out.print("Insert as left or right child of " + parentData + "? (left/right): ");
            String dir = sc.next();

            Node newNode = new Node(newData);

            if (dir.equalsIgnoreCase("left")) {
                if (parentNode.left == null) {
                    parentNode.left = newNode;
                } else {
                    System.out.println("Left child already exists! Skipping insertion.");
                }
            } else if (dir.equalsIgnoreCase("right")) {
                if (parentNode.right == null) {
                    parentNode.right = newNode;
                } else {
                    System.out.println("Right child already exists! Skipping insertion.");
                }
            } else {
                System.out.println("Invalid direction! Please enter 'left' or 'right'.");
            }
        }
    }

    // Inorder traversal: Left -> Root -> Right
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // Preorder traversal: Root -> Left -> Right
    public void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // Postorder traversal: Left -> Right -> Root
    public void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }
}

// Main class
public class bt_traversal {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Build the tree interactively
        tree.buildTree();

        // Display all 3 types of traversals
        System.out.println("\nInorder Traversal:");
        tree.inorder(tree.root);

        System.out.println("\nPreorder Traversal:");
        tree.preorder(tree.root);

        System.out.println("\nPostorder Traversal:");
        tree.postorder(tree.root);
    }
}
