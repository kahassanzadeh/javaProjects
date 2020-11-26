import java.util.ArrayList;
import java.util.HashMap;

public class Teacher extends Person implements Commons{

    HashMap<Class, ArrayList<Student>> students;

    public Teacher(String name, String userName, String password) {
        super(name, userName, password);
    }

    public Teacher(String name){
        super(name);
    }


    @Override
    public void changingUserName(String oldUser, String newUser, String password) {

    }

    @Override
    public void changingPassword(String User, String oldPass, String newPassword) {

    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public HashMap<Class, ArrayList<Student>> getStudents() {
        return students;
    }
}
