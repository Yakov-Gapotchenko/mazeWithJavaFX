package sample;


class StackNode<T> {
    T data;
    StackNode prev;

    StackNode(T data)
    {
        this.data = data;
    }
}

public class Stack<T> {

    StackNode<T> current;



    public boolean isEmpty()
    {
        if (current == null) {
            return true;
        }
        else
            return false;
    }

    public void push(T data)
    {
        StackNode newNode = new StackNode(data);

        if (current == null) {
            current = newNode;
        }
        else {
            StackNode temp = current;
            current = newNode;
            newNode.prev = temp;
        }
        //System.out.println(data + " pushed to stack");
    }

    public T pop()
    {
        T popped=null;
        if (current == null) {
            System.out.println("Stack is Empty");
        }
        else {
            popped = current.data;
            current = current.prev;
        }
        return popped;
    }

    public T peek()
    {
        if (current == null) {
            System.out.println("Stack is empty");
            return null;
        }
        else {
            return current.data;
        }

    }



    public static void main(String[] args)
    {

        Stack<Integer> sll = new Stack<Integer> ();

        sll.push(10);
        sll.push(20);
        sll.push(30);

        System.out.println(sll.pop() + " popped from stack");

        System.out.println("Top element is " + sll.peek());
    }
}





