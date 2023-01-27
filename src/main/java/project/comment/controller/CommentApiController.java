package project.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.comment.service.CommentApiService;
import project.comment.request.CommentRequest;
import project.response.Response;
import project.response.ValidationResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentApiService commentApiService;

    @PostMapping("/{postId}/comment")
    public Response<ValidationResponse> create(@PathVariable("postId") Long postId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        commentApiService.create(postId, request, token);
        return new Response<>(new ValidationResponse("Create", "댓글 작성"));
    }

    @PutMapping("/comment/{commentId}")
    public Response<ValidationResponse> update(@PathVariable("commentId") Long commentId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        commentApiService.update(commentId, request, token);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @DeleteMapping("/comment/{commentId}")
    public Response<ValidationResponse> delete(@PathVariable("commentId") Long commentId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        commentApiService.delete(commentId, token);
        return new Response<>(new ValidationResponse("Delete", "댓글 삭제"));
    }

}