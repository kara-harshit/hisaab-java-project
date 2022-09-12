package com.kara;
import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
    public Conn()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  // driver load
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hisaab","root","");
            s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
