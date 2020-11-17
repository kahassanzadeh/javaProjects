
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
        String turn = "Allied";
        System.out.println("Allied will start the game");
        while(gm.checkEndOfTheGame() == null){
            int groups = gm.choosingCard(turn);
            ArrayList<Pile> tempPile = new ArrayList<>();
            if(groups != 5){
                for(int i = 0; i < groups; i++ ){
                    tempPile.add(gm.movePile(turn));
                    gm.mapShow();
                }
                for(Pile pi : tempPile){
                    gm.attack(pi,turn);
                    gm.mapShow();
                }
            }
            else{
                System.out.println("notice that the first type you entered as the first force. \n" +
                        "you should enter the same type as the first force");
                for(int i = 0; i < 3; i++ ){
                    tempPile.add(gm.movePile(turn));
                    if(i > 1 && !tempPile.get(tempPile.size() - 1).getName().substring(2,4).equals(tempPile.get(tempPile.size() - 2).getName().substring(2,4)) ){
                        tempPile.remove(tempPile.size() - 1);
                        System.out.println("Please enter the same type that you used as first force");
                        tempPile.add(gm.movePile(turn));
                        i--;
                        continue;
                    }
                    gm.mapShow();
                }
                for(Pile pi : tempPile){
                    gm.attack(pi,turn);
                    gm.mapShow();
                }
            }
            gm.renewCanAttack();
            counter++;
            if(counter % 2 == 1){turn = "Axis";}
            else{turn = "Allied";}
        }
        if(gm.getAlliedPlayerMedals() == 6){
            System.out.println("Congratulations to " + Colors.MAGENTA + gm.getAlliedPlayer().getName() + Colors.RESET +  " you are the winner");
        }
        if(gm.getAxisPlayerMedals() == 6){
            System.out.println("Congratulations to " + Colors.MAGENTA + gm.getAxisPlayer().getName() + Colors.RESET +  " you are the winner");
        }

    }
}
