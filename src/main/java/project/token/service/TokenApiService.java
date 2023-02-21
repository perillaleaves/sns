package project.token.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.token.repository.TokenRepository;

@Service
@Transactional
public class TokenApiService {

    public void deleteToken(String token) {
        tokenRepository.deleteByAccessToken(token);
    }

    private final TokenRepository tokenRepository;

    public TokenApiService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

}