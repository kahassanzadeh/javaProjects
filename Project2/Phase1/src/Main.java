import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
	    System.out.println(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");


        Admin admin = new Admin("Kamyar Hassanzadeh","admin","admin");
        Teacher t1 = new Teacher("alireza rezaii","8678987","123456","Omran");
        Teacher t2 = new Teacher("hossein hosseini","4556654","123456","Mavad");

        Student st1 = new Student("ali mahmoodi","9739001","123456");
        Student st2 = new Student("mohammad alighorbani","9739005","123456");
        Student st3 = new Student("fereshteh akhgar","9739021","123456");
        Student st4 = new Student("reza eshtiaghi","9923012","123456");

        Class cl1 = new Class("mohasebat",20,3,"Sat2",t1);
        Class cl2 = new Class("Azmayeshgah",50,1,"Tue2",t2);

        SystemManagement.setClasses(cl1);
        SystemManagement.setClasses(cl2);
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
        SystemManagement.getSystemList().put("Teacher",t);

        AdminProfile l = new AdminProfile(admin);
        l.showingAdminProfile();



    }
}
