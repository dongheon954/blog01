package org.example.blog.controller;

import lombok.RequiredArgsConstructor;
import org.example.blog.dto.UserDto;
import org.example.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody UserDto.SignUpRequest dto) {
        return ResponseEntity.ok(userService.signUp(dto));
    }

    // 유저 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}