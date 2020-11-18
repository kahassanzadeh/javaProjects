
/**
 * this class created for the forces
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public abstract class Military {
    //name of the force
    private  String name;
    //boolean is alive
    private boolean isAlive;

    /**
     * constructor for the military
     * @param name
     */
    public Military(String name){
        this.name = name;
        isAlive = true;
    }

    public abstract boolean move();

    public abstract boolean attack();
}
