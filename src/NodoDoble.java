public class NodoDoble<T> {
    private T info;
    private NodoDoble<T> anterior;
    private NodoDoble<T> siguiente;

    public NodoDoble(T info, NodoDoble<T> anterior, NodoDoble<T> siguiente) {
        this.info = info;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public T getData() {
        return info;
    }

    public void setData(T info) {
        this.info = info;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}