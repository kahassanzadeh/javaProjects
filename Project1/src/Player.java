import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
        for(int i = 0 ; i < 9; i ++ ){
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

    public void showingCardInfo(){
        for(CardTypes ct : cards){
            Cards.printCardsInfo(ct);
        }
    }


    public String getName() {
        return name;
    }

    public int removeAndAddCard(String ct){
        CardTypes tempCt = null;
        if(ct.equals("order 1 unit")){tempCt = CardTypes.TYPE1;}
        else if(ct.equals("order 2 units")){tempCt = CardTypes.TYPE2;}
        else if(ct.equals("order 3 units")){tempCt = CardTypes.TYPE3;}
        else if(ct.equals("order 4 units")){tempCt = CardTypes.TYPE4;}
        else if(ct.equals("order 3 units with a same military type")){tempCt = CardTypes.TYPE5;}
        Iterator<CardTypes> it = cards.iterator();
        int temp = 0;
        while(it.hasNext()){
            if(it.next().equals(tempCt)){
                temp = Integer.parseInt(tempCt.toString().substring(4,5));
                it.remove();
                cards.add(Cards.randomCardGenerator());
                break;
            }
        }
        return temp;
    }
}
