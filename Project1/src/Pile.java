import java.util.ArrayList;

public class Pile {

    private ArrayList<Military> pile;

    private static int counterOfPiles = 1;

    private String name;

    private int[] locations;

    public Pile(String teamName,String militaryType){
        pile = new ArrayList<>();
        locations = new int[2];
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
        this.locations[0] = y;
        this.locations[1] = x;
        GameManager.setGameLocations(pile.size() + this.name ,x,y);
    }

    public String getName() {
        return name;
    }

    private void movePile(){

    }

    private void attackPile(){

    }

}
