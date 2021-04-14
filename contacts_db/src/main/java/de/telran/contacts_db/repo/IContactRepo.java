package de.telran.contacts_db.repo;

import de.telran.contacts_db.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface IContactRepo extends CrudRepository<Contact, Integer> {
    Collection<Contact> findAllByName(String name);
}
