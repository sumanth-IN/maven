package org.example;
import java.sql.*;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StudentDao sd = new StudentDao();
        sd.connect();
        Student s = new Student();
        s.rollno = 5;
        s.sname = "sql";

        sd.addStudent(s);
        sd.close();

    }
}
class StudentDao{
    static Connection con = null;
    static Statement st = null;
    public static void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/demo";
            String uname = "root";
            String pwd = "x1x2x3x4";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, pwd);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void addStudent(Student s){
        try{
            String query = "insert into student values (?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,s.rollno);
            ps.setString(2,s.sname);
            ps.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        //return null;
    }
    public static void close(){
        try{
            con.close();
            st.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
class Student{
    int rollno;
    String sname;
}