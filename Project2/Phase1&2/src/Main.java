import javax.swing.*;


public class Main {

    public static void main(String[] args) throws Exception {
	    System.out.println(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");



        LoginForm login = new LoginForm("Login Form");
        login.showLoginForm();

    }
}
