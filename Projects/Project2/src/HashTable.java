import java.io.PrintStream;
import java.util.*;

/**
 * Generic Chaining Hashtable
 * @param <K> Key type
 * @param <V> Value type
 */
public class HashTable<K, V> {

    /**
     * Auxiliary class for HashTable
     * @param <F> Key type
     * @param <S> Value type
     */
    public class Pair<F, S> {
        public F key;
        public S value;

        /**
         * Construct a key value Pair
         * @throws NullPointerException if key is null
         * @param key key
         * @param value value
         */
        public Pair(F key, S value) {
            if(key == null) {
                throw new NullPointerException("Key cannot be null");
            }
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object other) {
            if(! (other instanceof Pair)) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Pair<?, ?> po = (Pair) other;
            // Hack here. We only compare key value
            // so that we can use it to call List.contains
            return key.equals(po.key);
        }
    }

    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271};

    private List<List<Pair<K, V>>> table;
    private int size;
    private int capacity;

    /**
     * Initialize an array of length capacity,
     * then initialize the table with empty Lists at each index.
     * @param capacity Initial capacity of the table.
     */
    public HashTable(int capacity) {
        //throw new NotImplementedException();

        table = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.size = 0;
        for(int i = 0; i < capacity; i++){
            table.add(i, new LinkedList<Pair<K, V>>());
        }
    }

    /**
     * Get the hash code of key.
     * You may design any hashCode function you like.
     * @param key key
     * @return hashCode of key
     */
    private int hashCode(K key) {
        //throw new NotImplementedException();
        int hashCode = 0;

        hashCode = (hashValue(key)) % capacity;

        return hashCode;
    }

    /**
     * This function calculates the hash value of a key depending on its type
     * @param key
     * @return hashValue
     */
    private int hashValue(K key){
        int hashValue = 0;

        // Hash the key depending on its type
        if(key instanceof String){
            String newKey = (String) key;
            for (int i = 0; i < newKey.length(); i++) {
                hashValue = (31 * hashValue + newKey.charAt(i)) % capacity;
            }
        }
        else {
            hashValue = (key.hashCode() & 0x77ffffff) % capacity;
        }

        return hashValue;
    }

    /**
     * Returns the value of the key if found, return null otherwise.
     * @param key key
     * @throws NullPointerException if key is null
     * @return Returns the value of the key if found. Otherwise, return null.
     */
    public V get(K key) {
        //throw new NotImplementedException();
        V retVal = null;

        // Check if key is null and throw NullPointer if it is
        if(key == null){
            throw new NullPointerException("In get, Key is null");
        }

        // Get the index of the key
        int index = hashCode(key);

        // Get the separate chain at index
        List<Pair<K, V>> chain = table.get(index);

        // Iterate through chain to find the key and get the value
        for(int i = 0; i < chain.size(); i++){
                if (chain.get(i).key.equals(key)){
                    retVal = chain.get(i).value;
                }
        }

        return retVal;
    }

    /**
     * Adds the (key, value) pair to the table.
     * If key already presents in the hashtable, overwrite the original value.
     * Resize the hashtable if the load factor becomes greater than 0.5 after inserting this (key, value) pair.
     * @throws NullPointerException if key or value is null
     * @param key key
     * @param value value
     * @return the previous value of the specified key in this hashtable, or null if it did not have one
     */
    public V put(K key, V value) {
        //throw new NotImplementedException();
        // Check if key is null and throw NullPointer if it is
        if(key ==  null){
            throw new NullPointerException("In put, Key is null");
        }

        V retVal = null;

        int index = hashCode(key);
        List<Pair<K, V>> chain = table.get(index);

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
                // If key exists, change its value to the new value
                retVal = chain.get(i).value;
                chain.get(i).value = value;
            }
        }

        if(retVal == null) {
            // If key is not in the list, add it into the head of the list
            Pair<K, V> newElement = new Pair(key, value);
            chain.add(newElement);
            this.size++;

            // Resize hashtable if the load factor is greater
            if (this.size / this.capacity > 0.5) {
                resize();
            }
        }

        return retVal;
    }

    /**
     * Resize the hashtable if the load factor becomes greater than 0.5 after inserting this (key, value) pair.
     * We hard code a list of prime number for you to use. You can assume you will never run out of prime number to use.
     */
    private void resize() {
        //throw new NotImplementedException();

        int newSize = 2 * capacity;

        // Find the prime number in the array
        for(int i = 0; i < primes.length; i++){
            if(primes[i] > newSize){
                newSize = primes[i];
                break;
            }
        }

        List<List<Pair<K, V>>> newTable = new ArrayList<>(newSize);

        for(int i = 0; i < capacity; i++){
            newTable.add(table.get(i));
        }

        this.capacity = newSize;
        table = newTable;
    }

    /**
     * Removes the key from the table, if it's there.
     * @throws NullPointerException if the key is null
     * @param key key
     * @return Return the value of the key if key exists in the hashtable, return null otherwise
     */
    public V remove(K key) {
        //throw new NotImplementedException();
        V retVal = null;

        if(key == null){
            throw new NullPointerException("In remove, key is null");
        }

        int index = hashCode(key);

        if(index < table.size()) {
            List<Pair<K, V>> chain = table.get(index);

            // Loop through table to find the key
            for (int i = 0; i < chain.size(); i++) {
                if (chain.get(i).key.equals(key)) {
                    // If found, remove the pair
                    retVal = chain.get(i).value;
                    chain.remove(i);
                    this.size--;
                    break;
                }
            }
        }

        return retVal;
    }

    /**
     * Returns whether the key is in the hashtable.
     * @throws NullPointerException if key is null
     * @param key key
     * @return Return true if the key is in the hashtable. Return false otherwise.
     */
    public boolean containsKey(K key) {
        //throw new NotImplementedException();

        boolean isFound = false;

        // Throw a NullPointer if the key is null
        if(key == null){
            throw new NullPointerException("In containsKey, key is null");
        }

        int index = hashCode(key);

        if(index < table.size()) {
            List<Pair<K, V>> chain = table.get(index);

            // Loop through table and check if the key exists
            for (int i = 0; i < chain.size(); i++) {
                if (chain.get(i).key.equals(key)) {
                    isFound = true;
                }
            }
        }

        return isFound;
    }

    /**
     * Returns the number of (key, value) pairs in the table.
     * @return size of hashtable
     */
    public int size() {
        //throw new NotImplementedException();
        return size;
    }

    /**
     * Replaces the value for the specified key only if it is currently mapped to some value.
     * @param key key
     * @param value value
     * @return Return the previous value associated with the specified key, or null if there was no mapping for the key.
     */
    public V replace(K key, V value) {
        //throw new NotImplementedException();
        // Check if key is null and throw NullPointer if it is
        if(key ==  null){
            throw new NullPointerException("In put, Key is null");
        }

        V retVal = null;

        int index = hashCode(key);
        List<Pair<K, V>> chain = table.get(index);

        // Check chains to see if the key already exists in the list
        if((chain.size()) > 0 && (chain.get(0).value != null)) {
            retVal = chain.get(0).value;
            chain.get(0).value = value;
        }

        return retVal;
    }

    /**
     * Return the chain length at index in this hashtable.
     * @param index index of the chain to get length of
     * @return chain length at index in this hashtable.
     */
    public int getCollision(int index) {
        //throw new NotImplementedException();
        return table.get(index).size();

        // TODO: To get anagrams, sort the word, see if they are the same
        // TODO: Sorted word string will be the key
    }

    /**
     * Read in an input file and write output to output stream
     * Scanner in starts from the beginning of the file
     * @param in input Scanner
     * @param out output PrintStream
     */
    public static void generateOutput(int capacity, Scanner in, PrintStream out) {
        //throw new NotImplementedException();

        String part = in.nextLine();
        HashTable hashTable = new HashTable(capacity);
        String totLines = in.nextLine();
        int numLines = Integer.parseInt(totLines);

        // Loop goes through each line and reads the input
        for(int i = 0; i < numLines; i++){
            // Gets the first line and the first character of the line
            String curLine = in.nextLine();
            char firstLetter = curLine.charAt(0);
            String[] sub = curLine.split(" ");

            if(firstLetter == 'p'){
                // Call put()
                String key = sub[1];
                int val = Integer.parseInt(sub[2]);

                Object retVal = hashTable.put(key, val);

                out.print(retVal);
            }
            else if(firstLetter == 'g'){
                // Call get()
                String key = sub[1];

                out.print(hashTable.get(key));
            }
            else if(firstLetter == 'r'){
                // Call replace()
                String key = sub[1];
                int val = Integer.parseInt(sub[2]);

                out.print(hashTable.replace(key, val));
            }
            else if(firstLetter == 'd'){
                // Call remove()
                String key = sub[1];

                out.print(hashTable.remove(key));
            }
            else if(firstLetter == 's'){
                // Call size()
                out.print(hashTable.size());
            }
            else if(firstLetter == 'c'){
                // Call containsKey()
                String key = sub[1];

                if(hashTable.containsKey(key)){
                    out.print(1);
                }
                else {
                    out.print(0);
                }
            }
            else {
                // Calls getCollision()
                char index = sub[1].charAt(0);

                out.print(hashTable.getCollision(Character.getNumericValue(index)));
            }

            if(i < (numLines - 1)){
                out.println();
            }

            hashTable.printHashTable();
        }

        in.close();
        out.close();
    }

    private void printHashTable(){
        System.out.println("Printing HashTable...");
        for(int i = 0; i < table.size(); i++){
            System.out.print("Element " + i + ": ");
            for(int j = 0; j < table.get(i).size(); j++){
                System.out.println("(" + table.get(i).get(j).key + ", " + table.get(i).get(j).value + ")");
            }
        }
    }
}
