package project.post.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.post.request.PostRequest;
import project.post.service.PostApiService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class PostApiController {

    private final PostApiService postApiService;

    public PostApiController(PostApiService postApiService) {
        this.postApiService = postApiService;
    }

    @PostMapping("/post")
    public Response<ValidationResponse> create(@RequestParam(value = "image") List<MultipartFile> images, PostRequest request, HttpServletRequest httpServletRequest) throws IOException {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        postApiService.create(loginUserId, request, images, "post");
        return new Response<>(new ValidationResponse("Create", "게시글 작성"));
    }

    @PutMapping("/post/{postId}")
    public Response<ValidationResponse> update(@PathVariable("postId") Long postId, @RequestBody PostRequest request, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        postApiService.update(postId, loginUserId, request);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @DeleteMapping("/post/{postId}")
    public Response<ValidationResponse> delete(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        postApiService.delete(postId, loginUserId);
        return new Response<>(new ValidationResponse("Delete", "게시글 삭제"));
    }
}