package project.comment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.comment.response.CommentListResponse;
import project.comment.response.PostAndCommentsResponse;
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
    public Response<CommentListResponse> getComments(@PathVariable("postId") Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PostAndCommentsResponse commentList = commentQueryService.findCommentsByPost(postId, userId);
        return new Response<>(new CommentListResponse(commentList));
    }

}