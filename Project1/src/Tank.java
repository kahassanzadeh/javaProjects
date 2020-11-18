
/**
 * this class is created for Tank managing and if someone else want to develop the game
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public class Tank extends Military{


    /**
     * constructor for the Tank
     * @param name name of the Tank
     */
    public Tank(String name) {
        super(name);
    }
    /**
     * this method is for just Tank movement
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean move() {
        return false;
    }
    /**
     * this method is for just Tank attacking
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean attack() {
        return false;
    }
    /**
     * this method is for just Tank name
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public String toString() {
        return "T";
    }
}
