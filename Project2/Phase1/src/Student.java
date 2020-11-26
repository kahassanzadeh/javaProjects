import java.util.ArrayList;

public class Student extends Person implements Commons {
    private long balance;
    private float average;
    private int passedCredits;
    private ArrayList<Class> classes;
    private int currentCredits;



    public Student(String name, String userName, String password) {
        super(name, userName, password);
        classes = new ArrayList<>();
    }

    public Student(String name){
        super(name);
    }

    @Override
    public void changingUserName(String oldUser, String newUser, String password) {

    }

    @Override
    public void changingPassword(String User, String oldPass, String newPassword) {

    }

    public void setPassedCredits(int passedCredits) {
        this.passedCredits = passedCredits;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setAverage(float average) {
        this.average = average;
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
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setCurrentCredits() {
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
}
