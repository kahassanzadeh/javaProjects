import javax.swing.*;
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

    public static void addingFood(String name,String costString,String dayOfDistribution) throws Exception {
        if(!costString.matches("[0-9]+")){
            throw new Exception("invalid cost");
        }
        int cost = Integer.parseInt(costString);
        dayOfDistribution = dayOfDistribution.substring(0,1).toUpperCase() + dayOfDistribution.substring(1).toLowerCase();
        if(!validDay(dayOfDistribution)){
            throw new Exception("invalid day");
        }
        Food temp = new Food(name,cost,dayOfDistribution);

        for(Food fd : foodsSchedules){
            if(fd.getName().equals(name) && fd.getDistributionDay().equals(dayOfDistribution)){
                throw new Exception("Had been added");
            }
        }
        int counter = countingFoodsInOneDay(temp.getDistributionDay());
        if(counter > 1){
            throw new Exception("more than 2 Foods are added. You can add 2 foods in a day");
        }
        foodsSchedules.add(temp);
    }
    private static boolean validDay(String day){
        return day.equals("Sunday") || day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Saturday");
    }
    private static int countingFoodsInOneDay(String day){
        int counter = 0;
        for(Food fd : foodsSchedules){
            if(fd.getDistributionDay().equals(day)){
                counter++;
            }
        }
        return counter;
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

    public static Class searchingClassString(String classInfo){
        for(Class cl  : classes){
            if(cl.toString().equals(classInfo)){
                return cl;
            }
        }
        return null;
    }

    public static boolean checkEnrollment(Student registeredStudent, Class temp) throws Exception {

        for(Class cl : registeredStudent.getClasses()){
            if(cl.equals(temp)){
                throw new Exception("This course had been selected");
            }
            if(cl.getTIME_OF_THE_CLASS().equals(temp.getTIME_OF_THE_CLASS())){
                throw new Exception("Time Interference with " + cl.getName());
            }
        }
        if(registeredStudent.getCurrentCredits() + temp.getCREDITS() > 20 && registeredStudent.getAverage() < 17){
            throw new Exception("Can't get the course");
        }
        return true;
    }

    public static HashMap<String, ArrayList<Person>> getSystemList() {
        return systemList;
    }


    public static void addStudent(Student temp) throws Exception {
        if(searchPerson(temp.getUserName(),temp.getPassword()) != null){
            throw new Exception("this student has been added");
        }
        systemList.get("Student").add(temp);
    }

    public static void addTeacher(Teacher temp) throws Exception {
        if(searchPerson(temp.getUserName(),temp.getPassword()) != null){
            throw new Exception("this teacher has been added");
        }
        systemList.get("Teacher").add(temp);
    }
}
