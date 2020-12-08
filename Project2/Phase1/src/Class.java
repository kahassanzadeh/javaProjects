import java.util.ArrayList;
import java.util.Objects;

public class Class {
    private ArrayList<Student> students;
    private Teacher teacher;
    private int capacity;
    private String name;
    private final int CREDITS;
    private final ClassTime TIME_OF_THE_CLASS;

    Class(String name,int capacity,int credits,String classTime,Teacher teacher){
        this.name = name;
        this.CREDITS = credits;
        this.capacity = capacity;
        this.TIME_OF_THE_CLASS = ClassTime.valueOf(classTime);
        this.students = new ArrayList<>();
        this.teacher = teacher;

    }

    public int getCREDITS() {
        return CREDITS;
    }

    @Override
    public String toString() {
        String namePadded = String.format("%-40s",name);
        String creditPadded = String.format("%-1s",CREDITS);
        String dayPadded = String.format("%-3s",TIME_OF_THE_CLASS.toString().substring(0,3));
        String capacityPadded = String.format("%-3s", capacity);
        String teacherPadded = String.format("%-20s", teacher.getName());
        String tempTime = null;
        if(this.TIME_OF_THE_CLASS.toString().charAt(3) == '1'){
            tempTime = "8 to 10";
        }
        else if(this.TIME_OF_THE_CLASS.toString().charAt(3) == '2'){
            tempTime = "10 to 12";
        }
        else if(this.TIME_OF_THE_CLASS.toString().charAt(3) == '3'){
            tempTime = "14 to 16";
        }
        String timePadded = String.format("%-7s",tempTime);
        return namePadded + teacherPadded + "                    " + creditPadded + "                    " + dayPadded + "                    " + timePadded + "                    " + capacityPadded;
    }

    public void enroll(){
        capacity -= 1;
    }

    public ClassTime getTIME_OF_THE_CLASS() {
        return TIME_OF_THE_CLASS;
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
                TIME_OF_THE_CLASS == aClass.TIME_OF_THE_CLASS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, teacher, capacity, name, CREDITS, TIME_OF_THE_CLASS);
    }
}
