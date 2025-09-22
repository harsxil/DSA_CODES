import java.util.Scanner;

public class bt_operations {
    static int[] tree;     // Array to hold tree elements
    static int size;       // Total capacity of the tree
    static int index = 0;  // Tracks next available index

    // Function to insert an element into the tree
    public static void insert(int value) {
        if (index >= size) {
            System.out.println("Tree is full. Cannot insert more elements.");
        } else {
            tree[index] = value; // Insert at next available index
            System.out.println("Inserted " + value + " at index " + index);
            index++; // Move to next index for future insertions
        }
    }

    // Function to search for an element in the tree
    public static void search(int value) {
        for (int i = 0; i < index; i++) {
            if (tree[i] == value) {
                System.out.println("Element " + value + " found at index " + i);
                return;
            }
        }
        System.out.println("Element " + value + " not found in the tree.");
    }

    // Function to delete an element from the tree
    public static void delete(int value) {
        int i;
        for (i = 0; i < index; i++) {
            if (tree[i] == value) {
                break; // Element found at index i
            }
        }

        if (i == index) {
            System.out.println("Element " + value + " not found. Cannot delete.");
            return;
        }

        // Replace deleted element with last element in the tree
        tree[i] = tree[index - 1];
        index--; // Reduce the size of the tree
        System.out.println("Element " + value + " deleted successfully.");
    }

    // Function to display the current tree
    public static void display() {
        System.out.print("Current Tree (Level Order): ");
        for (int i = 0; i < index; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    // Main driver function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of binary tree (array size): ");
        size = sc.nextInt();   // Take total size of tree
        tree = new int[size];  // Initialize array

        while (true) {
            System.out.println("\n--- Binary Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); // Take user choice

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int valInsert = sc.nextInt();
                    insert(valInsert);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    int valSearch = sc.nextInt();
                    search(valSearch);
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    int valDelete = sc.nextInt();
                    delete(valDelete);
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}