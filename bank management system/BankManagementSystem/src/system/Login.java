package system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){
        setTitle("Banking Software");
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bicon.jpg"));
        Image i2=i1.getImage().getScaledInstance(550,450,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label= new JLabel(i3);
        label.setBounds(500,110,550,450);
        add(label);

        JLabel text = new JLabel("Automated Teller Machine");
        text.setFont(new Font("Osward",Font.BOLD,28));
        text.setBounds(600,10,400,100);
        add(text);

        JLabel cardno = new JLabel("Card No: ");
        cardno.setFont(new Font("Osward",Font.BOLD,18));
        cardno.setBounds(575,560,100,25);
        add(cardno);
        cardTextField= new JTextField();
        cardTextField.setBounds(675,560,125,25);
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Osward",Font.BOLD,18));
        pin.setBounds(800,560,50,25);
        add(pin);
        pinTextField= new JPasswordField();
        pinTextField.setBounds(850,560,125,25);
        add(pinTextField);

        login=new JButton("LOGIN");
        login.setBounds(575,610,100,50);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        clear=new JButton("CLEAR");
        clear.setBounds(720,610,100,50);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        signup=new JButton("SIGN UP");
        signup.setBounds(865,610,100,50);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        


        getContentPane().setBackground(Color.WHITE);
        setSize(1920,1080);
        setVisible(true);
        //setLocation(350,200);

    
    }
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==login){
                Conn c1 = new Conn();
                String cardno  = cardTextField.getText();
                String pin  = pinTextField.getText();
                String q  = "select * from login where cardno = '"+cardno+"' and pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }else if(ae.getSource()==clear){
                cardTextField.setText("");
                pinTextField.setText("");
            }else if(ae.getSource()==signup){
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new Login();
    }
}
