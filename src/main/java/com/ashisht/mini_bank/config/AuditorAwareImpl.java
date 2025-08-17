package com.ashisht.mini_bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorAwareImpl {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            // Here you can fetch the current user from SecurityContext or any other context
            // For now, always return "systemUser" as fallback
            String currentUser = getCurrentUser();
            return Optional.of(currentUser != null ? currentUser : "systemUser");
        };
    }

    private String getCurrentUser() {
        // Example: SecurityContextHolder.getContext().getAuthentication().getName()
        return null; // Always fallback to systemUser for now
    }
}

