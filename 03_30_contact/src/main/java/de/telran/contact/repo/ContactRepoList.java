package de.telran.contact.repo;

import de.telran.contact.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class ContactRepoList implements IContactRepo {

    private ArrayList<Contact> contacts = new ArrayList<>();
    // private int lastUsedId;
    AtomicInteger currentId = new AtomicInteger();


    @Override
    public void save(Contact contact) {
        if (contact.getId() <= 0) {
            // contact.setId(++lastUsedId);
            contact.setId(currentId.incrementAndGet());
            contacts.add(contact);
        } else {
            Contact oldContact = find(contact.getId());
            oldContact.setName(contact.getName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setAge(contact.getAge());
        }
    }


    @Override
    public Contact find(int id) {
        Contact contact = contacts.stream()
                .filter(cont -> cont.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return contact;
    }

    @Override
    public Contact remove(int id) {
        /*int currentId = getIndex(id);
        Contact contact = contacts.remove(currentId);
        return contact;*/
        Contact res = find(id);
        contacts.remove(res);
        return res;
    }

   /* private int getIndex(int index) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getId() == index) {
                return i;
            }
        }
        return -1;
    }*/

    @Override
    public List<Contact> findAll() {
        return Collections.unmodifiableList(contacts);
    }
}
