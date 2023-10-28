package DataSturcture.ArrayList;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> {
    public static final int INITIAL_CAPACITY = 10;
    private Object[] arr;
    private int size = 0;

    public MyArrayList(int initialCapacity) {
        this.arr = new Object[initialCapacity];
    }

    public MyArrayList() {
        this(INITIAL_CAPACITY);
    }

    public boolean add(E item) {
        if (arr.length == size)
            grow();
        arr[size++] = item;
        return true;
    }

    private void grow() {
        int newCapacity = arr.length + arr.length / 2 + 1;
        arr = Arrays.copyOf(arr, newCapacity);
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, arr.length);
        if (size - 1 >= index)
            return (E)arr[index];
        else
            throw new IllegalArgumentException("Index bound of exeption");
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Objects.checkIndex(index, arr.length);
        Object oldValue = null;
        if (size - 1 >= index) {
            oldValue = arr[index];
            int newSize;
            if ((newSize = size - 1) > index)
                System.arraycopy(arr, index + 1, arr, index, newSize - index);
            arr[size = newSize] = null;
        } else {
            throw new IllegalArgumentException("Index off bounds exeptions");
        }

        return (E)oldValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(arr, size));
    }

    public static void main(String[] args) {
       MyArrayList<String> lang = new MyArrayList<>();
    }
}
