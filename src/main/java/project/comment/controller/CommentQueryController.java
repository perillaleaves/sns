package project.comment.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.comment.response.CommentResponse;
import project.comment.response.CommentListResponse;
import project.comment.service.CommentQueryService;
import project.common.response.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    public CommentQueryController(CommentQueryService commentQueryService) {
        this.commentQueryService = commentQueryService;
    }

    @GetMapping("/post/{postId}/comments")
    public Response<CommentResponse> getComments(@PathVariable("postId") Long postId,
                                                 @RequestParam(value = "commentId", required = false) Long lastCommentId,
                                                 @PageableDefault Pageable pageable,
                                                 HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        CommentListResponse commentList = commentQueryService.findCommentsByPost(lastCommentId, postId, loginUserId, pageable);
        return new Response<>(new CommentResponse(commentList));
    }

}