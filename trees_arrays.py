import java.util.*;

class TrieNode {

    TrieNode[] children = new TrieNode[26]; // keeps an array for 26 children
    boolean isEndOfWord;

    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }
}

class tries_arrays {
    private   TrieNode root;

    Trie () {
        root = new TrieNode();
    }

    // ------------------- INSERT OPERATION -------------------
    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();

            node = node.children[index];
        }

        node.isEndOfWord = true;
        System.out.println("Inserted: " + word);
    }

    // ------------------- SEARCH OPERATION -------------------
    boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null)
                return false;

            node = node.children[index];
        }

        return node.isEndOfWord;
    }

    // ------------------- DELETE OPERATION -------------------

    boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {

        if (node == null)
            return false;

        if (depth == word.length()) {

            if (!node.isEndOfWord)
                return false;

            node.isEndOfWord = false;

            return isEmpty(node);
        }

        int index = word.charAt(depth) - 'a';
        if (deleteHelper(node.children[index], word, depth + 1)) {
            node.children[index] = null;

            return !node.isEndOfWord && isEmpty(node);
        }
        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null)
                return false;
        }
        return true;
    }

    // ------------------- DISPLAY ALL WORDS -------------------

    void displayAllWords() {
        System.out.println("\nWords in the Trie:");
        displayHelper(root, "");
    }
    private void displayHelper(TrieNode node, String prefix) {

        if (node.isEndOfWord)
            System.out.println(prefix);

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                displayHelper(node.children[i], prefix + (char) (i + 'a'));
            }
        }
    }

    // ------------------- DISPLAY TRIE STRUCTURE -------------------

    void displayTrieStructure() {
        System.out.println("\nTrie Structure:");
        printTrie(root, "", "(root)");
    }

    private void printTrie(TrieNode node, String indent, String currentChar) {

        System.out.println(indent + currentChar + (node.isEndOfWord ? " [word end]" : ""));

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char ch = (char) (i + 'a');
                printTrie(node.children[i], indent + "   ", "-> " + ch);
            }
        }
    }
}
// ------------------- MAIN CLASS -------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie trie = new Trie();
        int choice;
        do {

            System.out.println("\n--- TRIE OPERATIONS MENU ---");
            System.out.println("1. Insert a word");
            System.out.println("2. Search a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Display all words");
            System.out.println("5. Display Trie structure");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: System.out.print("Enter word to insert: ");
                    String wordInsert = sc.nextLine().toLowerCase();
                    trie.insert(wordInsert);
                    break;
                case 2: System.out.print("Enter word to search: ");
                    String wordSearch = sc.nextLine().toLowerCase();
                    System.out.println(trie.search(wordSearch) ? "Word found!" : "Word not found!");
                    break;
                case 3: System.out.print("Enter word to delete: ");
                    String wordDelete = sc.nextLine().toLowerCase();
                    trie.delete(wordDelete);
                    System.out.println("Deleted: " + wordDelete);
                    break;
                case 4: trie.displayAllWords();
                    break;
                case 5: trie.displayTrieStructure();
                    break;
                case 6: System.out.println("Exiting...");
                    break;
                default: System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
        sc.close();
    }
}
