package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.repository.TokenRepository;
import project.request.LogoutAndTokenRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenAPIService {

    private final TokenRepository tokenRepository;

    public void deleteToken(LogoutAndTokenRequest request) {
        tokenRepository.deleteByAccessToken(request.getAccessToken());
    }


}
