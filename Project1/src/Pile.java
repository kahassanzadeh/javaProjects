import java.util.ArrayList;
import java.util.Random;

public class Pile {

    private ArrayList<Military> pile;

    private static int counterOfPiles = 1;

    private String name;

    private int[] location;

    private boolean canAttack;

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

    public boolean canAttack() {
        return canAttack;
    }

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

    public ArrayList<Military> getPile() {
        return pile;
    }

    public void setLocations(int x,int y) {
        this.location[1] = y;
        this.location[0] = x;
        GameManager.setGameLocations(pile.size() + this.name ,x,y);
    }

    public String getPileFullName(){
        return(pile.size() + this.name );
    }

    public String getName() {
        return name;
    }

    public boolean movePile(int[] loc,int sum){
        if(this.name.contains("So")){
            if(sum > 2){return false;}
            else if(sum == 2){canAttack = false;return changeLocation(loc[0],loc[1]);}
            else if(sum == 1){canAttack = true;return changeLocation(loc[0],loc[1]);}
        }
        else if(this.name.contains("Ta")){
            if(sum > 3){return false;}
            else{
                canAttack = true;
                return changeLocation(loc[0],loc[1]);
            }
        }
        else if(this.name.contains("Ar")){
            if(sum == 1){canAttack = false;return changeLocation(loc[0],loc[1]);}
        }
        return false;
    }

    public void attackPile(Pile defender,int distance){
        int counterOfDices = dicesCounter(distance);
        counterOfDices = checkLocation(defender,counterOfDices);
        System.out.println("You can just have " + counterOfDices + " dice / dices");
        int randomNumber = 0;
        for(int i = 0; i < counterOfDices ; i++){
            randomNumber = randomNumberGenerator();
            if((randomNumber == 1 || randomNumber == 6) && defender.getName().substring(2,4).equals("So")){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println( i+1 + ".dice : " + randomNumber + " \n"+
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());

            }
            else if((randomNumber == 2) && defender.getName().substring(2,4).equals("Ta")){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println( i+1 + ".dice : " + randomNumber + " \n"+
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());
            }
            else if((randomNumber == 5)){
                defender.getPile().remove(defender.getPile().size() - 1);
                System.out.println( i+1 + ".dice : " + randomNumber + " \n"+
                        "your attack was successful \n" +
                        "Attack info : From " + this.getName() + " To " + defender.getName());
            }
            else if(randomNumber == 3 || randomNumber == 4){
                System.out.println( i + 1 + ".dice : " + randomNumber + " \n" + "You can't attack");
            }
            else{
                System.out.println( i + 1 + ".dice : " + randomNumber + " \n" + "You can't attack");
            }
        }
    }

    private boolean changeLocation(int x,int y){
        if(GameManager.getGameLocations()[y][x] == null){
            GameManager.setGameLocations(null,this.location[0],this.location[1]);
            this.setLocations(x,y);
            return true;
        }
        else{return false;}
    }

    public int[] getLocation() {
        return location;
    }

    private int randomNumberGenerator() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

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
                return 3;
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
    public void setCanAttack(boolean status){
        canAttack = status;
    }

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
