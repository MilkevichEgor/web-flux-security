package net.emapp.webfluxsecurity.serialization;

import net.emapp.webfluxsecurity.entity.UserEntity;
import net.emapp.webfluxsecurity.entity.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class UserJsonTest {

    @Autowired
    private JacksonTester<UserEntity> json;

    LocalDateTime date = LocalDateTime.of(2023, 10, 26, 15, 30);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    UserEntity userEntity = new UserEntity( 1L, "egor", "password", UserRole.ADMIN, "Egor", "Milkevich", true, date, date);


    public UserJsonTest() throws IOException {
    }

    @Test
    public void UserSerializationTest() throws IOException {
        Assertions.assertThat(json.write(userEntity)).isStrictlyEqualToJson("user_expected.json");
        assertThat(json.write(userEntity)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(userEntity)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.username");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.username").isEqualTo("egor");
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.password");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.password").isEqualTo("password");
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.role");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.role").isEqualTo("ADMIN");
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.firstName");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.firstName").isEqualTo("Egor");
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.lastName");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.lastName").isEqualTo("Milkevich");
        assertThat(json.write(userEntity)).hasJsonPathBooleanValue("@.enabled");
        assertThat(json.write(userEntity)).extractingJsonPathBooleanValue("@.enabled").isEqualTo(true);
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.createdAt");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.createdAt").isEqualTo(date.format(formatter));
        assertThat(json.write(userEntity)).hasJsonPathStringValue("@.updatedAt");
        assertThat(json.write(userEntity)).extractingJsonPathStringValue("@.updatedAt").isEqualTo(date.format(formatter));

    }

    @Test
    public void UserDeserializationTest() throws IOException {

        String expected = """
                {
                  "id": 1,
                  "username": "egor",
                  "password": "password",
                  "role": "ADMIN",
                  "firstName": "Egor",
                  "lastName": "Milkevich",
                  "enabled": true,
                  "createdAt": "2023-10-26T15:30:00",
                  "updatedAt": "2023-10-26T15:30:00"
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(userEntity);
        assertThat(json.parseObject(expected).getId()).isEqualTo(1);
        assertThat(json.parseObject(expected).getUsername()).isEqualTo("egor");
        assertThat(json.parseObject(expected).getPassword()).isEqualTo("password");
        assertThat(json.parseObject(expected).getRole()).isEqualTo(UserRole.ADMIN);
        assertThat(json.parseObject(expected).getFirstName()).isEqualTo("Egor");
        assertThat(json.parseObject(expected).getLastName()).isEqualTo("Milkevich");
        assertThat(json.parseObject(expected).isEnabled()).isEqualTo(true);
        assertThat(json.parseObject(expected).getCreatedAt()).isEqualTo(date);
        assertThat(json.parseObject(expected).getUpdatedAt()).isEqualTo(date);
    }

}
