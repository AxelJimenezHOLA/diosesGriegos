public class DoubleNode<T> {
    private T info;
    private DoubleNode<T> previous;
    private DoubleNode<T> next;

    public DoubleNode(T info, DoubleNode<T> previous, DoubleNode<T> next) {
        this.info = info;
        this.previous = previous;
        this.next = next;
    }

    public T getData() {
        return info;
    }

    public void setData(T info) {
        this.info = info;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}