import java.io.IOException;
import java.lang.Math;

/**
 * Takes in two files then finds the count for each word, compares
 * the count for each word for both files if both files have the same
 * word by subtracting them then taking the absolute value.
 * if the difference is greater than 20, then the word gets added
 * into an array of datacounts that can be analyzed by the user
 * to determine keywords to correlate from.
 */
public class Keywords {
    private static DataCount<String>[] countWords(String file, String file2) {
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

        //file2

        DataCounter<String> counter2 = new AVLTree<String>();

        try {
            FileWordReader reader = new FileWordReader(file2);
            String word = reader.nextWord();
            while (word != null) {
                counter2.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }

        DataCount<String>[] counts2 = counter2.getCounts();
        sortByDescendingCount(counts2);

        DataCount<String>[] keyword = new DataCount[5000];
        int k = 0;
        for (int i = 0; i < counts.length; i++){
            for(int j = 0; j < counts2.length; j++){
                if (counts[i].data.equalsIgnoreCase(counts2[j].data)){
                    if(counts[i].count > 1){
                        counts[i].count -= counts2[j].count;
                        counts[i].count = Math.abs(counts[i].count);
                        if(counts[i].count > 29){
                            keyword[k] = counts[i];
                            k++;
                        }
                    }
                    break;
                }
            }
        }
        return keyword;

    }

    /**
     * TODO Replace this comment with your own.
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
        if (args.length !=2 ){
            System.err.println("Not the right amount of args:\njava Keywords <filename1> <filename2>");
            System.exit(1);
        }


        if( !(args[0].contains(".txt"))) {
            System.err.println("Not a valid .txt file or directory");
            System.exit(1);
        }
        if( !(args[1].contains(".txt"))) {
            System.err.println("Not a valid .txt file or directory");
            System.exit(1);
        }

        //start

        //file1 and 2
        DataCount<String>[] file1;


        file1 = countWords(args[0], args[1]);

        for (DataCount<String> c : file1) {
            if(c != null) {
                System.out.println(c.count + " \t" + c.data);
            }
        }
    }
}
