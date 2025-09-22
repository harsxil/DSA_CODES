public class bt_height {
    // Define the class for tree nodes
    static class Node {
        int data;         // Data in the node
        Node left;        // Left child
        Node right;       // Right child

        // Constructor to initialize a node
        Node(int value) {
            data = value;
            left = null;
            right = null;
        }
    }
    // Function to compute height of a binary tree
    public static int height(Node root) {
        // Base case: if node is null, return -1 (height in terms of edges)
        if (root == null) {
            return -1;
        }

        // Recursively compute height of left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Height of tree = 1 + max of left and right subtree heights
        return 1 + Math.max(leftHeight, rightHeight);
    }
    // Main function to test the program
    public static void main(String[] args) {
        // Create a binary tree
    /*
           1
          / \
         2   3
        /
       4
    */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);

        // Print height
        System.out.println("Height of the binary tree: " + height(root));
    }
}
