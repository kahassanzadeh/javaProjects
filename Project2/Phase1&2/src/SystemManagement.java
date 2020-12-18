import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * this class created for managing the whole system and managing the saving and reading
 *
 * @author Mohammadreza Hassanzadeh
 * @since Dec 16 2020
 * @version 1.0
 *
 */
public class SystemManagement {
    //hash map of the whole person in the System
    private static HashMap<String, ArrayList<Person>> systemList = new HashMap<>();
    //food of the whole system
    private static ArrayList<Food> foodsSchedules = new ArrayList<>();
    //classes of the whole system
    private static ArrayList<Class> classes = new ArrayList<>();

    /**
     * this method will read and renew all the fields of this class from file
     * @throws IOException if reading is corrupted
     */
    public static void renew() throws IOException {
        FileManager flManager = new FileManager();
        systemList = flManager.totalPersonInfoReading();
        foodsSchedules = flManager.readFoods();
        classes = flManager.readClass();
    }

    /**
     * this method will save all the things that have been added to the whole system
     *
     */
    public static void save(){
        FileManager flManager = new FileManager();
        flManager.adminWriteToFile(systemList);
        flManager.teacherWriteToFile(systemList);
        flManager.studentWritingToFile(systemList);
        flManager.writeClass(classes);
        flManager.writeFoods(foodsSchedules);
    }

    /**
     * this method will write admin to the file
     */
    public static void saveAdmin(){
        FileManager flManager = new FileManager();
        flManager.adminWriteToFile(systemList);
    }

    /**
     * this method will add classes to the systemList
     * @param cl class that we want to add
     */
    public static void addClass(Class cl){
        classes.add(cl);
    }

    /**
     * this method will search a person for login and if it couldn't find, it will return null
     *
     * @param userName user name of the person
     * @param password password of the person
     * @return the person that has found
     */
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

    /**
     * this method will check if a person's password is matched to the password that have been written
     *
     * @param person person that writes the password
     * @param password password of the person
     * @throws Exception if there is a problem with matching the passwords
     */
    public static void checkPassword(Person person,String password) throws Exception {
        if(!person.getPassword().equals(password)){
            throw new Exception("Incorrect Password");
        }
    }

    /**
     * this method will check if the login has successful or not
     * @param userName user name of the person
     * @param password password of the person
     * @return true if iy can login successfully
     */
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

    /**
     * getter for food schedules
     *
     * @return array list of food objects
     * @throws Exception if there isn't any food added to the food schedules
     */
    public static ArrayList<Food> getFoodsSchedules() throws Exception {
        if(foodsSchedules == null){
            throw new Exception("food not created");
        }
        return foodsSchedules;
    }

    /**
     * this method will remove a class from teacher's list
     *
     * @param cl class that we want to remove
     * @param teacher teacher that we want to remove its class
     */
    public static void removeClassFromTeachersList(Class cl,Teacher teacher){
        teacher.removeClass(cl);

    }

    /**
     * this method will add foods to the foods schedules
     * @param foodsSchedule food that we want to add
     */
    public static void setFoodsSchedules(Food foodsSchedule) {
        SystemManagement.foodsSchedules.add(foodsSchedule);
    }

    /**
     * this method will search the food by its name and distribution day
     *
     * @param name String name of the food
     * @param day String day of thr food
     * @return the food that founded
     */
    public static Food searchFood(String name,String day){
        for(Food fd : foodsSchedules){
            if(fd.getName().equals(name) && fd.getDistributionDay().equals(day)){
                return fd;
            }
        }
        return null;
    }

    /**
     * this method will manage food reserving
     *
     * @param person person that we want to reser ve food for
     * @param reservingFood the food that we want to reserve
     * @return true if it can reserve successfully
     */
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

    /**
     * this method will add food by its name, cost and day of distribution
     *
     * @param name name of the food
     * @param costString cost of the food
     * @param dayOfDistribution day of the food
     * @throws Exception if it can't answer the needs
     */
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

    /**
     * this method will check if the day is valid
     *
     * @param day day that we have entered
     * @return true if the day is valid
     */
    private static boolean validDay(String day){
        return day.equals("Sun") || day.equals("Mon") || day.equals("Tue") || day.equals("Wed") || day.equals("Sat");
    }

    /**
     * this method will count the food in a day
     * @param day day that we want to count foods
     * @return number of foods that have been added
     */
    private static int countingFoodsInOneDay(String day){
        int counter = 0;
        for(Food fd : foodsSchedules){
            if(fd.getDistributionDay().equals(day)){
                counter++;
            }
        }
        return counter;
    }

    /**
     * getting the number of classes
     *
     * @return number of classes
     */
    public static int getClassNumbers() {
        if(classes.size() < 25){
            return 25;
        }
        return classes.size();
    }

    /**
     * getting the classes array list
     * @return array list of classes
     */
    public static ArrayList<Class> getClasses() {
        return classes;
    }

    /**
     * searching a class by toString method
     *
     * @param classInfo String of the class
     * @return founded class
     */
    public static Class searchingClassString(String classInfo){
        for(Class cl  : classes){
            if(cl.toString().equals(classInfo)){
                return cl;
            }
        }
        return null;
    }

    /**
     * this method searches a class by its name and time
     * @param name name of the class
     * @param timeOfTheClass first time of the class
     * @param dayTime2 second time of the class
     * @return the class founded
     * @throws Exception if there isn't a class with this info
     */
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

    /**
     * overloaded method for getting a class
     *
     * @param st name of the class
     * @param name name of the student
     * @return Class
     * @throws Exception if the class doesn't exist
     */
    public static Class searchClass(String st, String name) throws Exception {
        for(Class cl : classes){
            if(cl.getName().equals(st) && checkStudentInClass(cl,name))
            {
                return cl;
            }
        }
        throw new Exception("class is not exists");
    }

    /**
     * this method will search students in a teacher's class list
     *
     * @param className name of the classes
     * @param registeredTeacher Teacher
     * @return array list of the students that belongs to the class
     * @throws Exception if there isn't any classes with that info
     */
    public static ArrayList<Student> searchClassStudents(String className,Teacher registeredTeacher) throws Exception {
        for(Class cl : registeredTeacher.getClasses()){
            if(cl.getName().equals(className)){
                return cl.getStudents();
            }
        }
        return null;
    }

    /**
     * this method will check if the student can enroll the class
     *
     * @param registeredStudent registered student
     * @param temp class that student wants to enroll
     * @return true if there isn't any problem
     * @throws Exception if the student can't enroll
     */
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
        if(temp.getCapacity() == 0){
            throw new Exception("full capacity");
        }
        if(registeredStudent.getEducationalReport().containsKey(temp.getName())){
            throw new Exception("You have passed this course");
        }
        return true;
    }

    /**
     * getting the whole system list of the people
     * @return hashmap
     */
    public static HashMap<String, ArrayList<Person>> getSystemList() {
        return systemList;
    }

    /**
     * searching a person by its username
     *
     * @param userName user name of the person
     * @return Person object
     */
    public static Person searchPerson(String userName){
        for(String st : SystemManagement.systemList.keySet()){
            for(Person pr : SystemManagement.systemList.get(st)){
                if(pr.getUserName().equals(userName)){
                    return pr;
                }
            }
        }
        return null;
    }

    /**
     * searching a student by its user name
     * @param userName user name of the student
     * @return Student
     * @throws Exception if it can't find the student
     */
    public static Student searchStudent(String userName) throws Exception {
        for(Person st : SystemManagement.systemList.get("Student")){
            if(st.getUserName().equals(userName)){
                return (Student)st;
            }
        }
        throw new Exception("Student Id is Incorrect");
    }

    /**
     * adding a student to the student's systemList
     * @param temp Student
     * @throws Exception if the user had been added
     */
    public static void addStudent(Student temp) throws Exception {
        if(searchPerson(temp.getUserName()) != null){
            throw new Exception("this user has been added");
        }
        else{
            systemList.get("Student").add(temp);
        }
    }

    /**
     * searching a teacher
     * @param userName user name of the teacher
     * @param name name of the teacher
     * @return Teacher
     */
    public static Teacher searchTeacher(String userName,String name){
        for(Person st : SystemManagement.systemList.get("Teacher")){
            if(st.getName().equals(name) && st.getUserName().equals(userName)){
                return (Teacher) st;
            }
        }
        return null;
    }

    /**
     * adding a teacher to the teacher SystemList
     * @param temp teacher
     * @throws Exception if the user had been added
     */
    public static void addTeacher(Teacher temp) throws Exception {
        if(searchPerson(temp.getUserName()) != null){
            throw new Exception("this user has been added");
        }
        systemList.get("Teacher").add(temp);
    }


    /**
     * this method will student to the class
     * @param student student
     * @param temp class
     */
    public static void addStudentToClass(Student student, Class temp) {
        temp.addStudent(student);
    }

    /**
     * getting the students of a class
     * @param cla class
     * @return array list of the class
     */
    public static ArrayList<Student> getClassList(Class cla) {
        return cla.getStudents();
    }

    /**
     * setting the score for an student
     * @param st student
     * @param clName name of the lass
     * @param score score of that class
     */
    public static void setScore(Student st, String clName,Double score) {
        for(Class cl : st.getClasses()){
            if(cl.getName().equals(clName)){
                st.getEducationalReport().replace(cl.getName(),score);
                st.setAverage();
            }
        }
    }

    /**
     * this method will update the teacher's class lists
     * @param temp class
     */
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

    /**
     * this method will update the students of a class
     *
     * @param st student
     * @param s class name
     */
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

    /**
     * this method will update the teacher list
     * @param st student
     * @param temp class
     */
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
    }

    /**
     * this method will update the students class list
     * @param registeredStudent registered student
     */
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

    /**
     * this method will generate String for the combo box days
     * @param n if n == 0 then it won't returns the --- option
     * @return string array
     */
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

    /**
     * getting the valid times
     * @return String of vlaid times
     */
    public static String[] getValidTime() {
        String[] temp = new String[3];
        temp[0] = "8 to 10";
        temp[1] = "10 to 12";
        temp[2] = "14 to 16";
        return  temp;

    }

    /**
     * this method wil change ID
     *
     * @param oldUserID old user name
     * @param newUserID new user name
     * @param password password
     * @param registeredPerson registered person
     * @throws Exception if there is any problem for changing the ID
     */
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

    /**
     * changing the password
     * @param userID user name
     * @param oldPass old password
     * @param newPass new password
     * @param newPassConfirm new password to confirm
     * @param person registered person
     * @throws Exception if there is any problem for changing the pass
     */
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

    /**
     * this method will renew students in a class
     * @param st student
     * @param aClass class
     * @throws Exception if there is any problem
     */
    public static void renewStudentClassList(ArrayList<Student> st, Class aClass) throws Exception {
        for(Student student : st){
            searchStudent(student.getUserName()).removeClass(aClass);
        }
    }

    /**
     * this method will check if the ID had been entered or not and it will check the password
     * @param temp new person that we want to add
     * @throws Exception if there is any problem for adding
     */
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

    /**
     * this method will check if a student belongs to a class
     * @param cl class
     * @param name name of the student
     * @return true if the student belongs to the class
     */
    public static boolean checkStudentInClass(Class cl,String name){
        for(Student st : cl.getStudents()){
            if(st.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
