package rockPaperScissors;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;


public class RockPaperScissors {
	
    public static void main(String[] args) {
    	/* 	
    	 * The code here does two things:
    	 * It first creates a new RockPaperScissors -object with the
    	 * code `new RockPaperScissors()`. Then it calls the `run()`
    	 * method on the newly created object.
         */
        new RockPaperScissors().run();
    } 

    Scanner sc = new Scanner(System.in);
    int roundCounter = 1;
    int humanScore = 0;
    int computerScore = 0;
    List<String> rpsChoices = Arrays.asList("rock", "paper", "scissors");
    boolean continue_game = true;

    public void run() {
        
        while (continue_game){
            System.out.println("Let's play round " + roundCounter);
            String user = user_choice();
            String computer = random_choice();
            if (outcome(user, computer) == 0 ) {
                System.out.println("Human chose " + user + ", computer chose " + computer + ", Its a tie");
                System.out.println("Score: human " + humanScore + ", computer " + computerScore);
            }
            if (outcome(user, computer) == 1) {
                System.out.println("Human chose " + user + ", computer chose " + computer + ", human wins!" );
                humanScore += 1;
                System.out.println("Score: human " + humanScore + ", computer " + computerScore);
                
            }
            if (outcome(user, computer) == 2) {
                System.out.println("Human chose " + user + ", computer chose " + computer + ", Computer wins!");
                computerScore += 1;
                System.out.println("Score: human " + humanScore + ", computer " + computerScore);
                
            }
            roundCounter += 1;
            user_choice_continue();
        }   
    }
    public String random_choice(){
        Random random = new Random();
        int choose_random = random.nextInt(rpsChoices.size());
        return rpsChoices.get(choose_random);
    }
    
    public int outcome(String choice1, String choice2) {
        if (choice1.equals(choice2)) {
            return 0;
        }
        else if ((choice1.equals("rock") && choice2.equals("scissors")) || (choice1.equals("paper") &&  choice2.equals("rock")) || (choice1.equals("scissors") && choice2.equals("paper"))) {
            return 1;
        }
        else {
            return 2;
        }
    }
    /*
     *          rock        paper       scissors
     * rock     0           1           2
     * paper    2           0           1
     * scissors 1           2           0
     */

    public String user_choice() {
        String main_choice = readInput("Your choice (Rock/Paper/Scissors)?");
        main_choice.toLowerCase();
        while (!(main_choice.equals("rock") || main_choice.equals("paper") || main_choice.equals("scissors"))) {
            System.out.println("I do not understand " + main_choice + ". Could you try again?");
            main_choice = readInput("Your choice (Rock/Paper/Scissors)?");
        }
        return main_choice;
    }
    public boolean user_choice_continue() {
        String choice_continue = readInput("Do you wish to continue playing? (y/n)?");
        choice_continue.toLowerCase();
        while (!(choice_continue.equals("y") || choice_continue.equals("n"))) {
            System.out.println("I do not understand " + choice_continue + ". Could you try again?");
            choice_continue = readInput("Do you wish to continue playing? (y/n)?");
        }
        if (choice_continue.equals("n")){
            System.out.println("Bye bye :)");
            continue_game = false;
        }
        return choice_continue.equals("y");
    }
    


    /**
     * Reads input from console with given prompt
     * @param prompt
     * @return string input answer from user
     */
    public String readInput(String prompt) {
        System.out.println(prompt);
        String userInput = sc.next();
        return userInput;
    }

}