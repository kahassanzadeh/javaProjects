import java.io.Serializable;

/**
 * this class created for admin object and it extends person class
 *
 * @author Mohammadreza Hassanzadeh
 * @since Dec 17 2020
 * @version 1.0
 */
public class Admin extends Person implements Commons, Serializable {

    /**
     * constructor for admin
     *
     * @param name name of the admin
     * @param userName userName for Person class
     * @param password password for Person class
     */
    public Admin(String name, String userName, String password) {
        super(name, userName, password);
    }

    /**
     * constructor for just making the admin
     *
     * @param name name of the person
     */
    public Admin(String name) {
        super(name);
    }

    /**
     * override method
     * setting a password
     *
     * @param password new password
     */
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * override method
     * setting the userName
     *
     * @param userName user name of admin
     */
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    /**
     * override method
     * for getting the full info of admin
     *
     * @return String contains name and username of admin
     */
    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }
}
