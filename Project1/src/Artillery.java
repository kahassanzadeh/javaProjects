/**
 * this class is created for Artillery managing and if someone else want to develop the game
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public class Artillery extends Military{

    /**
     * constructor for the artillery
     * @param name name of the artillery
     */
    public Artillery(String name) {
        super(name);
    }

    /**
     * this method is for just artillery movement
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean move() {
        return false;
    }
    /**
     * this method is for just artillery attacking
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public boolean attack() {
        return false;
    }
    /**
     * this method is for just artillery name
     * if someone else wants to control 1 unit
     * @return
     */
    @Override
    public String toString() {
        return "A";
    }
}
