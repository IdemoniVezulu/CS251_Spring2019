//Implement singly linked list, using Node class

public class LinkedList {
    //use necessary variables and methods
    private Node head;
    private int size = 0;

    public void add(Node newNode){
        newNode.setNext(this.head);
        size++;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }
}
