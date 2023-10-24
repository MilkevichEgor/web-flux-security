package net.emapp.webfluxsecurity.service;

import net.emapp.webfluxsecurity.entity.BookEntity;
import net.emapp.webfluxsecurity.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<BookEntity> addNewBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public Mono<BookEntity> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Mono<BookEntity> updateBook(Long bookId, BookEntity book) {
        return bookRepository.findById(bookId)
                .flatMap(book1 -> {
                    book1.setTitle(book.getTitle());
                    book1.setAuthor(book.getAuthor());
                    book1.setDescription(book.getDescription());
                    return bookRepository.save(book1);
                });
    }

    public Mono<Void> deleteBook(Long bookId) {
        return bookRepository.deleteById(bookId);
    }

//    public Mono<BookEntity> addComment(Long bookId, String comment) {
//        return bookRepository.findById(bookId)
//                .flatMap(book -> {
//                    book.getComments().put(comment, comment);
//                    return bookRepository.save(book);
//                });
//    }
}

