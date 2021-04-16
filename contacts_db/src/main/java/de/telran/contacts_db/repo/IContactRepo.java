package de.telran.contacts_db.repo;

import de.telran.contacts_db.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface IContactRepo extends CrudRepository<Contact, Integer> {
    Collection<Contact> findAllByName(String name);

    Collection<Contact> findAllByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String partialName, String partialLastName);

    @Query("select c from Contact c where UPPER(c.name) like concat('%',UPPER(:pattern),'%') or UPPER(c.lastName) like concat('%',UPPER(:pattern),'%')")
    Collection<Contact> findAllContainingPattern(@Param("pattern") String pattern);
}
