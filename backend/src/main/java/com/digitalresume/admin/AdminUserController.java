package com.digitalresume.admin;

import com.digitalresume.user.UserEntity;
import com.digitalresume.user.UserRepository;
import com.digitalresume.user.UserRole;
import com.digitalresume.user.UserStatus;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserEntity> updateStatus(@PathVariable Long userId, @RequestBody StatusRequest request) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setStatus(request.status());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PatchMapping("/{userId}/roles")
    public ResponseEntity<UserEntity> updateRoles(@PathVariable Long userId, @RequestBody RolesRequest request) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setRoles(request.roles());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    public record StatusRequest(UserStatus status) {}
    public record RolesRequest(Set<UserRole> roles) {}
}
