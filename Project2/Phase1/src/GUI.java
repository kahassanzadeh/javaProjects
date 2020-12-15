import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    public void changingID(Person registeredPerson){
        JFrame fr = new JFrame("changing ID");
        fr.setLocation(200,200);
        fr.setSize(400,300);

        fr.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fr.setVisible(false);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        JPanel IDChanger = new JPanel(new GridLayout(3,2));

        JLabel oldUserLB = new JLabel(" old ID");
        oldUserLB.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField oldUserField = new JTextField();

        JLabel newUserLB = new JLabel("new ID");
        newUserLB.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField newUserField = new JTextField();

        JLabel userPassLb = new JLabel(" password");
        userPassLb.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField userPassField = new JPasswordField();

        IDChanger.add(oldUserLB);
        IDChanger.add(oldUserField);
        IDChanger.add(newUserLB);
        IDChanger.add(newUserField);
        IDChanger.add(userPassLb);
        IDChanger.add(userPassField);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SystemManagement.changeID(oldUserField.getText(),newUserField.getText(),userPassField.getPassword(),registeredPerson);
                    fr.setVisible(false);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(fr,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(IDChanger,BorderLayout.CENTER);
        panel.add(confirm,BorderLayout.SOUTH);

        fr.add(panel);
        fr.setVisible(true);



    }
    public void changePass(Person registeredPerson){

        JFrame fr = new JFrame("changing PASS");
        fr.setLocation(200,200);
        fr.setSize(400,300);

        fr.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fr.setVisible(false);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        JPanel IDChanger = new JPanel(new GridLayout(4,2));

        JLabel userID = new JLabel("your ID");
        userID.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField UserField = new JTextField();

        JLabel oldPassLb = new JLabel(" old pass");
        oldPassLb.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField oldPass = new JPasswordField();

        JLabel newPassLb = new JLabel(" new password");
        newPassLb.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField newPass = new JPasswordField();

        JLabel newPassConfirmLb = new JLabel("new password again");
        newPassConfirmLb.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField newPassConfirm = new JPasswordField();


        IDChanger.add(userID);
        IDChanger.add(UserField);
        IDChanger.add(oldPassLb);
        IDChanger.add(oldPass);
        IDChanger.add(newPassLb);
        IDChanger.add(newPass);
        IDChanger.add(newPassConfirmLb);
        IDChanger.add(newPassConfirm);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SystemManagement.changePass(UserField.getText(), oldPass.getPassword(),newPass.getPassword(),newPassConfirm.getPassword(),registeredPerson);
                    fr.setVisible(false);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(fr,exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(IDChanger,BorderLayout.CENTER);
        panel.add(confirm,BorderLayout.SOUTH);

        fr.add(panel);
        fr.setVisible(true);

    }

}
