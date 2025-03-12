/**
 * The LinearProbing class extends the Hashtable class and implements the
 * linear probing strategy for open addressing in hash tables
 *
 * @param <K> the type of keys maintained by this hashtable
 * @param <V> the type of mapped values
 * 
 * @author Jeremiah Robinson
 */
public class LinearProbing<K, V> extends Hashtable<K, V> {

    /**
     * Constructs a new LinearProbing hashtable with the specified capacity
     *
     * @param capacity the capacity of the hashtable
     */
    public LinearProbing(int capacity) {
        super(capacity);
    }

    /**
     * Probes the hashtable using the linear probing strategy
     *
     * @param key the key to be hashed
     * @param i the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    @Override
    public int probe(K key, int i) {
        return (key.hashCode() + i) % capacity;
    }
}