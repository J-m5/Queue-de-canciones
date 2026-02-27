package umg.edu.gt.datastructure.queue;

public class Queue<T> {

    private static final class Node<E> {
        private final E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

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

    public T dequeue() {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot dequeue from an empty queue");
        }

        T value = head.value;
        head = head.next;
        size--;

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
