import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private String name;

    private String teamName;

    private ArrayList<CardTypes> cards;

    private HashMap<MilitaryTypes,ArrayList<Pile>> forces;

    public Player(String name,String teamName){
        this.name = name;
        this.teamName = teamName.substring(0, 1).toUpperCase() + teamName.substring(1).toLowerCase();
        cards = new ArrayList<>();
        forces = new HashMap<>();
        if(this.teamName.equals("Axis")){
            axisCardsAndMilitary();
        }
        if(this.teamName.equals("Allied")){
            alliedCardsAndMilitary();
        }
    }

    private void alliedCardsAndMilitary() {
        ArrayList<Pile> temp = new ArrayList<>();
        cards.add(Cards.randomCardGenerator());
        cards.add(Cards.randomCardGenerator());
        cards.add(Cards.randomCardGenerator());
        cards.add(Cards.randomCardGenerator());
        for(int i = 0 ; i < 8; i ++ ){
            temp.add(new Pile(teamName,"SOLDIER"));
        }
        forces.put(MilitaryTypes.SOLDIER,temp);
        temp = new ArrayList<>();
        for(int i = 0 ; i < 3; i ++ ){
            temp.add(new Pile(teamName,"TANK"));
        }
        forces.put(MilitaryTypes.TANK,temp);
        temp = new ArrayList<>();
        for(int i = 0 ; i < 2; i ++ ){
            temp.add(new Pile(teamName,"ARTILLERY"));
        }
        forces.put(MilitaryTypes.ARTILLERY,temp);
    }

    private void axisCardsAndMilitary() {
        ArrayList<Pile> temp = new ArrayList<>();
        cards.add(Cards.randomCardGenerator());
        cards.add(Cards.randomCardGenerator());
        for(int i = 0 ; i < 7; i ++ ){
            temp.add(new Pile(teamName,"SOLDIER"));
        }
        forces.put(MilitaryTypes.SOLDIER,temp);
        temp = new ArrayList<>();
        for(int i = 0 ; i < 6; i ++ ){
            temp.add(new Pile(teamName,"TANK"));
        }
        forces.put(MilitaryTypes.TANK,temp);

    }

    public HashMap<MilitaryTypes, ArrayList<Pile>> getForces() {
        return forces;
    }

    private void showingCardInfo(){
        for(CardTypes ct : cards){
            Cards.printCardsInfo(ct);
        }
    }
}
