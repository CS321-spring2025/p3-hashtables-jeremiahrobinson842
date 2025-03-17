/**
 * The DoubleHashing class extends the Hashtable class and implements the
 * double hashing strategy for open addressing in hash tables
 * 
 * @author Jeremiah Robinson
 */
public class DoubleHashing extends Hashtable {

    /**
     * Constructs a new DoubleHashing hashtable with the specified capacity
     *
     * @param capacity the capacity of the hashtable
     */
    public DoubleHashing(int size) {
        super(size);
    }

    /**
     * Computes the secondary hash function
     *
     * @param key the key to be hashed
     * @return the result of the secondary hash function
     */
    private int h2(Object key) {
        return 1 + positiveMod(key.hashCode(), size - 2);
    }

    /**
     * Probes the hashtable using the double hashing strategy
     *
     * @param key the key to be hashed
     * @param i the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    @Override
    public int hash(Object key, int probeNum) {
        return positiveMod(h1(key) + probeNum * h2(key), size);
    }
}