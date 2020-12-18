import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class created for the Person
 *
 * @author Mohammadreza hassanzadeh
 * @since 17 Dec 200
 * @version 1.0
 */
public class Person implements Serializable {
    //name of the person
    private String name;
    //username of the person
    private String userName;
    //pass of the person
    private String password;
    //reserved food for the person
    private ArrayList<Food> reservedFoods;

    /**
     * constructor for the person class
     *
     * @param name name of the person
     * @param userName username of the person
     * @param password pass of the person
     */
    public Person(String name,String userName,String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
        reservedFoods = new ArrayList<>();
    }

    /**
     * constructor for Person class
     *
     * @param name name of the person
     */
    public Person(String name){
        this.name = name;
    }

    /**
     * setter for password
     * @param password pass
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * setter for username of the person
     * @param userName username of the person
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * getting the user name of the person
     * @return String user name of the person
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getting the pass of the person
     * @return password of the person
     */
    public String getPassword() {
        return password;
    }

    /**
     * getting the name of the person
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * setting the reserved food
     * @param reservedFoods Food
     */
    public void setReservedFoods(Food reservedFoods) {
        this.reservedFoods.add(reservedFoods);
    }

    /**
     * getting the reserved food of this person
     * @return Array list of the person
     */
    public ArrayList<Food> getReservedFoods() {
        return reservedFoods;
    }

    /**
     * changing the userName
     * @param newUserID new userName
     */
    public void changeUserID(String newUserID){
        this.userName = newUserID;
    }

    /**
     * changing the pass of the person
     * @param newPass new password
     */
    public void changePass(String newPass) {
        this.password = newPass;
    }
}
