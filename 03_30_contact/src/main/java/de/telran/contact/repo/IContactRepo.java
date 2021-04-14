package de.telran.contact.repo;

import de.telran.contact.entity.Contact;

import java.util.List;
//The repo layer is responsible for starting data
public interface IContactRepo {

    void save(Contact contact);

    Contact find(int id);

    Contact remove(int id);

    List<Contact> findAll();
}
