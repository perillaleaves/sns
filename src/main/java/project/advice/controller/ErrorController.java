package project.advice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.token.service.TokenQueryService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class ErrorController {

    private final TokenQueryService tokenQueryService;

    public ErrorController(TokenQueryService tokenQueryService) {
        this.tokenQueryService = tokenQueryService;
    }

    @RequestMapping("/api/error")
    public void apiError(HttpServletRequest request) {
        tokenQueryService.certification(request);
    }

}