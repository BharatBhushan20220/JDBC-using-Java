import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String url="jdbc:mysql://127.0.0.1:3306/bharatDB";
    private static final String userName="root";
    private static final String password="Bharat@123";

    public static void printDatabaseValue(){
        try{
            Connection connection= DriverManager.getConnection(url,userName,password);
            Statement statement= connection.createStatement();
            String query="select * from student";
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("Name");
                int age=resultSet.getInt("age");
                double marks= resultSet.getDouble("marks");
                System.out.println("ID: "+id);
                System.out.println("NAME: "+name);
                System.out.println("AGE: "+age);
                System.out.println("MARKS: "+marks);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    private static void insertValueInDatabase() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement=connection.createStatement();
            String query=String.format("INSERT INTO STUDENT (name, age, marks) values ('%s', %o, %f) ","Akash", 25, 75.0);
            int rowsAffected=statement.executeUpdate(query);
            if(rowsAffected>0) System.out.println("Value inserted into table Successfully! ");
            else System.out.println("Something is missing in code. ");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void updateValueInDatabase() {
        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement=connection.createStatement();
            String query=String.format("Update Student SET marks= %f where id= %o",88.0,2);
            int rowsAffected=statement.executeUpdate(query);
            if(rowsAffected>0) System.out.println("Value update into table Successfully! ");
            else System.out.println("Something is missing in code. ");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void deleteValueInDatabase() {
        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement=connection.createStatement();
            String query=String.format("DELETE from Student where id= %o ",2);
            int rowsAffected=statement.executeUpdate(query);
            if(rowsAffected>0) System.out.println("Value Deleted from table Successfully! ");
            else System.out.println("Something is missing in code. ");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //PreparedStatement code start from here ..........

    private static void insertValueInDatabase2() {
        try{
            Connection connection=DriverManager.getConnection(url,userName,password);
            String query="INSERT INTO STUDENT (name, age, marks) VALUES (?, ?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,"Ankita");
            preparedStatement.setInt(2,20);
            preparedStatement.setDouble(3,84.5);
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0) System.out.println("Data Inserted Successfully!! ");
            else System.out.println("Data not Inserted");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void printDatabaseValue2() {
        try{
            Connection connection=DriverManager.getConnection(url,userName,password);
            String query="SELECT marks from STUDENT where id= ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,3);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) System.out.println("Marks = " +resultSet.getDouble("marks"));
            else System.out.println("Marks not found");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void updateValueInDatabase2() {
        try{
            Connection connection=DriverManager.getConnection(url,userName,password);
            String query="Update Student SET marks=? where id=? ";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setDouble(1,92.0);
            preparedStatement.setInt(2,3);
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0) System.out.println("Data updated successfully !!");
            else System.out.println("Data not updated");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void deleteValueInDatabase2() {
        try{
            Connection connection=DriverManager.getConnection(url,userName,password);
            String query="Delete from Student where id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,3);
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0) System.out.println("Data Deleted Successfully!! ");
            else System.out.println("Data not Deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertValueInDatabaseByUser() {
        try{
            Connection connection=DriverManager.getConnection(url,userName,password);
            Scanner sc=new Scanner(System.in);
            Statement statement=connection.createStatement();
            while(true){
                System.out.print("Enter name:  ");
                String name=sc.nextLine();
                System.out.print("enter age : ");
                int age=sc.nextInt();
                System.out.print("Enter marks : ");
                double marks= sc.nextDouble();
                System.out.print("Enter more student data (Y/N) : ");
                String choice= sc.next();
                if(choice.equalsIgnoreCase("N")) {break;}

                String query= String.format("INSERT into student (name ,age, marks) values('%s', %d, %f)",name,age,marks);
                statement.addBatch(query);

            }
            int[] rowAffected=statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    private static void insertValueInDatabaseByUser2() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            String query = "Insert into student (name, age, marks) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enter name:  ");
                String name = sc.nextLine();
                System.out.print("enter age : ");
                int age = sc.nextInt();
                System.out.print("Enter marks : ");
                double marks = sc.nextDouble();
                System.out.print("Enter more student data (Y/N) : ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("N")) {break;}

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3,marks );
                preparedStatement.addBatch();
            }
            int [] rowAffected= preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());}
    }

    public static void main(String[] args){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");}
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        //By Statement Interface we have done the CRUD Operation
        printDatabaseValue();
        //insertValueInDatabase();
        //updateValueInDatabase();
        //deleteValueInDatabase();

        //By PreparedStatement we have done the CRUD Operation
        //insertValueInDatabase2();
        //printDatabaseValue2();
        //updateValueInDatabase2();
        //deleteValueInDatabase2();

        //Batch job By JDBC
        //By Statement Interface
        //insertValueInDatabaseByUser();

        //By PreparedStatement Interface

        //insertValueInDatabaseByUser2();



    }
}