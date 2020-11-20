import javax.swing.*;
import java.awt.*;

public class LoginForm {


    private JFrame loginForm;
    private JButton loginButton;
    private JTextField userNameField;
    private JPasswordField passwordField;

    public LoginForm(String title){
        loginForm = new JFrame(title);
        loginForm.setLocation(null);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        loginForm.setContentPane(panel);

        JLabel label = new JLabel("Please enter your username and password");
        label.setBackground(Color.lightGray);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        ButtonHandler handler = new ButtonHandler();

        



    }

    private class ButtonHandler {
    }
}
