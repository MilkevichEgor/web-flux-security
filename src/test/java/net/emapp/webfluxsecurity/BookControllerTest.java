package net.emapp.webfluxsecurity;

import net.emapp.webfluxsecurity.config.WebSecurityConfig;
import net.emapp.webfluxsecurity.entity.BookEntity;
import net.emapp.webfluxsecurity.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;


import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;


//@WebFluxTest(BookControllerTest.class)
////@Import(WebSecurityConfig.class)
//class BookControllerTest {
//
//    @Autowired
//    WebTestClient webClient;
//
//    @MockBean
//    BookService bookService;
//
//    @Autowired
//    ApplicationContext context;
//
//    @Test
//    @WithMockUser
//    void createBookTest() {
//        Mockito.when(bookService.addNewBook(Mockito.any())).thenReturn(Mono.justOrEmpty(new BookEntity(1L, "New Book", "New Author", "New Description")));
//
//        webClient.mutateWith(csrf())
//                .post().uri("/api/books/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(new BookEntity(1L, "New Book", "New Author", "New Description"))                .exchange()
//                .expectStatus().isCreated();
//
//    }
//
//}