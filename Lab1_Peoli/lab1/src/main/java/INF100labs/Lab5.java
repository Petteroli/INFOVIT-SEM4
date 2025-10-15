package INF100labs;

import java.util.ArrayList;

/**
 * Implement the methods removeThrees, uniqueValues and addList.
 * These programming tasks was part of lab5 in INF100 fall 2022/2023. You can find them here: https://inf100h22.stromme.me/lab/5/
 */
public class Lab5 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs

    }

    public static ArrayList<Integer> multipliedWithTwo(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++ ) {
            newList.add(list.get(i) * 2);
        }   return newList;
    }

    public static ArrayList<Integer> removeThrees(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++ ) {
            if (list.get(i) != 3) {
                newList.add(list.get(i));
            } 
        }   return newList;
    }

    public static ArrayList<Integer> uniqueValues(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++ ) {
            int num = list.get(i);
            boolean num_not_exist = true;
            for (int j = 0; j < newList.size(); j++) {
                if (num == newList.get(j)){
                    num_not_exist = false;
                
                } 
            }
            if (num_not_exist) {
                newList.add(num);
            }     
             
        }   return newList;
    }

    public static void addList(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i = 0; i < a.size(); i++ ) {
           a.set(i, a.get(i) + b.get(i));
        }
    }


}
