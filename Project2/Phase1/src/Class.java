import java.util.ArrayList;

public class Class {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private final int CAPACITY;
    private String name;
    private final int CREDITS;
    private final ClassTime TIME_OF_THE_CLASS;

    Class(String name,int capacity,int credits,String classTime){
        this.name = name;
        this.CREDITS = credits;
        this.CAPACITY = capacity;
        this.TIME_OF_THE_CLASS = ClassTime.valueOf(classTime);
    }

}
