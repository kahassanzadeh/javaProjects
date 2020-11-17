import java.util.*;

public class GameManager {


    private Player axisPlayer;

    private Player alliedPlayer;

    private static int axisPlayerMedals;

    private static int alliedPlayerMedals;

    private static String[][] gameLocations;

    private static String[][] locationTypes;




    public GameManager(String axisPLayer, String alliedPlayer) {
        Cards card = new Cards();
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



    public static String[][] getLocationTypes() {
        return locationTypes;
    }

    public Pile movePile(String team) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the name of the force that you want to move or attack: ");
        String tempName = input.nextLine();
        if (team.equals("Axis")) {
            if (tempName.substring(2,4).equals("So")) {
                for (Pile pl : axisPlayer.getForces().get(MilitaryTypes.SOLDIER)) {
                    if (pl.getName().equals(tempName)) {
                        do {
                            System.out.print("Please enter the directions that you want to move (0 for not moving): ");
                            String tempMove = input.nextLine();
                            if(tempMove.equals("0")){return pl;}
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){return pl;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
            if (tempName.substring(2,4).equals("Ta")) {
                for (Pile pl : axisPlayer.getForces().get(MilitaryTypes.TANK)) {
                    if (pl.getName().equals(tempName)) {
                        do {
                            System.out.print("Please enter the directions that you want to move (0 for not moving): ");
                            String tempMove = input.nextLine();
                            if(tempMove.equals("0")){return pl;}
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){return pl;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
        }
        else if (team.equals("Allied")) {
            if (tempName.substring(2,4).equals("So")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.SOLDIER)) {
                    if (pl.getName().equals(tempName)) {
                        do {
                            System.out.print("Please enter the directions that you want to move (0 for not moving): ");
                            String tempMove = input.nextLine();
                            if(tempMove.equals("0")){return pl;}
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){return pl;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);

                    }
                }
            }
            if (tempName.substring(2,4).equals("Ta")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.TANK)) {
                    if (pl.getName().equals(tempName)) {
                        do {
                            System.out.print("Please enter the directions that you want to move (0 for not moving): ");
                            String tempMove = input.nextLine();
                            if(tempMove.equals("0")){return pl;}
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){return pl;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
            if (tempName.substring(2,4).equals("Ar")) {
                for (Pile pl : alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY)) {
                    if (pl.getName().equals(tempName)) {
                        do {
                            System.out.print("Please enter the directions that you want to move (0 for not moving): ");
                            String tempMove = input.nextLine();
                            if(tempMove.equals("0")){return pl;}
                            int[] temp = checkDirectionAndStringToNumber(tempMove,pl);
                            int tempCounterOfMove = countingMoves(tempMove);
                            if(pl.movePile(temp,tempCounterOfMove)){return pl;}
                            else{System.out.println("Error PLease try another location");}
                        }while(true);
                    }
                }
            }
        }
        return null;
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
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                        tempPileLocation[0] -= 1;
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                        tempPileLocation[0] -= 1;
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    }
                }

            } else if (st.substring(1).equals("L")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    tempPileLocation[0] -= 1;
                    if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                        tempPileLocation[0] += 1;
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                        tempPileLocation[0] += 1;
                        System.out.println("you can't move any more");
                        return tempPileLocation;
                    }
                }

            } else if (st.substring(1).equals("UR")) {
                for (int i = 0; i < Integer.parseInt(st.substring(0,1)); i++) {
                    if(tempPileLocation[1] % 2 == 1) {
                        tempPileLocation[1] -= 1;
                        tempPileLocation[0] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] -= 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] -= 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            System.out.println("you can't move any more");
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
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] += 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            tempPileLocation[0] += 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] -= 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] += 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] += 1;
                            System.out.println("you can't move any more");
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
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] -= 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] -= 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            System.out.println("you can't move any more");
                            tempPileLocation[1] -= 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            System.out.println("you can't move any more");
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
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            System.out.println("you can't move any more");
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] += 1;
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            tempPileLocation[0] += 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        }
                    }
                    else{
                        tempPileLocation[1] += 1;
                        if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("JG") || locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("CC")) {
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("RV")) {
                            tempPileLocation[1] -= 1;
                            System.out.println("you can't move any more");
                            return tempPileLocation;
                        } else if (locationTypes[tempPileLocation[1]][tempPileLocation[0]].equals("SH") && (pl.getName().contains("Ta") || pl.getName().contains("Ar"))) {
                            tempPileLocation[1] -= 1;
                            System.out.println("you can't move any more");
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
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(4).setLocations(4,4);
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

    private int distance(Pile pl1,Pile pl2){
        int[] tempLocation = new int[2];
        int counter = 0;
        tempLocation[1] = pl1.getLocation()[1];
        tempLocation[0] = pl1.getLocation()[0];
        while(pl2.getLocation()[0] != tempLocation[0] || pl2.getLocation()[1] != tempLocation[1]){
            if(tempLocation[1] % 2 == 0){
                if(tempLocation[0] > pl2.getLocation()[0] && tempLocation[1] > pl2.getLocation()[1]){
                    tempLocation[0]--;
                    tempLocation[1]--;
                    counter++;
                }
                else if(tempLocation[0] < pl2.getLocation()[0] && tempLocation[1] > pl2.getLocation()[1]){
                    tempLocation[1]--;
                    counter++;
                }
                else if(tempLocation[0] < pl2.getLocation()[0] && tempLocation[1] < pl2.getLocation()[1]){
                    tempLocation[1]--;
                    counter++;
                }
                else if(tempLocation[0] > pl2.getLocation()[0] && tempLocation[1] < pl2.getLocation()[1]){
                    tempLocation[0]--;
                    tempLocation[1]++;
                    counter++;
                }
                else if(tempLocation[1] == pl2.getLocation()[1]){
                    if(tempLocation[0] < pl2.getLocation()[0]){
                        tempLocation[0]++;
                    }
                    else{
                        tempLocation[0]--;
                    }
                    counter++;
                }
                else if(tempLocation[0] == pl2.getLocation()[0]){
                    if(tempLocation[1] < pl2.getLocation()[1]){
                        tempLocation[1]++;
                    }
                    else{
                        tempLocation[1]--;
                    }
                    counter++;
                }
            }
            else if(tempLocation[1] % 2 == 1){
                if(tempLocation[0] > pl2.getLocation()[0] && tempLocation[1] > pl2.getLocation()[1]){
                    tempLocation[1]--;
                    counter++;
                }
                else if(tempLocation[0] < pl2.getLocation()[0] && tempLocation[1] > pl2.getLocation()[1]){
                    tempLocation[0]++;
                    tempLocation[1]--;
                    counter++;
                }
                else if(tempLocation[0] < pl2.getLocation()[0] && tempLocation[1] < pl2.getLocation()[1]){
                    tempLocation[0]++;
                    tempLocation[1]++;
                    counter++;
                }
                else if(tempLocation[0] > pl2.getLocation()[0] && tempLocation[1] < pl2.getLocation()[1]){
                    tempLocation[1]++;
                    counter++;
                }
                else if(tempLocation[1] == pl2.getLocation()[1]){
                    if(tempLocation[0] < pl2.getLocation()[0]){
                        tempLocation[0]++;
                    }
                    else{
                        tempLocation[0]--;
                    }
                    counter++;
                }
                else if(tempLocation[0] == pl2.getLocation()[0]){
                    if(tempLocation[1] < pl2.getLocation()[1]){
                        tempLocation[1]++;
                    }
                    else{
                        tempLocation[1]--;
                    }
                    counter++;
                }
            }
        }
        return counter;
    }

    public void attack(Pile pl,String attacker){
        System.out.println("Please enter the name of the force that you want to attack with " + pl.getName() + " (0 for not attacking): ");
        Scanner input = new Scanner(System.in);
        String defender = null;
        Pile tempDefender = null;
        String tempForceToAttackName = input.nextLine();
        if(tempForceToAttackName.equals("0")){
            return;
        }
        Iterator<MilitaryTypes> it = axisPlayer.getForces().keySet().iterator();
        if(attacker.equals("Axis")){defender = "Allied";}
        if(attacker.equals("Allied")){defender = "Axis";}
        if(defender.equals("Axis") && pl.getName().contains("Al")){
            while(it.hasNext()){
                for(Pile pi : axisPlayer.getForces().get(it.next())){
                    if(pi.getName().equals(tempForceToAttackName)){
                        tempDefender = pi;
                        break;
                    }
                }
            }
            if(!pl.getCanAttack()){
                System.out.println("You can't attack with this Pile ");
                return;
            }
            int dis = distance(pl,tempDefender);
            pl.attackPile(tempDefender,dis);
            renewMap();
        }
        if(defender.equals("Allied") && pl.getName().contains("Ax")){
            while(it.hasNext()){
                for(Pile pi : alliedPlayer.getForces().get(it.next())){
                    if(pi.getName().equals(tempForceToAttackName)){
                        tempDefender = pi;
                        break;
                    }
                }
            }
            if(!pl.getCanAttack()){
                System.out.println("You can't attack with this Pile ");
                return;
            }
            int dis = distance(pl,tempDefender);
            pl.attackPile(tempDefender,dis);
            renewMap();
        }
    }

    public void renewMap(){
        for (MilitaryTypes militaryTypes : axisPlayer.getForces().keySet()) {
            for (Pile pi : axisPlayer.getForces().get(militaryTypes)) {
                if(pi.getPile().size() == 0){
                    gameLocations[pi.getLocation()[1]][pi.getLocation()[0]] = null;
                }
                else {
                    gameLocations[pi.getLocation()[1]][pi.getLocation()[0]] = pi.getPileFullName();
                }
            }
        }
        for (MilitaryTypes militaryTypes : alliedPlayer.getForces().keySet()) {
            for (Pile pi : alliedPlayer.getForces().get(militaryTypes)) {
                if(pi.getPile().size() == 0){
                    gameLocations[pi.getLocation()[1]][pi.getLocation()[0]] = null;
                }
                else {
                    gameLocations[pi.getLocation()[1]][pi.getLocation()[0]] = pi.getPileFullName();
                }
            }
        }
    }

    public void mapShow(){
        View view = new View();
        view.viewingTheMap();
    }

    public String checkEndOfTheGame(){
        if(alliedPlayerMedals == 6){
             return alliedPlayer.getName();
        }
        else if(axisPlayerMedals == 6){
            return axisPlayer.getName();
        }
        else{
            return null;
        }
    }

    public int choosingCard(String teamName){
        Scanner input = new Scanner(System.in);
        if(teamName.equals("Axis")){
            axisPlayer.showingCardInfo();
            System.out.print("Please enter the card name : ");
            String tempCardName = input.nextLine();
            return axisPlayer.removeAndAddCard(tempCardName);
        }
        else if(teamName.equals("Allied")){
            alliedPlayer.showingCardInfo();
            System.out.print("Please enter the card name : ");
            String tempCardName = input.nextLine();
            return alliedPlayer.removeAndAddCard(tempCardName);
        }
        return 0;
    }

    public static void increaseMedals(String name){
        if(name.equals("Al")){alliedPlayerMedals += 1;}
        else if(name.equals("Ax")){axisPlayerMedals += 1;}
    }
    public void renewCanAttack(){
        for (MilitaryTypes militaryTypes : axisPlayer.getForces().keySet()) {
            for (Pile pi : axisPlayer.getForces().get(militaryTypes)) {
                pi.setCanAttack(true);
            }
        }
        for (MilitaryTypes militaryTypes : alliedPlayer.getForces().keySet()) {
            for (Pile pi : alliedPlayer.getForces().get(militaryTypes)) {
                pi.setCanAttack(true);
            }
        }
    }

    public static int getAlliedPlayerMedals() {
        return alliedPlayerMedals;
    }

    public static int getAxisPlayerMedals() {
        return axisPlayerMedals;
    }

    public Player getAlliedPlayer() {
        return alliedPlayer;
    }

    public Player getAxisPlayer() {
        return axisPlayer;
    }
}
