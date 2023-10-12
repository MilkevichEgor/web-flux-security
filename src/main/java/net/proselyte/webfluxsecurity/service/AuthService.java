package net.proselyte.webfluxsecurity.service;

import net.proselyte.webfluxsecurity.dto.AuthRequestDto;
import net.proselyte.webfluxsecurity.dto.AuthResponseDto;
import net.proselyte.webfluxsecurity.security.SecurityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private SecurityService securityService;

    public AuthService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public Mono<AuthResponseDto> authenticate(@RequestBody AuthRequestDto dto) {
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
