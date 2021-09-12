package CollectionsSamples;

import java.util.HashMap;
import java.util.Map;

public class HashMapSamples {
    public static void main(String[] args) {
        typicalUsage();
        HashMap<StringBuffer, String> hm = new HashMap<>();
    }

    static void typicalUsage(){
        HashMap<Integer, String> mapping = new HashMap<>(100);
        mapping.put(1, "car");
        mapping.put(2, "cat");

        System.out.println(mapping.get(1)); // car
        System.out.println(mapping.containsKey(2)); // true

        // Iterate through HashMap
        for (Map.Entry<Integer, String> element : mapping.entrySet()){
            Integer key = element.getKey();
            String value = element.getValue();
            System.out.println(key + " " + value);
        }
    }
}