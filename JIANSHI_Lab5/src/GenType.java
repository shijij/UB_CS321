public class GenType <T> {
    private T element;

    public GenType(T element){
        this.element = element;
    }

    public T get() {
        return element;
    }

    public void set(T element) {
        this.element = element;
    }
}
