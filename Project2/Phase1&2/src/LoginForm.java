import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this method will creates the login panel
 *
 * @author Mohammadreza hassanzadeh
 * @since 17 Dec 200
 * @version 1.0
 */
public class LoginForm {

    //main frame of the panel
    private JFrame loginForm;
    //login button
    private JButton loginButton;
    //username text field
    private JTextField userNameField;
    //password
    private JPasswordField passwordField;
    //registered person
    private Person registeredPerson;

    /**+
     * constructor for making the login form
     * @param title title of the form
     * @throws IOException if it cant read from files
     */
    public LoginForm(String title) throws IOException {
        loginForm = new JFrame(title);
        loginForm.setLocation(500,300);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setPreferredSize(new Dimension(400,200));
        SystemManagement.renew();
        JPanel panel = new JPanel(new BorderLayout(10,10));
        loginForm.setContentPane(panel);

        JLabel label = new JLabel("Please enter your username and password");
        label.setPreferredSize(new Dimension(150, 50));
        label.setBackground(Color.lightGray);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Calibri",Font.BOLD,17));

        ButtonHandler buttonHandler = new ButtonHandler();
        MouseHandler mouseHandler = new MouseHandler();

        JLabel userNameLabel = new JLabel("User Name : ");
        userNameField = new JTextField("Enter your User Name...");
        userNameField.setForeground(Color.lightGray);
        userNameField.addMouseListener(mouseHandler);
        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField = new JPasswordField();


        JPanel fields = new JPanel(new GridLayout(2,2,5,5));
        fields.add(userNameLabel);
        fields.add(userNameField);
        fields.add(passwordLabel);
        fields.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(buttonHandler);
        loginButton.addKeyListener(new KeyBoardHandler());


        panel.add(label,BorderLayout.NORTH);
        panel.add(fields,BorderLayout.CENTER);
        panel.add(loginButton,BorderLayout.SOUTH);


    }

    /**
     * showing the login form
     */
    public void showLoginForm(){
        loginForm.pack();
        loginForm.setVisible(true);
    }

    /**
     * setting the registered person
     * @param person registered person
     */
    public void setRegisteredPerson(Person person){
        this.registeredPerson = person;
    }

    public Person getRegisteredPerson() {
        return registeredPerson;
    }

    /**
     * inner class for handling the button
     */
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(loginButton)){
                if(SystemManagement.getSystemList().get("Admin").size() == 0){
                    Admin admin = new Admin("Kamyar Hassanzadeh","admin","admin");
                    ArrayList<Person> a = new ArrayList<>();
                    a.add(admin);
                    SystemManagement.getSystemList().put("Admin",a);
                    SystemManagement.saveAdmin();
                }
                if(SystemManagement.checkLogin(userNameField.getText(), String.valueOf(passwordField.getPassword()))){
                    setRegisteredPerson(SystemManagement.searchPersonForLogin(userNameField.getText(),String.valueOf(passwordField.getPassword())));
                    loginForm.setVisible(false);
                    if(registeredPerson != null ){
                        StudentProfile profileS = null;
                        AdminProfile profileA = null;
                        TeachersProfile profileT = null;
                        try {
                            if(registeredPerson instanceof Student){
                                profileS = new StudentProfile((Student)registeredPerson);
                            }
                            else if(registeredPerson instanceof Admin){
                                profileA = new AdminProfile((Admin) registeredPerson);
                            }
                            else if( registeredPerson instanceof Teacher){
                                profileT = new TeachersProfile((Teacher) registeredPerson);
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
                        else if(profileT != null){
                            profileT.showTeacherProfile();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginForm,"Invalid password or user name","Error",JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    userNameField.setText("");
                    setRegisteredPerson(null);
                }
            }
        }
    }

    /**
     * inner class for handling the mouse adapter
     */
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(userNameField)){
                userNameField.setText("");
                userNameField.setForeground(Color.BLACK);
            }
        }

    }

    /**
     * inner class for handling the pressed keys
     */
    private  class KeyBoardHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                loginButton.doClick();
            }
        }
    }
}
