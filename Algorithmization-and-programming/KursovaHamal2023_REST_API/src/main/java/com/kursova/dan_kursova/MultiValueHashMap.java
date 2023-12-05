package com.kursova.dan_kursova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MultiValueHashMap<K, V> {
    private final HashMap<K, ArrayList<V>> map;

    // Constructor
    public MultiValueHashMap() {
        map = new HashMap<>();
    }

    public void put(K key, V value) {
        
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public List<V> get(K key) {
        return map.getOrDefault(key, new ArrayList<>());
    }

    public void remove(K key) {
        if(map.containsKey(key))
            map.remove(key);
    }

    public Set<Map.Entry<K,ArrayList<V>>> entrySet() {
        return map.entrySet();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public void clear() {
         map.clear();
    }

}