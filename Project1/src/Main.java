public class Main {

    public static void main(String[] args) {
        Cards mainCards = new Cards();
        GameManager gm = new GameManager("kamyar","alireza");
        gm.setLocations("Axis");
        gm.setLocations("Allied");
        View m = new View();
        m.viewingTheMap();
    }
}
