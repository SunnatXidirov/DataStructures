package DataSturcture.DoubleLinkedList;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class DoubleLinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

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
        var sj = new StringJoiner(", ", "{", "}");
        this.forEach(str -> sj.add(String.valueOf(str)));
        return sj.toString();
    }
    public int size() {
        return size;
    }
    public boolean add(E element) {
        Node<E> last = tail;
        var newNode = new Node<>(element);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        size++;
        return true;
    }
    public boolean set(int index, E elment) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            addAtBeginning(elment);
            return true;
        }
        if (index == size - 1) {
            add(elment);
            return true;
        }

        var newNode = new Node<E>(elment);
        Node<E> current = null;
        Node<E> setNode = null;


        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            setNode = current.next;
            current.next = newNode;
            newNode.prev = current;
            newNode.next = setNode;
            setNode.prev = newNode;
            size++;
            return true;
        } else {
            current = tail;
            for (int i = size - 1; i > index - 1; i--) {
                current = current.prev;
            }
            setNode = current.next;
            current.next = newNode;
            newNode.prev = current;
            newNode.next = setNode;
            setNode.prev = newNode;
            size++;
            return true;
        }
    }
    public boolean addAtBeginning(E element) {
        var newNode = new Node<E>(element);
        Node<E> last = tail;
        var current = head;
        tail = newNode;
        if (last == null) {
            size++;
            head = newNode;
            return true;
        } else {
            head = newNode;
            head.next = current;
            current.prev = head;
            head.prev = null;
            size++;
            return true;
        }
    }
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) return head.element;
        Node<E> current = null;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.element;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current.element;
        }
    }
    public boolean reomveFromBegining() {
        if (head == null) {
            return false;
        } else {
            head = head.next;
            size--;
            return true;
        }
    }
    public boolean remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            reomveFromBegining();
            size--;
            return true;
        }
        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return true;
        }

        Node<E> current = null;
        if (index < size / 2) {

            current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            var deletingNode = current.next;
            current.next = deletingNode.next;
            deletingNode.next.prev = current;
            size--;
            return true;
        } else {

            current = tail;
            for (int i = size - 1; i > index - 1; i--) { /// index: 7 , size: 9
                current = current.prev;
            }
            var deletingNode = current.next;
            current.next = deletingNode.next;
            deletingNode.next.prev = current;
            size--;
            return true;
        }
    }
    public boolean remove(Object o) {
        if (head == null) return false;

        Node<E> prevHead = null;
        Node<E> prevTail = null;
        var removeHead = head;
        var removeTail = tail;

        for (int i = 0; i <= size / 2; i++) {

            if (Objects.equals(o, removeHead.element)) {
                if (prevHead==null){
                 reomveFromBegining();
                 return true;
                }else {
                    prevHead.next=removeHead.next;
                    removeHead.next.prev=prevHead;
                    size--;
                    return true;
                }
            }
            if (Objects.equals(o, removeTail.element)) {
                if (prevTail==null){
                    tail=tail.prev;
                    tail.next=null;
                    size--;
                    return true;
                }else {
                    prevTail.prev=removeTail.prev;
                    removeTail.prev.next=prevTail;
                    size--;
                    return true;
                }
            }
            prevHead=removeHead;
            prevTail=removeTail;

            removeHead = removeHead.next;
            removeTail = removeTail.prev;
        }
        return false;
    }
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

    }
}
