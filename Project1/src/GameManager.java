import java.util.Scanner;

public class GameManager {

    private Player axisPlayer;

    private Player alliedPlayer;

    private static String[][] gameLocations;


    public GameManager(String axisPLayer,String alliedPlayer){
        this.axisPlayer = new Player(axisPLayer,"Axis");
        this.alliedPlayer = new Player(alliedPlayer,"Allied");
        gameLocations = new String[9][13];
    }

    public static void setGameLocations(String name, int x, int y) {
        gameLocations[y][x] = name;
    }

    public void setLocations(String teamName){
        if(teamName.equals("Axis")){
            System.out.println("setting each forces locations.write the locations like:x y");
            System.out.println("Axis region valid x is between 0 to 12 and y is between 0 to 2");
            System.out.println("Soldiers:");
            checkAndInitializeLocationsAxis(MilitaryTypes.SOLDIER);
            System.out.println("Tanks:");
            checkAndInitializeLocationsAxis(MilitaryTypes.TANK);
        }
        else if(teamName.equals("Allied")){
            System.out.println("setting each forces locations.write the locations like:x y");
            System.out.println("Axis region valid x is between 0 to 12 and y is between 3 to 8");
            System.out.println("Soldiers:");
            checkAndInitializeLocationsAllied(MilitaryTypes.SOLDIER);
            System.out.println("Tanks:");
            checkAndInitializeLocationsAllied(MilitaryTypes.TANK);
            System.out.println("Artillery:");
            checkAndInitializeLocationsAllied(MilitaryTypes.ARTILLERY);
        }
    }
    private void checkAndInitializeLocationsAxis(MilitaryTypes ml){
        int[] temp = new int[2];
        Scanner input = new Scanner(System.in);
        for(Pile pl : axisPlayer.getForces().get(ml)){
            do{
                System.out.print(pl.getName() + " : " );
                temp[0] = input.nextInt();
                temp[1] = input.nextInt();
                if(temp[0] < 0 || temp[0] > 12 || temp[1] < 0 || temp[1] > 2){
                    System.out.println("Error! Please try valid locations");
                }
                else{break;}
            }while(true);
            pl.setLocations(temp[0],temp[1]);
        }
    }
    private void checkAndInitializeLocationsAllied(MilitaryTypes ml){
        int[] temp = new int[2];
        Scanner input = new Scanner(System.in);
        for(Pile pl : alliedPlayer.getForces().get(ml)){
            do{
                System.out.print(pl.getName() + " : " );
                temp[0] = input.nextInt();
                temp[1] = input.nextInt();
                if(temp[0] < 0 || temp[0] > 12 || temp[1] < 3 || temp[1] > 8){
                    System.out.println("Error! Please try valid locations");
                }
                else{break;}
            }while(true);
            pl.setLocations(temp[0],temp[1]);
        }
    }

    public static String[][] getGameLocations() {
        return gameLocations;
    }
}
