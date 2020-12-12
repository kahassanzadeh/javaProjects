import java.io.Serializable;

public class Admin extends Person implements Commons, Serializable {


    public Admin(String name, String userName, String password) {
        super(name, userName, password);
    }

    public Admin(String name) {
        super(name);
    }

    @Override
    public void changingUserName(String oldUser, String newUser, String password) {

    }

    @Override
    public void changingPassword(String User, String oldPass, String newPassword) {

    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }
}
