import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginForm {


    private JFrame loginForm;
    private JButton loginButton;
    //private JButton changingPassword;
    private JTextField userNameField;
    private JPasswordField passwordField;

    private Person registeredPerson;

    public LoginForm(String title){
        loginForm = new JFrame(title);
        loginForm.setLocation(500,300);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        loginForm.setContentPane(panel);

        JLabel label = new JLabel("Please enter your username and password");
        label.setBackground(Color.lightGray);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        ButtonHandler buttonHandler = new ButtonHandler();
        MouseHandler mouseHandler = new MouseHandler();

        JLabel userNameLabel = new JLabel("User Name : ");
        userNameField = new JTextField("Enter your User Name...");
        userNameField.setForeground(Color.lightGray);
        userNameField.addMouseListener(mouseHandler);


        JLabel passwordLabel = new JLabel("Password : ");
        passwordField = new JPasswordField();


        JPanel fields = new JPanel(new GridLayout(2,2,5,5));
        fields.add(userNameLabel);
        fields.add(userNameField);
        fields.add(passwordLabel);
        fields.add(passwordField);

        //JPanel buttons = new JPanel(new GridLayout(1,2,5,5));

        loginButton = new JButton("Login");
        loginButton.addActionListener(buttonHandler);

        //changingPassword = new JButton("Changing password");
        //ChangingPasswordHandler changePasswordHandler = new ChangingPasswordHandler();
        //changingPassword.addActionListener(changePasswordHandler);
        //buttons.add(loginButton);
        //buttons.add(changingPassword);

        panel.add(label,BorderLayout.NORTH);
        panel.add(fields,BorderLayout.CENTER);
        panel.add(loginButton,BorderLayout.SOUTH);


    }
    public void showLoginForm(){
        loginForm.pack();
        loginForm.setVisible(true);
    }

    /*public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }*/
    public void setRegisteredPerson(Person person){
        this.registeredPerson = person;
    }

    public Person getRegisteredPerson() {
        return registeredPerson;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(loginButton)){
                if(SystemManagement.checkLogin(userNameField.getText(), String.valueOf(passwordField.getPassword()))){
                    setRegisteredPerson(SystemManagement.searchPersonForLogin(userNameField.getText(),String.valueOf(passwordField.getPassword())));
                    loginForm.setVisible(false);
                    if(registeredPerson != null ){
                        StudentProfile profileS = null;
                        AdminProfile profileA = null;
                        try {
                            if(registeredPerson instanceof Student){
                                profileS = new StudentProfile((Student)registeredPerson);
                            }
                            else if(registeredPerson instanceof Admin){
                                profileA = new AdminProfile((Admin) registeredPerson);
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        if( profileS != null){
                            profileS.showingStudentsProfile();
                        }
                        else if(profileA != null){
                            profileA.showingAdminProfile();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginForm,"Invalid password or user name","Error",JOptionPane.ERROR_MESSAGE);
                    setRegisteredPerson(null);
                }
            }
        }
    }
    private class ChangingPasswordHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(userNameField)){
                userNameField.setText("");
                userNameField.setForeground(Color.BLACK);
            }
        }

        /*@Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource().equals(userNameField)){
                userNameField.setText("Enter your User Name...");
                userNameField.setForeground(Color.lightGray);
            }
        }*/
    }
}
