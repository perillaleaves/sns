package project.controller.query;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.response.PostDetailResponse;
import project.response.PostResponse;
import project.response.Response;
import project.service.query.PostQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryService postQueryService;

    @GetMapping("/post/{postId}")
    public Response<PostResponse> getPostDetail(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        PostDetailResponse postDetail = postQueryService.findPostDetail(postId, httpServletRequest);
        return new Response<>(new PostResponse(postDetail));
    }

}