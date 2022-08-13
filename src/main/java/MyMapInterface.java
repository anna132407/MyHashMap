public interface MyMapInterface<K,V>{
    int size();
    boolean isEmpty();
    Object get(Object key);
    Object put(Object key,Object value);

    interface Entry<K,V>{
        K getKey();
        V getValue();
    }

}
