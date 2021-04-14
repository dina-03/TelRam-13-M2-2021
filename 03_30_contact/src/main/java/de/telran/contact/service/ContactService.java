package de.telran.contact.service;

import de.telran.contact.entity.Contact;
import de.telran.contact.repo.IContactRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
//the domain logic lies here
@Service
public class ContactService {

    IContactRepo contactRepo;

   /* public ContactService(@Qualifier("contactRepoList") IContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }*/

    public ContactService(IContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    public Contact get(int id) {
        return contactRepo.find(id);
    }

    public void save(Contact contact) {
        contactRepo.save(contact);
    }

    public Contact remove(int id) {
        return contactRepo.remove(id);
    }
}
