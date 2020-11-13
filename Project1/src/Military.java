public abstract class Military {

    private  String name;

    private boolean isAlive;


    public Military(String name){
        this.name = name;
        isAlive = true;
    }

    public abstract boolean move();

    public abstract boolean attack();
}
