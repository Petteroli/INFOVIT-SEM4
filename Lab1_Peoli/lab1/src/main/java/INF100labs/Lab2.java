package INF100labs;

import java.util.Arrays;
import java.util.Collections;

/**
 * Implement the methods findLongestWords, isLeapYear and isEvenPositiveInt.
 * These programming tasks was part of lab2 in INF100 fall 2022/2023. You can find them here: https://inf100h22.stromme.me/lab/2/
 */
public class Lab2 {
    
    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
        findLongestWords("game","action","Champion");
        findLongestWords("apple", "carrot", "ananas");
        findLongestWords("Four", "Five", "Nine");

        isLeapYear(2022);
        isLeapYear(1996);
        isLeapYear(1900);
        isLeapYear(2000);

        isEvenPositiveInt(123456);
        isEvenPositiveInt(-2);
        isEvenPositiveInt(123);


    }

    public static void findLongestWords(String word1, String word2, String word3) {
        if ((word1.length()) >= (word2.length())) {
            if ((word1.length()) >= (word3.length())) {
                System.out.println(word1);
                
        }};
        if ((word2.length()) >= (word3.length())) {
            if ((word2.length()) >= (word1.length())) {
                System.out.println(word2);
          
        }};
        if ((word3.length()) >= (word2.length())) {
            if ((word3.length()) >= (word3.length())) {
                System.out.println(word3);
              
        }};
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            return true;
    }  else {
           return false;
        }
    };

    public static boolean isEvenPositiveInt(int num) {
       if ((num % 2 == 0) && (num >= 0 )){
            return true;
    }  else {
           return false;
        }
    };

}
