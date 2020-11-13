import java.util.ArrayList;

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

    private void attackPile(){

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
}
