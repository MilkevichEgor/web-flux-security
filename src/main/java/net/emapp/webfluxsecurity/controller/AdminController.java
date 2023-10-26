package net.emapp.webfluxsecurity.controller;

import lombok.RequiredArgsConstructor;
import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.mapper.UserMapper;
import net.emapp.webfluxsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final Sinks.Many<String> sink;
    private final UserMapper userMapper;


//    @GetMapping("/info")
//    public Mono<UserDto> getUserInfo1(@AuthenticationPrincipal Authentication authentication) {
//        return userService.getUserInfo(authentication)
//                .map(userMapper::map);
//    }

    @GetMapping("/info/{userId}")
    public Mono<UserEntity> getUserInfo(@PathVariable Long userId) {
        return userService.getUserById(userId);
//                .map(userMapper::map);
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable Long userId) {
//        sink.emitNext("User deleted", Sinks.EmitFailureHandler.FAIL_FAST);
        return userService.deleteUser(userId);
    }
}
