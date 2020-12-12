import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Teacher extends Person implements Commons, Serializable {

    private ArrayList<Class> classes;
    private String faculty;

    public Teacher(String name, String userName, String password,String faculty) {
        super(name, userName, password);
        this.faculty = faculty;
        classes = new ArrayList<>();
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

    public ArrayList<Class> getStudents() {
        return classes;
    }

    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }

    public String getFaculty() {
        return faculty;
    }


    public void addClass(Class cl) throws Exception {
        for(Class temp : classes){
            if(cl.getTIME_OF_THE_CLASS1().equals(temp.getTIME_OF_THE_CLASS1())){
                throw new Exception("In this time You have another class");
            }
            if(cl.getTIME_OF_THE_CLASS2() != null && temp.getTIME_OF_THE_CLASS2()!= null && cl.getTIME_OF_THE_CLASS2().equals(temp.getTIME_OF_THE_CLASS2())){
                throw new Exception("In this time You have another class");
            }
            if(temp.getTIME_OF_THE_CLASS2()!= null && cl.getTIME_OF_THE_CLASS1().equals(temp.getTIME_OF_THE_CLASS2())){
                throw new Exception("In this time You have another class");
            }
            if(cl.getTIME_OF_THE_CLASS2() != null && cl.getTIME_OF_THE_CLASS2().equals(temp.getTIME_OF_THE_CLASS1())){
                throw new Exception("In this time You have another class");
            }
        }
        classes.add(cl);
    }
    /*private void initialize(Class temp){
        for(Class cl : students.keySet()){
            if(temp.equals(cl)){
                students.
            }
        }
    }*/

    public void removeClass(Class cl){
        classes.remove(cl);
    }
    private Class searchClass(String name){
        for(Class cl : classes){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }

    public void addStudentToClass(String className, Student student) {
        Class temp = searchClass(className);
        SystemManagement.addStudentToClass(student,temp);
    }

    public ArrayList<Student> getList(Class cl){
        return SystemManagement.getClassList(cl);
    }

    public String[] getClassesString(){
        int counter = 0;
        String[] temp = new String[classes.size()];
        for(Class cl : classes){
            temp[counter] = cl.getName();
            counter++;
        }
        return temp;
    }

    public ArrayList<Student> getStudentOfClass(String cla){
        for(Class cl : classes){
            if(cl.getName().equals(cla)){
                return cl.getStudents();
            }
        }
        return null;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }
}
