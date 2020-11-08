public class Soldier extends Military {


    public Soldier(String name) {
        super(name);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean attack() {
        return false;
    }


}
