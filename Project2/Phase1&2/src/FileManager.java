import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is created for making and managing the files that had been written or id we want
 * to write our objects to the files
 *
 * @author Mohammadreza Hassanzadeh
 * @since Dec 16 2020
 * @version 1.0
 */
public class FileManager {
    //teacher file address
    private final String TEACHER_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Teacher.ser";
    //student file address
    private final String STUDENT_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Student.ser";
    //admin file address
    private final String ADMIN_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Admin.ser";
    //class file address
    private final String CLASS_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Class.ser";
    //food file address
    private final String FOOD_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Food.ser";

    /**
     * this method will read whole systemList in the file. Admin,Teacher and Student will be added to a HashMap
     * keys are "Student" "Teacher" "Admin"
     *
     * @return HashMap of the systemList
     * @throws IOException if reading is corrupted
     */
    public HashMap<String, ArrayList<Person>> totalPersonInfoReading() throws IOException {
        HashMap<String, ArrayList<Person>> temp = new HashMap<>();
        ArrayList<Person> students = new ArrayList<>();
        ArrayList<Person> teachers = new ArrayList<>();
        ArrayList<Person> admins = new ArrayList<>();

        ReadObjectFromFile readStudents = null;
        ReadObjectFromFile readTeachers = null;
        ReadObjectFromFile readAdmins = null;

        try{
            readStudents = new ReadObjectFromFile(STUDENT_FILE_ADDRESS);
            while(true){
                Student st = (Student) readStudents.readFromFile();
                students.add(st);
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
            try {
                readStudents.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
            catch(NullPointerException e2){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            readTeachers = new ReadObjectFromFile(TEACHER_FILE_ADDRESS);
            while(true){
                Teacher te = (Teacher) readTeachers.readFromFile();
                teachers.add(te);
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
            try {
                readTeachers.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
            catch(NullPointerException e2){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            readAdmins = new ReadObjectFromFile(ADMIN_FILE_ADDRESS);
            while(true){
                Admin ad = (Admin) readAdmins.readFromFile();
                admins.add(ad);
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
            try {
                readAdmins.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }
            catch (NullPointerException e2){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        temp.put("Admin",admins);
        temp.put("Student",students);
        temp.put("Teacher",teachers);

        return temp;

    }

    /**
     * this method will read all the foods that have been added to the list
     * @return Array list of foods
     */
    public ArrayList<Food> readFoods(){
        ArrayList<Food> foods = new ArrayList<>();

        ReadObjectFromFile readFood  = null;

        try{
            readFood = new ReadObjectFromFile(FOOD_FILE_ADDRESS);
            while(true){
                Food fd = (Food) readFood.readFromFile();
                foods.add(fd);
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
            try {
                readFood.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }catch (NullPointerException e2){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foods;
    }

    /**
     * this method will read whole classes that have been added to the class list
     *
     * @return Array list of the Class
     */
    public ArrayList<Class> readClass(){
        ArrayList<Class> classes = new ArrayList<>();

        ReadObjectFromFile readClasses  = null;

        try{
            readClasses = new ReadObjectFromFile(CLASS_FILE_ADDRESS);
            while(true){
                Class cl = (Class) readClasses.readFromFile();
                classes.add(cl);
            }
        } catch (FileNotFoundException | EOFException | ClassNotFoundException e) {
            try {
                readClasses.closeConnection();
            } catch (IOException e1) {
                System.out.println("error in closing file!");
            }catch(NullPointerException e2){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    /**
     * this method will write students to their specific file
     * @param systemPersonInfo hashMap of systemList
     */
    public void studentWritingToFile(HashMap<String , ArrayList<Person>> systemPersonInfo){
        File studentsFile = new File(STUDENT_FILE_ADDRESS);

        writeToFile(systemPersonInfo.get("Student"),studentsFile);

    }
    /**
     * this method will write teachers to their specific file
     * @param systemPersonInfo hashMap of systemList
     */
    public void teacherWriteToFile(HashMap<String , ArrayList<Person>> systemPersonInfo){
        File teacherFile = new File(TEACHER_FILE_ADDRESS);
        writeToFile(systemPersonInfo.get("Teacher"),teacherFile);
    }
    /**
     * this method will write admins to their specific file
     * @param systemPersonInfo hashMap of systemList
     */
    public void adminWriteToFile(HashMap<String , ArrayList<Person>> systemPersonInfo){
        File adminFile = new File(ADMIN_FILE_ADDRESS);
        writeToFile(systemPersonInfo.get("Admin"),adminFile);
    }

    /**
     * this method will write Person Objects to the File address given to the method
     *
     * @param person Array list of people that have been added to the system list
     * @param fileAddress file address of specific group of people
     */
    public void writeToFile(ArrayList<Person> person, File fileAddress){
        if(fileAddress.exists()){
            fileAddress.delete();
        }
        try{
            WriteObjectToFile write = new WriteObjectToFile(fileAddress.getAbsolutePath());
            for(Person pr : person){
                write.writeToFile(pr);
            }
            write.closeConnection();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e2){
            e2.printStackTrace();
        }
    }

    /**
     * this method will write foods to its specific file
     * @param food array list of foods
     */
    public void writeFoods(ArrayList<Food> food){
        WriteObjectToFile foodWriter = null;
        File foodFl = new File(FOOD_FILE_ADDRESS);
        if(foodFl.exists()){
            foodFl.delete();
        }
        try{
            foodWriter = new WriteObjectToFile(FOOD_FILE_ADDRESS);
            for(Food fd : food){
                foodWriter.writeToFile(fd);
            }
            foodWriter.closeConnection();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e){
            return;
        }

    }

    /**
     * this method will write classes into its file address
     * @param classes array list of the classes
     */
    public void writeClass(ArrayList<Class> classes){
        WriteObjectToFile classWriter = null;
        File classFl = new File(CLASS_FILE_ADDRESS);
        if(classFl.exists()){
            classFl.delete();
        }
        try{
            classWriter = new WriteObjectToFile(CLASS_FILE_ADDRESS);
            for(Class cl : classes){
                classWriter.writeToFile(cl);
            }
            classWriter.closeConnection();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e2){
            return;
        }

    }

}








