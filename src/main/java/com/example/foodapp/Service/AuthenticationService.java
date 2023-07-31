package com.example.phonebook.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.phonebook.Repo.UserdetailRepo;
import com.example.phonebook.Repo.UserloginRepo;
import com.example.phonebook.dto.Request.AuthenticationRequest;
import com.example.phonebook.dto.Request.RegisterRequest;
import com.example.phonebook.dto.Response.AuthenticationResponse;
import com.example.phonebook.models.Role;
import com.example.phonebook.models.Userdetail;
import com.example.phonebook.models.Userlogin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	

    private final UserloginRepo userRepository;
    private final UserdetailRepo detailRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Userlogin
                .builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phoneno(request.getPhoneno())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var detail = Userdetail
        		.builder()
        		.email(request.getEmail())
        		.password(request.getPassword())
        		.build();
        detailRepo.save(detail);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
