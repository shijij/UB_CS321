import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<T> {
    private LinkedList<T> elements;
    private int capacity;

    public Stack(int capacity){
        this.capacity = capacity;
        elements =  new LinkedList<>();
    }

    public synchronized T pop() throws EmptyStackException {
        try{
            return elements.pop();
        } catch (NoSuchElementException e){
            throw new EmptyStackException();
        }
    }

    public synchronized T push(T e){
        if(elements.size() >= capacity){
            throw new FullStackException();
        } else {
            elements.add(e);
        }
        return e;
    }

    public synchronized T top(T e){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return elements.peek();
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

}
