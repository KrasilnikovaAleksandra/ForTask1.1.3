package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Column;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Statement currentStatement;

    public UserDaoJDBCImpl() {

    }

    private void checkConnectionWithBD(){
        try {
            currentStatement = Util.openConnectionWithBD().createStatement();
            System.out.println("---Установлено соединение с БД---");
        } catch (SQLException e) {
            System.out.println("Проблема с подключением к БД");
        }
    }

    public void createUsersTable() {
        this.checkConnectionWithBD();
        try {
            currentStatement.executeUpdate("create table users3 " +
                    "(id int auto_increment," +
                    "nameUser nvarchar(30)," +
                    "lastNameUser nvarchar(30)," +
                    "age int," +
                    " PRIMARY KEY(id) );");
            System.out.println("Таблица пользователей создана");
        } catch (SQLException e) {
            System.out.println("Таблица  с таким названием уже существует!");
        } finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }
    }

    public void dropUsersTable() {
        this.checkConnectionWithBD();
        try {
            currentStatement.executeUpdate("drop table users3;");
            System.out.println("Таблица пользователей удалена");


        } catch (SQLException e) {
            System.out.println("Таблицы пользователей не существует!");
        } finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        this.checkConnectionWithBD();
        try {
            currentStatement.executeUpdate(String.format("insert  users3 (nameUser, lastNameUser, age) " +
                    "values ('%s', '%s', %o);", name, lastName, age));
        } catch (SQLException e) {
            System.out.println("Пользователь задан некорректно");
        }
        finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }

    }

    public void removeUserById(long id) {
        this.checkConnectionWithBD();
        try {
            currentStatement.executeUpdate(String.format("delete from users3 where id = %o", id));
        } catch (SQLException e) {
        }
        finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }
    }

    public List<User> getAllUsers() {
        this.checkConnectionWithBD();
        List <User> listUsers = new ArrayList<>();
        try {
           ResultSet resultSet =  currentStatement.executeQuery("select * from users3");
           while (resultSet.next()){
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age =  resultSet.getByte(4);
                User currentUser = new User(name, lastName, age);
                listUsers.add(currentUser);
           }
            System.out.println("Все пользователи из таблицы получены");
        } catch (SQLException e) {
        }
        finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        this.checkConnectionWithBD();
        try {
            currentStatement.executeUpdate("delete from users3");
        } catch (SQLException e) {
        }
        finally {
            Util.closeConnectionWithBD();
            System.out.println("---Закрыто соединение с БД---");
        }
    }
}
