import java.util.*;

/**
 * this class will managing the whole game and its issues
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */
public class GameManager {

    //axis player
    private Player axisPlayer;
    // allied player
    private Player alliedPlayer;
    //counting axis medals
    private static int axisPlayerMedals;
    //counting allied medals
    private static int alliedPlayerMedals;
    //location of each Pile
    private static String[][] gameLocations;
    //location of each type of land
    private static String[][] locationTypes;


    /**
     * constructor for the game and making the players
     * @param axisPLayer axis player name
     * @param alliedPlayer allied player name
     */
    public GameManager(String axisPLayer, String alliedPlayer) {
        Cards card = new Cards();
        this.axisPlayer = new Player(axisPLayer, "Axis");
        this.alliedPlayer = new Player(alliedPlayer, "Allied");
        gameLocations = new String[9][13];
        locationTypes = new String[9][13];
        setLocationTypes();
    }

    /**
     * this method will setting the gameLocation array by the name of the pile
     * @param name name of the pile
     * @param x x of the pile
     * @param y y of the pile
     */
    public static void setGameLocations(String name, int x, int y) {
        gameLocations[y][x] = name;
    }

    /**
     * this method will set each type of the land to the whole map
     * @param name name of the land
     * @param x x of the landType
     * @param y y of the landType
     */
    public static void setLocationTypesArray(String name, int x, int y) {
        locationTypes[y][x] = name;
    }

    /**
     * this method will set the pile locations by user
     * @param teamName name of the team axis or allied
     */
    public void setLocationsByUser(String teamName) {
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

    /**
     * this method will get and check the coordination that the user entered for the axis team
     * @param ml military type
     */
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

    /**
     * this method will get and check the coordination that the user entered for the allied team
     * @param ml military type
     */
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

    /**
     * this method will initialize the whole map land types
     */
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

    /**
     * getter for the gameLocations array
     * @return 2D array String gameLocations
     */
    public static String[][] getGameLocations() {
        return gameLocations;
    }


    /**
     * getter for the landTypes array
     * @return 2D array String landTypes
     */
    public static String[][] getLocationTypes() {
        return locationTypes;
    }

    /**
     * this mehod will search a pile with its name and its team
     * @param teamName team of the pile
     * @param forceName name of the pile
     * @return pile that searched
     */
    private Pile searchPile(String teamName,String forceName){
        if(teamName.equals("Axis")){
            for (MilitaryTypes militaryTypes : axisPlayer.getForces().keySet()) {
                for (Pile pi : axisPlayer.getForces().get(militaryTypes)) {
                    if(pi.getName().equals(forceName)){
                        return pi;
                    }
                }
            }
        }
        if(teamName.equals("Allied")){
            for (MilitaryTypes militaryTypes : alliedPlayer.getForces().keySet()) {
                for (Pile pi : alliedPlayer.getForces().get(militaryTypes)) {
                    if(pi.getName().equals(forceName)){
                        return pi;
                    }
                }
            }
        }
        return null;
    }

    /**
     * this method will control the movement of the pile
     * @param pl pile that we want to use
     * @return returns the pl that we moved
     */
    public Pile moveController(Pile pl){
        Scanner input = new Scanner(System.in);
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

    /**
     * this method will generate the whole moving process with the user
     * @param team name of the team that wants to move the pile
     * @return pile that moved
     */
    public Pile movePile(String team) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the name of the force that you want to move or attack: ");
        String tempName = input.nextLine();
        return moveController(searchPile(team,tempName));

    }

    /**
     * overloaded method
     * moving the pile with its name and its team name
     * @param team name of the team that wants to move the pile
     * @param name name of the pile
     * @return pile that we moved
     */
    public Pile movePile(String team,String name){
        return moveController(searchPile(team,name));
    }

    /**
     * this method will get a String and counts the number of whole movements
     * @param tempMove string that user entered for the moving process
     * @return int counter of moves
     */
    private int countingMoves(String tempMove) {
        String[] tempDirections = tempMove.split("\\s+");
        int counter = 0;
        for(String st : tempDirections){
            counter += Integer.parseInt(st.substring(0,1));
        }
        return counter;
    }

    /**
     * this method will get the string that user entered for the moving process and generates the new location that the new pile wants to enter
     * @param directions string that user entered for the moving process
     * @param pl pile that we want to move
     * @return the new location of the pile
     */
    private int[] checkDirectionAndStringToNumber(String directions, Pile pl) {
        String[] tempDirections = directions.split("\\s+");
        int[] tempPileLocation =new int[2];
        tempPileLocation[0] = pl.getLocation()[0];
        tempPileLocation[1] = pl.getLocation()[1];
        for (String st : tempDirections) {
            switch (st.substring(1)) {
                case "R":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
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

                    break;
                case "L":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
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

                    break;
                case "UR":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
                        if (tempPileLocation[1] % 2 == 1) {
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
                        } else {
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

                    break;
                case "UL":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
                        if (tempPileLocation[1] % 2 == 0) {
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
                        } else {
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
                    break;
                case "DR":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
                        if (tempPileLocation[1] % 2 == 1) {
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
                        } else {
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
                    break;
                case "DL":
                    for (int i = 0; i < Integer.parseInt(st.substring(0, 1)); i++) {
                        if (tempPileLocation[1] % 2 == 0) {
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
                        } else {
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
                    break;
            }
        }
        return tempPileLocation;
    }

    /**
     * this method will generate the default locations for the axis forces
     */
    public void defaultLocationsAxis(){
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(0).setLocations(8,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(1).setLocations(1,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(2).setLocations(2,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(3).setLocations(11,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(4).setLocations(4,1);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(5).setLocations(12,0);
        axisPlayer.getForces().get(MilitaryTypes.SOLDIER).get(6).setLocations(8,1);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(0).setLocations(0,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(1).setLocations(5,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(2).setLocations(9,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(3).setLocations(12,0);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(4).setLocations(10,1);
        axisPlayer.getForces().get(MilitaryTypes.TANK).get(5).setLocations(5,1);
    }
    /**
     * this method will generate the default locations for the allied forces
     */
    public void defaultLocationAllied(){
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(0).setLocations(8,8);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(1).setLocations(0,7);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(2).setLocations(9,7);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(3).setLocations(7,6);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(4).setLocations(3,5);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(5).setLocations(1,4);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(6).setLocations(6,4);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(7).setLocations(8,4);
        alliedPlayer.getForces().get(MilitaryTypes.SOLDIER).get(8).setLocations(11,4);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(0).setLocations(0,8);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(1).setLocations(1,8);
        alliedPlayer.getForces().get(MilitaryTypes.TANK).get(2).setLocations(12,8);
        alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY).get(0).setLocations(4,8);
        alliedPlayer.getForces().get(MilitaryTypes.ARTILLERY).get(1).setLocations(5,8);

    }

    /**
     * this method will calculate the distance of 2 piles
     * @param pl1 first pile
     * @param pl2 second pile
     * @return distant of these 2 piles
     */
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

    /**
     * this method will generate the attack process
     * @param pl pile that wnats to attack
     * @param attacker team that wants to attack
     */
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
            tempDefender = searchPile("Axis",tempForceToAttackName);
            if(!pl.getCanAttack()){
                System.out.println("You can't attack with this Pile ");
                return;
            }
            int dis = distance(pl,tempDefender);
            pl.attackPile(tempDefender,dis);
            renewMap();
        }
        if(defender.equals("Allied") && pl.getName().contains("Ax")){
            tempDefender = searchPile("Allied",tempForceToAttackName);
            if(!pl.getCanAttack()){
                System.out.println("You can't attack with this Pile ");
                return;
            }
            int dis = distance(pl,tempDefender);
            pl.attackPile(tempDefender,dis);
            renewMap();
        }
    }

    /**
     * this method will renew the map of the game after changing the locations
     */
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

    /**
     * this method will show the current map
     */
    public void mapShow(){
        View view = new View();
        view.viewingTheMap();
    }

    /**
     * this method will check if the game has ended or not
     * @return name of the player that has won
     */
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

    /**
     * this method will generate the whole choosing card by the user
     * @param teamName name of the car that we want to choose
     * @return number of the card type
     */
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

    /**
     * this method will increase the medals
     * @param name team that we want to increase the medals for
     */
    public static void increaseMedals(String name){
        if(name.equals("Al")){alliedPlayerMedals += 1;}
        else if(name.equals("Ax")){axisPlayerMedals += 1;}
    }

    /**
     * this method will renew the whole game attacking status
     */
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

    /**
     * getting the allied medals
     * @return int number of medals
     */
    public static int getAlliedPlayerMedals() {
        return alliedPlayerMedals;
    }

    /**
     * getting number of axis medals
     * @return int number of medals
     */
    public static int getAxisPlayerMedals() {
        return axisPlayerMedals;
    }

    /**
     * getter for the allied player
     * @return allied player
     */
    public Player getAlliedPlayer() {
        return alliedPlayer;
    }
    /**
     * getter for the axis player
     * @return axis player
     */
    public Player getAxisPlayer() {
        return axisPlayer;
    }
}
