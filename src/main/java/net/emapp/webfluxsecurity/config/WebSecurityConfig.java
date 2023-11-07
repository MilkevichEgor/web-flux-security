package net.emapp.webfluxsecurity.config;

import lombok.extern.slf4j.Slf4j;
import net.emapp.webfluxsecurity.entity.UserRole;
import net.emapp.webfluxsecurity.security.AuthenticationManager;
import net.emapp.webfluxsecurity.security.BearerTokenServerAuthenticationConverter;
import net.emapp.webfluxsecurity.security.JwtHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {


    @Value("${jwt.secret}")
    private String secret;

    private final String [] publicRoutes = {
        "/api/auth/register", "/api/auth/login", "/ws/message", "/api/send"
//            , "/api/**"
    };

    private final String [] adminRoutes = {
        "/api/admin/**"
    };

        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthenticationManager authenticationManager) {
            http
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                    .authorizeExchange((exchanges) -> exchanges
                            .pathMatchers(HttpMethod.OPTIONS).permitAll()
                            .pathMatchers(publicRoutes).permitAll()
                            .pathMatchers(adminRoutes).hasAuthority(UserRole.ADMIN.name())
                            .anyExchange()
                            .authenticated()
                    )
                .exceptionHandling(exception -> exception.authenticationEntryPoint((swe , e) -> {
                    log.error("IN securityWebFilterChain - unauthorized error: {}", e.getMessage());
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                })
                .accessDeniedHandler((swe, e) -> {
                    log.error("IN securityWebFilterChain - unauthorized error: {}", e.getMessage());
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                }));

        http.addFilterAt(bearerAuthenticationFilter(authenticationManager), SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

    private AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authenticationManager);
        bearerAuthenticationFilter.setServerAuthenticationConverter(new BearerTokenServerAuthenticationConverter(new JwtHandler(secret)));
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return bearerAuthenticationFilter;
    }

}
