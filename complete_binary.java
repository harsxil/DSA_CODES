import java.util.*;

// Class representing each node in the binary tree
class Node {
    int data;         // Value of the node
    Node left, right; // References to left and right child

    Node(int item) {
        data = item;
        left = right = null;
    }
}

// BinaryTree class to build the tree and check completeness
class BinaryTree {
    Node root; // Root node of the binary tree
    Scanner sc = new Scanner(System.in); // Scanner to read user input

    // Method to build the tree based on user input
    public void buildTree() {
        System.out.print("Enter the root node value: ");
        int rootData = sc.nextInt(); // Take root node input
        root = new Node(rootData);   // Create root node

        // Keep inserting nodes until the user stops
        while (true) {
            System.out.print("Do you want to insert another node? (yes/no): ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("no")) break; // Exit loop if no more insertions

            System.out.print("Enter the new node value: ");
            int newData = sc.nextInt();

            System.out.print("Enter the parent node value: ");
            int parentData = sc.nextInt();

            System.out.print("Insert as left or right child of " + parentData + "? (left/right): ");
            String dir = sc.next();

            Node parentNode = findNode(root, parentData); // Recursively search for parent
            if (parentNode == null) {
                System.out.println("Parent not found! Try again.");
                continue; // Skip to next iteration
            }

            Node newNode = new Node(newData); // Create the new node

            // Insert on the correct side if the spot is free
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
                System.out.println("Invalid direction! Please type 'left' or 'right'.");
            }
        }
    }

    // Recursive search for parent node
    private Node findNode(Node current, int value) {
        if (current == null) return null;
        if (current.data == value) return current;

        Node leftResult = findNode(current.left, value);
        if (leftResult != null) return leftResult;

        return findNode(current.right, value);
    }

    // Function to check if the tree is a complete binary tree
    public boolean isCompleteTree(Node root) {
        if (root == null) return true; // Empty tree is complete

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean end = false; // Flag if a missing child has been seen

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left != null) {
                if (end) return false; // A left child after a null means not complete
                queue.add(current.left);
            } else {
                end = true;
            }

            if (current.right != null) {
                if (end) return false; // A right child after a null means not complete
                queue.add(current.right);
            } else {
                end = true;
            }
        }
        return true;
    }

    // Method to print level-order traversal (just for clarity)
    public void levelOrder() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.print("Level Order Traversal: ");
        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
        System.out.println();
    }
}

// Main class to run the program
public class complete_binary {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); // Create tree object
        tree.buildTree();                   // Let user build the tree
        tree.levelOrder();                  // Display tree in level order

        if (tree.isCompleteTree(tree.root)) {
            System.out.println("✅ The tree is a Complete Binary Tree.");
        } else {
            System.out.println("❌ The tree is NOT a Complete Binary Tree.");
        }
    }
}
