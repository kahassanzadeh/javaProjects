import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Class implements Serializable {
    private ArrayList<Student> students;
    private Teacher teacher;
    private int capacity;
    private String name;
    private final int CREDITS;
    private final ClassTime TIME_OF_THE_CLASS1;
    private final ClassTime TIME_OF_THE_CLASS2;
    private boolean status;

    Class(String name,int capacity,int credits,String classTime,Teacher teacher) throws Exception {
        this.name = name;
        this.CREDITS = credits;
        this.capacity = capacity;
        this.TIME_OF_THE_CLASS1 = ClassTime.valueOf(classTime);
        this.TIME_OF_THE_CLASS2 = null;
        this.students = new ArrayList<>();
        this.teacher = teacher;
        SystemManagement.addClass(this);
        teacher.addClass(this);
        status = true;
    }

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

    public int getCREDITS() {
        return CREDITS;
    }

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

    public void enroll(){
        capacity -= 1;
    }

    public ClassTime getTIME_OF_THE_CLASS1() {
        return TIME_OF_THE_CLASS1;
    }

    public ClassTime getTIME_OF_THE_CLASS2() {
        return TIME_OF_THE_CLASS2;
    }

    public String getName() {
        return name;
    }

    public String getTeacherName(){
        return teacher.getName();
    }

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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void removeClassFromStudentList() throws Exception {
        SystemManagement.renewStudentClassList(this.students,this);
        for(Student st : students){
            st.getClasses().remove(this);
        }
        this.status = false;
    }
}
