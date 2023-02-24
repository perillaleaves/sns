package project.comment.controller;

import org.springframework.web.bind.annotation.*;
import project.comment.request.CommentRequest;
import project.comment.service.CommentApiService;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.user.domain.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentApiController {

    private final CommentApiService commentApiService;

    public CommentApiController(CommentApiService commentApiService) {
        this.commentApiService = commentApiService;
    }

    @PostMapping("/{postId}/comment")
    public Response<ValidationResponse> create(@PathVariable("postId") Long postId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        User loginUser = (User) httpServletRequest.getAttribute("user");
        commentApiService.create(postId, request, loginUser);
        return new Response<>(new ValidationResponse("Create", "댓글 작성"));
    }

    @PutMapping("/comment/{commentId}")
    public Response<ValidationResponse> update(@PathVariable("commentId") Long commentId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        commentApiService.update(commentId, request, loginUserId);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @DeleteMapping("/comment/{commentId}")
    public Response<ValidationResponse> delete(@PathVariable("commentId") Long commentId, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        commentApiService.delete(commentId, loginUserId);
        return new Response<>(new ValidationResponse("Delete", "댓글 삭제"));
    }

}