package net.emapp.webfluxsecurity.config;

import lombok.RequiredArgsConstructor;
import net.emapp.webfluxsecurity.errorhandling.AppErrorAttributes;
import net.emapp.webfluxsecurity.mapper.UserMapper;
import net.emapp.webfluxsecurity.repository.UserRepository;
import net.emapp.webfluxsecurity.security.AuthenticationManager;
import net.emapp.webfluxsecurity.service.UserService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Sinks;

@TestConfiguration
public class WebSecurityConfigTest {

//    @Bean
//    public Sinks.Many<String> sink() {
//        return Mockito.mock(Sinks.Many.class);
//    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public AppErrorAttributes appErrorAttributes() {
        return Mockito.mock(AppErrorAttributes.class);
    }

    @Bean
    public UserMapper userMapper() {
        return Mockito.mock(UserMapper.class);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return Mockito.mock(AuthenticationManager.class);
    }
}

