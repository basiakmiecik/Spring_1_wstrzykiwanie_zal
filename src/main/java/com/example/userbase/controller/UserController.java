package com.example.userbase.controller;

import com.example.userbase.model.User;
import com.example.userbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @ResponseBody
    public String getUsers() {
        List<User> users = userRepository.getUsers();
        String response = "";
        for (User user : users) {
            response += user.toString();
            response += "<br>";
        }
        return response;
    }
    @GetMapping("/znajdz")
    @ResponseBody
    //@RequestMapping(value="/find", method = RequestMethod.GET)
    public String findUser(@RequestParam String imie, @RequestParam String nazwisko, @RequestParam String wiek ) {
       /* System.out.println("Imię:"+ imie);
        System.out.println("Nazwisko:"+ nazwisko);
        System.out.println("Wiek:"+ wiek);*/

        List<User> users = userRepository.getUsers();
        String fUser = "";

        for (User user : users) {
            /*System.out.println(user.getFirstName());*/
            if (!(imie.isEmpty())&&user.getFirstName().equals(imie))
            {
                    return fUser = user.toString();
            } else if (!(nazwisko.isEmpty())&&user.getLastName().equals(imie))
            {
                    return fUser = user.toString();
            } else if (!(wiek.isEmpty())&&user.getAge() == Integer.valueOf(wiek))
            {
                    return fUser = user.toString();
            }
        }
        return fUser;
    }



    @PostMapping("/add")
    //@RequestMapping(value="/add", method = RequestMethod.POST)
    public String saveUser(@RequestParam String imie, @RequestParam String nazwisko, @RequestParam int wiek) {

        if(imie.isEmpty() ||nazwisko.isEmpty()|| wiek <= 0) {
            //http://localhost:8080/add?imie=&nazwisko=Rayjko&wiek=30
            //http://localhost:8080/add?imie=Johnny&nazwisko=Rayjko&wiek=-5
            return "redirect:err.html";
        }
        if(!(imie.isEmpty())&&!(nazwisko.isEmpty())&&wiek>0){
            //http://localhost:8080/add?imie=Johnny&nazwisko=Rayjko&wiek=30
            User user = new User(imie, nazwisko, wiek);
            userRepository.add(user);}

            return "redirect:success.html";
    }
}
