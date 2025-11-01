import java.util.*;  // Importing all utility classes (like Scanner, HashMap, etc.)

// Class representing a single node in the Trie
class TrieNode {
    // Each node stores its child nodes using a HashMap (for dynamic character storage)
    Map<Character, TrieNode> children = new HashMap<>();
    // This flag is true if the node represents the end of a valid word
    boolean isEndOfWord = false;
}
// Main Trie class containing all operations
class Trie {
    private final TrieNode root;  // Root node of the Trie
    // Constructor to initialize the root node
    public Trie() {
        root = new TrieNode();
    }

    // ---------------------- INSERT OPERATION ----------------------
    // Inserts a word into the Trie
    public void insert(String word) {
        TrieNode current = root;  // Start from the root node
        // Loop through each character in the word
        for (char ch : word.toCharArray()) {
            // If the character does not exist, create a new node
            current.children.putIfAbsent(ch, new TrieNode());
            // Move to the child node
            current = current.children.get(ch);
        }
        // After inserting all characters, mark the end of the word
        current.isEndOfWord = true;
        System.out.println("Inserted: " + word);
    }

    // ---------------------- SEARCH OPERATION ----------------------
    // Searches for a word in the Trie
    public boolean search(String word) {
        TrieNode current = root;  // Start from the root

        // Loop through each character
        for (char ch : word.toCharArray()) {
            // If any character is missing, word doesn’t exist
            if (!current.children.containsKey(ch))
                return false;

            // Move to next character node
            current = current.children.get(ch);
        }

        // Return true only if the last node marks end of word
        return current.isEndOfWord;
    }

    // ---------------------- DELETE OPERATION ----------------------
    // Deletes a word from the Trie
    public void delete(String word) {
        if (deleteRec(root, word, 0))
            System.out.println("Deleted: " + word);
        else
            System.out.println("Word not found: " + word);
    }
    // Recursive helper function for deletion
    private boolean deleteRec(TrieNode current, String word, int index) {
        // Base case: end of the word
        if (index == word.length()) {
            // Word not found
            if (!current.isEndOfWord)
                return false;
            // Unmark the end of word
            current.isEndOfWord = false;
            // If no child nodes, this node can be deleted
            return current.children.isEmpty();
        }

        // Get current character
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        // If node doesn't exist, word not found
        if (node == null)
            return false;
        // Recursive call for next character
        boolean shouldDeleteCurrentNode = deleteRec(node, word, index + 1);
        // If child node can be deleted, remove mapping
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            // Return true if no children and not end of another word
            return current.children.isEmpty() && !current.isEndOfWord;
        }
        return false;
    }

    // ---------------------- DISPLAY ALL WORDS ----------------------
    // Prints all stored words in the Trie
    public void displayAllWords() {
        List<String> words = new ArrayList<>();  // List to store all words
        collectWords(root, "", words);           // Start recursive collection
        System.out.println("Words in Trie:");
        for (String word : words)
            System.out.println(word);
    }

    // Helper function to recursively collect all words
    private void collectWords(TrieNode node, String prefix, List<String> words) {
        // If current node marks end of a word, add it to list
        if (node.isEndOfWord)
            words.add(prefix);

        // Traverse through all children
        for (char ch : node.children.keySet())
            collectWords(node.children.get(ch), prefix + ch, words);
    }

    // ---------------------- DISPLAY TRIE STRUCTURE ----------------------
    // Prints the hierarchical structure of the Trie
    public void displayTrieStructure() {
        System.out.println("\nTrie Structure:");
        displayTrieRec(root, "", true, '\0');  // Start from root node
    }

    // Recursive helper function to print the Trie in tree form
    private void displayTrieRec(TrieNode node, String prefix, boolean isLast, char ch) {
        // Print the current node
        if (ch == '\0')
            System.out.println("└── (root)");  // Root node (no character)
        else {
            System.out.print(prefix);            // Indentation
            System.out.print(isLast ? "└── " : "├── ");  // Branch style
            System.out.print(ch);                // Character
            if (node.isEndOfWord)
                System.out.print(" (*)");        // Mark end of word
            System.out.println();
        }

        // Prepare prefix for the next level
        String newPrefix = (ch == '\0') ? "    " : prefix + (isLast ? "    " : "│   ");

        // Sort children alphabetically for clean display
        List<Character> keys = new ArrayList<>(node.children.keySet());
        Collections.sort(keys);

        // Recursively print each child
        for (int i = 0; i < keys.size(); i++) {
            char nextChar = keys.get(i);
            displayTrieRec(node.children.get(nextChar), newPrefix, i == keys.size() - 1, nextChar);
        }
    }
}

// ---------------------- MAIN PROGRAM ----------------------
public class tries_hashmaps {
    public static void main(String[] args) {
        Trie trie = new Trie();         // Create a Trie object
        Scanner sc = new Scanner(System.in);  // For user input
        int choice;                     // To store user menu choice

        // Loop until user chooses to exit
        do {
            // Display menu
            System.out.println("\n--- TRIE OPERATIONS MENU ---");
            System.out.println("1. Insert a word");
            System.out.println("2. Search a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Display all words");
            System.out.println("5. Display Trie structure");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Insert operation
                    System.out.print("Enter word to insert: ");
                    trie.insert(sc.nextLine());
                    break;

                case 2:
                    // Search operation
                    System.out.print("Enter word to search: ");
                    String searchWord = sc.nextLine();
                    if (trie.search(searchWord))
                        System.out.println("Word found: " + searchWord);
                    else
                        System.out.println("Word not found: " + searchWord);
                    break;

                case 3:
                    // Delete operation
                    System.out.print("Enter word to delete: ");
                    trie.delete(sc.nextLine());
                    break;

                case 4:
                    // Display all words
                    trie.displayAllWords();
                    break;

                case 5:
                    // Display trie structure
                    trie.displayTrieStructure();
                    break;

                case 6:
                    // Exit option
                    System.out.println("Exiting...");
                    break;

                default:
                    // Invalid input
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);  // Continue until user exits
        sc.close();  // Close scanner
    }
}