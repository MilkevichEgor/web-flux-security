package net.emapp.webfluxsecurity.service;

import net.emapp.webfluxsecurity.repository.CommentRepository;
import net.emapp.webfluxsecurity.entity.CommentEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class CommentService {

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private final CommentRepository commentRepository;

    public Mono<CommentEntity> addComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }
}
