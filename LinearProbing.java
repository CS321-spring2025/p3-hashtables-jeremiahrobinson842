/**
 * The LinearProbing class extends the Hashtable class and implements the
 * linear probing strategy for open addressing in hash tables
 *
 * @author Jeremiah Robinson
 */
public class LinearProbing extends Hashtable {

    /**
     * Constructs a new LinearProbing hashtable with the specified capacity
     *
     * @param capacity the capacity of the hashtable
     */
    public LinearProbing(int size) {
        super(size);
    }

    /**
     * Probes the hashtable using the linear probing strategy
     *
     * @param key the key to be hashed
     * @param i the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    @Override
    public int hash(Object key, int probeNum) {
        return positiveMod(h1(key) + probeNum, size);
    }
}