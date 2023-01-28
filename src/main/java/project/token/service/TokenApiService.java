package project.token.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.token.repository.TokenRepository;

@Service
public class TokenApiService {

    private final TokenRepository tokenRepository;

    public TokenApiService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public void deleteToken(String token) {
        tokenRepository.deleteByAccessToken(token);
    }

}