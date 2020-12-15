import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SystemManagement {

    private static HashMap<String, ArrayList<Person>> systemList = new HashMap<>();
    private static ArrayList<Food> foodsSchedules = new ArrayList<>();
    private static ArrayList<Class> classes = new ArrayList<>();


    public static void renew() throws IOException {
        FileManager flManager = new FileManager();
        systemList = flManager.totalPersonInfoReading();
        foodsSchedules = flManager.readFoods();
        classes = flManager.readClass();
    }
    public static void save(){
        FileManager flManager = new FileManager();
        flManager.adminWriteToFile(systemList);
        flManager.teacherWriteToFile(systemList);
        flManager.studentWritingToFile(systemList);
        flManager.writeClass(classes);
        flManager.writeFoods(foodsSchedules);
    }
    public static void saveAdmin(){
        FileManager flManager = new FileManager();
        flManager.adminWriteToFile(systemList);
    }


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

    public static ArrayList<Food> getFoodsSchedules() throws Exception {
        if(foodsSchedules == null){
            throw new Exception("food not created");
        }
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
        return day.equals("Sun") || day.equals("Mon") || day.equals("Tues") || day.equals("Wed") || day.equals("Sat");
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
        if(registeredStudent.getEducationalReport().containsKey(temp.getName())){
            throw new Exception("You have passed this course");
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

    public static void updateTeacherList(Class temp) {
        Teacher t = searchTeacher(temp.getTeacher().getUserName(), temp.getTeacher().getName());
        int counter = 0;
        for (Class cl : t.getClasses()) {
            if (cl.equals(temp)) {
                t.getClasses().set(counter, temp);
            }
            counter++;
        }
    }

    //public static void updateStudentList()

    public static void updateClassList(Student st, String s) {
        Class temp = st.searchClass(s);
        int counter = 0;
        for(Class cl : classes){
            if(cl.equals(temp)){
                for(Student stt : cl.getStudents()){
                    if(stt.getUserName().equals(st.getUserName())){
                        cl.getStudents().set(counter,st);
                        break;
                    }
                    counter++;
                }
            }
        }
    }

    public static void updateTeacherList(Student st, Class temp) {
        Teacher t = searchTeacher(temp.getTeacher().getUserName(), temp.getTeacher().getName());
        int counter = 0;
        Class tempt = t.searchClass(temp.getName());
        for(Student student: tempt.getStudents()){
            if(student.getUserName().equals(st.getUserName())){
                tempt.getStudents().set(counter,st);
            }
            counter++;
        }
        /*for (Class cl : t.getClasses()) {
            if (cl.equals(temp)) {
                for(Student student : cl.getStudents()){
                    if(student.getUserName().equals(st.getUserName()))
                    {
                        cl.getStudents().set(counter,st);
                        return;
                    }
                }
                counter++;
            }
        }*/
    }

    public static void updateStudentClassList(Student registeredStudent) {
        int counter = 0;
        for(Class cl : registeredStudent.getClasses()){
            for(Class sysCl : classes){
                if(cl.equals(sysCl)){
                    registeredStudent.getClasses().set(counter,sysCl);
                    counter++;
                }
            }
        }
    }

    public static String[] getValidDays(int n) {
        String[] temp = new String[5];
        if(n == 0){
            temp[0] = "Sat";
            temp[1] = "Sun";
            temp[2] = "Mon";
            temp[3] = "Tue";
            temp[4] = "Wed";
        }
        else{
            temp = new String[6];
            temp[0] = "---";
            temp[1] = "Sat";
            temp[2] = "Sun";
            temp[3] = "Mon";
            temp[4] = "Tue";
            temp[5] = "Wed";
        }
        return temp;
    }

    public static String[] getValidTime() {
        String[] temp = new String[3];
        temp[0] = "8 to 10";
        temp[1] = "10 to 12";
        temp[2] = "14 to 16";
        return  temp;

    }

    public static void changeID(String oldUserID, String newUserID, char[] password, Person registeredPerson) throws Exception {
        if(!registeredPerson.getUserName().equals(oldUserID)){
            throw new Exception("your old User ID is Wrong");
        }
        for(String st : systemList.keySet()){
            for(Person pr : systemList.get(st)){
                if(pr.getUserName().equals(newUserID)){
                    throw new Exception("This User ID had been added");
                }
            }
        }
        String tempPass ="";
        for (char c : password) {
            tempPass += c;
        }
        if(!tempPass.equals(registeredPerson.getPassword())){
            throw new Exception("Password not Correct");
        }
        registeredPerson.changeUserID(newUserID);

    }

    public static void changePass(String userID, char[] oldPass, char[] newPass, char[] newPassConfirm, Person person) throws Exception {
        String tempOldPass = "";
        String tempNewPass = "";
        String tempNewPassConfirm = "";
        for (char value : oldPass) {
            tempOldPass += value;
        }
        for (char pass : newPass) {
            tempNewPass += pass;
        }
        for (char c : newPassConfirm) {
            tempNewPassConfirm += c;
        }
        if(!tempNewPass.equals(tempNewPassConfirm)){
            throw new Exception("check your passwords again,new pass and confirmations doesn't match");
        }
        if(!tempOldPass.equals(person.getPassword())){
            throw new Exception("your old Password is incorrect");
        }
        if(tempNewPass.length() < 8){
            throw new Exception("Password characters must be at least 8 characters");
        }
        if(!userID.equals(person.getUserName())){
            throw new Exception("your userID is incorrect");
        }

        person.changePass(tempNewPass);

    }

    public static void renewStudentClassList(ArrayList<Student> st, Class aClass) throws Exception {
        for(Student student : st){
            searchStudent(student.getUserName()).removeClass(aClass);
        }
    }

    public static void checkIDAndPass(Person temp) throws Exception {
        for(String st : systemList.keySet()){
            for(Person pr : systemList.get(st)){
                if(pr.getUserName().equals(temp.getUserName())){
                    throw new Exception("This User ID had been added");
                }
            }
        }
        if(temp.getPassword().length() < 8){
            throw new Exception("Password Must Have At Least 8 Characters");
        }
    }
}
