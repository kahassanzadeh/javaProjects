import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Set;

public class TeachersProfile {

    private JFrame frame;
    private JPanel teacherProfile;
    private JTextArea list;
    private JScrollPane scrollPane;
    private JLabel[][] labelsOfClassTable;
    private JPanel teacherClasses;
    private JPanel openingNewClass;

    public TeachersProfile(Teacher registeredTeacher){
        frame = new JFrame("Teacher Profile");
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);

        setClassManaging(registeredTeacher);
        setClassTime(registeredTeacher);

        JPanel scoreManaging = new JPanel(new BorderLayout());
        JPanel teacherInfo = new JPanel(new GridLayout(3,1,5,5));
        teacherInfo.setSize(600,800);
        teacherInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Teacher.png",50,10,70,70);
        JLabel firstName = new JLabel(registeredTeacher.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(registeredTeacher.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        JLabel faculty = new JLabel(String.valueOf(registeredTeacher.getFaculty()));
        faculty.setHorizontalAlignment(SwingConstants.CENTER);
        faculty.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Faculty"));
        faculty.setFont(new Font("Times New Roman",Font.PLAIN,20));
        faculty.setBackground(Color.white);
        faculty.setOpaque(true);


        teacherInfo.add(image);
        teacherInfo.add(new JLabel());
        teacherInfo.add(lastName);


        JPanel teacherInfo2 = new JPanel(new GridLayout(4,1,5,5));
        teacherInfo2.add(firstName);
        teacherInfo2.add(faculty);

        JPanel teacherInfoSection = new JPanel(new GridLayout(2,1,5,5));
        teacherInfoSection.add(teacherInfo);
        teacherInfoSection.add(teacherInfo2);
        teacherInfoSection.setPreferredSize(new Dimension(200,300));

        scoreManaging.add(teacherInfoSection,BorderLayout.WEST);


        JPanel studentInfoAndScoring = new JPanel(new BorderLayout());


        JLabel title= new JLabel();
        title.setText("Scoring and Student's Information");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setPreferredSize(new Dimension(200,100));
        studentInfoAndScoring.add(title,BorderLayout.NORTH);


        JPanel showInfoSection = new JPanel(new BorderLayout());
        JPanel comboAndButton = new JPanel(new BorderLayout());

        JTextArea studentInfo = new JTextArea();
        studentInfo.setFont(new Font("Times New Roman",Font.PLAIN,15));
        studentInfo.setEditable(false);



        JComboBox classes = new JComboBox(registeredTeacher.getClassesString());

        JButton show = new JButton("Show");
        show.setFont(new Font("Times New Roman",Font.BOLD,15));


        JScrollPane studentInfoScroll = new JScrollPane(studentInfo);
        studentInfoScroll.setPreferredSize(new Dimension(100,200));


        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    renewScoreList(studentInfo,classes,registeredTeacher);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //JPanel toolsPanel = new JPanel(new BorderLayout());

        JPanel scoring = new JPanel(new BorderLayout());

        JLabel studentTitle = new JLabel("Scoring");
        studentTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setPreferredSize(new Dimension(100,100));


        JPanel temp = new JPanel(new GridLayout(2,2));
        JLabel nameLb = new JLabel("Student ID : ");
        nameLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        nameLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField studentId = new JTextField();

        JLabel scoreLb = new JLabel("Score: ");
        scoreLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        scoreLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField score = new JTextField();



        temp.add(nameLb);
        temp.add(studentId);
        temp.add(scoreLb);
        temp.add(score);
        temp.setPreferredSize(new Dimension(50,100));

        JButton confirmingScore = new JButton("Confirm");
        confirmingScore.setFont(new Font("Times New Roman",Font.PLAIN,15));
        confirmingScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Student st = SystemManagement.searchStudent(studentId.getText());
                    SystemManagement.setScore(st,""+ classes.getItemAt(classes.getSelectedIndex()),Double.parseDouble(score.getText()));
                    renewScoreList(studentInfo,classes,registeredTeacher);
                }
                catch (Exception exception){

                }
            }
        });
        scoring.add(studentTitle,BorderLayout.NORTH);
        scoring.add(temp,BorderLayout.CENTER);
        scoring.add(confirmingScore,BorderLayout.SOUTH);
        JPanel m = new JPanel();
        m.setPreferredSize(new Dimension(100,100));
        JPanel f = new JPanel();
        f.setPreferredSize(new Dimension(100 ,100));
        scoring.add(m,BorderLayout.WEST);
        scoring.add(f,BorderLayout.EAST);
        scoring.setPreferredSize(new Dimension(100,300));


        showInfoSection.add(studentInfoScroll,BorderLayout.CENTER);
        comboAndButton.add(classes,BorderLayout.CENTER);
        comboAndButton.add(show,BorderLayout.EAST);
        showInfoSection.add(comboAndButton, BorderLayout.NORTH);
        studentInfoAndScoring.add(showInfoSection,BorderLayout.CENTER);
        studentInfoAndScoring.add(scoring,BorderLayout.SOUTH);


        scoreManaging.add(studentInfoAndScoring);



        frame.add(teacherProfile);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Class Managing",teacherProfile);
        tabs.addTab("Class Opening",openingNewClass);
        tabs.addTab("Scoring Operations",scoreManaging);
        frame.setContentPane(tabs);
    }
    private void renewScoreList(JTextArea textArea,JComboBox classes,Teacher registeredTeacher) throws Exception {
        textArea.setText("");
        textArea.append("Student Name" + "                     "+ " Students ID" + "          " + "Average" + "         " + "Current Credits \n");
        for(Student st : SystemManagement.searchClassStudents("" + classes.getItemAt(classes.getSelectedIndex()),registeredTeacher)){
            textArea.append(st.fullInfo() + " \n");
        }
    }
    public void setClassTime(Teacher registeredTeacher){
        openingNewClass = new JPanel(new BorderLayout());

        JPanel teacherInfo = new JPanel(new GridLayout(3,1,5,5));
        teacherInfo.setSize(600,800);
        teacherInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Teacher.png",50,10,70,70);
        JLabel firstName = new JLabel(registeredTeacher.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(registeredTeacher.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        JLabel faculty = new JLabel(String.valueOf(registeredTeacher.getFaculty()));
        faculty.setHorizontalAlignment(SwingConstants.CENTER);
        faculty.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Faculty"));
        faculty.setFont(new Font("Times New Roman",Font.PLAIN,20));
        faculty.setBackground(Color.white);
        faculty.setOpaque(true);


        teacherInfo.add(image);
        teacherInfo.add(new JLabel());
        teacherInfo.add(lastName);


        JPanel teacherInfo2 = new JPanel(new GridLayout(4,1,5,5));
        teacherInfo2.add(firstName);
        teacherInfo2.add(faculty);

        JPanel teacherInfoSection = new JPanel(new GridLayout(2,1,5,5));
        teacherInfoSection.add(teacherInfo);
        teacherInfoSection.add(teacherInfo2);
        teacherInfoSection.setPreferredSize(new Dimension(200,300));

        openingNewClass.add(teacherInfoSection,BorderLayout.WEST);


        JPanel classSchedule = new JPanel(new BorderLayout());

        teacherClasses = new JPanel(new GridLayout(4,6));

        labelsOfClassTable = new JLabel[4][6];
        tableSetter();
        updatingClassTable(registeredTeacher,labelsOfClassTable,teacherClasses);

        JLabel title= new JLabel();
        title.setText("Class Schedule");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setPreferredSize(new Dimension(200,100));

        JPanel addingClass = new JPanel(new BorderLayout());

        JLabel classTitle = new JLabel("Adding Class");
        classTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        classTitle.setHorizontalAlignment(SwingConstants.CENTER);
        classTitle.setPreferredSize(new Dimension(50,50));

        JPanel tempt = new JPanel(new GridLayout(6,2));
        JLabel tLb = new JLabel("Class's name : ");
        tLb.setFont(new Font("Times New Roman",Font.BOLD,16));
        tLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField className = new JTextField();

        JLabel tIdLb = new JLabel("Class credits : ");
        tIdLb.setFont(new Font("Times New Roman",Font.BOLD,16));
        tIdLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classCredits = new JTextField();

        JLabel capacity = new JLabel("Capacity : ");
        capacity.setFont(new Font("Times New Roman",Font.BOLD,16));
        capacity.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField capacityText = new JTextField();

        JLabel day = new JLabel("Class Day1: ");
        day.setFont(new Font("Times New Roman",Font.BOLD,16));
        day.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classDay1 = new JTextField();

        /*JLabel day2 = new JLabel("Class Day1: ");
        day2.setFont(new Font("Times New Roman",Font.BOLD,16));
        day2.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classDay2 = new JTextField();*/


        JLabel time = new JLabel("Class Time: ");
        time.setFont(new Font("Times New Roman",Font.BOLD,16));
        time.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classTime = new JTextField();

        JButton openClass = new JButton("Open a new Class");
        openClass.setFont(new Font("Times New Roman",Font.BOLD,17));
        openClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dayTime1 = classDay1.getText().substring(0,1).toUpperCase() + classDay1.getText().substring(1,3).toLowerCase();
                switch (classTime.getText()) {
                    case "8 to 10":
                        dayTime1 += "1";
                        break;
                    case "10 to 12":
                        dayTime1 += "2";
                        break;
                    case "14 to 16":
                        dayTime1 += "3";
                        break;
                }
                String dayTime2 = "";
                if(classDay1.getText().length() > 3){
                    dayTime2 = classDay1.getText().substring(4,5).toUpperCase() + classDay1.getText().substring(5).toLowerCase();
                    dayTime2 += dayTime1.charAt(3);
                }
                Class temp;
                try{
                    if(dayTime2.equals("")){
                        temp = new Class(className.getText(),Integer.parseInt(capacityText.getText()),Integer.parseInt(classCredits.getText()),dayTime1,registeredTeacher);
                    }
                    else{
                        temp = new Class(className.getText(),Integer.parseInt(capacityText.getText()),Integer.parseInt(classCredits.getText()),dayTime1,dayTime2,registeredTeacher);
                    }
                    SystemManagement.addClass(temp);
                    updatingClassTable(registeredTeacher,labelsOfClassTable,teacherClasses);
                    renewClasses(list,scrollPane,registeredTeacher);
                    className.setText("");
                    classCredits.setText("");
                    capacityText.setText("");
                    classDay1.setText("");
                    classTime.setText("");
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(frame,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    className.setText("");
                    classCredits.setText("");
                    capacityText.setText("");
                    classDay1.setText("");
                    classTime.setText("");
                }
            }
        });

        tempt.add(tLb);
        tempt.add(className);
        tempt.add(tIdLb);
        tempt.add(classCredits);
        tempt.add(capacity);
        tempt.add(capacityText);
        tempt.add(day);
        tempt.add(classDay1);
        tempt.add(time);
        tempt.add(classTime);
        tempt.setPreferredSize(new Dimension(50,200));

        addingClass.add(classTitle,BorderLayout.NORTH);
        addingClass.add(tempt,BorderLayout.CENTER);
        addingClass.add(openClass,BorderLayout.SOUTH);
        JPanel t = new JPanel();
        t.setPreferredSize(new Dimension(200,100));
        JPanel m = new JPanel();
        m.setPreferredSize(new Dimension(200,100));
        addingClass.add(t,BorderLayout.WEST);
        addingClass.add(m,BorderLayout.EAST);




        classSchedule.add(title,BorderLayout.NORTH);
        classSchedule.add(teacherClasses,BorderLayout.CENTER);
        classSchedule.add(addingClass,BorderLayout.SOUTH);
        openingNewClass.add(classSchedule,BorderLayout.CENTER);
    }
    public void updatingClassTable(Teacher registeredTeacher,JLabel[][] labelsOfClassTable,JPanel teacherClass){
        teacherClass.removeAll();
        tableSetter();
        ArrayList<Class> classes = registeredTeacher.getClasses();
        int i = 0;
        int j = 0;
        for(Class cl : classes){
            if(cl.getTIME_OF_THE_CLASS1().toString().substring(0,3).equals("Sat")){j = 1;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().substring(0,3).equals("Sun")){j = 2;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().substring(0,3).equals("Mon")){j = 3;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().substring(0,3).equals("Tue")){j = 4;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().substring(0,3).equals("Wed")){j = 5;}

            if(cl.getTIME_OF_THE_CLASS1().toString().charAt(3) == '1'){i = 1;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().charAt(3) == '2'){i = 2;}
            else if(cl.getTIME_OF_THE_CLASS1().toString().charAt(3) == '3'){i = 3;}
            labelsOfClassTable[i][j].setText(cl.getName() + "  " + cl.getCREDITS());
            labelsOfClassTable[i][j].setBackground(Color.pink);
        }
        i = 0;
        j = 0;
        for(Class cl : classes){
            if(cl.getTIME_OF_THE_CLASS2() == null){
                continue;
            }
            if(cl.getTIME_OF_THE_CLASS2().toString().substring(0,3).equals("Sat")){j = 1;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().substring(0,3).equals("Sun")){j = 2;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().substring(0,3).equals("Mon")){j = 3;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().substring(0,3).equals("Tue")){j = 4;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().substring(0,3).equals("Wed")){j = 5;}

            if(cl.getTIME_OF_THE_CLASS2().toString().charAt(3) == '1'){i = 1;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().charAt(3) == '2'){i = 2;}
            else if(cl.getTIME_OF_THE_CLASS2().toString().charAt(3) == '3'){i = 3;}
            labelsOfClassTable[i][j].setText(cl.getName() + "  " + cl.getCREDITS());
            labelsOfClassTable[i][j].setBackground(Color.pink);
        }
        for (int a = 0; a < 4; a++){
            for (int b = 0; b < 6; b++){
                teacherClass.add(labelsOfClassTable[a][b]);
            }
        }
    }

    public void tableSetter(){
        for(int i = 0;  i < 4 ; i++){
            for(int j = 0;  j < 6 ; j++){
                labelsOfClassTable[i][j] = new JLabel();
                labelsOfClassTable[i][j].setBackground(Color.white);
                labelsOfClassTable[i][j].setOpaque(true);
                labelsOfClassTable[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                labelsOfClassTable[i][j].setBorder(BorderFactory.createLineBorder(Color.gray,2));
                labelsOfClassTable[i][j].setText(" ");
                labelsOfClassTable[i][j].setFont(new Font("Times New Roman",Font.PLAIN,15));
                labelsOfClassTable[i][j].setPreferredSize(new Dimension(50,20));
            }
        }
        labelsOfClassTable[0][0].setText("Days / Times");
        labelsOfClassTable[0][0].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[1][0].setText("8 to 10");
        labelsOfClassTable[1][0].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[2][0].setText("10 to 12");
        labelsOfClassTable[2][0].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[3][0].setText("14 to 16");
        labelsOfClassTable[3][0].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[0][1].setText("Saturday");
        labelsOfClassTable[0][1].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[0][2].setText("Sunday");
        labelsOfClassTable[0][2].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[0][3].setText("Monday");
        labelsOfClassTable[0][3].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[0][4].setText("Tuesday");
        labelsOfClassTable[0][4].setFont(new Font("Times new Roman",Font.BOLD,20));
        labelsOfClassTable[0][5].setText("Wednesday");
        labelsOfClassTable[0][5].setFont(new Font("Times new Roman",Font.BOLD,20));

    }

    private void renewClasses(JTextArea textArea,JScrollPane scrollPane,Teacher registeredTeacher){
        textArea.setText("");

        textArea.append("Classes \n");
        String infoTemp = "Class Name";
        String name = "Teachers Name";
        list.append(String.format("%-40s",infoTemp) + String.format("%-20s",name)+ "        " +"Credits" + "        " + "Day of the class" + "     " + "Time of the class" + "       " + "Capacity\n");
        for(Class cl :  registeredTeacher.getClasses()){
            textArea.append(cl.toString()+ "\n");
        }
        textArea.setEditable(false);
        textArea.setFont(new Font("Times New Roman",Font.PLAIN,15));
        scrollPane.setPreferredSize(new Dimension(100,300));
    }

    public void setClassManaging(Teacher registeredTeacher){
        teacherProfile = new JPanel(new BorderLayout());
        JPanel teacherInfo = new JPanel(new GridLayout(3,1,5,5));
        teacherInfo.setSize(600,800);
        teacherInfo.setOpaque(true);

        ImagePanel image = new ImagePanel("E:\\university\\5th term\\AP\\Projects\\Project2\\Teacher.png",50,10,70,70);
        JLabel firstName = new JLabel(registeredTeacher.getName().split("\\s+")[0]);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"First name"));
        firstName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        firstName.setBackground(Color.white);
        firstName.setOpaque(true);

        JLabel lastName = new JLabel(registeredTeacher.getName().split("\\s+")[1]);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        lastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Last name"));
        lastName.setFont(new Font("Times New Roman",Font.PLAIN,25));
        lastName.setBackground(Color.white);
        lastName.setOpaque(true);

        JLabel faculty = new JLabel(String.valueOf(registeredTeacher.getFaculty()));
        faculty.setHorizontalAlignment(SwingConstants.CENTER);
        faculty.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Faculty"));
        faculty.setFont(new Font("Times New Roman",Font.PLAIN,20));
        faculty.setBackground(Color.white);
        faculty.setOpaque(true);


        teacherInfo.add(image);
        teacherInfo.add(new JLabel());
        teacherInfo.add(lastName);


        JPanel teacherInfo2 = new JPanel(new GridLayout(4,1,5,5));
        teacherInfo2.add(firstName);
        teacherInfo2.add(faculty);

        JPanel teacherInfoSection = new JPanel(new GridLayout(2,1,5,5));
        teacherInfoSection.add(teacherInfo);
        teacherInfoSection.add(teacherInfo2);
        teacherInfoSection.setPreferredSize(new Dimension(200,300));


        teacherProfile.add(teacherInfoSection,BorderLayout.WEST);


        JPanel classManagement = new JPanel(new BorderLayout());
        classManagement.setPreferredSize(new Dimension(800,600));

        //JPanel classMenu = new JPanel(new GridLayout(25,1));

        JLabel title= new JLabel();
        title.setText("Class Managing");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setPreferredSize(new Dimension(800,100));
        classManagement.add(title,BorderLayout.NORTH);
        teacherProfile.add(classManagement);

        list = new JTextArea(30,1);
        list.append("Classes \n");
        String infoTemp = "Class Name";
        String name = "Teachers Name";
        list.append(String.format("%-40s",infoTemp) + String.format("%-20s",name)+ "        " +"Credits" + "        " + "Day of the class" + "     " + "Time of the class" + "       " + "Capacity\n");
        for(Class cl :  registeredTeacher.getClasses()){
            list.append(cl.toString()+ "\n");
        }


        list.setEditable(false);
        list.setFont(new Font("Times New Roman",Font.PLAIN,15));
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(100,300));

        classManagement.add(scrollPane,BorderLayout.CENTER);
        //
        JPanel toolsPanel = new JPanel(new BorderLayout());

        JPanel closingClass = new JPanel(new BorderLayout());

        JLabel studentTitle = new JLabel("Closing a Class");
        studentTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setPreferredSize(new Dimension(100,20));


        JPanel temp = new JPanel(new GridLayout(4,2));
        JLabel nameLb = new JLabel("Class Name : ");
        nameLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        nameLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField className = new JTextField();

        JLabel dayLb = new JLabel("Day of the class: ");
        dayLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        dayLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classDayTime = new JTextField();

        JLabel timeLb = new JLabel("Time of the class: ");
        timeLb.setFont(new Font("Times New Roman",Font.PLAIN,15));
        timeLb.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classTime = new JTextField();


        JLabel passLb = new JLabel("Teacher's pass to confirm : ");
        passLb.setFont(new Font("Times New Roman",Font.PLAIN,13));
        passLb.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField teacherPass = new JPasswordField();

        temp.add(nameLb);
        temp.add(className);
        temp.add(dayLb);
        temp.add(classDayTime);
        temp.add(timeLb);
        temp.add(classTime);
        temp.add(passLb);
        temp.add(teacherPass);
        temp.setPreferredSize(new Dimension(50,300));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Times New Roman",Font.BOLD,15));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SystemManagement.checkPassword(registeredTeacher, new String(teacherPass.getPassword()));
                    String dayTime1 = classDayTime.getText().substring(0,1).toUpperCase() +  classDayTime.getText().substring(1,3).toLowerCase();
                    switch (classTime.getText()) {
                        case "8 to 10":
                            dayTime1 += "1";
                            break;
                        case "10 to 12":
                            dayTime1 += "2";
                            break;
                        case "14 to 16":
                            dayTime1 += "3";
                            break;
                    }
                    String dayTime2 = "";
                    if(classDayTime.getText().length() > 3){
                        dayTime2 = classDayTime.getText().substring(4,5).toUpperCase() + classDayTime.getText().substring(5).toLowerCase();
                        dayTime2 += dayTime1.charAt(3);
                    }
                    Class temp = SystemManagement.searchClass(className.getText(),dayTime1,dayTime2);
                    temp.setStatus(false);
                    registeredTeacher.removeClass(temp);
                    renewClasses(list,scrollPane,registeredTeacher);
                    updatingClassTable(registeredTeacher,labelsOfClassTable,teacherClasses);
                    classTime.setText("");
                    className.setText("");
                    classDayTime.setText("");
                    teacherPass.setText("");
                }
                catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        closingClass.add(studentTitle,BorderLayout.NORTH);
        closingClass.add(temp,BorderLayout.CENTER);
        closingClass.add(closeButton,BorderLayout.SOUTH);
        closingClass.setPreferredSize(new Dimension(100,200));
        toolsPanel.add(closingClass,BorderLayout.CENTER);
        JPanel t = new JPanel();
        t.setPreferredSize(new Dimension(200,100));
        JPanel m = new JPanel();
        m.setPreferredSize(new Dimension(200,100));
        toolsPanel.add(t,BorderLayout.WEST);
        toolsPanel.add(m,BorderLayout.EAST);
        classManagement.add(toolsPanel,BorderLayout.SOUTH);
        teacherProfile.add(classManagement);

    }
    public void showTeacherProfile(){
        frame.pack();
        frame.setVisible(true);
    }

}
