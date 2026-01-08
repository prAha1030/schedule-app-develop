package com.scheduleappdevelop.user.repository;

import com.scheduleappdevelop.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(@NotBlank String email, @NotBlank String password);
}
