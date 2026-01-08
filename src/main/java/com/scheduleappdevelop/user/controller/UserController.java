package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.CreateUserRequest;
import com.scheduleappdevelop.user.dto.CreateUserResponse;
import com.scheduleappdevelop.user.dto.GetOneUserResponse;
import com.scheduleappdevelop.user.dto.GetUsersResponse;
import com.scheduleappdevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUsersResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.find());
    }

    // 유저 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOneUserResponse> getOneUser(
            @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }
}
