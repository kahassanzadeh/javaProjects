import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StudentProfile {

    private JFrame frame;
    private BufferedImage image;
    private JPanel studentProfile ;
    private JPanel studentOperations;
    private JLabel balance;

    public StudentProfile(Student registeredStudent) throws IOException {


        frame = new JFrame("Student Profile");
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);

        settingStudentProfileInfo(registeredStudent);
        setReservingForm(registeredStudent);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Profile",studentProfile);
        tabs.addTab("Reserving Food",studentOperations);
        frame.setContentPane(tabs);

    }
    public void showingStudentsProfile(){
        frame.pack();
        frame.setVisible(true);
    }
    public void setClassesSchedule(){

    }
    public void settingStudentProfileInfo(Student registeredStudent){

        studentProfile = new JPanel(new BorderLayout());
        JPanel studentInfo = new JPanel(new GridLayout(3,1,5,5));
        studentInfo.setSize(400,300);
        studentInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Phase1\\Student.png",50,10,100,100);
        JLabel firstName = new JLabel(registeredStudent.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(registeredStudent.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        JLabel average = new JLabel(String.valueOf(registeredStudent.getAverage()));
        average.setHorizontalAlignment(SwingConstants.CENTER);
        average.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Average"));
        average.setFont(new Font("Times New Roman",Font.PLAIN,20));
        average.setBackground(Color.white);
        average.setOpaque(true);


        JLabel passedCredits = new JLabel(String.valueOf(registeredStudent.getPassedCredits()));
        passedCredits.setHorizontalAlignment(SwingConstants.CENTER);
        passedCredits.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Passed Credits"));
        passedCredits.setFont(new Font("Times New Roman",Font.PLAIN,20));
        passedCredits.setBackground(Color.white);
        passedCredits.setOpaque(true);

        balance = new JLabel(String.valueOf(registeredStudent.getBalance()) + " Tomans");
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        balance.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"balance"));
        balance.setFont(new Font("Times New Roman",Font.PLAIN,20));
        balance.setBackground(Color.white);
        balance.setOpaque(true);

        studentInfo.add(image);
        studentInfo.add(new JLabel());
        studentInfo.add(lastName);


        JPanel studentInfo2 = new JPanel(new GridLayout(4,1,5,5));
        studentInfo2.add(firstName);
        studentInfo2.add(average);
        studentInfo2.add(passedCredits);
        studentInfo2.add(balance);


        JPanel studentInfoSection = new JPanel(new GridLayout(2,1,5,5));
        studentInfoSection.add(studentInfo);
        studentInfoSection.add(studentInfo2);



        JPanel studentClasses = new JPanel(new GridLayout(6,4));
        JLabel[] labelsOfClassTable = new JLabel[24];
        for(int i = 0;  i < 24 ; i++){
            labelsOfClassTable[i] = new JLabel();
            labelsOfClassTable[i].setBackground(Color.white);
            labelsOfClassTable[i].setOpaque(true);
            labelsOfClassTable[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsOfClassTable[i].setBorder(BorderFactory.createLineBorder(Color.gray,2));
            studentClasses.add(labelsOfClassTable[i]);
        }
        labelsOfClassTable[0].setText("Days / Times");
        labelsOfClassTable[0].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[1].setText("8 to 10");
        labelsOfClassTable[1].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[2].setText("10 to 12");
        labelsOfClassTable[2].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[3].setText("14 to 16");
        labelsOfClassTable[3].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[4].setText("Saturday");
        labelsOfClassTable[4].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[8].setText("Sunday");
        labelsOfClassTable[8].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[12].setText("Monday");
        labelsOfClassTable[12].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[16].setText("Tuesday");
        labelsOfClassTable[16].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[20].setText("Wednesday");
        labelsOfClassTable[20].setFont(new Font("Times new Roman",Font.BOLD,20));


        JPanel studentClassesComplete = new JPanel(new BorderLayout());

        JLabel title= new JLabel();
        title.setText("Class Schedules");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        studentClassesComplete.add(studentClasses,BorderLayout.CENTER);
        title.setPreferredSize(new Dimension(200,100));
        studentClassesComplete.add(title,BorderLayout.NORTH);
        studentClassesComplete.add(new JPanel(),BorderLayout.SOUTH);
        studentClassesComplete.add(new JLabel("         "),BorderLayout.WEST);

        studentInfoSection.setPreferredSize(new Dimension(200,300));
        studentProfile.setPreferredSize(new Dimension(1024,560));

        studentProfile.add(studentInfoSection,BorderLayout.WEST);
        studentProfile.add(studentClassesComplete,BorderLayout.CENTER);
    }

    private class IncreasingBalanceForm{

        private JFrame form;
        private JButton button;
        private JTextField cardNumber;
        private JTextField addingBalance;

        private Person registeredPerson;

        public IncreasingBalanceForm(String title,Student registeredPerson){
            form = new JFrame(title);
            form.setLocation(500,300);
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout(10,10));
            form.setContentPane(panel);

            JLabel label = new JLabel("Please enter your card number and adding balance");
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBackground(Color.lightGray);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

            JLabel cardNumberText = new JLabel("Card number : ");
            cardNumber = new JTextField();


            JLabel addingBalanceText = new JLabel("Adding balance : ");
            addingBalance = new JTextField();


            JPanel fields = new JPanel(new GridLayout(2,2,5,5));
            fields.add(cardNumberText);
            fields.add(cardNumber);
            fields.add(addingBalanceText);
            fields.add(addingBalance);

            //JPanel buttons = new JPanel(new GridLayout(1,2,5,5));

            button = new JButton("Adding");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cardNumber.getText().length() == 16){
                        registeredPerson.addBalance(addingBalance.getText());
                        balance.setText(registeredPerson.getBalance() + " Tomans");
                        hiddenForm();
                    }
                    else{
                        JOptionPane.showMessageDialog(form,"Error in card number","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //changingPassword = new JButton("Changing password");
            //ChangingPasswordHandler changePasswordHandler = new ChangingPasswordHandler();
            //changingPassword.addActionListener(changePasswordHandler);
            //buttons.add(loginButton);
            //buttons.add(changingPassword);

            panel.add(label,BorderLayout.NORTH);
            panel.add(fields,BorderLayout.CENTER);
            panel.add(button,BorderLayout.SOUTH);


        }

        public void showForm(){
            form.pack();
            form.setVisible(true);
        }

        public void hiddenForm(){
            form.setVisible(false);
        }

    }

    private void setReservingForm(Student registeredStudent){

        JPanel studentInfo = new JPanel(new GridLayout(3,1,5,5));
        studentInfo.setSize(400,300);
        studentInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Phase1\\Student.png",50,10,100,100);
        JLabel firstName = new JLabel(registeredStudent.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(registeredStudent.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        balance = new JLabel(registeredStudent.getBalance() + " Tomans");
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        balance.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"balance"));
        balance.setFont(new Font("Times New Roman",Font.PLAIN,20));
        balance.setBackground(Color.white);
        balance.setOpaque(true);



        studentInfo.add(image);
        studentInfo.add(new JLabel());
        studentInfo.add(lastName);

        JPanel studentInfo2 = new JPanel(new GridLayout(4,1,5,5));
        studentInfo2.add(firstName);
        studentInfo2.add(balance);

        JButton increasingBalance = new JButton();
        increasingBalance.setText("Increasing Balance");
        increasingBalance.setToolTipText("for increasing your balance you need to enter this button");
        increasingBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncreasingBalanceForm temp = new IncreasingBalanceForm("Adding Balance Form",registeredStudent);
                temp.showForm();
            }
        });

        studentInfo2.add(increasingBalance);

        JPanel studentInfoSection = new JPanel(new GridLayout(2,1,5,5));
        studentInfoSection.add(studentInfo);
        studentInfoSection.add(studentInfo2);
        studentInfoSection.setPreferredSize(new Dimension(200,300));
        studentOperations = new JPanel(new BorderLayout());
        studentOperations.add(studentInfoSection,BorderLayout.WEST);

    }

    private void update(Student st){
        settingStudentProfileInfo(st);
        setReservingForm(st);
    }
}
