package com.kara;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class home extends JFrame implements ActionListener {
    JLabel tmoney_text,tmoney_amount;
    JButton add;
    JTable rec_table;
    public home(){
        setLayout(null);
        this.getContentPane().setBackground(new Color(87, 111, 114));

//        title icon change
        ImageIcon image=new ImageIcon("favicon H.jfif");
        this.setIconImage(image.getImage());
//        adding headings
        tmoney_text=new JLabel("Total Amount: ",SwingConstants.CENTER);
        tmoney_text.setFont(new Font("",Font.BOLD,15));
        tmoney_text.setBounds(430,15,150,100);
        this.add(tmoney_text);
//        inserting total amount from database
        int tot=0;
        try{
            Conn cc=new Conn();
            ResultSet res=cc.s.executeQuery("Select total from record");
            res.last();
            tot=res.getInt("total");
            res.close();
        }catch(Exception exc)
        {
            exc.printStackTrace();
        }

        tmoney_amount=new JLabel(String.valueOf(tot),SwingConstants.CENTER);
        tmoney_amount.setFont(new Font("",Font.BOLD,130));
        tmoney_amount.setBounds(300,100,400,150);
        this.add(tmoney_amount);

//        displaying table

        DefaultTableModel tablemodel=new DefaultTableModel();
        rec_table=new JTable(tablemodel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rec_table.getTableHeader().setFont(new Font("",Font.BOLD,15));
        tablemodel.addColumn("Date");
        tablemodel.addColumn("Amount");
        tablemodel.addColumn("Remark");
        tablemodel.addColumn("Status");
        JScrollPane sp=new JScrollPane(rec_table);
        sp.setBackground(Color.lightGray);
        sp.setBounds(100,300,700,300);
        sp.setFont(new Font("",Font.BOLD,12));
        this.add(sp);
        //        connection for showing record
        try{
            Conn co=new Conn();
            ResultSet res=co.s.executeQuery("select * from record");

            while(res.next())
            {
                String date=res.getString("date");
                String amount=res.getString("amount");
                String remark=res.getString("remark");
                String status=res.getString("status");
                tablemodel.insertRow(0,new Object[]{date,amount,remark,status});
            }
            res.close();
        }catch(Exception ecp)
        {
            ecp.printStackTrace();
        }
//        add button
        add=new JButton("ADD");
        add.setBounds(820,300,100,30);
        this.add(add);
        add.addActionListener(this);
//        FRAME
        setTitle("HISAAB");
        setResizable(false);
        setBounds(200,50,1000,700);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();

        if(cmd.equals("ADD"))
        {
            this.dispose();
            new add_money();
        }
    }
}
