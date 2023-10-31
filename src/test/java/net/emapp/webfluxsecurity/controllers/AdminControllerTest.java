package net.emapp.webfluxsecurity.controllers;

import net.emapp.webfluxsecurity.config.WebSecurityConfigTest;
import net.emapp.webfluxsecurity.config.WebSecurityConfig;
import net.emapp.webfluxsecurity.controller.AdminController;
import net.emapp.webfluxsecurity.dto.UserDto;
import net.emapp.webfluxsecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@WebFluxTest(AdminController.class)
@Import(WebSecurityConfig.class)
@SpringJUnitConfig(WebSecurityConfigTest.class)
public class AdminControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @MockBean
    private  Sinks.Many<String> sink;

    @Test
    void getUserInfoTest() {
        Mockito.when(userService.getUserInfo(1L)).thenReturn(Mono.just(new UserDto()));

        webTestClient.mutateWith(csrf())
                .mutateWith(mockUser().authorities("ADMIN"))
                .get().uri("/api/admin/info/{userId}", 1)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithAnonymousUser
    void getUserInfoTestUnauthorized() {
        webTestClient.mutateWith(csrf())
                .get().uri("/api/admin/info/{userId}", 1)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void getUserInfoTestIfNotAdmin() throws Exception {
        webTestClient.mutateWith(csrf())
                .mutateWith(mockUser().authorities("USER"))
                .get().uri("/api/admin/info/{userId}", 1)
                .exchange()
                .expectStatus().isForbidden();

    }

    @Test
    void deleteUserTest() {
        Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(Mono.empty());

        webTestClient.mutateWith(csrf())
                .mutateWith(mockUser().authorities("ADMIN"))
                .delete().uri("/api/admin/delete/{userId}", 1L)
                .exchange()
                .expectStatus().isNoContent();
    }
}
