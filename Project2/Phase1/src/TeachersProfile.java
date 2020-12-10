import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class TeachersProfile {

    private JFrame frame;

    public TeachersProfile(Teacher registeredTeacher){
        frame = new JFrame("Teacher Profile");
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);


        JPanel teacherProfile = new JPanel(new BorderLayout());
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

        JTextArea list = new JTextArea(30,1);
        list.append("Classes \n");
        String infoTemp = "Class Name";
        String name = "Teachers Name";
        list.append(String.format("%-40s",infoTemp) + String.format("%-20s",name)+ "        " +"Credits" + "        " + "Day of the class" + "     " + "Time of the class" + "       " + "Capacity\n");
        for(Class cl :  registeredTeacher.getClasses()){
            list.append(cl.toString()+ "\n");
        }


        list.setEditable(false);
        list.setFont(new Font("Times New Roman",Font.PLAIN,15));
        JScrollPane scrollPane = new JScrollPane(list);
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
                    String dayTime = classDayTime.getText().substring(0,1).toUpperCase() +  classDayTime.getText().substring(1).toLowerCase();
                    if(classTime.getText().equals("8 to 10")){
                        dayTime += "1";
                    }
                    else if(classTime.getText().equals("10 to 12")){
                        dayTime += "2";
                    }
                    else if(classTime.getText().equals("14 to 16")){
                        dayTime += "3";
                    }
                    Class temp = SystemManagement.searchClass(className.getText(),dayTime);
                    temp.setStatus(false);
                    registeredTeacher.removeClass(temp);
                    renewClasses(list,scrollPane,registeredTeacher);
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
        closingClass.setPreferredSize(new Dimension(300,200));
        toolsPanel.add(closingClass,BorderLayout.WEST);

        classManagement.add(toolsPanel,BorderLayout.SOUTH);
        teacherProfile.add(classManagement);
        frame.add(teacherProfile);

    }

    private void renewClasses(JTextArea textArea,JScrollPane scrollPane,Teacher registeredTeacher){
        textArea.setText("");
        textArea.append("Classes \n");
        for(Class cl :  registeredTeacher.getClasses()){
            textArea.append(cl.toString()+ "\n");
        }
        textArea.setEditable(false);
        textArea.setFont(new Font("Times New Roman",Font.PLAIN,15));
        scrollPane.setPreferredSize(new Dimension(100,300));
    }

    public void showTeacherProfile(){
        frame.pack();
        frame.setVisible(true);
    }

}
