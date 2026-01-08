package com.scheduleappdevelop.user.service;

import com.scheduleappdevelop.user.dto.CreateUserRequest;
import com.scheduleappdevelop.user.dto.CreateUserResponse;
import com.scheduleappdevelop.user.dto.GetOneUserResponse;
import com.scheduleappdevelop.user.dto.GetUsersResponse;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 생성 요청 -> 응답으로 변환
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName()
        );
    }

    // 유저 전체 조회 요청 -> 응답
    @Transactional(readOnly = true)
    public List<GetUsersResponse> find() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> new GetUsersResponse(
                        u.getId(),
                        u.getName(),
                        u.getEmail()
                )).toList();
    }

    // 유저 단건 조회 요청 -> 응답
    @Transactional(readOnly = true)
    public GetOneUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return new GetOneUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
