package INF100labs;

import java.util.Scanner;

/**
 * Implement the methods task1, and task2.
 * These programming tasks was part of lab1 in INF100 fall 2022/2023. You can find them here: https://inf100h22.stromme.me/lab/1/
 */
public class Lab1 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Call the methods here to test them on different inputs
    }

    public static void task1() {
        System.out.println("Hei, det er meg, datamaskinen. " + "\n" + "Hyggelig å se deg her." + "\n" + "Lykke til med INF101!");
    }

    public static void task2() {
        sc = new Scanner(System.in); // Do not remove this line
        System.out.println("Hva er ditt navn?");
        System.out.println("Hva er din adresse?");
        System.out.println("Hva er ditt postnummer og poststed?");
        
        String Name = sc.nextLine(); 
        String Adresse = sc.nextLine();
        String post = sc.nextLine(); 
        
        System.out.println("Kari Nordmanns adresse er: ");
        System.out.println("");
        System.out.println(Name);
        System.out.println(Adresse);
        System.out.println(post);

        ;
    }

    /**
     * Reads input from console with given prompt
     * @param prompt
     * @return string input answer from user
     */
    private static String readInput(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }


}
