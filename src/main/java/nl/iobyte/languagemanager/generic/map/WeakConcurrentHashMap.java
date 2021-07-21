package nl.iobyte.languagemanager.generic.map;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class WeakConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private final Map<K, Long> timeMap = new ConcurrentHashMap<>();
    private final long expiryInMillis;
    private boolean mapAlive = true;
    private Consumer<V> consumer;

    public WeakConcurrentHashMap() {
        this.expiryInMillis = 10 * 1000;
        initialize();
    }

    public WeakConcurrentHashMap(long expiryInMillis) {
        this.expiryInMillis = expiryInMillis;
        initialize();
    }

    void initialize() {
        new CleanerThread().start();
    }

    public void setConsumer(Consumer<V> consumer) {
        this.consumer = consumer;
    }

    @Override
    public V put(K key, V value) {
        if (!mapAlive)
            throw new IllegalStateException("WeakConcurrent Hashmap is no more alive.. Try creating a new one.");

        Date date = new Date();
        timeMap.put(key, date.getTime());
        return super.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if (!mapAlive)
            throw new IllegalStateException("WeakConcurrent Hashmap is no more alive.. Try creating a new one.");

        Date date = new Date();
        timeMap.put((K) key, date.getTime());
        return super.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (!mapAlive)
            throw new IllegalStateException("WeakConcurrent Hashmap is no more alive.. Try creating a new one.");

        for (K key : m.keySet())
            put(key, m.get(key));
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!mapAlive)
            throw new IllegalStateException("WeakConcurrent Hashmap is no more alive.. Try creating a new one.");

        return !containsKey(key) ? put(key, value) : get(key);
    }

    public void quitMap() {
        mapAlive = false;
    }

    public boolean isAlive() {
        return mapAlive;
    }

    class CleanerThread extends Thread {

        @Override
        public void run() {
            while (mapAlive) {
                cleanMap();
                try {
                    Thread.sleep(expiryInMillis / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void cleanMap() {
            long currentTime = new Date().getTime();
            V value;
            for (K key : timeMap.keySet())
                if (currentTime > (timeMap.get(key) + expiryInMillis)) {
                    timeMap.remove(key);
                    value = remove(key);
                    if(consumer != null)
                        consumer.accept(value);
                }
        }

    }

}
