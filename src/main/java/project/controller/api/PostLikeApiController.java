package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.PostLikeApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostLikeApiController {

    private final PostLikeApiService postLikeApiService;

    @PostMapping("/post/{postId}/toggle")
    public Response<ValidationResponse> likeToggle(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        postLikeApiService.flipLike(postId, httpServletRequest);
        return new Response<>(new ValidationResponse("Toggle", "토글"));
    }

}