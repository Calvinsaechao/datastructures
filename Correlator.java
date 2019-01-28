/**
 * Created by Calvin on 4/8/18.
 */
import java.io.IOException;
import java.lang.Math;
public class Correlator {
    private static DataCount<String>[] countWords(String file, DataCount<String>[] keywords) {
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
        DataCount<String>[] keyword = new DataCount[keywords.length];
        for (int i = 0; i < keywords.length; i++) {
            for (int j = 0; i < counts.length; j++) {
                if (keywords[i].data.equalsIgnoreCase(counts[j].data)) {
                    keywords[i].count = counts[j].count;
                    keyword[i] = new DataCount<String>(keywords[i].data, keywords[i].count);
                    break;
                }
            }
        }
        return keyword;

    }
    private static DataCount<String>[] countWordsBinary(String file, DataCount<String>[] keywords) {
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
        DataCount<String>[] keyword = new DataCount[keywords.length];
        for (int i = 0; i < keywords.length; i++) {
            for (int j = 0; i < counts.length; j++) {
                if (keywords[i].data.equalsIgnoreCase(counts[j].data)) {
                    keywords[i].count = counts[j].count;
                    keyword[i] = new DataCount<String>(keywords[i].data, keywords[i].count);
                    break;
                }
            }
        }
        return keyword;

    }
    private static DataCount<String>[] countWordsHash(String file, DataCount<String>[] keywords) {
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
        DataCount<String>[] keyword = new DataCount[keywords.length];
        for (int i = 0; i < keywords.length; i++) {
            for (int j = 0; i < counts.length; j++) {
                if (keywords[i].data.equalsIgnoreCase(counts[j].data)) {
                    keywords[i].count = counts[j].count;
                    keyword[i] = new DataCount<String>(keywords[i].data, keywords[i].count);
                    break;
                }
            }
        }
        return keyword;

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
        boolean BST = false, AVL = false, HASH = false;
        if (args.length != 3 ){
            System.err.println("Not enough args:\njava Correlator [ -b | -a | -h ] <filename1> <filename2>");
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


        if( !(args[1].contains(".txt"))) {
            System.err.println("Not a valid .txt file or directory");
            System.exit(1);
        }
        if( !(args[2].contains(".txt"))) {
            System.err.println("Not a valid .txt file or directory");
            System.exit(1);
        }

        //start

        long ED = 0;

        //file1 and 2
        DataCount<String>[] file1;
        DataCount<String>[] file2;

        //words
        DataCount<String>[] keywords = new DataCount[]{(new DataCount<String>("thou", 0)), (new DataCount<String>("for", 0)), (new DataCount<String>("ye", 0)), (new DataCount<String>("your", 0)), (new DataCount<String>("shall", 0)), (new DataCount<String>("may", 0)), (new DataCount<String>("how", 0)), (new DataCount<String>("must", 0)), (new DataCount<String>("hath", 0)), (new DataCount<String>("thee", 0))};
        System.out.print("Keywords used:\n| ");
        for (DataCount<String> c : keywords)
            System.out.print(c.data + " | ");
        System.out.println();

        if (AVL) {
            file1 = countWords(args[1], keywords);
            file2 = countWords(args[2], keywords);
        } else if (BST) {
            file1 = countWordsBinary(args[1], keywords);
            file2 = countWordsBinary(args[2], keywords);
        } else if (HASH) {
            file1 = countWordsHash(args[1], keywords);
            file2 = countWordsHash(args[2], keywords);
        }
        else{
            file1 = countWords(args[1], keywords);
            file2 = countWords(args[2], keywords);
        }

        //finds Euclidean distance
        for (int i = 0; i < file1.length; i++){
            for(int j = 0; j < file2.length; j++){
                if(file1[i].data.equalsIgnoreCase(file2[j].data)){
                    ED += Math.pow((file1[i].count - file2[j].count), 2);
                    break;
                }
            }
        }
        //print correlation
        System.out.println("Euclidean Distance: " + ED);
    }
}
