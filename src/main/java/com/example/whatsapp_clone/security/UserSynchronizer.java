package com.example.whatsapp_clone.security;

import com.example.whatsapp_clone.domain.entities.User;
import com.example.whatsapp_clone.domain.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserSynchronizer {

    private final UserRepo userRepository;

    public UserSynchronizer(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User syncUser(Jwt jwt) {

        UUID keycloakId = UUID.fromString(jwt.getSubject());

        User user = userRepository.findById(keycloakId)
                .orElseGet(() -> {
                    User u = new User();
                    u.setId(keycloakId);
                    u.setPhoneNumber(jwt.getClaimAsString("preferred_username"));
                    u.setName(jwt.getClaimAsString("name"));
                    return userRepository.save(u);
                });

        user.setLast_seen(LocalDateTime.now());
        return user;
    }

}
