public class Artillery extends Military{


    public Artillery(String name) {
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
    @Override
    public String toString() {
        return "A";
    }
}
