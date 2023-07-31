package com.example.phonebook.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phonebook.Repo.ContactsRepo;
import com.example.phonebook.models.Contacts;

import lombok.extern.slf4j.Slf4j;



@Service
public class Contactservice {
	
	@Autowired
	private ContactsRepo contactrepo;
	
	public void addcontct(Contacts contact)
	{
		contactrepo.save(contact);
	}
	public List<Contacts> getdetails(String user)
	{ 
		return contactrepo.findbyemail(user);
	}
	public void deleteContactByUserAndEmail(int contact_id) {
        contactrepo.deleteById(contact_id);
    }
	public Contacts getContactById(int contactId) {
        Optional<Contacts> optionalContact = contactrepo.findById(contactId);
        return optionalContact.orElse(null);
    }
	 public Contacts saveContact(Contacts contact) {
	        return contactrepo.save(contact);
	    }

	

}
