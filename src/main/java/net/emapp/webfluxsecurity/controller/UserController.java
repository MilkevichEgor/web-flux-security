package net.emapp.webfluxsecurity.controller;

import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public Mono<ResponseEntity<UserEntity>> updateUser(@PathVariable Long userId, @RequestBody UserEntity user) {
        return userService.updateUser(userId, user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
