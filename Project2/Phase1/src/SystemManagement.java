import java.util.ArrayList;
import java.util.HashMap;

public class SystemManagement {

    private static HashMap<String, ArrayList<Person>> systemList = new HashMap<>();
    private static ArrayList<Food> foodsSchedules = new ArrayList<>();
    private static ArrayList<Class> classes = new ArrayList<>();


    public static void addClass(Class cl){
        classes.add(cl);
    }
    public static Person searchPersonForLogin(String userName,String password){
        for(String st : systemList.keySet()){
            for(Person ps : systemList.get(st)){
                if(ps.getUserName().equals(userName) && ps.getPassword().equals(password)){
                    return ps;
                }
            }
        }
        return null;

    }

    public static void checkPassword(Person person,String password) throws Exception {
        if(!person.getPassword().equals(password)){
            throw new Exception("Incorrect Password");
        }
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

    public static void removeClassFromTeachersList(Class cl,Teacher teacher){
        teacher.removeClass(cl);

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


    public static Class searchingClassString(String classInfo){
        for(Class cl  : classes){
            if(cl.toString().equals(classInfo)){
                return cl;
            }
        }
        return null;
    }

    public static Class searchClass(String name,String timeOfTheClass,String dayTime2) throws Exception {
        for(Class cl : classes){
            if(cl.getName().equals(name) && cl.getTIME_OF_THE_CLASS1().toString().equals(timeOfTheClass) )
            {
                if(cl.getTIME_OF_THE_CLASS2()!= null && cl.getTIME_OF_THE_CLASS2().toString().equals(dayTime2)){
                    return cl;
                }
                else{
                    return cl;
                }
            }
        }
        throw new Exception("class is not exists");
    }

    public static ArrayList<Student> searchClassStudents(String className,Teacher registeredTeacher) throws Exception {
        for(Class cl : registeredTeacher.getClasses()){
            if(cl.getName().equals(className)){
                return cl.getStudents();
            }
        }
        return null;
    }
    public static boolean checkEnrollment(Student registeredStudent, Class temp) throws Exception {

        for(Class cl : registeredStudent.getClasses()){
            if(cl.equals(temp)){
                throw new Exception("This course had been selected");
            }
            if(cl.getTIME_OF_THE_CLASS1().equals(temp.getTIME_OF_THE_CLASS1())){
                throw new Exception("Time Interference with " + cl.getName());
            }
            if(cl.getTIME_OF_THE_CLASS2()!= null && cl.getTIME_OF_THE_CLASS2().equals(temp.getTIME_OF_THE_CLASS1())){
                throw new Exception("Time Interference with " + cl.getName());
            }
            if(cl.getTIME_OF_THE_CLASS1().equals(temp.getTIME_OF_THE_CLASS2())){
                throw new Exception("Time Interference with " + cl.getName());
            }
            if(cl.getTIME_OF_THE_CLASS2()!= null && cl.getTIME_OF_THE_CLASS2().equals(temp.getTIME_OF_THE_CLASS2())){
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
    public static Person searchPerson(String userName,String name){
        for(String st : SystemManagement.systemList.keySet()){
            for(Person pr : SystemManagement.systemList.get(st)){
                if(pr.getName().equals(name) && pr.getUserName().equals(userName)){
                    return pr;
                }
            }
        }
        return null;
    }

    public static Student searchStudent(String userName) throws Exception {
        for(Person st : SystemManagement.systemList.get("Student")){
            if(st.getUserName().equals(userName)){
                return (Student)st;
            }
        }
        throw new Exception("Student Id is Incorrect");
    }

    public static void addStudent(Student temp) throws Exception {
        if(searchPerson(temp.getUserName(),temp.getName()) != null){
            throw new Exception("this user has been added");
        }
        else{
            systemList.get("Student").add(temp);
        }
    }

    public static Teacher searchTeacher(String userName,String name){
        for(Person st : SystemManagement.systemList.get("Teacher")){
            if(st.getName().equals(name) && st.getUserName().equals(userName)){
                return (Teacher) st;
            }
        }
        return null;
    }

    public static void addTeacher(Teacher temp) throws Exception {
        if(searchPerson(temp.getUserName(),temp.getName()) != null){
            throw new Exception("this user has been added");
        }
        systemList.get("Teacher").add(temp);
    }



    public static void addStudentToClass(Student student, Class temp) {
        temp.addStudent(student);
    }

    public static ArrayList<Student> getClassList(Class cla) {
        return cla.getStudents();
    }

    public static void setScore(Student st, String clName,Double score) {
        for(Class cl : st.getClasses()){
            if(cl.getName().equals(clName)){
                st.getEducationalReport().replace(cl.getName(),score);
                st.setAverage();
            }
        }
    }
}
