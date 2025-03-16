import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The Hashtable class is an abstract class that provides the basic structure and functionality
 * for a hash table with open addressing.
 * 
 * @author Jeremiah Robinson
 */
public abstract class Hashtable {
    protected HashObject[] table;
    protected int size;

    /**
     * Constructs a new Hashtable with the specified capacity
     *
     * @param size the capacity of the hashtable
     */
    public Hashtable(int size) {
        this.size = size;
        this.table = new HashObject[size];
    }

    /**
     * Computes the hash value for the given key and probe number
     *
     * @param key the key to be hashed
     * @param probeNum the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    public abstract int hash(Object key, int probeNum);

    /**
     * Computes the primary hash function
     *
     * @param key the key to be hashed
     * @return the result of the primary hash function
     */
    protected int h1(Object key) {
        return positiveMod(key.hashCode(), size);
    }

    /**
     * Inserts the specified key into the hashtable
     *
     * @param key the key to be inserted
     * @return the number of probes required to insert the key, or -1 if the key is a duplicate or the table is full
     */
    public int insert(Object key) {
        int probeCount = 0;
    
        for (int i = 0; i < size; i++) {
            int index = hash(key, i);
            if (table[index] == null) {
                table[index] = new HashObject(key);
                table[index].setProbeCount(probeCount + 1);
                System.out.println("Inserted key: " + key + " at index: " + index + " with probe count: " + (probeCount + 1));
                return probeCount + 1;
            } else if (table[index].getKey().equals(key)) {
                table[index].incrementFrequencyCount();
                System.out.println("Duplicate key: " + key + " found at index: " + index + ", incrementing frequency count.");
                return -1; // Indicate duplicate
            }
            probeCount++;
        }
    
        System.out.println("Table is full, could not insert key: " + key);
        return -1; // Table is full
    }
    
    /**
     * Searches for the specified key in the hashtable
     *
     * @param key the key to be searched for
     * @return the number of probes required to find the key, or -1 if the key is not found
     */
    public int search(Object key) {
        for (int i = 0; i < size; i++) {
            int index = hash(key, i);
            if (table[index] == null) {
                return -1; // Key not found
            } else if (table[index].getKey().equals(key)) {
                System.out.println("Duplicate found for key: " + key + ", incrementing frequency count.");
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Computes the positive modulus of the given dividend and divisor
     *
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the positive modulus
     */
    protected int positiveMod(int dividend, int divisor) {
        int remainder = dividend % divisor;
        if (remainder < 0) {
            remainder += divisor;
        }
        return remainder;
    }

    /**
     * Returns the total number of duplicate keys in the hashtable
     *
     * @return the total number of duplicate keys
     */
    public int getDuplicateCount() {
        int duplicateCount = 0;
        for (HashObject obj : table) {
            if (obj != null && obj.getFrequencyCount() > 1) {
                duplicateCount += obj.getFrequencyCount() - 1;
            }
        }
        return duplicateCount;
    }

    /**
     * Returns the total number of insertions (probes) in the hashtable
     *
     * @return the total number of insertions
     */
    public int getInsertionCount() {
        int insertionCount = 0;
        for (HashObject obj : table) {
            if (obj != null) {
                insertionCount += obj.getProbeCount();
            }
        }
        return insertionCount;
    }

    /**
     * Dumps the contents of the hashtable to the specified file
     *
     * @param fileName the name of the file to dump the contents to
     * @throws FileNotFoundException if the file cannot be created or opened
     */
    public void dumpToFile(String fileName) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    HashObject hashObj = table[i];
                    out.println("table[" + i + "]: " + hashObj);
                    System.out.println("Writing to file: table[" + i + "]: " + hashObj);
                }
            }
        }
    }
}