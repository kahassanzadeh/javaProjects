public class Tank extends Military{



    public Tank(String name) {
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
