package com.kara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add_money extends JFrame implements ActionListener {
    JLabel amount,remark,status,date;
    JTextField amt_tf,remark_tf;
    JRadioButton rb1,rb2;
    JButton submit,cancel;
    add_money()
    {
        amount=new JLabel("Amount");
        amount.setBounds(40,20,50,50);
        add(amount);
        amt_tf=new JTextField();
        amt_tf.setBounds(110,30,200,30);
        add(amt_tf);
        remark=new JLabel("Remark");
        remark.setBounds(40,70,50,50);
        add(remark);
        remark_tf=new JTextField();
        remark_tf.setBounds(110,80,200,30);
        add(remark_tf);
        status=new JLabel("Status");
        status.setBounds(40,120,50,50);
        add(status);
        rb1=new JRadioButton("Add");
        rb1.setBounds(110,120,50,50);
        rb2=new JRadioButton("Withdrawl");
        rb2.setBounds(190,120,100,50);
        ButtonGroup bg=new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
//        button
        submit=new JButton("Submit");
        submit.setBounds(70,200,100,30);
        add(submit);
        submit.addActionListener(this);
        cancel=new JButton("Cancel");
        cancel.setBounds(190,200,100,30);
        add(cancel);
        cancel.addActionListener(this);
        this.add(rb1);
        this.add(rb2);
        date=new JLabel("Date");
        setLayout(null);
        setVisible(true);
        setBounds(500,300,400,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

//    public static void main(String[] args) {
//         new add_money();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("Cancel"))
       {
           this.dispose();
           new home();
       }else
       {
           if(e.getActionCommand().equals("Submit"))
           {
               String rem=remark_tf.getText();
               String what=rb1.isSelected()?"added":"withdrawl";
//       getting system date
               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               Date date=new Date();
               if(amt_tf.getText().equals("")|| rem.equals("")||what.equals(""))
               {
                   JOptionPane.showMessageDialog(this,"Please fill All the entries");
                   return;
               }
               try{
                   int amt=Integer.parseInt(amt_tf.getText());
                   Conn connect=new Conn();
                   ResultSet t=connect.s.executeQuery("select * from record");
                   t.last();
                   int i=t.getInt("total");
                   if(what.equals("added"))
                   {
                       i=i+amt;
                   }else{
                       i=i-amt;
                   }
                   connect.s.execute("insert into record values('"+sdf.format(date)+"','"+amt+"','"+rem+"','"+what+"','"+i+"')");
                   JOptionPane.showMessageDialog(this,"Successfully Submitted");
                   connect.s.close();
               }catch (Exception ae)
               {
                   ae.printStackTrace();
               }
           }
       }
    }
}
