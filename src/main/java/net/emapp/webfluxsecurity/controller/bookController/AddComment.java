package net.emapp.webfluxsecurity.controller.bookController;

import net.emapp.webfluxsecurity.entity.CommentEntity;
import net.emapp.webfluxsecurity.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/comments")
public class AddComment {

    private CommentService commentService;

    public AddComment(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public Mono<CommentEntity> addComment(CommentEntity comment) {
        return commentService.addComment(comment);
    }
}
