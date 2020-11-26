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
        StudentProfile st = new StudentProfile(s);
        st.showingStudentsProfile();
    }
}
