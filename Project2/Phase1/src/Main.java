import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
	    System.out.println(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");


        Admin admin = new Admin("Kamyar Hassanzadeh","admin","admin");
        /*Teacher t1 = new Teacher("alireza rezaii","8678987","123456","Omran");
        Class cl1 = new Class("mohasebat",20,3,"Sat2",t1);
        Class cl2 = new Class("Azmayeshgah",50,1,"Tue2","Sun2",t1);
        Teacher t2 = new Teacher("hossein hosseini","4556654","123456","Mavad");

        Student st1 = new Student("ali mahmoodi","9739001","123456");
        Student st2 = new Student("mohammad alighorbani","9739005","123456");
        Student st3 = new Student("fereshteh akhgar","9739021","123456");
        Student st4 = new Student("reza eshtiaghi","9923012","123456");

        st1.setClasses(cl1);
        cl1.addStudent(st1);
        st2.setClasses(cl1);
        cl1.addStudent(st2);
        st3.setClasses(cl1);
        cl1.addStudent(st3);
        st4.setClasses(cl2);
        cl2.addStudent(st4);



        ArrayList<Person> a = new ArrayList<>();
        a.add(admin);
        ArrayList<Person> s = new ArrayList<>();
        s.add(st1);
        s.add(st2);
        s.add(st3);
        s.add(st4);
        ArrayList<Person> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        SystemManagement.getSystemList().put("Admin",a);
        SystemManagement.getSystemList().put("Student",s);
        SystemManagement.getSystemList().put("Teacher",t);*/
        //SystemManagement.save();

        LoginForm login = new LoginForm("Login Form");

        login.showLoginForm();

        //AdminProfile l = new AdminProfile(admin);
        //l.showingAdminProfile();

        //TeachersProfile tp = new TeachersProfile(t1);
        //tp.showTeacherProfile();

        //StudentProfile m = new StudentProfile(st1);
        //m.showingStudentsProfile();

        //m = new StudentProfile(st2);
        //m.showingStudentsProfile();

        /*HashMap<String,ArrayList<String>> map = new HashMap<>();
        map.put("salam",new ArrayList<>());
        map.get("salam").add("chtoori?");*/
    }
}
