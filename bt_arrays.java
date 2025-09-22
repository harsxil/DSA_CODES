 // Import the Scanner class to take user input
import java.util.Scanner;

public class bt_arrays{
    public static void main(String[] args) {
        // Create a Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Define maximum size of the binary tree array
        int MAX = 100; // This means our array tree can hold up to 100 nodes

        // Create a String array to represent the tree nodes
        String[] tree = new String[MAX];

        // Ask the user how many nodes they want to insert
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt(); // Read the number of nodes
        sc.nextLine(); // Clear the newline character from the buffer

        // Loop to insert each node value into the array
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value for node " + i + ": ");
            tree[i] = sc.nextLine(); // Store the node value at index i
        }

        // Display the full binary tree as stored in array (level-order)
        System.out.println("\nBinary Tree in Array Representation (Level Order):");
        for (int i = 0; i < n; i++) {
            System.out.println("Index " + i + ": " + tree[i]);
        }

        // Display parent-child relationships using index formula
        System.out.println("\nParent-Child Relationships:");
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;  // Index of left child
            int right = 2 * i + 2; // Index of right child

            System.out.println("Node: " + tree[i]);

            // If left child index is within range, print it
            if (left < n) {
                System.out.println("  Left Child: " + tree[left]);
            }

            // If right child index is within range, print it
            if (right < n) {
                System.out.println("  Right Child: " + tree[right]);
            }
        }
    }
}