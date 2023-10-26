package net.emapp.webfluxsecurity.controller.bookController;

import net.emapp.webfluxsecurity.entity.BookEntity;
import net.emapp.webfluxsecurity.repository.BookRepository;
import net.emapp.webfluxsecurity.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;
    private final Sinks.Many<String> sink;

    public BookController(BookRepository bookRepository,
                          BookService bookService,
                          Sinks.Many<String> sink
                          ) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.sink = sink;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookEntity> addBook(@RequestBody BookEntity book) {
        return bookService.addNewBook(book);
    }

    @GetMapping("/{bookId}")
    public Mono<ResponseEntity<BookEntity>> getBookById(@PathVariable Long bookId) {
        return bookRepository.findById(bookId)
                .flatMap(book -> Mono.just(ResponseEntity.ok(book)))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PutMapping("/update/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BookEntity> updateBook(@PathVariable Long bookId, @RequestBody BookEntity book) {
        sink.emitNext("Book updated", Sinks.EmitFailureHandler.FAIL_FAST);
        return bookService.updateBook(bookId, book);
    }

//    @DeleteMapping("/{bookId}")
//    public Mono<Void> deleteBook(@PathVariable Long bookId) {
//        return bookService.deleteBook(bookId);
//    }
}

