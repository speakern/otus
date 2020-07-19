package ru.otus.generics;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DIYArrayList<E> implements List<E> {

    public Object[] arrayData;
    public int sizeArray = 0;
    public int size = 0;

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
        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
        //return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
        //return null;
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
        this.arrayData[size + 1] = e;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
        //return false;
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
        //return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        //return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        //return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        // return 0;
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        //return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //return null;
        throw new UnsupportedOperationException();
    }
}
