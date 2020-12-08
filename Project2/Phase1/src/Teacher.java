import java.util.ArrayList;
import java.util.HashMap;

public class Teacher extends Person implements Commons{

    private HashMap<Class, ArrayList<Student>> students;
    private String faculty;

    public Teacher(String name, String userName, String password,String faculty) {
        super(name, userName, password);
        this.faculty = faculty;
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

    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }
}
