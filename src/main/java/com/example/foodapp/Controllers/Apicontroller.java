package com.example.phonebook.Controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phonebook.Repo.ContactsRepo;
import com.example.phonebook.Repo.UserdetailRepo;
import com.example.phonebook.Repo.UserloginRepo;
import com.example.phonebook.Service.Contactservice;
import com.example.phonebook.Service.Userservice;
import com.example.phonebook.models.Contacts;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/demo")
//@RequiredArgsConstructor
public class Apicontroller {

    @Autowired
    private Userservice userService;
    @Autowired
    private Contactservice contactservice;
    @Autowired
    private UserdetailRepo repo;
    @Autowired
    private ContactsRepo contactrepo2;
    @Autowired
    private UserloginRepo userRepo;
    
    @GetMapping("/geti")
    public String geti()
    {
    	return "mukesh";
    }
    @GetMapping("/get/{user}")
    public List<Contacts> geth(@PathVariable String user)
    {
    	return contactservice.getdetails(user);
    }
    @GetMapping("/getbyid/{contact_id}")
    public Optional<Contacts> getid(@PathVariable int contact_id)
    {
    	return contactrepo2.findById(contact_id);
    }
    @PostMapping("/addcontact")
    public ResponseEntity<String> savecontact(@RequestBody Contacts contact)
    {
    	contactservice.addcontct(contact);
    	return ResponseEntity.ok("Contact added Succsessfully");
    }
    @DeleteMapping("/delete/{contact_id}")
    public String deleteRecord(@PathVariable int contact_id) {
        contactservice.deleteContactByUserAndEmail(contact_id);
        return "Record deleted successfully.";
    }
    @PutMapping("/update/{contactId}")
    public ResponseEntity<Contacts> updateContact(@PathVariable int contactId, @RequestBody Contacts updatedContact) {
        Contacts existingContact = contactservice.getContactById(contactId);

        // Update the fields with the new values
        existingContact.setName(updatedContact.getName());
        existingContact.setPhoneno(updatedContact.getPhoneno());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setAddress(updatedContact.getAddress());
        existingContact.setJob(updatedContact.getJob());
        existingContact.setNote(updatedContact.getNote());
        existingContact.setUserid(updatedContact.getUserid());

        // Save the updated contact
        Contacts updatedContactEntity = contactservice.saveContact(existingContact);

        return new ResponseEntity<>(updatedContactEntity, HttpStatus.OK);
    }

}
