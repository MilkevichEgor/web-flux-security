package net.emapp.webfluxsecurity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.emapp.webfluxsecurity.dto.UserDto;
import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.entity.UserRole;
import net.emapp.webfluxsecurity.mapper.UserMapper;
import net.emapp.webfluxsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public Mono<UserEntity> registerUser(UserEntity user) {
        return userRepository.save(user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .role(UserRole.USER)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        ).doOnSuccess(u -> {
            log.info("IN registerUser - user: {}", u);
        });
    }

    public Mono<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<UserDto> getUserInfo(Long id) {
        return userRepository.findById(id)
                .flatMap(u -> Mono.just(userMapper.map(u)));
    }

    public Mono<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Mono<UserEntity> updateUser(Long id, UserEntity user) {
        return userRepository.findById(id)
                .flatMap(u -> {
                    user.setUsername(u.getUsername());
                    return userRepository.save(user);
                });
    }
    public Mono<Void> deleteUser(Long userId) {
        System.out.println("IN deleteUser - id: " + userId);
        return userRepository.deleteById(userId);
    }
}
