/**
 * The HashObject class represents a key-value pair stored in a hashtable.
 * It includes a frequency count to track duplicates and a probe count.
 *
 * @param <K> the type of keys maintained by this object
 * @param <V> the type of mapped values
 * 
 * @author Jeremiah Robinson
 */
public class HashObject<K, V> {
    private K key;
    private V value;
    private int frequencyCount;
    private int probeCount;
    private boolean deleted;

    /**
     * Constructs a new HashObject with the specified key and value
     *
     * @param key the key associated with this object
     * @param value the value associated with this object
     */
    public HashObject(K key, V value) {
        this.key = key;
        this.value = value;
        this.frequencyCount = 1;
        this.probeCount = 0;
        this.deleted = false;
    }

    /**
     * Returns the key associated with this object.
     *
     * @return the key associated with this object
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value associated with this object
     *
     * @return the value associated with this object
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value associated with this object
     *
     * @param value the new value to be associated with this object
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Returns the frequency count of this object
     *
     * @return the frequency count of this object
     */
    public int getFrequencyCount() {
        return frequencyCount;
    }

    /**
     * Increments the frequency count of this object
     */
    public void incrementFrequencyCount() {
        this.frequencyCount++;
    }

    /**
     * Returns the probe count of this object
     *
     * @return the probe count of this object
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Sets the probe count of this object
     *
     * @param probeCount the new probe count to be set
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * Returns whether this object is marked as deleted
     *
     * @return true if this object is marked as deleted, false otherwise
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks this object as deleted
     */
    public void delete() {
        this.deleted = true;
    }

    /**
     * Compares this object to the specified object. The result is true if and only if 
     * the argument is not null and is a HashObject object that contains the same key as this object.
     *
     * @param obj the object to compare this HashObject against
     * @return true if the given object represents a HashObject equivalent to this object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashObject<?, ?> that = (HashObject<?, ?>) obj;
        return key.equals(that.key);
    }

    /**
     * Returns the hash code value for this object, computed from the key
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Returns a string representation of this object
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "HashObject{" +
                "key=" + key +
                ", value=" + value +
                ", frequencyCount=" + frequencyCount +
                ", probeCount=" + probeCount +
                ", deleted=" + deleted +
                '}';
    }
}