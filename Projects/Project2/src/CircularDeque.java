//implement circular deque here

public class CircularDeque {
    //create necessary variables and methods

    private int size = 2; // Total size of the queue
    private int numContents = 0; // Actual size of queue (Number of elements actually in the queue)
    private int front = -1;
    private int rear = -1;
    private int[] queue = new int[size];

    /*
        This method doubles the size of the queue if it becomes full
     */

    public void resize(){
        int[] temp = new int[2 * size];
        size *= 2;

        for(int i = front; i < (size / 2); i = (i + 1) % size){
            temp[i] = queue[i];
        }
        queue = temp;
    }

    /*
        This method accepts a node and adds it to the front of the queue
     */

    public void enqueue_front(int newData){

        // Resize the array if it is full

        if(!isEmpty()){
            resize();
        }

        if(front == -1){
            front = 0;
            rear = 0;
        }
        else if(front == 0){
            front = size - 1;
        }
        else {
            front--;
        }

        queue[front] = newData;
        numContents++;
    }

    /*
        This method accepts a node and adds it to the back of the queue
     */

    public void enqueue_back(int newData){

        // Resize the array of it is full

        if(!isEmpty()){
            resize();
        }

        if(front == -1){
            front = 0;
            rear = 0;
        }
        else if(rear == (size - 1)){
            rear = 0;
        }
        else {
            rear++;
        }


        queue[rear] = newData;
        numContents++;
    }

    /*
        This method removes a node from the front of the queue
     */

    public int dequeue_front(){

        int temp = -1;

        if (isEmpty()){
            return temp;
        }

        if(front == rear){
            temp = queue[front];
            front = -1;
            rear = -1;
        }
        else {
            if(front == (size - 1)){
                temp = queue[front];
                front = 0;
            }
            else {
                temp = queue[front];
                front++;
            }
        }

        return temp;
    }

    /*
        This method removes a node from the back of the queue
     */

    public int dequeue_back(){

        int temp = -1;

        if (isEmpty()){
            return temp;
        }


        if(front == rear){
            temp = queue[rear];
            front = -1;
            rear = -1;
        }
        else if(rear == 0){
            temp = queue[rear];
            rear = size - 1;
        }
        else {
            temp = queue[rear];
            rear--;
        }

        return temp;
    }

    /*
        This method returns the size of the array and prints it.
     */

    public int print_array_size(){
        System.out.println(size);
        return size;
    }

    /*
        This method returns the front index of the array and prints it.
     */

    public int print_front_index(){
        System.out.println(front);
        return front;
    }

    /*
        This method returns the rear index of the array and prints it.
     */

    public int print_rear_index(){
        System.out.println(rear);
        return rear;
    }

    public void setNumContents(int numContents) {
        this.numContents = numContents;
    }

    public int getNumContents() {
        return numContents;
    }

    public int getSize() {
        return size;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public boolean isEmpty(){
        return (front == rear);
    }
}
