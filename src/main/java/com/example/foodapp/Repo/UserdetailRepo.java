package com.example.phonebook.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.phonebook.models.Userdetail;

public interface UserdetailRepo extends JpaRepository<Userdetail,String> {
	
}
