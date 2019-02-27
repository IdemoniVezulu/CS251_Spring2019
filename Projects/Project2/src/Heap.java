import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Heap {

    private static final int BRANCHES = 2;

    private int[] heap;
    private int size;

    /**
     * Construct a new heap
     */
    public Heap() {
        //throw new NotImplementedException();
        size = 0;
        heap = new int[BRANCHES];
    }

    /**
     * Insert element into this heap
     */
    public void insert(int e) {
        //throw new NotImplementedException();
        if(heap.length == size()){
            resize();
        }

        // Inserting into heap
        heap[size++] = e;
    }

    /**
     * Return number of elements in this heap
     * @return size of the heap
     */
    public int size() {
       //throw new NotImplementedException();
        return size;
    }

    /**
     * Peek the smallest element in this heap
     * @throws NoSuchElementException if heap is empty
     * @return the smallest element in this heap.
     */
    public int min() {
        //throw new NotImplementedException();
        int retVal = heap[0];

        if(size() == 0){
            throw new NoSuchElementException();
        }
        else {
            int temp = heap[0];
            int at = -1;
            for(int i = 1; i < size(); i++) {
                if ((heap[i] < temp) && (heap[i] != 0)) {
                    temp = heap[i];
                    at = i;
                }
                if (at != -1) {
                    retVal = temp;
                }
            }
        }

        return retVal;
    }

    /**
     * Remove the smallest element in this heap
     * @throws NoSuchElementException if heap is empty
     * @return the smallest element in this heap
     */
    public int removeMin() {
        //throw new NotImplementedException();
        int retVal = -1;

        if(size() == 0){
            throw new NoSuchElementException();
        }
        else {
            int temp = heap[0];
            int at = -1;
            for(int i = 1; i < size(); i++){
                if((heap[i] < temp) && (heap[i] != 0)){
                    temp = heap[i];
                    at = i;
                }
                if (at != -1){
                    retVal = temp;
                }
            }
            if (at != -1) {
                heap[at] = 0;
            }
        }

        size--;
        return retVal;
    }

    /**
     * Function reverses the order of the elements in the heap
     */
    private void reverseOrder(){
        int i = 0;

        // Loop counts the number of zero elements in the heap
        for(int k = 0; k < heap.length; k++){
            if(heap[k] == 0){
                i++;
            }
        }

        int  j = heap.length - i;

        // Loop to sort the heap
        for(i = 1; i < j; i++){
            int val = heap[i];
            int k = i - 1;

            while((k >= 0) && (heap[k] > val)){
                heap[k + 1] = heap[k];
                k = k - 1;
            }
            heap[k + 1] = val;
        }
    }

    /**
     * This function empties the content of the heap
     */
    private void emptyHeap(){
        for(int i = 0; i < size(); i++){
            heap[i] = 0;
        }
    }

    /**
     * Read in an input file and write output to output stream
     * Scanner in starts from the beginning of the file
     * @param in input Scanner
     * @param out output PrintStream
     */
    public static void generateOutput(Scanner in, PrintStream out) {
        //throw new NotImplementedException();
        Heap heap = new Heap();

        String part = in.nextLine();
        String totLines = in.nextLine();
        int numLines = Integer.parseInt(totLines);

        for(int i = 0; i < numLines; i++){
            String curLine = in.nextLine();
            char firstChar = curLine.charAt(0);
            int val = -1;

            if(firstChar == 'i'){
                // Make string and then parse to int
                String multiDigit = "";

                // Insert into Heap
                val = Character.getNumericValue(curLine.charAt(2));
                // Get value depending on number of digits
                if((curLine.length() > 3) && (Character.isDigit(curLine.charAt(3)))){
                    if(curLine.length() > 5){
                        multiDigit = "" + curLine.charAt(2) + curLine.charAt(3) + curLine.charAt(4) + curLine.charAt(5);
                    }
                    else if(curLine.length() > 4) {
                        multiDigit = "" + curLine.charAt(2) + curLine.charAt(3) + curLine.charAt(4);
                    }
                    else {
                        multiDigit = "" + curLine.charAt(2) + curLine.charAt(3);
                    }
                    val = Integer.parseInt(multiDigit);
                }
                heap.insert(val);
            }
            else if(firstChar == 'm'){
                // Print heap minimum
                if(heap.size() == 0){
                    out.print("empty");
                    if(i < (numLines - 1)){
                        out.println();
                    }
                }
                else {
                    // Print the minimum value
                    out.print(heap.min());
                    if(i < (numLines - 1)){
                        out.println();
                    }
                }
            }
            else if(firstChar == 'r'){
                // Delete from heap
                if(heap.size() == 0){
                    out.print("empty");
                    if(i < (numLines - 1)){
                        out.println();
                    }
                }
                else {
                    heap.removeMin();
                }
            }
            else {
                // Print sorted contents of heap
                if(heap.size() == 0){
                    out.print("empty");
                    if(i < (numLines - 1)){
                        out.println();
                    }
                }
                else {
                    heap.reverseOrder();
                    int j = 0;
                    for (; j < heap.size(); j++) {
                        if(heap.heap[j] != 0){
                            out.print(heap.heap[j]);
                            if ((j < (heap.size() - 1)) && (heap.heap[j + 1] != 0)) {
                                out.println();
                            }
                        }
                    }
                    if (i < (numLines - 1)) {
                        out.println();
                    }

                    // Empty heap after sorting
                    heap.emptyHeap();
                    heap.size = 0;
                }
            }
        }

        in.close();
        out.close();
    }

    private void resize() {
        //throw new NotImplementedException();
        int[] newHeap = new int[2 * heap.length];

        for(int i = 0; i < heap.length; i++){
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    private void printHeap(){
        System.out.println("\nWhole heap size: " + size());
        System.out.println("\nWhole heap length: " + heap.length);
        for(int i = 0; i < heap.length; i++){
            System.out.println("Heap[" + i + "]: " + heap[i]);
        }
    }
}
