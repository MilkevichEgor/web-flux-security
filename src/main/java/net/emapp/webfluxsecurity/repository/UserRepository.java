package net.emapp.webfluxsecurity.repository;

import net.emapp.webfluxsecurity.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
    Mono<UserEntity> findByUsername(String username);

//    @Query("")
//    Mono<UserEntity> findOrCreate(String username);

}
