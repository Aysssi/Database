import java.sql.*;

import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static  String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/soft_uni";

    public static void main(String[] args)  {;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter DB username (<Enter> for 'root'):)");
        String  username = scanner.nextLine().trim();
        username = username.length() > 0? username : "root";

        System.out.println("Enter DB password (<Enter> for '2819'):)");
        String  password = scanner.nextLine().trim();
        password = password.length() > 0? password : "2819";




        //1.Load DB Driver
        try {
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.printf("Database driver: %s%n not found",DB_DRIVER);
            System.exit(0);
        }
        System.out.println("Driver loaded successfully.");

        //2.Connect to DB
        Properties properties = new Properties();
        properties.setProperty("user",username);
        properties.setProperty("password",password);
        Connection connection = null;
        try {
        connection = DriverManager.getConnection(DB_URL,properties);
        } catch (SQLException throwables) {
            System.out.printf("Can not connect to DB: %s ",DB_DRIVER);
            System.exit(0);
        }
        System.out.printf("DB connect create successfully: %s%n",DB_URL);

            //3.Read query params
        System.out.println("Enter minimal salary (<Enter> for '40000'):)");
        String  salaryStg  = scanner.nextLine().trim();
        salaryStg = salaryStg.length() > 0? salaryStg : "40000";
         double salary = 40000;
        try {
         salary = Double.parseDouble(salaryStg);
        }catch (NumberFormatException ex){
            System.err.printf("Invalid number: '%s'",salaryStg);

        }

        //4.Create preparedStatement
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM employees WHERE salary > ?");

            //5.Execute preparedStatement with parameter
        preparedStatement.setDouble(1,salary);
       ResultSet resultSet = preparedStatement.executeQuery();

            //6.Print result
            while (resultSet.next()){
                System.out.printf("| %10d | %-15.15s | %15.15s | %10.2f |%n",
                resultSet.getLong("employee_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDouble("salary")
                );

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

       //7.Close connection
        try {
            connection.close();
        } catch (SQLException throwables) {
          System.err.printf("Error closing DB connection %s",throwables.getMessage());
        }
    }

}