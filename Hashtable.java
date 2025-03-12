import java.util.Arrays;

/**
 * The Hashtable class represents a generic hash table with open addressing and provides basic functionality
 *
 * @param <K> the type of keys maintained by this hashtable
 * @param <V> the type of mapped values
 * 
 * @author Jeremiah Robinson
 */
public abstract class Hashtable<K, V> {
    protected HashObject<K, V>[] table;
    protected int size;
    protected int capacity;

    /**
     * Constructs a new Hashtable with the specified capacity
     *
     * @param capacity the capacity of the hashtable
     */
    public Hashtable(int capacity) {
        this.capacity = capacity;
        this.table = new HashObject[capacity];
        this.size = 0;
    }

    /**
     * Probes the hashtable to find an index for the given key and probe number
     *
     * @param key the key to be hashed
     * @param i the probe number
     * @return the index in the hashtable for the given key and probe number
     */
    public abstract int probe(K key, int i);

    /**
     * Inserts the specified key-value pair into the hashtable
     * If the key already exists, updates the value and increments the frequency count
     *
     * @param key the key to be inserted
     * @param value the value to be associated with the key
     * @throws RuntimeException if the hashtable is full
     */
    public void insert(K key, V value) {
        int i = 0;
        int index;
        do {
            index = probe(key, i);
            if (table[index] == null || table[index].isDeleted()) {
                table[index] = new HashObject<>(key, value);
                size++;
                return;
            } else if (table[index].getKey().equals(key)) {
                table[index].setValue(value);
                table[index].incrementFrequencyCount();
                return;
            }
            i++;
        } while (i < capacity);
        throw new RuntimeException("Hashtable is full");
    }

    /**
     * Searches for the value associated with the specified key in the hashtable
     *
     * @param key the key to be searched
     * @return the value associated with the key, or null if the key is not found
     */
    public V search(K key) {
        int i = 0;
        int index;
        do {
            index = probe(key, i);
            if (table[index] == null) {
                return null;
            } else if (table[index].getKey().equals(key) && !table[index].isDeleted()) {
                return table[index].getValue();
            }
            i++;
        } while (i < capacity);
        return null;
    }

    /**
     * Deletes the key-value pair associated with the specified key from the hashtable
     *
     * @param key the key to be deleted
     */
    public void delete(K key) {
        int i = 0;
        int index;
        do {
            index = probe(key, i);
            if (table[index] == null) {
                return;
            } else if (table[index].getKey().equals(key) && !table[index].isDeleted()) {
                table[index].delete();
                size--;
                return;
            }
            i++;
        } while (i < capacity);
    }

    /**
     * Returns the number of key-value pairs in the hashtable
     *
     * @return the number of key-value pairs in the hashtable
     */
    public int size() {
        return size;
    }

    /**
     * Returns the capacity of the hashtable
     *
     * @return the capacity of the hashtable
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns whether the hashtable is empty
     *
     * @return true if the hashtable is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the hashtable, removing all key-value pairs
     */
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }
}