public class ListaDoble<T> {
    private NodoDoble<T> inicio;

    public ListaDoble() {
        inicio = null;
    }

    public void addFirst(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato, null, inicio);
        if (inicio != null) inicio.setAnterior(n);
        inicio = n;
    }

    public void addLast(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato, null, null);
        if (inicio == null) {
            inicio = n;
        } else {
            NodoDoble<T> r = inicio;
            while (r.getSiguiente() != null) r = r.getSiguiente();
            r.setSiguiente(n);
            n.setAnterior(r);
        }
    }

    public T removeFirst() {
        T data;
        if (inicio == null) {
            throw new RuntimeException("La lista está vacía.");
        } else {
            data = inicio.getData();
            if (inicio.getSiguiente() == null) {
                inicio = null;
            } else {
                inicio = inicio.getSiguiente();
                inicio.setAnterior(null);
            }
        }
        return data;
    }

    public T removeLast() {
        T data;
        if (inicio == null) {
            throw new RuntimeException("La lista está vacía.");
        } else if (inicio.getSiguiente() == null) {
            data = inicio.getData();
            inicio = null;
        } else {
            NodoDoble<T> r = inicio;
            while (r.getSiguiente() != null) r = r.getSiguiente();
            data = r.getData();
            r.getAnterior().setSiguiente(null);
            r.setAnterior(null);
        }
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodoDoble<T> r = inicio;

        do {
            sb.append(r);
            r = r.getSiguiente();
            if (r != null) sb.append(", ");
        } while (r != null);
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        int counter = 0;
        NodoDoble<T> aux = inicio;
        while (aux != null) {
            counter++;
            aux = aux.getSiguiente();
        }
        return counter;
    }

    /*
    EXTRA
     */
    public int getIndexOf(T data) {
        int index = 0;
        NodoDoble<T> n = inicio;
        while (n != null) {
            if (n.getData().equals(data)) {
                return index;
            }
            n = n.getSiguiente();
            index++;
        }
        return -1;
    }

    public void sortAscending() {
        if (inicio == null || inicio.getSiguiente() == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            NodoDoble<T> current = inicio;

            while (current.getSiguiente() != null) {
                NodoDoble<T> next = current.getSiguiente();
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

    public void sortDescending() {
        if (inicio == null || inicio.getSiguiente() == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            NodoDoble<T> current = inicio;

            while (current.getSiguiente() != null) {
                NodoDoble<T> next = current.getSiguiente();
                if (((Comparable<T>) current.getData()).compareTo(next.getData()) < 0) {
                    T temp = current.getData();
                    current.setData(next.getData());
                    next.setData(temp);
                    swapped = true;
                }
                current = next;
            }
        } while (swapped);
    }

    public void addAt(T data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size()) {
            addLast(data);
        } else {
            NodoDoble<T> newNode = new NodoDoble<>(data, null, null);
            NodoDoble<T> current = inicio;
            for (int i = 0; i < index - 1; i++) {
                current = current.getSiguiente();
            }

            NodoDoble<T> nextNode = current.getSiguiente();
            current.setSiguiente(newNode);
            newNode.setAnterior(current);
            newNode.setSiguiente(nextNode);
            if (nextNode != null) {
                nextNode.setAnterior(newNode);
            }
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }

        NodoDoble<T> current = inicio;
        for (int i = 0; i < index; i++) {
            current = current.getSiguiente();
        }

        return current.getData();
    }

}