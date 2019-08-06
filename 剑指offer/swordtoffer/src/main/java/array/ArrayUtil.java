package array;

import java.util.ArrayList;

public class ArrayUtil {
    public static void print(Object[] obs){
        for (int i = 0; i < obs.length; i++) {
            System.out.print(obs[i]+",");
        }
        System.out.println();
    }

    public static void printList(ArrayList obs){
        for (int i = 0; i < obs.size(); i++) {
            System.out.print(obs.get(i)+",");
        }
        System.out.println();
    }
}
