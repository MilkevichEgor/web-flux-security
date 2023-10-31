package net.emapp.webfluxsecurity.serialization;

import net.emapp.webfluxsecurity.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {

    @Autowired
    private JacksonTester<BookEntity> json;

    public BookJsonTest() throws IOException {
    }

    //    @Test
//    public void MyFirstTest() {
//        assertThat(42).isEqualTo(42);
//    }
    @Test
    public void BookSerializationTest() throws IOException {

        BookEntity bookEntity = new BookEntity( 1L, "Dune", "Gerbert", "Description");
        assertThat(json.write(bookEntity)).isStrictlyEqualToJson("book_expected.json");
        assertThat(json.write(bookEntity)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(bookEntity)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(json.write(bookEntity)).hasJsonPathStringValue("@.title");
        assertThat(json.write(bookEntity)).extractingJsonPathStringValue("@.title").isEqualTo("Dune");
        assertThat(json.write(bookEntity)).hasJsonPathStringValue("@.author");
        assertThat(json.write(bookEntity)).extractingJsonPathStringValue("@.author").isEqualTo("Gerbert");
        assertThat(json.write(bookEntity)).hasJsonPathStringValue("@.description");
        assertThat(json.write(bookEntity)).extractingJsonPathStringValue("@.description").isEqualTo("Description");
    }

    @Test
    public void BookDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 1,
                    "title": "Dune",
                    "author": "Gerbert",
                    "description": "Description"
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new BookEntity( 1L, "Dune", "Gerbert", "Description"));
        assertThat(json.parseObject(expected).getId()).isEqualTo(1);
        assertThat(json.parseObject(expected).getTitle()).isEqualTo("Dune");
        assertThat(json.parseObject(expected).getAuthor()).isEqualTo("Gerbert");
        assertThat(json.parseObject(expected).getDescription()).isEqualTo("Description");
    }
}
