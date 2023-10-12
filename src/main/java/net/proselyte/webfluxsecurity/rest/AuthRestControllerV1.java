package net.proselyte.webfluxsecurity.rest;

import lombok.RequiredArgsConstructor;
import net.proselyte.webfluxsecurity.dto.AuthRequestDto;
import net.proselyte.webfluxsecurity.dto.AuthResponseDto;
import net.proselyte.webfluxsecurity.dto.UserDto;
import net.proselyte.webfluxsecurity.entity.UserEntity;
import net.proselyte.webfluxsecurity.mapper.UserMapper;
import net.proselyte.webfluxsecurity.repository.UserRepository;
import net.proselyte.webfluxsecurity.security.CustomPrincipal;
import net.proselyte.webfluxsecurity.security.SecurityService;
import net.proselyte.webfluxsecurity.service.AuthService;
import net.proselyte.webfluxsecurity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {

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
    public Mono<AuthResponseDto> authenticate(@RequestBody AuthRequestDto dto) {
        return authService.authenticate(dto);
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {

        sink.emitNext("Hello " + authentication.getName(), Sinks.EmitFailureHandler.FAIL_FAST);

        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::map);
    }
}
