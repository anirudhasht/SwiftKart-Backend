package com.swiftkart.user_service.Controller;

import com.swiftkart.user_service.model.LoginRequest;
import com.swiftkart.user_service.model.LoginResponse;
import com.swiftkart.user_service.model.User;
import com.swiftkart.user_service.repository.UserRepo;
import com.swiftkart.user_service.security.JwtUtil;
import com.swiftkart.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    public AuthController(UserService service, AuthenticationManager authManager, UserRepo userRepo, JwtUtil jwtUtil) {
        this.service = service;
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User saved = service.registerUser(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        // Authenticating using email + password
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        //  Setting authentication in context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //  Fetching the user from DB
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Generating JWT Token
        String jwtToken = jwtUtil.generateToken(user.getUsername(), user.getRole());

        //  Returning response
        return ResponseEntity.ok(new LoginResponse(jwtToken, user.getEmail(), user.getRole().name()));
    }
}
