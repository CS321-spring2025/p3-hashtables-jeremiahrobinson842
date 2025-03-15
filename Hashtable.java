import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Hashtable {
    protected HashObject[] table;
    protected int size;

    public Hashtable(int size) {
        this.size = size;
        this.table = new HashObject[size];
    }

    public abstract int hash(Object key, int probeNum);

    protected int h1(Object key) {
        return positiveMod(key.hashCode(), size);
    }

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

    protected int positiveMod(int dividend, int divisor) {
        int remainder = dividend % divisor;
        if (remainder < 0) {
            remainder += divisor;
        }
        return remainder;
    }

    public int getDuplicateCount() {
        int duplicateCount = 0;
        for (HashObject obj : table) {
            if (obj != null && obj.getFrequencyCount() > 1) {
                duplicateCount += obj.getFrequencyCount() - 1;
            }
        }
        return duplicateCount;
    }

    public int getInsertionCount() {
        int insertionCount = 0;
        for (HashObject obj : table) {
            if (obj != null) {
                insertionCount += obj.getProbeCount();
            }
        }
        return insertionCount;
    }

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