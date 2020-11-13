import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

    private Player axisPlayer;

    private Player alliedPlayer;

    private static String[][] gameLocations;

    private static String[][] locationTypes;


    public GameManager(String axisPLayer, String alliedPlayer) {
        this.axisPlayer = new Player(axisPLayer, "Axis");
        this.alliedPlayer = new Player(alliedPlayer, "Allied");
        gameLocations = new String[9][13];
        locationTypes = new String[9][13];
        setLocationTypes();
    }

    public static void setGameLocations(String name, int x, int y) {
        gameLocations[y][x] = name;
    }

    public static void setLocationTypesArray(String name, int x, int y) {
        locationTypes[y][x] = name;
    }

    public void setLocations(String teamName) {
        if (teamName.equals("Axis")) {
            System.out.println("setting each forces locations.write the locations like:x y");
            System.out.println("Axis region valid x is between 0 to 12 and y is between 0 to 2");
            System.out.println("Soldiers:");
            checkAndInitializeLocationsAxis(MilitaryTypes.SOLDIER);
            System.out.println("Tanks:");
            checkAndInitializeLocationsAxis(MilitaryTypes.TANK);
        } else if (teamName.equals("Allied")) {
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

    private void checkAndInitializeLocationsAxis(MilitaryTypes ml) {
        int[] temp = new int[2];
        Scanner input = new Scanner(System.in);
        for (Pile pl : axisPlayer.getForces().get(ml)) {
            do {
                System.out.print(pl.getName() + " : ");
                temp[0] = input.nextInt();
                temp[1] = input.nextInt();
                if (temp[0] < 0 || temp[0] > 12 || temp[1] < 0 || temp[1] > 2 || gameLocations[temp[1]][temp[0]] != null) {
                    System.out.println("Error! Please try valid locations");
                }
                else if(locationTypes[temp[1]][temp[0]].equals("RV") ||(locationTypes[temp[1]][temp[0]].equals("SH") && ((ml == MilitaryTypes.TANK) || ml == MilitaryTypes.ARTILLERY))){
                    System.out.println("Error! Please try valid locations");
                }
                else{break;}
            } while (true);
            pl.setLocations(temp[0], temp[1]);
        }
    }

    private void checkAndInitializeLocationsAllied(MilitaryTypes ml) {
        int[] temp = new int[2];
        Scanner input = new Scanner(System.in);
        for (Pile pl : alliedPlayer.getForces().get(ml)) {
            do {
                System.out.print(pl.getName() + " : ");
                temp[0] = input.nextInt();
                temp[1] = input.nextInt();
                if (temp[0] < 0 || temp[0] > 12 || temp[1] < 3 || temp[1] > 8 || gameLocations[temp[1]][temp[0]] != null) {
                    System.out.println("Error! Please try valid locations");
                }
                else if(locationTypes[temp[1]][temp[0]].equals("RV") ||(locationTypes[temp[1]][temp[0]].equals("SH") && ((ml == MilitaryTypes.TANK) || ml == MilitaryTypes.ARTILLERY))){
                    System.out.println("Error! Please try valid locations");
                }else {
                    break;
                }
            } while (true);
            pl.setLocations(temp[0], temp[1]);
        }
    }

    private void setLocationTypes() {
        LocationTypes[] tempLocationTypes = new LocationTypes[37];
        tempLocationTypes[0] = new LocationTypes("AL", 11, 8);
        tempLocationTypes[1] = new LocationTypes("JG", 3, 7);
        tempLocationTypes[2] = new LocationTypes("JG", 4, 7);
        tempLocationTypes[3] = new LocationTypes("JG", 8, 7);
        tempLocationTypes[4] = new LocationTypes("CC", 1, 6);
        tempLocationTypes[5] = new LocationTypes("JG", 7, 6);
        tempLocationTypes[6] = new LocationTypes("JG", 8, 6);
        tempLocationTypes[7] = new LocationTypes("JG", 3, 5);
        tempLocationTypes[8] = new LocationTypes("HL", 4, 5);
        tempLocationTypes[9] = new LocationTypes("HL", 10, 5);
        tempLocationTypes[10] = new LocationTypes("JG", 11, 5);
        tempLocationTypes[11] = new LocationTypes("RV", 0, 4);
        tempLocationTypes[12] = new LocationTypes("JG", 1, 4);
        tempLocationTypes[12] = new LocationTypes("HL", 5, 4);
        tempLocationTypes[13] = new LocationTypes("CC", 6, 4);
        tempLocationTypes[14] = new LocationTypes("JG", 8, 4);
        tempLocationTypes[15] = new LocationTypes("CC", 10, 4);
        tempLocationTypes[16] = new LocationTypes("HL", 11, 4);
        tempLocationTypes[17] = new LocationTypes("JG", 12, 4);
        tempLocationTypes[18] = new LocationTypes("BR", 0, 3);
        tempLocationTypes[19] = new LocationTypes("JG", 1, 3);
        tempLocationTypes[20] = new LocationTypes("JG", 3, 3);
        tempLocationTypes[21] = new LocationTypes("JG", 11, 3);
        tempLocationTypes[22] = new LocationTypes("AX", 0, 2);
        tempLocationTypes[23] = new LocationTypes("RV", 1, 2);
        tempLocationTypes[24] = new LocationTypes("HL", 6, 2);
        tempLocationTypes[25] = new LocationTypes("JG", 9, 2);
        tempLocationTypes[26] = new LocationTypes("JG", 12, 2);
        tempLocationTypes[27] = new LocationTypes("RV", 1, 1);
        tempLocationTypes[28] = new LocationTypes("RV", 2, 1);
        tempLocationTypes[29] = new LocationTypes("RV", 3, 1);
        tempLocationTypes[30] = new LocationTypes("SH", 4, 1);
        tempLocationTypes[31] = new LocationTypes("HL", 6, 1);
        tempLocationTypes[32] = new LocationTypes("HL", 0, 0);
        tempLocationTypes[33] = new LocationTypes("HL", 1, 0);
        tempLocationTypes[32] = new LocationTypes("CC", 3, 0);
        tempLocationTypes[34] = new LocationTypes("BR", 4, 0);
        for(int i = 0;i < 9;i++){
            for(int j = 0; j < 13;j++){
                if(locationTypes[i][j] == null){
                    locationTypes[i][j] = "NO";
                }
            }
        }
    }

    public static String[][] getGameLocations() {
        return gameLocations;
    }

    private int randomNumberGenerator() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public static String[][] getLocationTypes() {
        return locationTypes;
    }

    public void movePile(String team) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the name of the force that you want to move : ");
        String tempName = input.next();
        if (team.equals("Axis")) {
            if (tempName.contains("So")) {
                for (Pile pl : axisPlayer.getForces().get(MilitaryTypes.SOLDIER)) {
                    if (pl.getName().equals(tempName)) {
                        if(locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("JG") || locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("CC")){
                            System.out.println("you can't move this force");
                            break;
                        }
                        do {
                            System.out.print("Please enter the directions that you want to move : ");
                            input.nextLine();
                            String tempMove = input.nextLine();
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){break;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);

                    }
                }
            }
            if (tempName.contains("Ta")) {
                for (Pile pl : axisPlayer.getForces().get(MilitaryTypes.TANK)) {
                    if (pl.getName().equals(tempName)) {
                        if(locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("JG") || locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("CC")){
                            System.out.println("you can't move this force");
                            break;
                        }
                        do {
                            System.out.print("Please enter the directions that you want to move : ");
                            input.nextLine();
                            String tempMove = input.nextLine();
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){break;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
        }
        else if (team.equals("Allied")) {
            if (tempName.contains("So")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.SOLDIER)) {
                    if (pl.getName().equals(tempName)) {
                        if(locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("JG") || locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("CC")){
                            System.out.println("you can't move this force");
                            break;
                        }
                        do {
                            System.out.print("Please enter the directions that you want to move : ");
                            input.nextLine();
                            String tempMove = input.nextLine();
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){break;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);

                    }
                }
            }
            if (tempName.contains("Ta")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.TANK)) {
                    if (pl.getName().equals(tempName)) {
                        if(locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("JG") || locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("CC")){
                            System.out.println("you can't move this force");
                            break;
                        }
                        do {
                            System.out.print("Please enter the directions that you want to move : ");
                            input.nextLine();
                            String tempMove = input.nextLine();
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){break;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
            if (tempName.contains("Ar")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY)) {
                    if (pl.getName().equals(tempName)) {
                        if(locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("JG") || locationTypes[pl.getLocation()[1]][pl.getLocation()[0]].equals("CC")){
                            System.out.println("you can't move this force");
                            break;
                        }
                        do {
                            System.out.print("Please enter the directions that you want to move : ");
                            input.nextLine();
                            String tempMove = input.nextLine();
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){break;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
        }
        View.viewingTheMap();
    }

    private int countingMoves(String tempMove) {
        String[] tempDirections = tempMove.split("\\s+");
        int counter = 0;
        for(String st : tempDirections){
            counter += Integer.parseInt(st.substring(0,1));
        }
        return counter;
    }

    private int[] checkDirectionAndStringToNumber(String directions, Pile pl) {
        String[] tempDirections = directions.split("\\s+");
        int[] tempPileLocation =new int[2];
        tempPileLocation[0] = pl.getLocation()[0];
        tempPileLocation[1] = pl.getLocation()[1];
        for (String st : tempDirections) {
            if (st.substring(1).equals("R")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    tempPileLocation[0] += 1;
                    if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                        tempPileLocation[0] -= 1;
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                        tempPileLocation[0] -= 1;
                        return tempPileLocation;
                    }
                }

            } else if (st.substring(1).equals("L")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    tempPileLocation[0] -= 1;
                    if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                        tempPileLocation[0] += 1;
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                        tempPileLocation[0] += 1;
                        return tempPileLocation;
                    }
                }

            } else if (st.substring(1).equals("UR")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    if(tempPileLocation[1] % 2 == 1) {
                        tempPileLocation[1] -= 1;
                        tempPileLocation[0] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] -= 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] -= 1;
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            return tempPileLocation;
                        }
                    }
                }

            } else if (st.substring(1).equals("UL")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    if(tempPileLocation[1] % 2 == 0) {
                        tempPileLocation[1] -= 1;
                        tempPileLocation[0] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] += 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] += 1;
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            return tempPileLocation;
                        }
                    }
                }
            } else if (st.substring(1).equals("DR")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    if(tempPileLocation[1] % 2 == 1) {
                        tempPileLocation[1] += 1;
                        tempPileLocation[0] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] -= 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] -= 1;
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            return tempPileLocation;
                        }
                    }
                }
            }
            else if (st.substring(1).equals("DL")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    if(tempPileLocation[1] % 2 == 0) {
                        tempPileLocation[1] += 1;
                        tempPileLocation[0] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] += 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] += 1;
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            return tempPileLocation;
                        }
                    }
                }
            }
        }
        return tempPileLocation;
    }

    public void defaultLocationsAxis(){
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(0).setLocations(0,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(1).setLocations(1,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(2).setLocations(2,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(3).setLocations(3,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(4).setLocations(4,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(5).setLocations(5,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(6).setLocations(6,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(0).setLocations(8,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(1).setLocations(9,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(2).setLocations(10,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(3).setLocations(11,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(4).setLocations(12,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(5).setLocations(6,1);
    }
    public void defaultLocationAllied(){
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(0).setLocations(1,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(1).setLocations(2,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(2).setLocations(3,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(3).setLocations(4,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(4).setLocations(5,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(5).setLocations(7,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(6).setLocations(8,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(7).setLocations(10,6);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(0).setLocations(1,8);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(1).setLocations(2,8);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(2).setLocations(3,8);
        alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY).get(0).setLocations(4,8);
        alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY).get(1).setLocations(5,8);

    }

}
