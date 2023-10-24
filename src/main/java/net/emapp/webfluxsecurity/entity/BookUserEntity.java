package net.emapp.webfluxsecurity.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("book_users_id")
public class BookUserEntity {

    @Id
    private Long id;

    @Column("book_id")
    private Long bookId;

    @Column("user_id")
    private Long userId;

    // Геттеры и сеттеры

    public BookUserEntity(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }
}