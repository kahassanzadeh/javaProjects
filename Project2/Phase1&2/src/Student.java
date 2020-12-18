import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class created for the Students
 *
 * @author Mohammadreza hassanzadeh
 * @since 17 Dec 200
 * @version 1.0
 */
public class Student extends Person implements Commons , Serializable {
    //balance of the student
    private long balance;
    //average of the student
    private float average;
    //passed credits for student
    private int passedCredits;
    //array list of classes
    private ArrayList<Class> classes;
    //current credits for this term
    private int currentCredits;
    //hashmap of the whole studying info
    private HashMap<String,Double> educationalReport;


    /**
     * constructor for student
     *
     * @param name name of the student
     * @param userName username of the student
     * @param password password of the student
     */
    public Student(String name, String userName, String password) {
        super(name, userName, password);
        classes = new ArrayList<>();
        educationalReport = new HashMap<>();
    }


    /**
     * set the balance of the student
     * @param balance log number of balance
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * setting the Average of the student
     */
    public void setAverage() {
        float sum = 0;
        float creditCounter = 0;
        for(String cl : educationalReport.keySet()){
            if(educationalReport.get(cl) != 0.0){
                creditCounter += searchClass(cl).getCREDITS();
            }
            sum += searchClass(cl).getCREDITS() * educationalReport.get(cl);
        }
        this.average = sum/creditCounter;
    }

    /**
     * getting the average
     * @return float average of the student
     */
    public float getAverage() {
        return average;
    }

    /**
     * getting the balance of the student
     * @return balance of the student
     */
    public long getBalance() {
        return balance;
    }

    /**
     * getting the pass credits
     * @return
     */
    public int getPassedCredits() {
        return passedCredits;
    }

    /**
     * setting a class for the student
     * @param classes
     */
    public void setClasses(Class classes) {
        this.classes.add(classes);
        educationalReport.put(classes.getName(),0.0);
        classes.enroll();
    }

    /**
     * getting the students class
     * @return array list of the classes
     */
    public ArrayList<Class> getClasses() {
        return classes;
    }

    /**
     * setting the current credits
     */
    public void setCurrentCredits() {
        this.currentCredits = 0;
        for(Class cl : classes){
            currentCredits += cl.getCREDITS();
        }
    }

    /**
     * getting the current credits
     * @return int number of current credits
     */
    public int getCurrentCredits() {
        return currentCredits;
    }

    /**
     * override method for setting the password
     * @param password pass
     */
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * override method for setting the username
     * @param userName username of the person
     */
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    /**
     * this method will add money to the balance
     * @param number number that we want to add
     */
    public void addBalance(String number) {
        balance += Long.parseLong(number);
    }

    /**
     * override method for setting foods
     * @param reservedFoods Food
     */
    @Override
    public void setReservedFoods(Food reservedFoods) {
        super.setReservedFoods(reservedFoods);
    }

    /**
     * override method for getting the foods
     * @return array list of foods
     */
    @Override
    public ArrayList<Food> getReservedFoods() {
        return super.getReservedFoods();
    }

    /**
     * override method for getting the info of students
     * @return string of student
     */
    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }

    /**
     * this method will returns full Info
     * @return full info of the student
     */
    public String fullInfo() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()) + "   ");
    }

    /**
     * getting the educational report of the student
     * @return hashmap
     */
    public HashMap<String, Double> getEducationalReport() {
        return educationalReport;
    }

    /**
     * this method will returns the score of one class
     * @param cl class that we want to see the score
     * @return Double of the score
     */
    public Double getScore(Class cl){
        return educationalReport.get(cl);
    }

    /**
     * searching a class between student classes
     * @param className name of the class
     * @return Class object
     */
    public Class searchClass(String className){
        for(Class cl : classes)
        {
            if(cl.getName().equals(className)){
                return cl;
            }
        }
        return null;
    }

    /**
     * this method will remove class from classes list
     * @param aClass class that we want to remove
     */
    public void removeClass(Class aClass) {
        for(Class cl : classes){
            if(cl.equals(aClass)){
                classes.remove(cl);
                return;
            }
        }
    }

    /**
     * this method will set the passed credit
     */
    public void setPassedCredits(){
        this.passedCredits = 0;
        for(String st : educationalReport.keySet()){
            if(educationalReport.get(st) > 10.0){
                this.passedCredits += searchClass(st).getCREDITS();
            }
        }
    }
}
