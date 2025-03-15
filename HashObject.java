public class HashObject {
    private Object key;
    private int frequencyCount;
    private int probeCount;

    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    public Object getKey() {
        return key;
    }

    public void incrementFrequencyCount() {
        frequencyCount++;
    }

    public int getFrequencyCount() {
        return frequencyCount;
    }

    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    public int getProbeCount() {
        return probeCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashObject that = (HashObject) obj;
        return key.equals(that.key);
    }


    @Override
    public String toString() {
        return key + " " + frequencyCount + " " + probeCount;
    }
}