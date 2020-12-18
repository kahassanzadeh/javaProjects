import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * this class created for admin panel GUI
 * this class extends GUI class
 *
 * @author Mohammadreza Hassanzadeh
 * @since 17 Dec 2020
 * @version 1.0
 */

public class AdminProfile extends GUI{

    //mainframe
    private JFrame frame;
    //admin profile panel
    private JPanel adminProfile ;
    //admin system managing panel
    private JPanel adminSystemManaging;

    /**
     * creating the admin profile panel
     *
     * @param admin registered admin
     */
    public AdminProfile(Admin admin){
        frame = new JFrame("Student Profile");
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600,800);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SystemManagement.save();
                System.exit(0);
            }
        });

        setAdminProfile(admin);
        setAdminFoodSchedule(admin);
        setAdminSystemManagingMenu(admin);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("Menu");
        JMenu menu = new JMenu("Score and exit");
        JMenuItem exit = new JMenuItem("Exit");


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem changingUserID = new JMenuItem("changing Id");
        changingUserID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changingID(admin);
            }
        });

        JMenuItem changingPass = new JMenuItem("changing pass");
        changingPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePass(admin);
            }
        });
        menu.add(changingUserID);
        menu.add(changingPass);
        menu.add(exit);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Profile And Food Management",adminProfile);
        tabs.addTab("System Managing",adminSystemManaging);
        frame.setContentPane(tabs);
    }

    /**
     * this method will create the system managing of student and teachers
     * admin can student and teachers
     *
     * @param admin registered admin
     */
    public void setAdminSystemManagingMenu(Admin admin){
        adminSystemManaging = new JPanel(new BorderLayout());
        JPanel adminInfo = new JPanel(new GridLayout(5,1,5,5));
        adminInfo.setSize(400,300);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Phase1&2\\Admin.png",50,10,100,100);
        JLabel firstName = new JLabel(admin.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(admin.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        adminInfo.add(image);
        adminInfo.add(firstName);
        adminInfo.add(lastName);
        adminInfo.setPreferredSize(new Dimension(200,300));
        adminInfo.add(new JLabel());
        adminInfo.add(new JLabel());


        adminSystemManaging.add(adminInfo,BorderLayout.WEST);
        adminSystemManaging.setPreferredSize(new Dimension(1024,560));

        JPanel information = new JPanel(new BorderLayout(5,5));

        JLabel title= new JLabel();
        title.setText("Students and Teachers Information");
        title.setFont(new Font("Times New Roman",Font.BOLD,20));
        title.setPreferredSize(new Dimension(200,50));


        JTextArea list = new JTextArea(30,1);
        list.append("Admins \n");
        for(Person pr :  SystemManagement.getSystemList().get("Admin")){
            Admin adminTemp = (Admin) pr;
            list.append(adminTemp.toString()+ "\n");
        }
        list.append("\nTeachers \n");
        for(Person pr :  SystemManagement.getSystemList().get("Teacher")){
            Teacher teacher = (Teacher) pr;
            list.append(teacher.toString()+ "\n");
        }

        list.append("\nStudents \n");
        for(Person pr :  SystemManagement.getSystemList().get("Student")){
            Student student = (Student) pr;
            list.append(student.toString()+ "\n");
        }
        list.append("\nClasses \n");
        for(Class cl :  SystemManagement.getClasses()){
            list.append(cl.toString()+ "\n");
        }


        list.setEditable(false);
        list.setFont(new Font("Times New Roman",Font.PLAIN,15));
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(100,300));

        information.add(scrollPane,BorderLayout.CENTER);
        information.add(title,BorderLayout.NORTH);
        adminSystemManaging.add(information,BorderLayout.CENTER);


        JPanel toolsPanel = new JPanel(new BorderLayout());

        JPanel addingStudent = new JPanel(new BorderLayout());

        JLabel studentTitle = new JLabel("Adding Student");
        studentTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setPreferredSize(new Dimension(100,20));


        JPanel temp = new JPanel(new GridLayout(3,2));
        JLabel nameLb = new JLabel("Student's name : ");
        nameLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        nameLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField studentName = new JTextField();

        JLabel IdLb = new JLabel("Student's ID : ");
        IdLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        IdLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField studentID = new JTextField();

        JLabel passLb = new JLabel("Student's password for login : ");
        passLb.setFont(new Font("Times New Roman",Font.PLAIN,13));
        passLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField studentPass = new JTextField();

        temp.add(nameLb);
        temp.add(studentName);
        temp.add(IdLb);
        temp.add(studentID);
        temp.add(passLb);
        temp.add(studentPass);
        temp.setPreferredSize(new Dimension(50,300));

        JButton addingStudentButton = new JButton("add");
        addingStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student temp = new Student(studentName.getText(),studentID.getText(),studentPass.getText());
                try{
                    SystemManagement.checkIDAndPass(temp);
                    SystemManagement.addStudent(temp);
                    setNewStudentOrTeacher(list,scrollPane);
                    studentName.setText("");
                    studentID.setText("");
                    studentPass.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    studentName.setText("");
                    studentID.setText("");
                    studentPass.setText("");
                }
            }
        });

        addingStudent.add(studentTitle,BorderLayout.NORTH);
        addingStudent.add(temp,BorderLayout.CENTER);
        addingStudent.add(addingStudentButton,BorderLayout.SOUTH);
        addingStudent.setPreferredSize(new Dimension(400,300));


        JPanel addingTeacher = new JPanel(new BorderLayout());

        JLabel TeacherTitle = new JLabel("Adding Teacher");
        TeacherTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        TeacherTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel tempt = new JPanel(new GridLayout(4,2));
        JLabel tLb = new JLabel("Teacher's name : ");
        tLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        tLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField teacherName = new JTextField();

        JLabel tIdLb = new JLabel("Teacher's ID : ");
        tIdLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        tIdLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField teacherID = new JTextField();

        JLabel tPassLb = new JLabel("Teacher's password for login : ");
        tPassLb.setFont(new Font("Times New Roman",Font.PLAIN,13));
        tPassLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField teacherPass = new JTextField();

        JLabel tFaculty = new JLabel("Teacher's Faculty: ");
        tFaculty.setFont(new Font("Times New Roman",Font.PLAIN,15));
        tFaculty.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField teacherFaculty = new JTextField();

        tempt.add(tLb);
        tempt.add(teacherName);
        tempt.add(tIdLb);
        tempt.add(teacherID);
        tempt.add(tPassLb);
        tempt.add(teacherPass);
        tempt.add(tFaculty);
        tempt.add(teacherFaculty);
        tempt.setPreferredSize(new   Dimension(50,300));

        JButton addingTeacherButton = new JButton("add");
        addingTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher temp = new Teacher(teacherName.getText(),teacherID.getText(),teacherPass.getText(),teacherFaculty.getText());
                try{
                    SystemManagement.checkIDAndPass(temp);
                    SystemManagement.addTeacher(temp);
                    setNewStudentOrTeacher(list,scrollPane);
                    teacherName.setText("");
                    teacherID.setText("");
                    teacherPass.setText("");
                    teacherFaculty.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    teacherName.setText("");
                    teacherID.setText("");
                    teacherPass.setText("");
                    teacherFaculty.setText("");
                }
            }
        });

        addingTeacher.add(TeacherTitle,BorderLayout.NORTH);
        addingTeacher.add(tempt,BorderLayout.CENTER);
        addingTeacher.add(addingTeacherButton,BorderLayout.SOUTH);
        addingTeacher.setPreferredSize(new Dimension(400,300));



        toolsPanel.add(addingTeacher,BorderLayout.CENTER);
        toolsPanel.add(addingStudent,BorderLayout.WEST);
        toolsPanel.setPreferredSize(new Dimension(300,200));
        information.add(toolsPanel,BorderLayout.SOUTH);

    }

    /**
     * this method created for showing the admin profile
     *
     */
    public void showingAdminProfile() {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * this method will update the teachers and students list
     *
     * @param list JTextArea consists of students info and teachers info
     * @param scrollPane scroll pane for JTextArea
     */
    private void setNewStudentOrTeacher(JTextArea list,JScrollPane scrollPane){
        list.setText("");
        list.append("Admins \n");
        for(Person pr :  SystemManagement.getSystemList().get("Admin")){
            Admin adminTemp = (Admin) pr;
            list.append(adminTemp.toString()+ "\n");
        }
        list.append("\nTeachers \n");
        for(Person pr :  SystemManagement.getSystemList().get("Teacher")){
            Teacher teacher = (Teacher) pr;
            list.append(teacher.toString()+ "\n");
        }

        list.append("\nStudents \n");
        for(Person pr :  SystemManagement.getSystemList().get("Student")){
            Student student = (Student) pr;
            list.append(student.toString()+ "\n");
        }
        list.append("\nClasses \n");
        for(Class cl :  SystemManagement.getClasses()){
            list.append(cl.toString()+ "\n");
        }


        list.setEditable(false);
        list.setFont(new Font("Times New Roman",Font.PLAIN,15));
        scrollPane.setPreferredSize(new Dimension(100,300));
    }

    /**
     * this method will create the Panel for adding foods
     *
     * @param admin registered admin
     */
    public void setAdminFoodSchedule(Admin admin){
        JPanel foodPanel = new JPanel(new BorderLayout(10,10));

        JPanel foodStatus = new JPanel(new GridLayout(3,6));

        JLabel[] foodTable = new JLabel[18];

        for(int i = 0; i < 18 ; i++){
            foodTable[i] = new JLabel();
            foodTable[i].setBackground(Color.white);
            foodTable[i].setOpaque(true);
            foodTable[i].setHorizontalAlignment(SwingConstants.CENTER);
            foodTable[i].setFont(new Font("Times New Roman",Font.PLAIN,13));
            foodTable[i].setBorder(BorderFactory.createLineBorder(Color.gray,2));
            foodStatus.add(foodTable[i]);
        }
        foodTable[0].setText("Type / Day");
        foodTable[0].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[1].setText("Saturday");
        foodTable[1].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[2].setText("Sunday");
        foodTable[2].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[3].setText("Monday");
        foodTable[3].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[4].setText("Tuesday");
        foodTable[4].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[5].setText("Wednesday");
        foodTable[5].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[6].setText("Type 1 ");
        foodTable[6].setFont(new Font("Times New Roman",Font.BOLD,15));
        foodTable[12].setText("Type 2 ");
        foodTable[12].setFont(new Font("Times New Roman",Font.BOLD,15));
        try{
            for(Food fd : SystemManagement.getFoodsSchedules()) {
                switch (fd.getDistributionDay()) {
                    case "Sat":
                        if (!foodTable[7].getText().equals("")) {
                            foodTable[13].setText(fd.toString());
                        } else {
                            foodTable[7].setText(fd.toString());
                        }
                        break;
                    case "Sun":
                        if (!foodTable[8].getText().equals("")) {
                            foodTable[14].setText(fd.toString());
                        } else {
                            foodTable[8].setText(fd.toString());
                        }
                        break;
                    case "Mon":
                        if (!foodTable[9].getText().equals("")) {
                            foodTable[15].setText(fd.toString());
                        } else {
                            foodTable[9].setText(fd.toString());
                        }
                        break;
                    case "Tue":
                        if (!foodTable[10].getText().equals("")) {
                            foodTable[16].setText(fd.toString());
                        } else {
                            foodTable[10].setText(fd.toString());
                        }
                        break;
                    case "Wed":
                        if (!foodTable[11].getText().equals("")) {
                            foodTable[17].setText(fd.toString());
                        } else {
                            foodTable[11].setText(fd.toString());
                        }
                        break;
                }

            }

        } catch (Exception exception) {

        }


        JLabel title= new JLabel();
        title.setText("Food Schedules");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setPreferredSize(new Dimension(200,100));

        JPanel foodReservingMenu = new JPanel(new GridLayout(2,1,1,1));
        foodReservingMenu.setPreferredSize(new Dimension(200,250));

        JLabel reservingTitle = new JLabel("Please enter the information of the food");
        reservingTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        reservingTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel tempPanel = new JPanel(new BorderLayout());
        JPanel foodReservingPanel = new JPanel(new GridLayout(3,2,5,5));

        JLabel labelForName = new JLabel("Name of the food : ");
        labelForName.setFont(new Font("Times New Roman",Font.BOLD,15));
        labelForName.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField nameOfTheFoodTextfield = new JTextField();

        JLabel labelForCost = new JLabel("Cost of the food : ");
        labelForCost.setFont(new Font("Times New Roman",Font.BOLD,15));
        labelForCost.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField costOfTheFoodTextfield = new JTextField();

        JLabel labelForDistributionDay = new JLabel("Distribution day of the food : ");
        labelForDistributionDay.setFont(new Font("Times New Roman",Font.BOLD,15));
        labelForDistributionDay.setHorizontalAlignment(SwingConstants.CENTER);
        JComboBox distributionDayOfTheFoodTextfield = new JComboBox(SystemManagement.getValidDays(0));

        JButton confirmingTheFood = new JButton("add");
        confirmingTheFood.setFont(new Font("Times New Roman",Font.BOLD,15));

        confirmingTheFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SystemManagement.addingFood(nameOfTheFoodTextfield.getText(),costOfTheFoodTextfield.getText(),distributionDayOfTheFoodTextfield.getItemAt(distributionDayOfTheFoodTextfield.getSelectedIndex()).toString());
                    renewReservedFoods(foodTable,foodStatus);
                    nameOfTheFoodTextfield.setText("");
                    costOfTheFoodTextfield.setText("");
                }
                catch (Exception exception){
                    if(exception.getMessage().equals("Had been added")){
                        JOptionPane.showMessageDialog(frame,"Had been added","Error",JOptionPane.ERROR_MESSAGE);
                        nameOfTheFoodTextfield.setText("");
                        costOfTheFoodTextfield.setText("");
                    }
                    else if(exception.getMessage().equals("more than 2 Foods are added. You can add 2 foods in a day")){
                        JOptionPane.showMessageDialog(frame,"more than 2 Foods are added. You can add 2 foods in a day","Error",JOptionPane.ERROR_MESSAGE);
                        nameOfTheFoodTextfield.setText("");
                        costOfTheFoodTextfield.setText("");
                    }
                    else if(exception.getMessage().equals("invalid day")){
                        JOptionPane.showMessageDialog(frame,"invalid day","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(exception.getMessage().equals("invalid cost")){
                        JOptionPane.showMessageDialog(frame,"invalid cost","Error",JOptionPane.ERROR_MESSAGE);
                        costOfTheFoodTextfield.setText("");
                    }
                }
            }
        });

        foodReservingPanel.add(labelForName);
        foodReservingPanel.add(nameOfTheFoodTextfield);
        foodReservingPanel.add(labelForCost);
        foodReservingPanel.add(costOfTheFoodTextfield);
        foodReservingPanel.add(labelForDistributionDay);
        foodReservingPanel.add(distributionDayOfTheFoodTextfield);


        JPanel temp1 = new JPanel();
        temp1.setPreferredSize(new Dimension(200,10));

        JPanel temp2 = new JPanel();
        temp2.setPreferredSize(new Dimension(200,10));
        tempPanel.add(temp1,BorderLayout.EAST);
        tempPanel.add(temp2,BorderLayout.WEST);
        tempPanel.add(foodReservingPanel,BorderLayout.CENTER);
        tempPanel.add(confirmingTheFood,BorderLayout.SOUTH);
        tempPanel.setPreferredSize(new Dimension(200,300));




        foodReservingMenu.add(reservingTitle);
        foodReservingMenu.add(tempPanel);



        foodPanel.add(foodStatus,BorderLayout.CENTER);
        foodPanel.add(title,BorderLayout.NORTH);
        foodPanel.add(foodReservingMenu,BorderLayout.SOUTH);
        adminProfile.add(foodPanel);
    }

    /**
     * this method will update the food list
     *
     * @param table JLabel of food schedules list
     * @param foodStatus panel for showing food reservations
     */
    public void renewReservedFoods(JLabel[] table,JPanel foodStatus){
        foodStatus.removeAll();
        for(int i = 0; i < 18 ; i++){
            table[i] = new JLabel();
            table[i].setBackground(Color.white);
            table[i].setOpaque(true);
            table[i].setHorizontalAlignment(SwingConstants.CENTER);
            table[i].setFont(new Font("Times New Roman",Font.PLAIN,13));
            table[i].setBorder(BorderFactory.createLineBorder(Color.gray,2));
            foodStatus.add(table[i]);
        }
        table[0].setText("Type / Day");
        table[0].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[1].setText("Saturday");
        table[1].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[2].setText("Sunday");
        table[2].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[3].setText("Monday");
        table[3].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[4].setText("Tuesday");
        table[4].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[5].setText("Wednesday");
        table[5].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[6].setText("Type 1 ");
        table[6].setFont(new Font("Times New Roman",Font.BOLD,15));
        table[12].setText("Type 2 ");
        table[12].setFont(new Font("Times New Roman",Font.BOLD,15));
        try{
            for(Food fd : SystemManagement.getFoodsSchedules()) {
                switch (fd.getDistributionDay()) {
                    case "Sat":
                        if (!table[7].getText().equals("")) {
                            table[13].setText(fd.toString());
                        } else {
                            table[7].setText(fd.toString());
                        }
                        break;
                    case "Sun":
                        if (!table[8].getText().equals("")) {
                            table[14].setText(fd.toString());
                        } else {
                            table[8].setText(fd.toString());
                        }
                        break;
                    case "Mon":
                        if (!table[9].getText().equals("")) {
                            table[15].setText(fd.toString());
                        } else {
                            table[9].setText(fd.toString());
                        }
                        break;
                    case "Tues":
                        if (!table[10].getText().equals("")) {
                            table[16].setText(fd.toString());
                        } else {
                            table[10].setText(fd.toString());
                        }
                        break;
                    case "Wed":
                        if (!table[11].getText().equals("")) {
                            table[17].setText(fd.toString());
                        } else {
                            table[11].setText(fd.toString());
                        }
                        break;
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * this method will add admin's info to the frame
     * @param admin registered admin
     */
    private void setAdminProfile(Admin admin){
        adminProfile = new JPanel(new BorderLayout());
        JPanel adminInfo = new JPanel(new GridLayout(5,1,5,5));
        adminInfo.setSize(400,300);
        adminInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Phase1&2\\Admin.png",50,10,100,100);
        JLabel firstName = new JLabel(admin.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(admin.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        adminInfo.add(image);
        adminInfo.add(firstName);
        adminInfo.add(lastName);
        adminInfo.setPreferredSize(new Dimension(200,300));
        adminInfo.add(new JLabel());
        adminInfo.add(new JLabel());


        adminProfile.add(adminInfo,BorderLayout.WEST);
        adminProfile.setPreferredSize(new Dimension(1024,560));
        frame.add(adminProfile);
    }
}
