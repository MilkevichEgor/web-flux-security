package net.emapp.webfluxsecurity.controller;

import lombok.RequiredArgsConstructor;
import net.emapp.webfluxsecurity.dto.AuthRequestDto;
import net.emapp.webfluxsecurity.dto.AuthResponseDto;
import net.emapp.webfluxsecurity.dto.UserDto;
import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.mapper.UserMapper;
import net.emapp.webfluxsecurity.security.CustomPrincipal;
import net.emapp.webfluxsecurity.service.AuthService;
import net.emapp.webfluxsecurity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final Sinks.Many<String> sink;

    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto dto) {
        UserEntity entity = userMapper.map(dto);
        return userService.registerUser(entity)
                .map(userMapper::map);
    }

    @PostMapping("/login")
    public Mono<AuthResponseDto> authentication(@RequestBody AuthRequestDto dto) {
        sink.emitNext("Welcome " + dto.getUsername(), Sinks.EmitFailureHandler.FAIL_FAST);
        return authService.authenticateUser(dto);
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {
        sink.emitNext("Welcome " + authentication.getName(), Sinks.EmitFailureHandler.FAIL_FAST);

        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::map);
    }

}
