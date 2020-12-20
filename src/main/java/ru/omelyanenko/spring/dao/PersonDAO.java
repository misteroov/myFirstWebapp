package ru.omelyanenko.spring.dao;

import ru.omelyanenko.spring.models.Person;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

   private static final String URL = "jdbc:mysql://localhost:3306/users?serverTimezone=" + TimeZone.getDefault().getID();

   private static final String NAME= "admin";
   private static final String PASS= "12345";
 private static Connection connection;

 static {
     try {
         Class.forName("com.mysql.jdbc.Driver");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }

     try {
         connection= DriverManager.getConnection(URL,NAME,PASS);

     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
 }


            public List<Person> getListUser(){
     List<Person>listUser = new ArrayList<>();
     String querySQL = "SELECT * FROM person";


                try {
                    Statement statement =connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(querySQL);

                    while (resultSet.next()){
                        Person person =new Person();
                        person.setId( resultSet.getInt("id"));
                        person.setAge( resultSet.getInt("age"));
                        person.setName( resultSet.getString("name"));
                        person.setEmail( resultSet.getString("email"));
                        listUser.add(person);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        return listUser;
            }
            public Person show(int id){
         Person person = new Person();
         String SQL ="SELECT * FROM person WHERE id="+id;
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(SQL);
                    while (resultSet.next()) {
                        person.setId(resultSet.getInt("id"));
                        person.setAge(resultSet.getInt("age"));
                        person.setName(resultSet.getString("name"));
                        person.setEmail(resultSet.getString("email"));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return person;
            }

            public void addPerson(Person person){
            String SQL ="INSERT INTO person (age, name, email) values ("+person.getAge()+",'"+
                    person.getName()+"','"+person.getEmail()+"');";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(SQL);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


    public void update(Person person, int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE person SET age =?,name= ?, email = ? WHERE id = ?");
            preparedStatement.setInt(1,person.getAge());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void delete (int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from person WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}