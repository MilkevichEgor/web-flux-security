package net.emapp.webfluxsecurity.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("comments")
public class CommentEntity {
    @Id
    private Long id;
    private String text;
//    @Getter
//    @Setter
//    private Long bookId;
//    @Getter
//    @Setter
//    private Long userId;
}
