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

    public int getCREDITS() {
        return CREDITS;
    }

    @Override
    public String toString() {
        String namePadded = String.format("%-70s",name);
        String creditPadded = String.format("%-1s",CREDITS);
        String dayPadded = String.format("%-3s",TIME_OF_THE_CLASS.toString().substring(0,3));
        String capacityPadded = String.format("%-3s",CAPACITY);
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
        return namePadded + creditPadded + "                    " + dayPadded + "                    " + timePadded + "                    " + capacityPadded;
    }
}
