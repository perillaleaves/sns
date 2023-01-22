package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.request.CommentRequest;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.CommentApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentApiService commentApiService;

    @PostMapping("/{postId}/comment")
    public Response<ValidationResponse> create(@PathVariable("postId") Long postId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        commentApiService.create(postId, request, httpServletRequest);
        return new Response<>(new ValidationResponse("Create", "댓글 작성"));
    }

    @PutMapping("/comment/{commentId}")
    public Response<ValidationResponse> update(@PathVariable("commentId") Long commentId, @RequestBody CommentRequest request, HttpServletRequest httpServletRequest) {
        commentApiService.update(commentId, request, httpServletRequest);
        return new Response<>(new ValidationResponse("Fix", "수정 완료"));
    }

    @DeleteMapping("/comment/{commentId}")
    public Response<ValidationResponse> delete(@PathVariable("commentId") Long commentId, HttpServletRequest httpServletRequest) {
        commentApiService.delete(commentId, httpServletRequest);
        return new Response<>(new ValidationResponse("Delete", "댓글 삭제"));
    }

}