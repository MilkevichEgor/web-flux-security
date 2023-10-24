package net.emapp.webfluxsecurity;

import net.emapp.webfluxsecurity.entity.BookEntity;
import net.emapp.webfluxsecurity.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@SpringBootTest
public class WebFluxSecurityApplicationTests {
	@Autowired
	private ApplicationContext context;

	private WebTestClient webClient;

	@MockBean
	private BookService bookService;

	@BeforeEach
	void setup() {
		webClient = WebTestClient.bindToApplicationContext(context)
				.apply(springSecurity())
				.configureClient()
				.build();
	}

	@Test
	@WithMockUser
	void createBookTest() {
		Mockito.when(bookService.addNewBook(Mockito.any())).thenReturn(Mono.justOrEmpty(new BookEntity(1L, "New Book", "New Author", "New Description")));

		webClient.mutateWith(csrf())
				.post().uri("/api/books/add")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new BookEntity(1L, "New Book", "New Author", "New Description"))
				.exchange()
				.expectStatus().isCreated();

	}

	@Test
	@WithAnonymousUser
	void createBookTestUnauthorized() {

		webClient.mutateWith(csrf())
				.post().uri("/api/books/add")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new BookEntity(1L, "New Book", "New Author", "New Description"))
				.exchange()
				.expectStatus().isUnauthorized();
	}
}
