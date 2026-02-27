package umg.edu.gt.datastructure.queue;

/**
 * Cola (FIFO) implementada manualmente con nodos enlazados.
 *
 * Reglas:
 * - enqueue O(1)
 * - dequeue O(1)
 * - no se exponen nodos internos
 */
public class Queue<T> {

    private static final class Node<E> {
        private final E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    // Referencias privadas
    private Node<T> head;
    private Node<T> tail;

    // Tamaño
    private int size;

    /** Inserta al final (O(1)) */
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    /** Remueve del frente (O(1)) */
    public T dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot dequeue from an empty queue");
        }

        T value = head.value;
        head = head.next;
        size--;

        // Si quedó vacía, tail también debe ser null
        if (head == null) {
            tail = null;
        }

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot peek from an empty queue");
        }
        return head.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
