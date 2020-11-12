package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
   //private static String urlBD = "jdbc:mysql://localhost:3306/usersbd?serverTimezone=Europe/Moscow";
    private static String urlBD = "jdbc:mysql://localhost:3306/usersbd";
    private static String nameBD = "Sandra";
    private static String passwordBD = "Sandra16";
    private static String driverBD = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    public static Connection openConnectionWithBD() {

        try {
            Class.forName(driverBD);
            connection = DriverManager.getConnection(urlBD, nameBD, passwordBD);
            /*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                System.out.println(resultSet.getString(4));
            }*/
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnectionWithBD() {
        try {
            connection.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }
}
