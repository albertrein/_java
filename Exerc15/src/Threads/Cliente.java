package Threads;

import java.util.HashMap;

public class Cliente {
    public static void main(String[] args) {
        HashMap<String,Integer> testeHash = new HashMap<>();

        testeHash.put("t1",001);
        testeHash.put("t2",002);
        testeHash.put("t4",004);
        testeHash.put("t3",003);

        String findThis = "t4"; //String de busca no HashMap

        for(String i : testeHash.keySet()){
            if(i.equals(findThis))
                System.out.println(i);
        }

    }
}
