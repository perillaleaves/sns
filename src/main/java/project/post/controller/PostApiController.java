package project.post.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.s3.S3Service;
import project.post.request.PostRequest;
import project.post.service.PostApiService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class PostApiController {

    private final PostApiService postApiService;
    private final S3Service s3Service;

    public PostApiController(PostApiService postApiService, S3Service s3Service) {
        this.postApiService = postApiService;
        this.s3Service = s3Service;
    }

    @PostMapping("/post")
    public Response<ValidationResponse> create(@RequestParam(value = "image") List<MultipartFile> images, PostRequest request, HttpServletRequest httpServletRequest) throws IOException {
        String token = httpServletRequest.getHeader("token");
        postApiService.create(request, token, images, "images");
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