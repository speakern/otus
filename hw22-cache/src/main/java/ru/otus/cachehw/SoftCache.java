package ru.otus.cachehw;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

public class SoftCache<K, V>
{
    private HashMap<K, WeakReference<V>> map = new HashMap<>();

    public V get(K key)
    {
        WeakReference<V> softRef = map.get(key);

        if (softRef==null)
            return null;

        return softRef.get();
    }

    public Object put(K key, V value)
    {
        WeakReference<V> softRef = map.put(key, new WeakReference<V>(value));

        if (softRef==null)
            return null;

        V oldValue = softRef.get();
        softRef.clear();

        return oldValue;
    }

    public V remove(K key)
    {
        WeakReference<V> softRef = (WeakReference<V>)map.remove(key);

        if (softRef==null)
            return null;

        V oldValue = softRef.get();
        softRef.clear();

        return oldValue;
    }
}
