import java.io.IOException;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

    private static void countWords(String file) {
        DataCounter<String> counter = new AVLTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        for (DataCount<String> c : counts)
            System.out.println(c.count + " \t" + c.data);
    }

    private static void countWordsBinary(String file) {
        DataCounter<String> counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        for (DataCount<String> c : counts)
            System.out.println(c.count + " \t" + c.data);
    }
    private static void countWordsHash(String file) {
        DataCounter<String> counter = new HashTable();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        for (DataCount<String> c : counts)
            System.out.println(c.count + " \t" + c.data);
    }
    private static void sizeh(String file) {
        DataCounter<String> counter = new HashTable();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        System.out.println(counter.getSize());
    }
    private static void sizeb(String file) {
        DataCounter<String> counter = new BinarySearchTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        System.out.println(counter.getSize());
    }
    private static void sizea(String file) {
        DataCounter<String> counter = new AVLTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts = counter.getCounts();
        System.out.println(counter.getSize());
    }

    /**
     *
     * 
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. You should modify it to use a different
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (counts[j].count >= x.count) {
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }

    public static void main(String[] args) {

        boolean BST = false, AVL = false, HASH = false,
                freq = false, num = false;
        if (args.length != 3 ){
            System.err.println("Not the right amount of args:\njava WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>");
            System.exit(1);
        }
        if(!(args[0].equalsIgnoreCase("-a") || args[0].equalsIgnoreCase("-b") || args[0].equalsIgnoreCase("-h"))){
            System.err.println("[ -b | -a | -h ]");
            System.exit(1);
        }
        else if(args[0].equalsIgnoreCase("-b")){
            BST = true;
        }
        else if(args[0].equalsIgnoreCase("-h")){
            HASH = true;
        }
        else if(args[0].equalsIgnoreCase("-a")){
            AVL = true;
        }

        if(!(args[1].equalsIgnoreCase("-frequency") || args[1].equalsIgnoreCase("-num_unique"))) {
            System.err.println("[ -frequency | -num_unique ]");
            System.exit(1);
        }
        else if(args[1].equalsIgnoreCase("-frequency")){
            freq = true;
        }
        else if(args[1].equalsIgnoreCase("-num_unique")){
            num = true;
        }
        if( !(args[2].contains(".txt"))) {
            System.err.println("Not a valid .txt file");
            System.exit(1);
        }

        //start
        if(freq) {
            if (AVL) {
                countWords(args[2]);
            } else if (BST) {
                countWordsBinary(args[2]);
            } else if (HASH) {
                countWordsHash(args[2]);
            }
        }
        else if(num){
            if (AVL) {
                sizea(args[2]);
            } else if (BST) {
                sizeb(args[2]);
            } else if (HASH) {
                sizeh(args[2]);
            }
        }
    }
}
