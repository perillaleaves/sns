package project.post.controller;

import org.springframework.web.bind.annotation.*;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.post.request.PostRequest;
import project.post.service.PostApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PostApiController {

    private final PostApiService postApiService;

    public PostApiController(PostApiService postApiService) {
        this.postApiService = postApiService;
    }

    @PostMapping("/post")
    public Response<ValidationResponse> create(@RequestBody PostRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        postApiService.create(request, token);
        return new Response<>(new ValidationResponse("Create", "게시글 작성"));
    }

    @PutMapping("/post/{postId}")
    public Response<ValidationResponse> update(@PathVariable("postId") Long postId, @RequestBody PostRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        postApiService.update(postId, request, token);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @DeleteMapping("/post/{postId}")
    public Response<ValidationResponse> delete(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        postApiService.delete(postId, token);
        return new Response<>(new ValidationResponse("Delete", "게시글 삭제"));
    }
}