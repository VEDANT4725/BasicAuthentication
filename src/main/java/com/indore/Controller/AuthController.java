package com.indore.Controller;

import com.indore.dto.AuthResponse;
import com.indore.dto.LoginRequest;
import com.indore.dto.RegisterRequest;
import com.indore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register-user")
    private String registeUser(@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody RegisterRequest registerRequest){

        return authService.registerAdmin(registerRequest);

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){

        return authService.login(request);

    }


}
