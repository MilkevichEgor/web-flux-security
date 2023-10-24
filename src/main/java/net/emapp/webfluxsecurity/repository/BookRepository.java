package net.emapp.webfluxsecurity.repository;

import net.emapp.webfluxsecurity.entity.BookEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends R2dbcRepository<BookEntity, Long> {

    Mono<BookEntity> findById(Long id);

}
