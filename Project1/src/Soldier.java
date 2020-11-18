
/**
 * this class is created for Soldier managing and if someone else want to develop the game
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public class Soldier extends Military {

    /**
     * constructor for the Soldier
     * @param name name of the Soldier
     */
    public Soldier(String name) {
        super(name);
    }
    /**
     * this method is for just Soldier movement
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean move() {
        return false;
    }
    /**
     * this method is for just Soldier attacking
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean attack() {
        return false;
    }
    /**
     * this method is for just Soldier name
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public String toString() {
        return "S";
    }
}
