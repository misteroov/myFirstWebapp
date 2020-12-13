package ru.omelyanenko.spring.dao;

import ru.omelyanenko.spring.models.Person;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

   private static int PERSON_ID;

   List<Person>listUser;
    {
        listUser=new ArrayList<>();
        listUser.add(new Person(++PERSON_ID,"Tom",18,"tom@gmail.com"));
        listUser.add(new Person(++PERSON_ID,"Andrey",16,"andr@gmail.com"));
        listUser.add(new Person(++PERSON_ID,"Anna",15,"anna@gmail.com"));
        listUser.add(new Person(++PERSON_ID,"Kate",21,"kate@gmail.com"));
            }

            public List<Person> getListUser(){
        return listUser;
            }
            public Person show(int id){
       return listUser.stream().filter(listUser->listUser.getId()==id).findAny().orElse(null);
            }
            public void addPerson(Person person){
        person.setId(++PERSON_ID);
        listUser.add(person);
            }


    public void update(Person person, int id) {
        Person updUser = show(id);
        updUser.setName(person.getName());
        updUser.setAge(person.getAge());
        updUser.setEmail(person.getEmail());

    }
    public void delete (int id){
        listUser.removeIf(p->p.getId()==id);
    }
}