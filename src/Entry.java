public class Entry<V, K> {
    private V value;
    private K key;

    public Entry(V value, K key) {
        this.value = value;
        this.key = key;
    }

    public Entry(Entry<V, K> entry) {
        this.key = entry.key;
        this.value = entry.value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    protected void setKey(K key) {
        this.key = key;
    }

    protected void setValue(V value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "{" + "key=" + key + ", value=" + value + '}';
    }

}
