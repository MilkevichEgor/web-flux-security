package net.emapp.webfluxsecurity.controllers;

import net.emapp.webfluxsecurity.config.WebSecurityConfig;
import net.emapp.webfluxsecurity.config.WebSecurityConfigTest;
import net.emapp.webfluxsecurity.controller.AuthController;
import net.emapp.webfluxsecurity.dto.UserDto;
import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.entity.UserRole;
import net.emapp.webfluxsecurity.mapper.UserMapper;
import net.emapp.webfluxsecurity.repository.UserRepository;
import net.emapp.webfluxsecurity.service.AuthService;
import net.emapp.webfluxsecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@WebFluxTest(AuthController.class)
//@Import(WebSecurityConfig.class)
//@SpringJUnitConfig(WebSecurityConfigTest.class)
//public class AuthControllerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private AuthService authService;
//
//    @MockBean
//    private Sinks.Many<String> sink;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private UserMapper userMapper;
//
//    @Test
//    @WithMockUser
//    public void testRegisterUser() {
//        UserDto userDto = new UserDto();
//        userDto.setId(1L);
//        userDto.setUsername("username");
//        userDto.setPassword("password");
//        userDto.setRole(UserRole.USER);
//        userDto.setFirstName("firstName");
//        userDto.setLastName("lastName");
//        userDto.setEnabled(true);
//        userDto.setCreatedAt(LocalDateTime.now());
//        userDto.setUpdatedAt(LocalDateTime.now());
//
//        UserEntity userEntity = new UserEntity(1L, "username", "password", UserRole.USER, "firstName", "lastName", true, LocalDateTime.now(), LocalDateTime.now()); // Создайте объект UserEntity для теста
//        userEntity.setPassword("password"); // Установите необходимые свойства
//
//        // Мокируйте вызовы сервиса и верните ожидаемый результат
//        when(userService.registerUser(any(UserEntity.class))).thenReturn(Mono.just(userEntity));
//
//        webTestClient.post()
//                .uri("/api/auth/register")
//                .bodyValue(userDto)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(UserDto.class);
//
//    }
//}
