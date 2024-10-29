import java.util.LinkedList;
import java.util.*;
class HashMaps<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size;
    public HashMaps(int capacity) {
        table = new LinkedList[capacity];
        size=0;
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public V get(K key){
        int index=getHash(key);
        if(table[index]==null){
            return null;
        }
        for(Entry<K,V>entry:table[index]){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }
    public V remove(K key){
        int index =getHash(key);
        if(table[index]==null){
            return null;
        }

        for(int i=0;i<table.length;i++){
            Entry<K,V>entry=table[index].get(i);
            if(entry.key.equals(key)){
                V oldvalue=entry.value;
                table[index].remove(i);
                size--;
                return oldvalue;
            }
        }
        return null;
    }


    public void put(K key, V value) {
        int index = getHash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; 

                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }
    public Integer size(){
        return size;
    }
    public Boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    System.out.println("Key: " + entry.key + ", Value: " + entry.value);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMaps<Boolean, Integer> h1 = new HashMaps<>(6);
        h1.put(true, 23);
        h1.put(false, 2);
        h1.put(true, 45);
        h1.display();
        System.out.println(h1.get(true));
        System.out.println(h1.size());
        
        HashMaps<Integer, String> h2 = new HashMaps<>(6);
        h2.put(1, "One");
        h2.put(2, "Two");
        h2.put(1, "Updated One");
        h2.remove(1);
        h2.display();
        System.out.println(h2.isEmpty());

        HashMap<Integer,String> h=new HashMap<>();
        // h.put(null, null)
        
    }
}
