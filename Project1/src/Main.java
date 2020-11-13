public class Main {

    public static void main(String[] args) {
        Cards mainCards = new Cards();
        GameManager gm = new GameManager("kamyar","alireza");
        gm.defaultLocationAllied();
        gm.defaultLocationsAxis();
        View.viewingTheMap();
        gm.movePile("Allied");
    }
}
