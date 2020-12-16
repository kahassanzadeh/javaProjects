import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person implements Commons , Serializable {
    private long balance;
    private float average;
    private int passedCredits;
    private ArrayList<Class> classes;
    private int currentCredits;
    private HashMap<String,Double> educationalReport;



    public Student(String name, String userName, String password) {
        super(name, userName, password);
        classes = new ArrayList<>();
        educationalReport = new HashMap<>();
    }

    public Student(String name){
        super(name);
    }


    public void setPassedCredits(int passedCredits) {
        this.passedCredits = passedCredits;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

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

    public float getAverage() {
        return average;
    }

    public long getBalance() {
        return balance;
    }

    public int getPassedCredits() {
        return passedCredits;
    }

    public void setClasses(Class classes) {
        this.classes.add(classes);
        educationalReport.put(classes.getName(),0.0);
        classes.enroll();
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setCurrentCredits() {
        this.currentCredits = 0;
        for(Class cl : classes){
            currentCredits += cl.getCREDITS();
        }
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    public void addBalance(String number) {
        balance += Long.parseLong(number);
    }

    @Override
    public void setReservedFoods(Food reservedFoods) {
        super.setReservedFoods(reservedFoods);
    }

    @Override
    public ArrayList<Food> getReservedFoods() {
        return super.getReservedFoods();
    }

    @Override
    public String toString() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()));
    }

    public String fullInfo() {
        return String.format("%-60s",String.format("%-40s",this.getName()) + String.format("%-20s",this.getUserName()) + "   ");
    }

    public HashMap<String, Double> getEducationalReport() {
        return educationalReport;
    }

    public Double getScore(Class cl){
        return educationalReport.get(cl);
    }

    public Class searchClass(String className){
        for(Class cl : classes)
        {
            if(cl.getName().equals(className)){
                return cl;
            }
        }
        return null;
    }

    public void removeClass(Class aClass) {
        for(Class cl : classes){
            if(cl.equals(aClass)){
                classes.remove(cl);
                return;
            }
        }
    }

    public void setPassedCredits(){
        this.passedCredits = 0;
        for(String st : educationalReport.keySet()){
            if(educationalReport.get(st) > 10.0){
                this.passedCredits += searchClass(st).getCREDITS();
            }
        }
    }
}