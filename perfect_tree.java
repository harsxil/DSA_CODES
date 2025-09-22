import java.util.*;

// Class representing a single node in the binary tree
class Node {
    int data;      // Stores the value of the node
    Node left;     // Left child
    Node right;    // Right child

    Node(int item) {
        data = item;
        left = right = null;
    }
}

// Class to handle binary tree operations
class BinaryTree {
    Node root; // Root node of the tree
    Scanner sc = new Scanner(System.in); // Scanner for user input

    // Method to build the tree by taking user input
    public void buildTree() {
        System.out.print("Enter the root node value: ");
        int rootData = sc.nextInt();
        root = new Node(rootData);

        while (true) {
            System.out.print("Do you want to insert another node? (yes/no): ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("no")) break;

            System.out.print("Enter the new node value: ");
            int newData = sc.nextInt();

            System.out.print("Enter the parent node value: ");
            int parentData = sc.nextInt();

            System.out.print("Insert as left or right child of " + parentData + "? (left/right): ");
            String dir = sc.next();

            Node parentNode = findNode(root, parentData); // Search for parent recursively
            if (parentNode == null) {
                System.out.println("Parent node not found! Try again.");
                continue;
            }

            Node newNode = new Node(newData);
            switch (dir.toLowerCase()) {
                case "left":
                    if (parentNode.left == null) {
                        parentNode.left = newNode;
                    } else {
                        System.out.println("Left child already exists!");
                    }
                    break;

                case "right":
                    if (parentNode.right == null) {
                        parentNode.right = newNode;
                    } else {
                        System.out.println("Right child already exists!");
                    }
                    break;

                default:
                    System.out.println("Invalid direction! Use 'left' or 'right'.");
            }
        }
    }

    // Recursive search for a node by its value
    private Node findNode(Node current, int value) {
        if (current == null) return null;
        if (current.data == value) return current;

        Node leftResult = findNode(current.left, value);
        if (leftResult != null) return leftResult;

        return findNode(current.right, value);
    }

    // Helper method to find the depth of the leftmost node
    private int findDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }

    // Recursive method to check if the tree is a perfect binary tree
    private boolean isPerfect(Node node, int depth, int level) {
        if (node == null) return true;

        if (node.left == null && node.right == null)
            return (depth == level + 1);

        if (node.left == null || node.right == null)
            return false;

        return isPerfect(node.left, depth, level + 1) &&
                isPerfect(node.right, depth, level + 1);
    }

    // Wrapper method
    public boolean checkPerfectTree() {
        int depth = findDepth(root);
        return isPerfect(root, depth, 0);
    }

    // Inorder traversal
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }
}

// Main class
public class perfect_tree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildTree();

        System.out.println("\nInorder Traversal of the Tree:");
        tree.inorder(tree.root);

        if (tree.checkPerfectTree()) {
            System.out.println("\n\nThe tree is a Perfect Binary Tree ✔");
        } else {
            System.out.println("\n\nThe tree is NOT a Perfect Binary Tree ❌");
        }
    }
}
