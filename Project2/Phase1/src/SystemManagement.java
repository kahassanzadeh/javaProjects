import java.util.ArrayList;
import java.util.HashMap;

public class SystemManagement {

    private static HashMap<String, ArrayList<Person>> systemList = new HashMap<>();
    private static ArrayList<Food> foodsSchedules = new ArrayList<>();
    private static ArrayList<Class> classes = new ArrayList<>();

    public static Person searchPerson(String userName,String password){
        for(String st : systemList.keySet()){
            for(Person ps : systemList.get(st)){
                if(ps.getUserName().equals(userName) && ps.getPassword().equals(password)){
                    return ps;
                }
            }
        }
        return null;

    }


    public static boolean checkLogin(String userName,String password){

        for(String st : systemList.keySet()){
            for(Person ps : systemList.get(st)){
                if(ps.getUserName().equals(userName)){
                    return ps.getPassword().equals(password);
                }
            }
        }
        return false;
    }

    public static ArrayList<Food> getFoodsSchedules() {
        return foodsSchedules;
    }

    public static void setFoodsSchedules(Food foodsSchedule) {
        SystemManagement.foodsSchedules.add(foodsSchedule);
    }

    public static Food searchFood(String name,String day){
        for(Food fd : foodsSchedules){
            if(fd.getName().equals(name) && fd.getDistributionDay().equals(day)){
                return fd;
            }
        }
        return null;
    }

    public static boolean reservingFood(Person person,Food reservingFood){
        if(reservingFood == null){
            return  false;
        }
        Student temp = (Student) person;
        for(Food fd : temp.getReservedFoods()){
            if(reservingFood.getDistributionDay().equals(fd.getDistributionDay())){
                return false;
            }
        }
        temp.setReservedFoods(reservingFood);
        long cost = temp.getBalance() - reservingFood.getCost();
        if(cost < 0){
            return false;
        }
        temp.setBalance(cost);
        return true;
    }

    public static int getClassNumbers() {
        if(classes.size() < 25){
            return 25;
        }
        return classes.size();
    }

    public static ArrayList<Class> getClasses() {
        return classes;
    }

    public static void setClasses(Class classes) {
        SystemManagement.classes.add(classes);
    }

    //function parameter
}
