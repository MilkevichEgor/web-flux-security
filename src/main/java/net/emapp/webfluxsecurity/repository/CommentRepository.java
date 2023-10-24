package net.emapp.webfluxsecurity.repository;

import net.emapp.webfluxsecurity.entity.CommentEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CommentRepository extends R2dbcRepository<CommentEntity, Long> {
}
