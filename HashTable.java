import java.lang.Math;
/**
 * I implemented a Hashtable using a hashcode function that
 * changes each char into its ascii value and adds them to
 * a running sum to be hashed.
 *
 * I am using a quadratic probing method to insert each data object
 * containing the data and count
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */
public class HashTable implements DataCounter<String> {
    protected hashNode[] table;
    protected int size;

    public HashTable(){
        table = new hashNode[15001];
        int size = 0;
    }

    /** {@inheritDoc} */
    public DataCount<String>[] getCounts() {
        // TODO Auto-generated method stub
        DataCount<String>[] counts = new DataCount[size];
        int j = 0;
        for(int i = 0; i < 15001; i++){
            if (table[i] != null) {
                counts[j] = new DataCount<String>(table[i].data, table[i].count);
                j++;
            }
        }
        return counts;
    }

    /** {@inheritDoc} */
    public int getSize() {
        // TODO Auto-generated method stub
        return size;
    }

    /** {@inheritDoc} */
    public void incCount(String data) {
        // TODO Auto-generated method stub
        int hashcode = hashCode(data);
        int i = 0;
        int position = 0;
        while (true){
            position = (hashcode + (int)Math.pow(i,2)) % 15001;
            if(table[position] == null){
                table[position] = new hashNode(data);
                break;
            }
            else if(table[position].data.compareTo(data) == 0){
                table[position].count++;
                break;
            }
            i++;
        }

    }
    private int hashCode(String s) {
        int hash = (int) s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            hash += (int) s.charAt(i);
        }
        return hash;
    }

    protected class hashNode{
        public String data;
        public int count;
        public hashNode(String data){
            this.data = data;
            count = 1;
            size++;
        }
    }
}

class testHash {
    public static void main(String[] args) {
        System.out.println(hashCode("hello"));
        int hash = 'h' + 'e' + 'l' + 'l' + 'o';
        System.out.println(hash);
    }

    public static int hashCode(String s) {
        int hash = (int) s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            hash += (int) s.charAt(i);
        }
        return hash;
    }
}
