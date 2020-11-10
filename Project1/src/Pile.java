import java.util.ArrayList;

public class Pile {
    private ArrayList<Military> pile;

    public Pile(String teamName,String militaryType){
        pile = new ArrayList<>();
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
        }
        else if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.TANK){
            for(int i = 1; i <= 4;i++){
                pile.add(new Tank("T" + i));
            }
        }
    }

    private void alliedPileGenerator(String militaryType){
        if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.SOLDIER){
            for(int i = 1; i <= 4;i++){
                pile.add(new Soldier("S" + i));
            }
        }
        else if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.TANK){
            for(int i = 1; i <= 3;i++){
                pile.add(new Soldier("T" + i));
            }
        }
        else if(MilitaryTypes.valueOf(militaryType.toUpperCase()) == MilitaryTypes.ARTILLERY){
            for(int i = 1; i <= 2;i++){
                pile.add(new Artillery("A" + i));
            }
        }
    }

    public ArrayList<Military> getPile() {
        return pile;
    }

    private void movePile(){

    }

    private void attackPile(){

    }

}
