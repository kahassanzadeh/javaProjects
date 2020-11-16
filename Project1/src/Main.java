
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Hi Welcome to the game \n" +
                "Please write the name of the Axis Player: ");
        String tempAxisName = input.nextLine();
        System.out.print("Please enter the name of the Allied Player : ");
        String tempAlliedName = input.nextLine();
        GameManager gm = new GameManager(tempAxisName,tempAlliedName);
        gm.mapShow();
        System.out.print("now you should choose if you want to play with the default locations or you want to enter the locations \n" +
                "Type 1 for the default locations and 2 for entering the locations of the forces : ");
        if(input.nextInt() == 1){
            gm.defaultLocationsAxis();
            gm.defaultLocationAllied();
        }
        else{
            System.out.println("Axis set locations");
            gm.setLocations("Axis");
            System.out.println("Allied set locations");
            gm.setLocations("Allied");
        }
        gm.mapShow();
        int counter = 0;
        String turn = "Axis";
        System.out.println("Axis will start the game");
        while(gm.checkEndOfTheGame() == null){
            int groups = gm.choosingCard(turn);
            ArrayList<Pile> tempPile = new ArrayList<>();
            if(groups != 5){
                for(int i = 0; i < groups; i++ ,counter++){
                    tempPile.add(gm.movePile(turn));
                    gm.mapShow();
                }
                for(Pile pi : tempPile){
                    gm.attack(pi,turn);
                    gm.mapShow();
                }
            }
        }

    }
}
