package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.domain.post.Post;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.LikeApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeApiService likeApiService;

    @PostMapping("/post/{postId}/like")
    public Response<ValidationResponse> like(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        likeApiService.flipLike(postId, httpServletRequest);
        return new Response<>(new ValidationResponse("Like", "좋아요"));
    }

}
