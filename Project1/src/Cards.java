import java.util.HashMap;
import java.util.Random;

public class Cards {

    private static HashMap<CardTypes,Integer> allCards = new HashMap<>();;

    public Cards(){
        allCards.put(CardTypes.TYPE1,6);
        allCards.put(CardTypes.TYPE2,13);
        allCards.put(CardTypes.TYPE3,10);
        allCards.put(CardTypes.TYPE4,6);
        allCards.put(CardTypes.TYPE5,5);
    }

    public static CardTypes randomCardGenerator(){
        Random random = new Random();
        String temp;
        do{
            temp = "TYPE" + (random.nextInt(5) + 1) ;
            checkAndRenewAllCards();
            if(allCards.get(CardTypes.valueOf(temp)) != 0){
                allCards.put(CardTypes.valueOf(temp),allCards.get(CardTypes.valueOf(temp)) - 1) ;
                return CardTypes.valueOf(temp);
            }
        }while(true);
    }

    private static void checkAndRenewAllCards(){
        if(allCards.get(CardTypes.TYPE1) == 0 && allCards.get(CardTypes.TYPE2) == 0 && allCards.get(CardTypes.TYPE3) == 0 && allCards.get(CardTypes.TYPE4) == 0 && allCards.get(CardTypes.TYPE5) == 0){
            allCards.put(CardTypes.TYPE1,6);
            allCards.put(CardTypes.TYPE2,13);
            allCards.put(CardTypes.TYPE3,10);
            allCards.put(CardTypes.TYPE4,6);
            allCards.put(CardTypes.TYPE5,5);
        }
    }


    public static void printCardsInfo(CardTypes ct){
        if(ct == CardTypes.TYPE1){
            System.out.println(
                    "****************\n" +
                    "*              *\n" +
                    "* order 1 unit *\n" +
                    "*              *\n" +
                    "****************");

        }
        else if(ct == CardTypes.TYPE2){
            System.out.println(
                    "*****************\n" +
                    "*               *\n" +
                    "* order 2 units *\n" +
                    "*               *\n" +
                    "*****************");

        }
        else if(ct == CardTypes.TYPE3){
            System.out.println(
                    "*****************\n" +
                    "*               *\n" +
                    "* order 3 units *\n" +
                    "*               *\n" +
                    "*****************");

        }
        else if(ct == CardTypes.TYPE4){
            System.out.println(
                    "*****************\n" +
                    "*               *\n" +
                    "* order 4 units *\n" +
                    "*               *\n" +
                    "*****************");

        }
        else if(ct == CardTypes.TYPE5){
            System.out.println(
                    "*****************\n" +
                    "* order 3 units *\n" +
                    "*  with a same  *\n" +
                    "* military type *\n" +
                    "*****************");

        }
    }

}
