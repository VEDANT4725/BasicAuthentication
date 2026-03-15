package com.indore.service;

import com.indore.dto.AuthResponse;
import com.indore.dto.LoginRequest;
import com.indore.dto.RegisterRequest;
import com.indore.entity.Users;
import com.indore.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest registerRequest){
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "user registered successfully";
    }

    public String registerAdmin(RegisterRequest registerRequest){

        Users Admin =new Users();
        Admin.setUsername(registerRequest.getUsername());
        Admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Admin.setRole("ADMIN");
        userRepository.save(Admin);
        return "Admin registered successfully";
    }

    public AuthResponse login(LoginRequest loginRequest){
        Users user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not Found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        AuthResponse authResponse=new AuthResponse();
        authResponse.setUsername(user.getUsername());
        authResponse.setRole(user.getRole());
        return authResponse;
    }
}
