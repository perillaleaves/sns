package project.post.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.post.response.NewsFeedListResponse;
import project.post.response.NewsFeedResponse;
import project.post.response.PostDetailResponse;
import project.post.response.PostResponse;
import project.post.service.PostQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PostQueryController {

    private final PostQueryService postQueryService;

    public PostQueryController(PostQueryService postQueryService) {
        this.postQueryService = postQueryService;
    }

    @GetMapping("/post/{postId}")
    public Response<PostResponse> getPostDetail(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        PostDetailResponse postDetail = postQueryService.findPostDetail(postId, loginUserId);
        return new Response<>(new PostResponse(postDetail));
    }

    @GetMapping("/posts")
    public Response<NewsFeedResponse> getPostList(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
                                                  @RequestParam(value = "postId", required = false) Long lastPostId,
                                                  HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        NewsFeedListResponse postList = postQueryService.findPostList(lastPostId, loginUserId, pageable);
        return new Response<>(new NewsFeedResponse(postList));
    }

}