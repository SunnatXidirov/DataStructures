package DataSturcture.SingleLinkedList;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class SingleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private int size;


    private static class Node<E> {
        E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {
        var sj = new StringJoiner(",", "[", "]");
        var current = this.head;
        while (current != null) {
            sj.add(String.valueOf(current.element));
            current = current.next;
        }
        return sj.toString();
    }


    public boolean add(E element) {
        var newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            var current = this.head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        this.size++;
        return true;
    }

    public boolean addAtBeginning(E element) {
        var newNode = new Node<>(element);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
        return true;
    }

    public boolean removeFromBeginning() {
        if (head == null) return false;
        head = head.next;
        size--;
        return true;
    }

    public boolean remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            removeFromBeginning();
            size--;
        } else {
            var current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            var deletingNode = current.next;
            current.next = deletingNode.next;
            size--;
        }

        return true;
    }

    public Integer size() {
        return size;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0)
            return head.element;
        var current = this.head;

        for (int i = 1; i < index; i++)
            current = current.next;

        return current.element;

    }

    public boolean set(int index, E element) {
        Objects.checkIndex(index, size);
        var newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return true;
        } else {
            var current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            var nextNode = current.next;
            current.next = newNode;
            newNode.next = nextNode;
            size++;
            return true;
        }
    }

    public boolean remove(Object o) {
        if (head == null) return false;
        Node<E> prev = null;
        var current = head;
        while (current != null) {
            if (Objects.equals(o, current.element)) {
                if (prev == null) {
                    head = current.next;
                } else
                    prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;

        }
        return false;
    }


    public static void main(String[] args) {
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.add("Sunnat1");
        linkedList.add("Scala");
        linkedList.add("Sunnat3");
        linkedList.add("Sunnat4");
        linkedList.addAtBeginning("java");
        System.out.println(linkedList);
        System.out.println(linkedList.set(4, "Bahodir"));
        System.out.println(linkedList);
        System.out.println(linkedList.size);


    }

}
