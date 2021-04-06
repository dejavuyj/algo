package leetcode.algorithm.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {

    private Integer capacity;
    private Map<Integer, Integer> keyToValue;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKey;
    private Integer minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        minFreq = 0;
    }

    public int get(int key) {
        Integer value = keyToValue.get(key);
        if (value == null) {
            return -1;
        } else {
            increaseFreq(key);
            return value;
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        Integer existed = keyToValue.get(key);
        if (existed == null) {
            if (keyToValue.size() >= capacity) {
                removeMinFreqKey();
            }

            keyToValue.put(key, value);
            keyToFreq.put(key, 1);
            LinkedHashSet<Integer> set = freqToKey.get(1);
            if (set == null) {
                set = new LinkedHashSet<>();
                freqToKey.put(1, set);
            }
            set.add(key);
            minFreq = 1;
        } else {
            keyToValue.put(key, value);
            increaseFreq(key);
        }
    }

    private void increaseFreq(int key) {
        // 更新频率
        int oldFreq = keyToFreq.get(key);
        int newFreq = oldFreq + 1;
        keyToFreq.put(key, newFreq);
        freqToKey.get(oldFreq).remove(key);
        LinkedHashSet<Integer> newFreqSet = freqToKey.get(newFreq);
        if (newFreqSet == null) {
            newFreqSet = new LinkedHashSet<>();
            freqToKey.put(newFreq, newFreqSet);
        }
        newFreqSet.add(key);

        if (freqToKey.get(oldFreq).size() == 0) {
            if (oldFreq == minFreq) {
                minFreq++;
            }
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> list = freqToKey.get(minFreq);
        Integer delKey = list.iterator().next();
        list.remove(delKey);

        keyToValue.remove(delKey);
        keyToFreq.remove(delKey);
    }
}

public class Code460_lfu_cache {

    public static void main(String[] args) {
        LFUCache c = new LFUCache(2);
        int v;

        c.put(1, 1);
        c.put(2, 2);
        v = c.get(1);
        System.out.println(v);
        c.put(3, 3);
        v = c.get(2);
        System.out.println(v);
        v = c.get(3);
        System.out.println(v);
        c.put(4, 4);
        v = c.get(1);
        System.out.println(v);
        v = c.get(3);
        System.out.println(v);
        v = c.get(4);
        System.out.println(v);
    }
}
