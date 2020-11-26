import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
	    System.out.println(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        LoginForm l = new LoginForm("Login Page");

        ArrayList<Person> p = new ArrayList<>();
        Student s = new Student("Kamyar Hassanzadeh","9739021","0022919678");
        p.add(s);
        /*SystemManagement.systemList.put("Student",p);
        l.showLoginForm();*/
        Food fd = new Food("gheyme",2000,"Tuesday");
        SystemManagement.setFoodsSchedules(fd);
        fd = new Food("zereshkpolo",3000,"Monday");
        SystemManagement.setFoodsSchedules(fd);
        fd = new Food("adasi",1000,"Saturday");
        SystemManagement.setFoodsSchedules(fd);
        fd = new Food("albalopolo",3000,"Sunday");
        SystemManagement.setFoodsSchedules(fd);
        fd = new Food("ghorme sabzi",5000,"Sunday");
        SystemManagement.setFoodsSchedules(fd);
        fd = new Food("makaroni",3000,"Wednesday");
        SystemManagement.setFoodsSchedules(fd);
        s.setReservedFoods(new Food("makaroni",3000,"Wednesday"));

        Class temp = new Class("AP",30,3,"Sat3");
        SystemManagement.setClasses(temp);
        temp = new Class("Discrete",50,2,"Sat1");
        SystemManagement.setClasses(temp);
        temp = new Class("Casting",15,3,"Tue2");
        SystemManagement.setClasses(temp);
        temp = new Class("ECW",30,1,"Wed3");
        SystemManagement.setClasses(temp);
        temp = new Class("Solidification",25,2,"Wed2");
        SystemManagement.setClasses(temp);
        temp = new Class("Algorithm",50,3,"Mon2");
        SystemManagement.setClasses(temp);

        temp = new Class("Math1",110,3,"Tue3");
        SystemManagement.setClasses(temp);




        StudentProfile st = new StudentProfile(s);
        st.showingStudentsProfile();


    }
}
