package ru.omelyanenko.spring.dao;

import ru.omelyanenko.spring.models.Person;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {


   private static final String URL= "jdbc:postgresql://localhost:5432/mydb";
   private static final String NAME= "postgres";
   private static final String PASS= "admin";
 private static Connection connection;

 static {
     try {
         Class.forName("org.postgresql.Driver");
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
     String querySQL = "SELECT * FROM users";


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
         String SQL ="SELECT * FROM users WHERE id="+id;
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
            String SQL ="INSERT INTO users (age, name, email) values ("+person.getAge()+",'"+
                    person.getName()+"','"+person.getEmail()+"');";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(SQL);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


    public void update(Person person, int id) {


    }
    public void delete (int id){

    }
}