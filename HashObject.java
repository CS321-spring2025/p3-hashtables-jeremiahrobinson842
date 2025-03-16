/**
 * The HashObject class represents an object stored in a hash table.
 * It contains the key, frequency count, and probe count.
 * 
 * @author Jeremiah Robinson
 * 
 */

public class HashObject {
    private Object key;
    private int frequencyCount;
    private int probeCount;

    /**
     * Constructs a new HashObject with the specified key
     *
     * @param key the key of the hash object
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    /**
     * Returns the key of this hash object
     *
     * @return the key of this hash object
     */
    public Object getKey() {
        return key;
    }

    /**
     * Increments the frequency count of this hash object
     */
    public void incrementFrequencyCount() {
        frequencyCount++;
    }

    /**
     * Returns the frequency count of this hash object
     *
     * @return the frequency count of this hash object
     */
    public int getFrequencyCount() {
        return frequencyCount;
    }

    /**
     * Sets the probe count of this hash object
     *
     * @param probeCount the probe count to set
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * Returns the probe count of this hash object
     *
     * @return the probe count of this hash object
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Indicates whether a key is "equal to" this one
     *
     * @param obj the reference object with which to compare
     * @return true if this key is the same as the key argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashObject that = (HashObject) obj;
        return key.equals(that.key);
    }

    /**
     * Returns a string representation of the hash object
     *
     * @return a string representation of the hash object
     */
    @Override
    public String toString() {
        return key + " " + frequencyCount + " " + probeCount;
    }
}