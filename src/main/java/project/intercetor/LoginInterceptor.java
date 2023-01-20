package project.intercetor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import project.domain.member.UserToken;
import project.repository.TokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String token = request.getHeader("token");
        Optional<UserToken> accessToken = tokenRepository.findByAccessToken(token);

        if(accessToken.isEmpty()){
            request.getRequestDispatcher("/api/error").forward(request, response);
            return false;
        }

        request.setAttribute("memberId", accessToken.get().getMemberId());
        return true;
    }

}