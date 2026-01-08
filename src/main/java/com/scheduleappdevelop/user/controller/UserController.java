package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성 (회원가입)
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 유저 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUsersResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.find());
    }

    // 유저 단건 조회
    @GetMapping("/myuser")
    public ResponseEntity<GetOneUserResponse> getOneUser(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(sessionUser.getId()));
    }

    // 유저 수정
    @PutMapping("/users")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(sessionUser.getId(), request));
    }

    // 유저 삭제
    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        userService.delete(sessionUser.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
