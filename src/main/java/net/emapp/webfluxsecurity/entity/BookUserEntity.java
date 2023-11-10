package net.emapp.webfluxsecurity.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("book_users_id")
public class BookUserEntity {

    @Id
    private Long id;

    @Column("book_id")
    @Getter
    @Setter
    private Long bookId;

    @Column("user_id")
    @Getter
    @Setter
    private Long userId;

    // Геттеры и сеттеры
//
//    public BookUserEntity(Long bookId, Long userId) {
//        this.bookId = bookId;
//        this.userId = userId;
//    }
}