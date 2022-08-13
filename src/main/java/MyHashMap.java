public class MyHashMap<K,V> implements MyMapInterface{
    private final int DEFAULT_INITIAL_CAPACITY = 16;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];
    private  int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object put(Object key, Object value) {
        int hashValue = hash(key);
        int i = indexFor(hashValue,table.length);
        for(Node node = table[i];node != null; node = node.next){
            Object k;
            if(node.hash == hashValue && ((k = node.key)==key||key.equals(k))){
                Object oldValue = node.value;
                node.value = value;
                return  oldValue;
            }
        }
        addEntry(key,value,hashValue,i);
        return null;
    }

    @Override
    public Object get(Object key) {
        int hashValue = hash(key);
        int i = indexFor(hashValue,table.length);
        for(Node node = table[i];node != null;node = node.next){
            if(node.key.equals(key) && hashValue == node.hash){
                return node.value;
            }
        }
        return null;
    }

    public void addEntry(Object key,Object value,int hashValue,int i){
        if(++size >= table.length * DEFAULT_LOAD_FACTOR){
            Node[] newTable = new Node[table.length << 1];
            System.arraycopy(table,0,newTable,0,table.length);
            transfer(table,newTable);
            table = newTable;
        }
        Node eNode = table[i];
        table[i] = new Node(hashValue,key,value,eNode);
    }


    public void transfer (Node [] src, Node [] newTable) {
        int newCapacity = newTable.length;
        for (int j = 0; j <src.length; j ++) {
            Node e = src [j];
            if (e != null) {
                src [j] = null;
                do {
                    Node next = e.next;
                    int i = indexFor (e.hash, newCapacity);
                    e.next = newTable [i];
                    newTable [i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    public int indexFor(int hashValue,int length){
        return hashValue % length;
    }

    public int hash(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : table) {
            if (node != null)
                sb = sb.append(node);
        }
        return sb.toString();
    }


    static class Node implements MyMapInterface.Entry{
        int hash;
        Object key;
        Object value;
        Node next;
        Node(int hash,Object key,Object value,Node next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key +","+ value +
                    "\n";
        }
    }

}
