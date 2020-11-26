

public class Person {
    String name;
    String userName;
    String password;

    public Person(String name,String userName,String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
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
}
