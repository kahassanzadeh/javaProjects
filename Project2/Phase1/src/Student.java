

public class Student extends Person implements Commons {
    long balance;
    float average;
    int passedCredits;

    public Student(String name, String userName, String password) {
        super(name, userName, password);
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
}
