package com.example.phonebook.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.phonebook.models.Contacts;

@Repository
public interface ContactsRepo extends JpaRepository<Contacts,Integer> {
	
	@Query(value = "select * from Contacts where userid like %?1", nativeQuery=true)
	 public List<Contacts> findbyemail (String email);

}
