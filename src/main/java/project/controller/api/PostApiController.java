package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.request.PostRequest;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.PostApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostApiService postApiService;

    @PostMapping("/post")
    public Response<ValidationResponse> create(@RequestBody PostRequest request, HttpServletRequest httpServletRequest) {
        postApiService.create(httpServletRequest, request);
        return new Response<>(new ValidationResponse("Create", "게시글 작성"));
    }

    @PutMapping("/post/{postId}")
    public Response<ValidationResponse> update(@PathVariable("postId") Long postId, @RequestBody PostRequest request, HttpServletRequest httpServletRequest) {
        postApiService.update(postId, request, httpServletRequest);
        return new Response<>(new ValidationResponse("Fix", "수정 완료"));
    }

    @DeleteMapping("post/{postId}")
    public Response<ValidationResponse> delete(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        postApiService.delete(postId, httpServletRequest);
        return new Response<>(new ValidationResponse("Delete", "게시글 삭제"));
    }
}