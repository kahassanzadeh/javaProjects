import java.util.ArrayList;

public class Person {
    private String name;
    private String userName;
    private String password;
    private ArrayList<Food> reservedFoods;

    public Person(String name,String userName,String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
        reservedFoods = new ArrayList<>();
    }

    public Person(String name){
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setReservedFoods(Food reservedFoods) {
        this.reservedFoods.add(reservedFoods);
    }

    public ArrayList<Food> getReservedFoods() {
        return reservedFoods;
    }
}
