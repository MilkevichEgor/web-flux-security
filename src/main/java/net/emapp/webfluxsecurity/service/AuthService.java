package net.emapp.webfluxsecurity.service;

import net.emapp.webfluxsecurity.dto.AuthRequestDto;
import net.emapp.webfluxsecurity.dto.AuthResponseDto;
import net.emapp.webfluxsecurity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private SecurityService securityService;

    public AuthService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public Mono<AuthResponseDto> authenticateUser(@RequestBody AuthRequestDto dto) {
        return securityService.authenticate(dto.getUsername(), dto.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponseDto.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }
}
