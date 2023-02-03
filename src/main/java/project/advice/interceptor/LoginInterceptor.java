package project.advice.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    public LoginInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String token = request.getHeader("token");
        Optional<UserToken> accessToken = tokenRepository.findByAccessToken(token);

        if (accessToken.isEmpty()) {
            request.getRequestDispatcher("/api/error").forward(request, response);
            return false;
        }

        request.setAttribute("user", accessToken.get().getUser());
        request.setAttribute("userId", accessToken.get().getUser().getId());
        return true;
    }

}