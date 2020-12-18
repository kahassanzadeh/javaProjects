import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * this class created for managing the classes and its students
 *
 * @author Mohammadreza Hassanzadeh
 * @since 17 Dec 2020
 * @version 1.0
 *
 */
public class Class implements Serializable {
    //students of the class
    private ArrayList<Student> students;
    //teacher of the class
    private Teacher teacher;
    //capacity of the class
    private int capacity;
    //name of the class
    private String name;
    //credits of this class
    private final int CREDITS;
    //first tim of the class
    private final ClassTime TIME_OF_THE_CLASS1;
    //second time of the class ad it can be NULL
    private final ClassTime TIME_OF_THE_CLASS2;
    //if the class is active or not
    private boolean status;

    /**
     * Constructor for this class
     * it will add the class to the system list and the teacher's list
     *
     * @param name name of the class
     * @param capacity capacity of the class
     * @param credits credits of the class
     * @param classTime time of the class
     * @param teacher  the teacher of the class
     * @throws Exception if there is any problem for adding the class
     */
    Class(String name,int capacity,int credits,String classTime,Teacher teacher) throws Exception {
        this.name = name;
        this.CREDITS = credits;
        this.capacity = capacity;
        this.TIME_OF_THE_CLASS1 = ClassTime.valueOf(classTime);
        this.TIME_OF_THE_CLASS2 = null;
        this.students = new ArrayList<>();
        this.teacher = teacher;
        teacher.addClass(this);
        SystemManagement.addClass(this);
        status = true;
    }

    /**
     * Constructor for this class
     * it will add the class to the system list and the teacher's list
     *
     * @param name name of the class
     * @param capacity capacity of the class
     * @param credits credits of the class
     * @param classTime1 first time of the class
     * @param classTime2 second time of the class
     * @param teacher  the teacher of the class
     * @throws Exception if there is any problem for adding the class
     */
    Class(String name,int capacity,int credits,String classTime1,String classTime2,Teacher teacher) throws Exception {
        this.name = name;
        this.CREDITS = credits;
        this.capacity = capacity;
        this.TIME_OF_THE_CLASS1 = ClassTime.valueOf(classTime1);
        this.TIME_OF_THE_CLASS2 = ClassTime.valueOf(classTime2);
        this.students = new ArrayList<>();
        this.teacher = teacher;
        SystemManagement.addClass(this);
        teacher.addClass(this);
        status = true;
    }

    /**
     * getter for credits
     * @return int credits of the class
     */
    public int getCREDITS() {
        return CREDITS;
    }

    /**
     * override method
     * getting the full info of the class
     * @return string info of the class
     */
    @Override
    public String toString() {
        String namePadded = String.format("%-40s",name);
        String creditPadded = String.format("%-1s",CREDITS);
        String dayPadded1 = String.format("%-4s",TIME_OF_THE_CLASS1.toString().substring(0,3));
        String dayPadded2 = "";
        if(TIME_OF_THE_CLASS2 != null){
            dayPadded2 = String.format("%-4s",TIME_OF_THE_CLASS2.toString().substring(0,3));
        }
        String capacityPadded = String.format("%-3s", capacity);
        String teacherPadded = String.format("%-20s", teacher.getName());
        String tempTime = null;
        if(this.TIME_OF_THE_CLASS1.toString().charAt(3) == '1'){
            tempTime = "8 to 10";
        }
        else if(this.TIME_OF_THE_CLASS1.toString().charAt(3) == '2'){
            tempTime = "10 to 12";
        }
        else if(this.TIME_OF_THE_CLASS1.toString().charAt(3) == '3'){
            tempTime = "14 to 16";
        }
        String timePadded = String.format("%-7s",tempTime);
        return namePadded + teacherPadded + "                    " + creditPadded + "                    " + dayPadded1 +" "+ dayPadded2 + "                    " + timePadded + "                    " + capacityPadded;
    }

    /**
     * this method will update the capacity whenever somebody enroll
     */
    public void enroll(){
        capacity -= 1;
    }

    /**
     * getter for first time of the class
     * @return String time of the class
     */
    public ClassTime getTIME_OF_THE_CLASS1() {
        return TIME_OF_THE_CLASS1;
    }

    /**
     * getter for second time of the class
     * @return String time of the cass
     */
    public ClassTime getTIME_OF_THE_CLASS2() {
        return TIME_OF_THE_CLASS2;
    }

    /**
     * getter for name of the class
     *
     * @return String name of the class
     */
    public String getName() {
        return name;
    }

    /**
     * getting the name of the teacher of the class
     *
     * @return String name of the class
     */
    public String getTeacherName(){
        return teacher.getName();
    }

    /**
     * override method
     * if the name, credits,time and the teacher name of the class is equals
     * @param o object
     * @return tru if objects are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return  CREDITS == aClass.CREDITS &&
                Objects.equals(name, aClass.name) &&
                TIME_OF_THE_CLASS1 == aClass.TIME_OF_THE_CLASS1 && teacher.getName().equals(aClass.getTeacherName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, teacher, capacity, name, CREDITS, TIME_OF_THE_CLASS1);
    }

    /**
     * getting the array list of students
     *
     * @return array list of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * setting the status of the class
     * @param status stautus of the class
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * getting the status of the class
     *
     * @return getting the status of the class
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * adding student to the student list
     *
     * @param student object of Student class
     */
    public void addStudent(Student student){
        students.add(student);
    }

    /**
     * getting the teacher of the class
     * @return object of Teacher class
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * this method will remove this class from students list
     * @throws Exception if this class is not founded in systemManagement class list
     */
    public void removeClassFromStudentList() throws Exception {
        SystemManagement.renewStudentClassList(this.students,this);
        for(Student st : students){
            st.getClasses().remove(this);
        }
        this.status = false;
    }

    /**
     * getting the capacity of the class
     * @return int number of capacity
     */
    public int getCapacity() {
        return capacity;
    }
}
