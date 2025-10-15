package INF100labs;

/**
 * Implement the methods multiplesOfSevenUpTo, multiplicationTable and crossSum.
 * These programming tasks was part of lab3 in INF100 fall 2022/2023. You can find them here: https://inf100h22.stromme.me/lab/3/
 */
public class Lab3 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs

        multiplesOfSevenUpTo(49);
        multiplesOfSevenUpTo(35);
        multiplesOfSevenUpTo(80);

        multiplicationTable(3);
        multiplicationTable(5);
       
        crossSum(1);
        crossSum(12);
        crossSum(123);
        crossSum(1234);
        crossSum(4321);

    }

    public static void multiplesOfSevenUpTo(int n) {
        for (int i = 7; i <= n; i += 7) {
            System.out.println(i);
    }
}

    public static void multiplicationTable(int n) {
       String number;
        for (int i = 1; i <= n; i++) {
            number = i + ":";
            for (int j = 1; j <= n; j++) {
                number += " " + (j*i);
            }
            System.out.println(number);
        }
    }  

    public static int crossSum(int num) {
        String converted = Integer.toString(num);
        int sum = 0;
        for (int i = 0; i < converted.length(); i++) {
                sum += Character.getNumericValue(converted.charAt(i));
        }
        return sum; 
    }
    
}
        
    

