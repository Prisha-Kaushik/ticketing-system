package com.ticketing.system.config;

import com.ticketing.system.entity.Role;
import com.ticketing.system.entity.User;
import com.ticketing.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@ticketing.com");
            admin.setPassword(passwordEncoder.encode("Admin@2024"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        // Create support agent if not exists
        if (!userRepository.existsByUsername("agent1")) {
            User agent = new User();
            agent.setUsername("agent1");
            agent.setEmail("agent1@ticketing.com");
            agent.setPassword(passwordEncoder.encode("Agent@2024"));
            agent.setFirstName("Support");
            agent.setLastName("Agent");
            agent.setRole(Role.SUPPORT_AGENT);
            userRepository.save(agent);
        }

        // Create regular user if not exists
        if (!userRepository.existsByUsername("user1")) {
            User user = new User();
            user.setUsername("user1");
            user.setEmail("user1@ticketing.com");
            user.setPassword(passwordEncoder.encode("User@2024"));
            user.setFirstName("Regular");
            user.setLastName("User");
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
}
