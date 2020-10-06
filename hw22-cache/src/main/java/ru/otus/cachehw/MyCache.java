package ru.otus.cachehw;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы
    private final Map<K, V> map = new WeakHashMap<>();
    private List<WeakReference<HwListener<K, V>>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
        listeners.forEach(listener -> {
            if (listener.get() != null) listener.get().notify(key, value, "put");
        });
    }

    @Override
    public void remove(K key) {
        V value = map.get(key);
        map.remove(key);
        for (WeakReference<HwListener<K, V>> listener : listeners) {
            if (listener.get() != null) listener.get().notify(key, value, "remove");
        }
    }

    @Override
    public V get(K key) {
        V value = map.get(key);
        for (WeakReference<HwListener<K, V>> listener : listeners) {
            if (listener.get() != null) listener.get().notify(key, value, "get");
        }
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(new WeakReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        WeakReference<HwListener<K, V>> removeListener = null;
        for (WeakReference<HwListener<K, V>> weakListener : listeners) {
            if (weakListener.get().equals(listener)) {
                removeListener = weakListener;
            }
        }
        listeners.remove(removeListener);
    }
}
