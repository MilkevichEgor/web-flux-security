package net.emapp.webfluxsecurity;

import net.emapp.webfluxsecurity.config.WebSecurityConfig;
import net.emapp.webfluxsecurity.controller.bookController.BookController;
import net.emapp.webfluxsecurity.entity.BookEntity;
import net.emapp.webfluxsecurity.entity.UserRole;
import net.emapp.webfluxsecurity.errorhandling.AppErrorAttributes;
import net.emapp.webfluxsecurity.repository.BookRepository;
import net.emapp.webfluxsecurity.security.AuthenticationManager;
import net.emapp.webfluxsecurity.service.BookService;
import net.emapp.webfluxsecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest(BookController.class)
@Import(WebSecurityConfig.class)
class BookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private  Sinks.Many<String> sink;

    @MockBean
    private AppErrorAttributes appErrorAttributes;

    @MockBean
    private AuthenticationManager authenticationManager;


    @Test
    @WithMockUser
    void createBookTest() {
        var mock = Mockito.when(bookService.addNewBook(Mockito.any())).thenReturn(Mono.justOrEmpty(new BookEntity(1L, "New Book", "New Author", "New Description")));

        webTestClient.mutateWith(csrf())
                .post().uri("/api/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mock)
                .exchange()
                .expectStatus().isCreated();

    }
    @Test
    @WithAnonymousUser
    void createBookTestUnauthorized() {

        webTestClient.mutateWith(csrf())
                .post().uri("/api/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new BookEntity(1L, "New Book", "New Author", "New Description"))
                .exchange()
                .expectStatus().isUnauthorized();
    }


}