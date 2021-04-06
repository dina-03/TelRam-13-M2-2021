package de.telran.contact.controller;

import de.telran.contact.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ContactController {
    List<Contact> contacts = new ArrayList<>();
    {
        contacts.add(new Contact(1, "Vasya", "Vasin", 21));
        contacts.add(new Contact(2, "Petya", "Peterson", 22));
        contacts.add(new Contact(3, "Sonja", "Zasonja", 42));
        contacts.add(new Contact(4, "Alla", "Kwas", 52));
        contacts.add(new Contact(5, "Taras", "Bulba", 67));
    }
    static int lastUsedId = 5;

    /**
     * The endpoint should return the page containing the list of contacts
     *
     * @return
     */
    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    /**
     * The endpoint should return the page with the empty form for creating a new contact.
     *
     * @return
     */
    @GetMapping("/add-contact")
    public String addContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    /**
     * The endpoint should return the page with the form filled in with a certain contact.
     *
     * @param id the id of the specified contact
     * @return
     */
    @GetMapping("/edit-contact/{id}")
    public String editContact(@PathVariable int id, Model model) {
        Contact contact = contacts.stream()
                .filter(cont -> cont.getId() == id)
                .findFirst()
                .orElseThrow();
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    /**
     * The endpoint should return the page with the details of a certain contact.
     *
     * @param id
     * @return
     */
    @GetMapping("/contacts/{id}")
    public String contact(@PathVariable int id, Model model) {
        Contact contact = contacts.stream()
                .filter(cont -> cont.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        model.addAttribute("contact", contact);
        return "user-details";
    }

    /**
     * The endpoint saves ether a new contact ot the existing contact (depending on the value of the field "id") and
     * then redirects to the contacts page
     *
     * @return
     */
    @PostMapping("/save-contact")
    public String saveContact(@ModelAttribute Contact contact) {
        if (contact.getId() <= 0) {
            contact.setId(++lastUsedId);
            contacts.add(contact);
        } else {
            Contact oldContact = contacts.stream()
                    .filter(cont -> cont.getId() == contact.getId())
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            oldContact.setName(contact.getName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setAge(contact.getAge());
        }
        return "redirect:/contacts";
    }

    /**
     * The endpoint removes the contact and return the redirect to the contacts page.
     *
     * @param id
     * @return
     */
    @GetMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable int id) {
        int currentId = getIndex(id);
        contacts.remove(currentId);
        return "redirect:/contacts";
    }

    private int getIndex(int index) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getId() == index){
                return i;
            }
        }
        return -1;
    }
}
