package umg.edu.gt.handler;

public class SongStack {

    private static final class Node {
        private final Song value;
        private Node next;

        private Node(Song value) {
            this.value = value;
        }
    }

    private Node top;
    private int size;

    public void push(Song song) {
        Node node = new Node(song);
        node.next = top;
        top = node;
        size++;
    }

    public Song pop() {
        if (isEmpty()) return null;
        Song value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
