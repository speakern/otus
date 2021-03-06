package ru.otus.io;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    public Object[] arrayData;
    private int sizeArray = 0;
    private int size = 0;

    private static final int DEFAULT_CAPACITY = 5;
    private static final int MULTIPLIER_INCREASE = 2;

    public DIYArrayList() {
        this.arrayData = new Object[DEFAULT_CAPACITY];
        this.sizeArray = DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new DIYListIterator<>();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if (this.size == this.sizeArray) {
            Object[] arrayData = new Object[this.sizeArray * MULTIPLIER_INCREASE];
            System.arraycopy(this.arrayData, 0,
                    arrayData, 0, this.sizeArray);
            this.arrayData = arrayData;
            this.sizeArray *= MULTIPLIER_INCREASE;
        }
        this.arrayData[size] = e;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.arrayData[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = get(index);
        this.arrayData[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    class DIYListIterator<E> implements ListIterator<E> {
        private int iteratorIndex = -1;

        @Override
        public boolean hasNext() {
            return iteratorIndex != size - 1;
        }

        @Override
        public E next() {
            if (iteratorIndex >= size) {
                throw new NoSuchElementException();
            }
            iteratorIndex += 1;
            return (E) arrayData[iteratorIndex];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            arrayData[iteratorIndex] = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYListIterator<>();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
}