//Node class is to be used for the implementation of Linked List

public class Node {
    //Declare necessary variables and methods

    private Pair data = new Pair();
    private Node next;

    Node(int a, int b){
        data.setA(a);
        data.setB(b);
        this.next = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setData(Pair data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public Pair getData() {
        return data;
    }
}
