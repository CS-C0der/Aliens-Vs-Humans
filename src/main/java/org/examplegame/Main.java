package org.examplegame;


import java.util.Scanner;

// ToDO refactor no static variables
public class Main {
    public static void main(String[] args) {

        System.out.print("Welcome to the Game Aliens vs. Humans!\n\n");
        System.out.println("First we want to set the size of the Teams (both Teams will have same size at the Beginning)");
        System.out.println("Please enter a number between 1 and 10:");

        int teamMemberCount = getTeamMemberCountFromInput() ;

        System.out.println("To make the game a bit more diversified we want to add some Cats to the battlefield! ");
        System.out.println("Cats are not very loyal, so the cats are randomly assigned to either Team Human or Team Alien!");
        System.out.println("Now please enter the number of Cats that should be added to the battlefield at the beginning: ");

        int catCount = getCatCountFromInput();

        System.out.println("Thank you! Now we can start...\n");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        Battlefield battlefield = new Battlefield(teamMemberCount, catCount);
        battlefield.startWar();

    }

    private static int getTeamMemberCountFromInput(){
        Scanner userInput = new Scanner(System.in);
        boolean invalidInput = true;
        int teamMemberCount = 0;

        while (invalidInput){
            if (userInput.hasNextInt()){
                teamMemberCount = userInput.nextInt();
                if (teamMemberCount >= 1 && teamMemberCount <= 10 ){
                    invalidInput = false;
                } else {
                    System.out.println("Invalid input: Number should be between 1 and 10! Please try again: ");
                    userInput.nextLine();
                }
            } else {
                System.out.println("Invalid input: input was not a number! Please select a number between 1 and 10: ");
                userInput.nextLine();
            }
        }

        return teamMemberCount;
    }

    private static int getCatCountFromInput(){
        Scanner userInput = new Scanner(System.in);
        boolean invalidInput = true;
        int catCount = 0;

        while (invalidInput){
            if (userInput.hasNextInt()){
                catCount = userInput.nextInt();
                if (catCount >= 0 && catCount <= 10 ){
                    invalidInput = false;
                } else {
                    System.out.println("Invalid input: Number should be between 0 and 10! Please try again: ");
                    userInput.nextLine();
                }
            } else {
                System.out.println("Invalid input: input was not a number! Please select a number between 0 and 10: ");
                userInput.nextLine();
            }
        }

        return catCount;
    }

}
