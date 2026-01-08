package com.scheduleappdevelop.user.service;

import com.scheduleappdevelop.config.PasswordEncoder;
import com.scheduleappdevelop.exception.DuplicateEmailException;
import com.scheduleappdevelop.exception.PasswordNotMatchException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 생성 요청 -> 응답으로 변환
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        boolean existence = userRepository.existsByEmail(request.getEmail());
        if (existence) {
            throw new DuplicateEmailException("중복된 이메일이 존재합니다.");
        }
        // 비밀번호 암호화하여 저장
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword
        );
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName()
        );
    }

    // 로그인 요청 -> 응답
    @Transactional
    public SessionUser login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("등록되지 않은 이메일입니다."));
        // 비밀번호 검증
        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        return new SessionUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    // 유저 전체 조회 요청 -> 응답
    @Transactional(readOnly = true)
    public List<GetUsersResponse> find() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> new GetUsersResponse(
                        u.getId(),
                        u.getName()
                )).toList();
    }

    // 유저 단건 조회 요청 -> 응답
    @Transactional(readOnly = true)
    public GetOneUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        return new GetOneUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // 유저 수정 요청 -> 응답
    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        user.update(request.getName());
        return new UpdateUserResponse(
                user.getId(),
                user.getName()
        );
    }

    // 유저 삭제 요청 -> 응답
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
