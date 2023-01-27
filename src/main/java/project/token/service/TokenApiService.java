package project.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.token.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class TokenApiService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void deleteToken(String token) {
        tokenRepository.deleteByAccessToken(token);
    }

}