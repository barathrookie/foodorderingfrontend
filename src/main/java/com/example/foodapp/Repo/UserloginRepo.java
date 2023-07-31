package com.example.phonebook.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.phonebook.models.Userlogin;


public interface UserloginRepo extends JpaRepository<Userlogin,String> {
	Optional<Userlogin> findByEmail(String email);
}
