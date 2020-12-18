import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class created for the Teacher Object
 *
 * @author Mohammadreza Hassanzadeh
 * @since Dec 16 2020
 * @version 1.0
 *
 */

public class Teacher extends Person implements Commons, Serializable {
    //array list of classes
    private ArrayList<Class> classes;
    //faculty
    private String faculty;

    /**
     * constructor for teacher
     *
     * @param name name of the teacher
     * @param userName user nam eof the teacher
     * @param password pass of the teacher
     * @param faculty faculty of the teacher
     */
    public Teacher(String name, String userName, String password,String faculty) {
        super(name, userName, password);
        this.faculty = faculty;
        classes = new ArrayList<>();
    }


    /**
     * override method
     *
     * @param userName username of the person
     */
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    /**
     * override method
     * @param password pass
     */
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * override method for getting the teacher String
     * @return String info of the teacher
     */
    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }

    /**
     * getting the faculty of the teacher
     * @return
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * adding a class to the teacher's list and check if there isn't any time interference
     * @param cl class
     * @throws Exception if there is any problem
     */
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

    /**
     * remove a class from teachers list
     * @param cl class
     */
    public void removeClass(Class cl){
        classes.remove(cl);
    }
    public  Class searchClass(String name){
        for(Class cl : classes){
            if(cl.getName().equals(name)){
                return cl;
            }
        }
        return null;
    }

    /**
     * getting the name of the classes
     * @return String list of classes
     */
    public String[] getClassesString(){
        int counter = 0;
        String[] temp = new String[classes.size()];
        for(Class cl : classes){
            temp[counter] = cl.getName();
            counter++;
        }
        return temp;
    }

    /**
     * getting the classes
     * @return array list of classes
     */

    public ArrayList<Class> getClasses() {
        return classes;
    }
}
