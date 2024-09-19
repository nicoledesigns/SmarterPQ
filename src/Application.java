public class Application {
    public static void main(String[] args) throws Exception {
       

        // Creates the queue with an initial length of two to test the expansion
        SPQ<Character, Integer> queue = new SPQ<>(new NumericalValue(), 2);

        // Inserting entries in the MAX priority
        queue.insert('A', 4); //A  is at the top since it is the biggest number
        queue.insert('B', 2);
        queue.insert('C', 3);
       
        queue.printQueue();
        
        // Removing entry with value 'A' by assigning the top element to "removeEntry"
        Entry<Character, Integer> removeEntry = queue.top();
        queue.remove(removeEntry);
        queue.printQueue();


        queue.insert('D', 4); //D is the top element
        queue.printQueue();

        
        // Changing entry with value 'D' to -9
        queue.replaceKey(queue.top(), -9);
        queue.printQueue();

        System.out.println("The top value is " + queue.top().getValue());


        // Prints states and toggles
        System.out.println("Current state: " + queue.state());
        queue.printQueue();
        queue.toggle();
        System.out.println("New state: " + queue.state());
        queue.printQueue();


        //queue.insert('E', 5);
        //queue.insert('F', 0);
        //queue.insert('G', -1);
        
        // testEntry will be entry with value 'D'
        Entry<Character, Integer> testEntry = queue.top();
        // This will place 'D' as the fifth value in the queue
        System.out.println("Previous test entry key: " + queue.replaceKey(testEntry, 5));
        queue.printQueue();

        //the top entry is now 'B' with value 2
        Entry<Character, Integer> DEntry = queue.top();
        // Replacing value of the top entry 'B' to 'L' and its key to 2
        System.out.println("Previous D entry value: " + queue.replaceValue(DEntry, 'L'));
        // testEntry will be entry with value 'D'
        System.out.println("Previous D entry key: " + queue.replaceKey(DEntry, 2));
        queue.printQueue();

        //Remove the top entry 'L'
        Entry<Character, Integer> removedDEntry = queue.remove(DEntry);
        System.out.println("Removed entry value: " + removedDEntry.getValue());
        System.out.println("Removed entry key: " + removedDEntry.getKey());
        queue.printQueue();


        // current size of queue 
        System.out.println("Queue item number: " + queue.getSize());
        
        // Replacing value of top, which is 'C', to 'K'
        System.out.println("Previous top value: " + queue.replaceValue(queue.top(), 'K'));

        
        System.out.println("Adding elements");
        queue.insert('O', 5);
        queue.insert('M', 6);
        queue.insert('N', 8);



        // Removing everything in order of priority from smallest key to biggest, numerically
        while (!queue.isEmpty()) {
            Entry<Character, Integer> topEntry = queue.removeTop();
            System.out.println(topEntry.getValue());
        }

        // Size once empty
        System.out.println("Queue item number: " + queue.getSize()+ "\n");
        
        System.out.println("After inserting X,Y,Z: ");
     // Test inserting when queue is empty
        queue.insert('X', 10);
        queue.insert('Y', 5);
        queue.insert('Z', 8);
        queue.printQueue();

        // Test handling duplicate keys
        queue.insert('M', 8); // Duplicate key with 'Z'
        queue.printQueue();

     // Test replacing keys at boundaries
        queue.replaceKey(queue.top(), 15); // Replace top of min heap ('Y') key to 15
        queue.printQueue();

        // Example of finding left and right children of a node
        int leftChildIdxOfY = queue.leftChild(0); // 0 is the index of 'Y'
        int rightChildIdxOfY = queue.rightChild(0); // 0 is the index of 'Y'
        System.out.println("Left child index of Y: " + leftChildIdxOfY); // Output: 1 (index of 'M')
        System.out.println("Right child index of Y: " + rightChildIdxOfY); // Output: 2 (index of 'Z')

        
       // queue.insert('U', 11);
        //queue.insert('P', 2);
        // Swap elements at indices 0 and 2
        System.out.println("Swapping elements at indices 0 and 3:");
        queue.swap(0, 3);
        queue.printQueue();


        // Downheaping 'Y' (index 0)
        queue.downHeap(0);

        // Print the queue after downheaping 'Y'
        System.out.println("After downHeap:");
        queue.printQueue();

    }
}
