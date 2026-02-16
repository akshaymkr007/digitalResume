package com.digitalresume.auth;

import com.digitalresume.user.UserEntity;
import com.digitalresume.user.UserRepository;
import com.digitalresume.user.UserRole;
import com.digitalresume.user.UserStatus;
import jakarta.validation.Valid;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDtos.AuthResponse> register(@Valid @RequestBody AuthDtos.RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(Set.of(UserRole.CANDIDATE));
        userRepository.save(user);
        return ResponseEntity.ok(new AuthDtos.AuthResponse("User registered. Use OAuth2 login or token flow for login."));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthDtos.AuthResponse> logout() {
        return ResponseEntity.ok(new AuthDtos.AuthResponse("Logout handled by OAuth2 provider/token revocation endpoint."));
    }
}
