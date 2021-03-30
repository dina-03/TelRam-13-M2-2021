package de.telran.contact.controller;

import de.telran.contact.dto.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {
    @GetMapping("/contact/{name}")
    public String contact(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "contact";
    }

    @RequestMapping("/contact")
    @ResponseBody
    public String contactString(@RequestBody Contact name){
        return "Contact: name: " + name.getName() + ", lastName: " + name.getLastName() + ", age: " + name.getAge();
    }
}
