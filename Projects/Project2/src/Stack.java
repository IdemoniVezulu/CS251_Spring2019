//implementation of stack using your implemented Linked List

public class Stack {
    //Declare necessary variables and methods
    private LinkedList list = new LinkedList();
    private int numContents = 0;

    /*
        This method is used to delete an element from the stack.
        Ir returns null is the list is empty, otherwise it returns the element that is deleted.
     */

    public Node pop(){

        if(!isEmpty()){
            Node temp = top();
            list.setHead(null);
            list.setHead(temp.getNext());
            numContents--;
            return temp;
        }

        return null;
    }

    /*
        In this method we accept two integers and insert them into the stack.
     */

    public void push(Pair item){
        Node newNode = new Node(item.getA(), item.getB());

        newNode.setNext(top());
        list.setHead(newNode);
        numContents++;
    }

    /*
        This function returns the head of the list
     */

    public Node top(){
        return list.getHead();
    }

    /*
        This function returns true or false depending on whether the list is empty or not.
     */

    public boolean isEmpty(){
        return (top() == null);
    }

    public LinkedList getList(){
        return list;
    }
}