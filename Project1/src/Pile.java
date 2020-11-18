import java.util.ArrayList;
import java.util.Random;
/**
 * this class is for generating the piles and managing them
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public class Pile {
    //array list of the forces
    private ArrayList<Military> pile;
    //counter of the forces
    private static int counterOfPiles = 1;
    //name of this pile
    private String name;
    //location of the pile
    private int[] location;
    //attacking status of a pile
    private boolean canAttack;

    /**
     * constructor for the this class
     * @param teamName nam eof the team that we want to make pile for
     * @param militaryType type of the military
     */
    public Pile(String teamName,String militaryType){
        pile = new ArrayList<>();
        location = new int[2];
        if(teamName.equals("Axis")){
            axisPileGenerator(militaryType);
        }
        else if(teamName.equals("Allied")){
            alliedPileGenerator(militaryType);
        }
        canAttack = true;
    }

    /**
     * generating piles for axis
     * @param militaryType type of the force
     */
    private void axisPileGenerator(String militaryType){
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.SOLDIER){
            for(int i = 1; i <= 4;i++){
                pile.add(new Soldier("S" + i));
            }
            name = "AxSo" + counterOfPiles;
        }
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.TANK){
            for(int i = 1; i <= 4;i++){
                pile.add(new Tank("T" + i));
            }
            name = "AxTa" + (counterOfPiles - 7);
        }
        counterOfPiles++;
    }

    /**
     * generating piles for  allied
     * @param militaryType type of the force
     */
    private void alliedPileGenerator(String militaryType){
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.SOLDIER){
            for(int i = 1; i <= 4;i++){
                pile.add(new Soldier("S" + i));
            }
            name = "AlSo" + (counterOfPiles - 13);
        }
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.TANK){
            for(int i = 1; i <= 3;i++){
                pile.add(new Soldier("T" + i));
            }
            name = "AlTa" + (counterOfPiles - 21);
        }
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.ARTILLERY){
            for(int i = 1; i <= 2;i++){
                pile.add(new Artillery("A" + i));
            }
            name = "AlAr" + (counterOfPiles - 24);
        }
        counterOfPiles++;
    }

    /**
     * getter for the pile
     * @return array list of the force types
     */
    public ArrayList<Military> getPile() {
        return pile;
    }

    /**
     * setting locations for the piles
     * @param x x of the pile
     * @param y y of the pile
     */
    public void setLocations(int x,int y) {
        this.location[1] = y;
        this.location[0] = x;
        GameManager.setGameLocations(pile.size() + this.name ,x,y);
    }

    /**
     * getting the name of the pile with its numbers
     * @return full name of the pile
     */
    public String getPileFullName(){
        return(pile.size() + this.name );
    }
    /**
     * getting the name of the pile
     * @return name of the pile
     */
    public String getName() {
        return name;
    }

    /**
     * this method will move the pile and change the locations
     * @param loc location that we want to move
     * @param sum sum of the moves
     * @return true if we can move it
     */
    public boolean movePile(int[] loc,int sum){
        if(this.name.contains("So")){
            if(sum > 2){return false;}
            else if(sum == 2){
                canAttack = false;
                return changeLocation(loc[0],loc[1]);
            }
            else if(sum == 1){
                if(GameManager.getLocationTypes()[loc[1]][loc[0]].equals("JG") || GameManager.getLocationTypes()[loc[1]][loc[0]].equals("CC")){
                    canAttack = false;
                }
                return changeLocation(loc[0],loc[1]);
            }
        }
        else if(this.name.contains("Ta")){
            if(sum > 3){return false;}
            else{
                if(GameManager.getLocationTypes()[loc[1]][loc[0]].equals("JG") || GameManager.getLocationTypes()[loc[1]][loc[0]].equals("CC")){
                    canAttack = false;
                }
                return changeLocation(loc[0],loc[1]);
            }
        }
        else if(this.name.contains("Ar")){
            if(sum == 1){
                canAttack = false;
                return changeLocation(loc[0],loc[1]);
            }

        }
        return false;
    }

    /**
     * this method will make the attacking process for a pile
     * @param defender pile that should defends
     * @param distance distant between these piles
     */
    public void attackPile(Pile defender,int distance){
        int counterOfDices = dicesCounter(distance);
        counterOfDices = checkLocation(defender,counterOfDices);
        if(counterOfDices <= 0){
            System.out.println("You can't attack with this Pile ");
            return;
        }

        System.out.println("You can just have " + counterOfDices + " dice / dices");
        int randomNumber = 0;
        for(int i = 0; i < counterOfDices; i++){
            if((defender.getPile().size() == 0)){
                return;
            }
            randomNumber = randomNumberGenerator();
            if((randomNumber == 1 || randomNumber == 6) && defender.getName().substring(2,4).equals("So")){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println(i + 1 + ".dice : " + randomNumber + " \n" +
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());
                if(defender.getPile().size() == 0){
                    GameManager.increaseMedals(this.name.substring(0,2));
                }

            }
            else if((randomNumber == 2) && defender.getName().substring(2,4).equals("Ta")){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println(i + 1 + ".dice : " + randomNumber + " \n" +
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());
                if(defender.getPile().size() == 0){
                    GameManager.increaseMedals(this.name.substring(0,2));
                }
            }
            else if((randomNumber == 5)){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println(i + 1 + ".dice : " + randomNumber + " \n" +
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());
                if(defender.getPile().size() == 0){
                    GameManager.increaseMedals(this.name.substring(0,2));
                }
            }
            else if(randomNumber == 3 || randomNumber == 4){
                System.out.println( i + 1 + ".dice : " + randomNumber + " \n" + "You can't attack");
            }
            else{
                System.out.println( i + 1 + ".dice : " + randomNumber + " \n" + "You can't attack");
            }
        }
    }

    /**
     * changing the location of the pile
     * @param x x of the pile
     * @param y y of the pile
     * @return true if it can moved
     */
    private boolean changeLocation(int x, int y){
        if(GameManager.getGameLocations()[y][x] == null){
            GameManager.setGameLocations(null,this.location[0],this.location[1]);
            this.setLocations(x,y);
            if(this.name.substring(0,2).equals("Ax") && GameManager.getLocationTypes()[this.location[1]][this.location[0]].equals("AL")){
                GameManager.increaseMedals("Ax");
            }
            if(this.name.substring(0,2).equals("Al") && GameManager.getLocationTypes()[this.location[1]][this.location[0]].equals("AX")){
                GameManager.increaseMedals("Al");
            }
            return true;
        }
        else{return false;}
    }

    /**
     * getting the location of the ile
     * @return
     */
    public int[] getLocation() {
        return location;
    }

    /**
     * generating random number between 1 to 6
     * @return random number
     */
    private int randomNumberGenerator() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    /**
     * this method will counts the dices that we should use in the attack method
     * @param distance distant between 2 piles
     * @return number of dices that we can use
     */
    private int dicesCounter(int distance){
        String force = this.name.substring(2,4);
        switch (force) {
            case "So":
                if (distance == 3) {
                    return 1;
                } else if (distance == 2) {
                    return 2;
                } else if (distance == 1) {
                    return 3;
                } else {
                    return 0;
                }
            case "Ta":
                if(distance <= 3){return 3;}
                else {return 0;}
            case "Ar":
                if (distance == 5 || distance == 6) {
                    return 1;
                } else if (distance == 3 || distance == 4) {
                    return 4;
                } else if (distance == 1 || distance == 2) {
                    return 3;
                } else {
                    return 0;
                }
        }
        return 0;
    }

    /**
     * setting the attack status for the pile
     * @param status true or false
     */
    public void setCanAttack(boolean status){
        canAttack = status;
    }

    /**
     * getting the attack pile status
     * @return true if we can attack
     */
    public boolean getCanAttack(){
        return canAttack;
    }

    /**
     * checking if the land type making any differences for the dices
     * @param defender pile that should defend
     * @param dices the whole dices that had been counted in counterOfDices method
     * @return number of dices
     */
    private int checkLocation(Pile defender , int dices){
        if(GameManager.getLocationTypes()[defender.getLocation()[1]][defender.getLocation()[0]].equals("HL")){
            return dices - 1;
        }
        else if(GameManager.getLocationTypes()[defender.getLocation()[1]][defender.getLocation()[0]].equals("JG") && this.name.substring(2,4).equals("So")){
            return dices - 1;
        }
        else if(GameManager.getLocationTypes()[defender.getLocation()[1]][defender.getLocation()[0]].equals("JG") && this.name.substring(2,4).equals("Ta")){
            return dices - 2;
        }
        else if(GameManager.getLocationTypes()[this.location[1]][this.location[0]].equals("CC")){
            return dices - 2;
        }
        else if(GameManager.getLocationTypes()[defender.getLocation()[1]][defender.getLocation()[0]].equals("SH") && defender.getName().substring(0,2).equals("Ax") && this.name.substring(2,4).equals("Ta")){
            return dices - 2;
        }
        else if(GameManager.getLocationTypes()[defender.getLocation()[1]][defender.getLocation()[0]].equals("SH") && defender.getName().substring(0,2).equals("Ax") && this.name.substring(2,4).equals("So")){
            return dices - 1;
        }
        return dices;
    }

}
