import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    private final String TEACHER_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Teacher.ser";
    private final String STUDENT_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Student.ser";
    private final String ADMIN_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Admin.ser";
    private final String CLASS_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Class.ser";
    private final String FOOD_FILE_ADDRESS = "E:\\university\\5th term\\AP\\Projects\\Project2\\textFiles\\Food.ser";

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        temp.put("Admin",admins);
        temp.put("Student",students);
        temp.put("Teacher",teachers);

        return temp;

    }

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foods;
    }

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    public void totalPersonInfoWriting(HashMap<String , ArrayList<Person>> systemPersonInfo){
        WriteObjectToFile writeStudents = null;
        WriteObjectToFile writeTeacher = null;
        WriteObjectToFile writeAdmin = null;

        File studentsFile = new File(STUDENT_FILE_ADDRESS);

        ArrayList<Person> students = systemPersonInfo.get("Student");
        ArrayList<Person> teachers = systemPersonInfo.get("Teacher");
        ArrayList<Person> admins = systemPersonInfo.get("Admin");


        if(studentsFile.exists()){
            studentsFile.delete();
        }
        try{
            writeStudents = new WriteObjectToFile(STUDENT_FILE_ADDRESS);
            for(Person pr : students){
                writeStudents.writeToFile(pr);
            }
            writeStudents.closeConnection();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            writeTeacher = new WriteObjectToFile(TEACHER_FILE_ADDRESS);
            for(Person pr : teachers){
                writeTeacher.writeToFile(pr);
            }
            writeTeacher.closeConnection();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            writeAdmin = new WriteObjectToFile(ADMIN_FILE_ADDRESS);
            for(Person pr : admins){
                writeAdmin.writeToFile(pr);
            }
            writeAdmin.closeConnection();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        }

    }

}








