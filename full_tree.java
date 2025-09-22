import java.util.Scanner;

// Node class representing each node in the binary tree
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class full_tree {

    // Recursive function to find a node by its value
    public static Node findNode(Node current, int value) {
        if (current == null) return null;
        if (current.data == value) return current;

        Node leftSearch = findNode(current.left, value);
        if (leftSearch != null) return leftSearch;

        return findNode(current.right, value);
    }

    // Function to insert a node
    public static void insertNode(Scanner sc, Node root) {
        System.out.print("Enter parent node value: ");
        int parentVal = sc.nextInt();

        // Find parent node
        Node parent = findNode(root, parentVal);
        if (parent == null) {
            System.out.println("Parent not found. Please insert a valid parent.");
            return;
        }

        System.out.print("Enter new node value: ");
        int newVal = sc.nextInt();
        Node newNode = new Node(newVal);

        System.out.print("Insert as 'left' or 'right' child of " + parentVal + ": ");
        String dir = sc.next();

        if (dir.equalsIgnoreCase("left")) {
            if (parent.left == null) {
                parent.left = newNode;
            } else {
                System.out.println("Left child already exists!");
            }
        } else if (dir.equalsIgnoreCase("right")) {
            if (parent.right == null) {
                parent.right = newNode;
            } else {
                System.out.println("Right child already exists!");
            }
        } else {
            System.out.println("Invalid direction. Use 'left' or 'right'.");
        }
    }

    // Function to check if a binary tree is a Full Binary Tree
    public static boolean isFullBinaryTree(Node node) {
        if (node == null) return true;

        // If leaf node
        if (node.left == null && node.right == null) return true;

        // If both children exist, check recursively
        if (node.left != null && node.right != null) {
            return isFullBinaryTree(node.left) && isFullBinaryTree(node.right);
        }

        // If one child missing
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create root node
        System.out.print("Enter root node value: ");
        int rootVal = sc.nextInt();
        Node root = new Node(rootVal);

        // Menu using switch-case
        while (true) {
            System.out.println("\n1. Insert Node");
            System.out.println("2. Check if Full Binary Tree");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    insertNode(sc, root);
                    break;
                case 2:
                    if (isFullBinaryTree(root)) {
                        System.out.println("The tree IS a FULL Binary Tree.");
                    } else {
                        System.out.println("The tree is NOT a Full Binary Tree.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
