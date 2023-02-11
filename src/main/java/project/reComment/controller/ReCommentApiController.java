package project.reComment.controller;

import org.springframework.web.bind.annotation.*;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.reComment.request.ReCommentRequest;
import project.reComment.service.ReCommentApiService;
import project.user.domain.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReCommentApiController {

    private final ReCommentApiService reCommentApiService;

    public ReCommentApiController(ReCommentApiService reCommentApiService) {
        this.reCommentApiService = reCommentApiService;
    }

    @PostMapping("/comment/{commentId}/recomment")
    public Response<ValidationResponse> create(@PathVariable("commentId") Long commentId, @RequestBody ReCommentRequest request, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        reCommentApiService.create(commentId, request, user);
        return new Response<>(new ValidationResponse("Create", "댓글 작성"));
    }

    @PutMapping("/recomment/{reCommentId}")
    public Response<ValidationResponse> update(@PathVariable("reCommentId") Long reCommentId, @RequestBody ReCommentRequest request, HttpServletRequest httpServletRequest) {
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        reCommentApiService.update(reCommentId, request, userId);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @DeleteMapping("/recomment/{reCommentId}")
    public Response<ValidationResponse> delete(@PathVariable("reCommentId") Long reCommentId, HttpServletRequest httpServletRequest) {
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        reCommentApiService.delete(reCommentId, userId);
        return new Response<>(new ValidationResponse("Delete", "댓글 삭제"));
    }

}