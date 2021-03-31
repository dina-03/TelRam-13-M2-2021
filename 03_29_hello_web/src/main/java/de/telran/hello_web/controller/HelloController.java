package de.telran.hello_web.controller;

import de.telran.hello_web.dto.Auto;
import de.telran.hello_web.dto.Greetings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name, Model model){
        String capitalName = name.toUpperCase();
        model.addAttribute("nameBigLetters", capitalName);
        return "hello";
    }

    @RequestMapping(value = "/hello-string/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String helloString(@PathVariable String name){
        String capitalName = name.toUpperCase();
        Greetings response = new Greetings(capitalName, "Hello!");
        return "Hello " + capitalName + "!";
    }

    @RequestMapping(value = "/hello-json/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Greetings helloJson(@PathVariable String name){
        String capitalName = name.toUpperCase();
        Greetings response = new Greetings(capitalName, "Привет!");
        return response;
    }

    @PostMapping("/auto")
    @ResponseBody
    public String acceptAuto(@RequestBody Auto auto){
        return "The following auto was received: " + "make: " + auto.make + ", color: " + auto.color;
    }

    @PatchMapping("/auto")
    @ResponseBody
    public String patchAuto(@RequestBody Auto auto){
        System.out.println(auto);
        return "The following auto was received: " + "make: " + auto.make + ", color: " + auto.color;
    }

    @PutMapping("/auto")
    @ResponseBody
    public String putAuto(@RequestBody Auto auto){
        System.out.println(auto);
        return "The following auto was received: " + "make: " + auto.make + ", color: " + auto.color;
    }

    @DeleteMapping("/auto")
    @ResponseBody
    public String deleteAuto(){
        String response = "deleted";
        return response;
    }
}
