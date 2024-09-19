import java.util.Comparator;

public class SPQ<V, K> {
    // The array that holds the entries
    private Entry<V, K>[] a;

    // Holds the position of the next entry to be inserted in the heap
    private int nextEntry = 0;

    // State 1 represents max, -1 min
    // The logic is that multiplying the result of the comparator by state inverts the input or not depending on the need
    private byte state;

    // Variable to hold the priority queue's comparator
    private Comparator<K> comparator;

    // Constructor with comparator
    public SPQ(Comparator<K> comparator) {
        a = (Entry<V, K>[])(new Entry[1]);
        this.comparator = comparator;
        this.state = 1;
    }

    // Constructor with comparator and size
    public SPQ(Comparator<K> comparator, int capacity) {
        a = (Entry<V, K>[])(new Entry[capacity]);
        this.comparator = comparator;
        this.state = 1;
    }

    // Constructor with comparator, state
    public SPQ(Comparator<K> comparator, byte state) {
        a = (Entry<V, K>[])(new Entry[1]);
        this.comparator = comparator;
        this.state = state;
    }
    

    // Constructor with comparator, state, and size
    public SPQ(Comparator<K> comparator, int capacity, byte state) {
        a = (Entry<V, K>[])(new Entry[capacity]);
        this.comparator = comparator;
        this.state = state;
    }

    // Returns the size of the queue
    public int getSize() {
        return nextEntry;
    }

    // Expands the array by doubling the size, making the amortized time complexity O(1)
    private void expand() {
        System.out.println("expanded");
        Entry<V, K>[] newPQ = (Entry<V, K>[])(new Entry[a.length * 2]);

        for (int i = 0; i < getSize(); i++) {
            newPQ[i] = a[i];
        }

        a = newPQ;
    }

    // Used to find the parent of a node
    private int parent(int position) {
        return (position % 2 == 0) ? (position - 2) / 2 : (position - 1) / 2;
    }

    // Used to find the left child of a node
    int leftChild(int position) {
        return position * 2 + 1;
    }

    // Used to find the right child of a node
    int rightChild(int position) {
        return position * 2 + 2;
    }

    // Swaps two entries, used in downHeap and upHeap
    void swap(int i, int j) {
        Entry<V, K> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Upheap function to restore order in the heap
    private void upHeap(int position) {
        // Will always swap if the comparison result shows necessity
        while (position != 0 && comparator.compare(a[parent(position)].getKey(), a[position].getKey()) * state < 0) {
            swap(position, parent(position));
            position = parent(position);
        }
    }

    // Downheap is used to restore correct hierarchy
    void downHeap(int position) {
        // Checks if the parent can be swapped with a child
        while (leftChild(position) < a.length && leftChild(position) < nextEntry) {
            int downHeapPosition = 0;

            // Check which child the parent should be swapped with, if any
            if (comparator.compare(a[position].getKey(), a[leftChild(position)].getKey()) * state < 0) {
                downHeapPosition = leftChild(position);
            }

            if (rightChild(position) < a.length && rightChild(position) < nextEntry && comparator.compare(a[position].getKey(), a[rightChild(position)].getKey()) * state < 0) {
                if (downHeapPosition == 0) downHeapPosition = rightChild(position);
                else if (comparator.compare(a[downHeapPosition].getKey(), a[rightChild(position)].getKey()) * state < 0) {
                    downHeapPosition = rightChild(position);
                }
            }

            if (downHeapPosition != 0) {
                swap(position, downHeapPosition);
                position = downHeapPosition;
            } else break;
        }
    }

    // Insert an entry in the queue
    public void insert(V value, K key) {
        if (nextEntry == a.length) expand();

        a[nextEntry] = new Entry<>(value, key);

        upHeap(nextEntry);

        nextEntry++;
    }

    // Change the state of the queue from max to min and vice versa
    public void toggle() {
        state *= -1;

        // Rebuilds the heap in place
        for (int i = (nextEntry / 2) - 1; i >= 0; i--) { // (nextEntry / 2) means we ignore downHeap of leaves
            downHeap(i);
        }
    }

    // Replaces the key of an entry
    public K replaceKey(Entry<V, K> entry, K key) {
        Entry<V, K> returnEntry = null;

        for (int i = 0; i < nextEntry; i++) {
            if (a[i] == entry) {
                returnEntry = new Entry<>(entry);
                a[i].setKey(key);

                int keyDifference = comparator.compare(returnEntry.getKey(), key) * state;

                if (keyDifference < 0) {
                    upHeap(i);
                } else if (keyDifference > 0) {
                    downHeap(i);
                }

                break;
            }
        }

        return (returnEntry != null) ? returnEntry.getKey() : null;
    }

    // Replace the value of an entry
    public V replaceValue(Entry<V, K> entry, V value) {
        Entry<V, K> returnEntry = null;

        for (int i = 0; i < nextEntry; i++) {
            if (a[i] == entry) {
                returnEntry = new Entry<>(entry);
                a[i].setValue(value);
                break;
            }
        }

        return (returnEntry != null) ? returnEntry.getValue() : null;
    }

    // Remove an entry
    public Entry<V, K> remove(Entry<V, K> entry) {
        Entry<V, K> returnEntry = null;

        for (int i = 0; i < nextEntry; i++) {
            if (a[i] == entry) {
                returnEntry = new Entry<>(entry);
                a[i] = a[nextEntry - 1];
                downHeap(i);
                nextEntry--;
                break;
            }
        }

        return (returnEntry != null) ? returnEntry : null;
    }

    // Remove the top entry, or entry at position zero in the queue
    public Entry<V, K> removeTop() {
        return remove(top());
    }

    // Return the top entry
    public Entry<V, K> top() {
        return a[0];
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return nextEntry == 0;
    }

    // Returns the state of the queue as a string
    public String state() {
        return (state > 0) ? "max" : "min";
    }

    // Print the contents of the queue
    public void printQueue() {
        System.out.println("Queue contents:");
        for (int i = 0; i < nextEntry; i++) {
            System.out.println(a[i]);
        }
        System.out.println();
    }
}

