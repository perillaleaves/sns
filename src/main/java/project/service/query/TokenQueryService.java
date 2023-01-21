package project.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exception.APIError;
import project.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenQueryService {

    private final TokenRepository tokenRepository;

    public void certification(HttpServletRequest request) {
        String token = request.getHeader("token");
        tokenRepository.findByAccessToken(token)
                .orElseThrow(() -> new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다."));
    }

}