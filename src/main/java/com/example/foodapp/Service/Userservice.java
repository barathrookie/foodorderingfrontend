package com.example.phonebook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phonebook.Repo.UserdetailRepo;
import com.example.phonebook.Repo.UserloginRepo;
import com.example.phonebook.models.Userdetail;
import com.example.phonebook.models.Userlogin;
@Service
public class Userservice {

    @Autowired
    private UserdetailRepo userDetailsRepository;

    @Autowired
    private UserloginRepo userRepository;

    public void saveUserDetails(Userdetail userDetails) {
        userDetailsRepository.save(userDetails);
        
        Userlogin user = new Userlogin();
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        userRepository.save(user);
    }
}
