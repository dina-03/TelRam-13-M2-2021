package de.telran.contact.repo;

import de.telran.contact.entity.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactRepoMap implements IContactRepo{

   private HashMap<Integer, Contact> contactById;

    public ContactRepoMap(HashMap<Integer, Contact> contactById) {
        this.contactById = new HashMap<>(contactById) ;
    }

    @Override
    public void save(Contact contact) {
        contactById.put(contact.getId(), contact);
    }

    @Override
    public Contact find(int id) {
        return contactById.get(id);
    }

    @Override
    public Contact remove(int id) {
        return contactById.remove(id);
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contactById.values());
    }
}
