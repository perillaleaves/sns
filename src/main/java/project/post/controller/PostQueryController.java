package project.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.post.response.PostDetailResponse;
import project.post.response.PostResponse;
import project.post.service.PostQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryService postQueryService;

    @GetMapping("/post/{postId}")
    public Response<PostResponse> getPostDetail(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        PostDetailResponse postDetail = postQueryService.findPostDetail(postId, token);
        return new Response<>(new PostResponse(postDetail));
    }

}