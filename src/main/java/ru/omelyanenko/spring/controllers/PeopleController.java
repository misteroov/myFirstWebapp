package ru.omelyanenko.spring.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.omelyanenko.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.omelyanenko.spring.models.Person;

import javax.validation.Valid;


/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

private PersonDAO personDAO;
@Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
    model.addAttribute("persons",personDAO.getListUser());
    return "/people/index";
    }
    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
    model.addAttribute("user",personDAO.show(id));
    return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){

    return "/people/new";
    }
    @PostMapping()
    public String create (@ModelAttribute("person") @Valid Person person,
                          BindingResult bindingResult){
    if (bindingResult.hasErrors())
        return "/people/new";
    personDAO.addPerson(person);
    return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id){
    model.addAttribute("person",personDAO.show(id));
    return "people/edit";
    }

    @PostMapping("{id}")
    public String update(@ModelAttribute("person") @Valid Person  person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
    if (bindingResult.hasErrors())
        return "people/edit";
    personDAO.update(person,id);
    return "redirect:/people";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
    personDAO.delete(id);
    return "redirect:/people";
    }
}