package com.example.phonebook.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	
	String email;
	String firstname;
	String lastname;
	String password;
	String address;
	int age;
	long phoneno;
	
}