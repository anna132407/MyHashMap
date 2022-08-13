public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(0,"Bob");
        myHashMap.put(0,"Mila");
        myHashMap.put(1,"Tom");
        myHashMap.put(2,"Mike");
        myHashMap.put(2,"Lola");
        myHashMap.put(3,"Ann");
        System.out.println(myHashMap.toString());
        System.out.println(myHashMap.get(0));
        System.out.println(myHashMap.get(3));
    }
}
