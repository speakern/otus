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
    private final List<WeakReference<HwListener<K, V>>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
        notify(key, value, "put");
    }

    @Override
    public void remove(K key) {
        V value = map.get(key);
        map.remove(key);
        notify(key, value, "remove");
    }

    @Override
    public V get(K key) {
        V value = map.get(key);
        notify(key, value, "get");
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(new WeakReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        int removeIndex = 0;
        for (int i = 0; i < listeners.size(); i++) {
            if (listeners.get(i)!= null) {
                if (listeners.get(i).get().equals(listener)) {
                    removeIndex = i;
                    break;
                }
            }
        }
        listeners.remove(removeIndex);
    }

    private void notify(K key, V value, String actionName) {
        try {
            listeners.forEach(listener -> {
                if (listener != null && listener.get() != null) listener.get().notify(key, value, actionName);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
