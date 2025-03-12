/**
 * The DoubleHashing class extends the Hashtable class and implements the
 * double hashing strategy for open addressing in hash tables
 *
 * @param <K> the type of keys maintained by this hashtable
 * @param <V> the type of mapped values
 * 
 * @author Jeremiah Robinson
 */
public class DoubleHashing<K, V> extends Hashtable<K, V> {

    /**
     * Constructs a new DoubleHashing hashtable with the specified capacity
     *
     * @param capacity the capacity of the hashtable
     */
    public DoubleHashing(int capacity) {
        super(capacity);
    }

    /**
     * Computes the secondary hash function
     *
     * @param key the key to be hashed
     * @return the result of the secondary hash function
     */
    private int hash2(K key) {
        return 1 + (key.hashCode() % (capacity - 2));
    }

    /**
     * Probes the hashtable using the double hashing strategy
     *
     * @param key the key to be hashed
     * @param i the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    @Override
    public int probe(K key, int i) {
        return (key.hashCode() + i * hash2(key)) % capacity;
    }
}