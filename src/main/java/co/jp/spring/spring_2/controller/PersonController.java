package co.jp.spring.spring_2.controller;

import co.jp.spring.spring_2.Service.PersonService;
import co.jp.spring.spring_2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("personList",this.personService.getPersonList());
        return "personList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("form", new Person());
        return "personAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addConfirm(Model model, @ModelAttribute("form") Person p){
        this.personService.addPerson(p);
        return "redirect:/";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable int id){
        model.addAttribute("form",this.personService.getPerson(id));
        return "personDetail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") int id){
        model.addAttribute("form",this.personService.getPerson(id));
        return "personUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateConfirm(Model model, @ModelAttribute("from") Person p){
        this.personService.updatePerson(p);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, @RequestParam("id") int id){
        this.personService.deletePerson(id);
        return "redirect:/";
    }
}
