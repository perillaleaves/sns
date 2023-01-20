package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.query.TokenQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ErrorController {

    private final TokenQueryService tokenQueryService;

    @PostMapping("/api/error")
    public void apiError(HttpServletRequest request) {
        tokenQueryService.certification(request);
    }

}
