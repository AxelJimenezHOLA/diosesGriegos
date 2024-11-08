import java.util.NoSuchElementException;

public class DoubleList<T> {
    private DoubleNode<T> start;

    public DoubleList() {
        start = null;
    }

    public void pushStart(T data) {
        DoubleNode<T> n = new DoubleNode<>(data, null, start);
        if (start != null) start.setPrevious(n);
        start = n;
    }

    public void pushEnd(T data) {
        DoubleNode<T> n = new DoubleNode<>(data, null, null);
        if (start == null) {
            start = n;
        } else {
            DoubleNode<T> r = start;
            while (r.getNext() != null) r = r.getNext();
            r.setNext(n);
            n.setPrevious(r);
        }
    }

    public T popStart() {
        T data;
        if (start == null) {
            throw new RuntimeException("La lista está vacía.");
        } else {
            data = start.getData();
            if (start.getNext() == null) {
                start = null;
            } else {
                start = start.getNext();
                start.setPrevious(null);
            }
        }
        return data;
    }

    public T popEnd() {
        T data;
        if (start == null) {
            throw new RuntimeException("La lista está vacía.");
        } else if (start.getNext() == null) {
            data = start.getData();
            start = null;
        } else {
            DoubleNode<T> r = start;
            while (r.getNext() != null) r = r.getNext();
            data = r.getData();
            r.getPrevious().setNext(null);
            r.setPrevious(null);
        }
        return data;
    }

    public DoubleNode<T> getStart() {
        return start;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoubleNode<T> r = start;

        do {
            sb.append(r);
            r = r.getNext();
            if (r != null) sb.append(", ");
        } while (r != null);
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        int counter = 0;
        DoubleNode<T> aux = start;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    /*
    CRITERIOS EXAMEN
     */

    public String toStringRecursive(DoubleNode<T> n) {
        if (n.getNext() == null) return n.getData().toString();
        return (n.getData().toString() + ", " + toStringRecursive(n.getNext()));
    }

    public T popData(T data) {
        T poppedData = null;
        DoubleNode<T> aux = start;

        if (start == null) {
            throw new NoSuchElementException("Empty list");
        } else if (start.getData().equals(data)) {
            poppedData = start.getData();
            start = start.getNext();
            if (start != null) {
                start.setPrevious(null);
            }
        } else {
            while (aux != null) {
                if (aux.getData().equals(data)) {
                    poppedData = aux.getData();
                    DoubleNode<T> prev = aux.getPrevious();
                    DoubleNode<T> next = aux.getNext();

                    if (prev != null) {
                        prev.setNext(next);
                    }
                    if (next != null) {
                        next.setPrevious(prev);
                    }
                    break;
                }
                aux = aux.getNext();
            }
        }

        if (poppedData == null) throw new NoSuchElementException("Element not found");
        return poppedData;
    }

    public int getIndexOf(T data) {
        int index = 0;
        DoubleNode<T> n = start;
        while (n != null) {
            if (n.getData().equals(data)) {
                return index;
            }
            n = n.getNext();
            index++;
        }
        return -1;
    }

    public void sortList() {
        if (start == null || start.getNext() == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            DoubleNode<T> current = start;

            while (current.getNext() != null) {
                DoubleNode<T> next = current.getNext();
                if (((Comparable<T>) current.getData()).compareTo(next.getData()) > 0) {
                    T temp = current.getData();
                    current.setData(next.getData());
                    next.setData(temp);
                    swapped = true;
                }
                current = next;
            }
        } while (swapped);
    }

    public void pushAt(T data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            pushStart(data);
        } else if (index == size()) {
            pushEnd(data);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(data, null, null);
            DoubleNode<T> current = start;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            DoubleNode<T> nextNode = current.getNext();
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(nextNode);
            if (nextNode != null) {
                nextNode.setPrevious(newNode);
            }
        }
    }
}