package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.repository.TokenRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenApiService {

    private final TokenRepository tokenRepository;

    public void deleteToken(String token) {
        tokenRepository.deleteByAccessToken(token);
    }

}
