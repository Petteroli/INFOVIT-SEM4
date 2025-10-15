package INF100labs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implement the methods removeRow and allRowsAndColsAreEqualSum.
 * These programming tasks was part of lab7 in INF100 fall 2022/2023. You can find
 * them here: https://inf100h22.stromme.me/lab/7/
 */
public class Lab7 {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> grid1 = new ArrayList<>();
    grid1.add(new ArrayList<>(Arrays.asList(3, 0, 9)));
    grid1.add(new ArrayList<>(Arrays.asList(4, 5, 3)));
    grid1.add(new ArrayList<>(Arrays.asList(6, 8, 1)));

    boolean equalSums1 = allRowsAndColsAreEqualSum(grid1);
    System.out.println(equalSums1); // false


    }

    public static void removeRow(ArrayList<ArrayList<Integer>> grid, int row) {
        grid.remove(row);
    }

    public static boolean allRowsAndColsAreEqualSum(ArrayList<ArrayList<Integer>> grid) {
        int startSum = 0; 
        boolean same_sum = true;
        for (int i = 0; i < grid.size(); i++) {
            int sum = 0;
            for (int j = 0; j < grid.get(i).size(); j++) {
                sum += grid.get(i).get(j);
            }
            if (i == 0) {
                startSum = sum;
            }
            if (startSum != sum) {
                same_sum = false;
            }

        }
        
        int startSum2 = 0;
        boolean same_sum2 = true;
        for (int i = 0; i < grid.get(0).size(); i++){
            int sum2 = 0;
            for(int j = 0; j < grid.size(); j++) {
                sum2 += grid.get(j).get(i);
            }
            if (i == 0) {
                startSum2 = sum2;
            }
            if (startSum2 != sum2) {
                same_sum2 = false;
            }
        }
        if (same_sum && same_sum2) {
            return true;
        }
        return false;
        

        
    }

}