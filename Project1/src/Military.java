public abstract class Military {

    private  String name;

    private boolean isAlive;

    private boolean canAttack;

    public Military(String name){
        this.name = name;
        isAlive = true;
        canAttack = false;
    }

    public abstract boolean move();

    public abstract boolean attack();
}
