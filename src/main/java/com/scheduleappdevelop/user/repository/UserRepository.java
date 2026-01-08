package com.scheduleappdevelop.user.repository;

import com.scheduleappdevelop.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(@NotBlank String email, @NotBlank String password);

    boolean existsByEmail(@NotBlank @Email(message = "올바른 이메일 형식이 아닙니다.") String email);
}
