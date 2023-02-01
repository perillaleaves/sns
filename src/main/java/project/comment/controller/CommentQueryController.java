package project.comment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.comment.response.CommentListResponse;
import project.comment.response.PostCommentResponse;
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
        String token = request.getHeader("token");
        PostCommentResponse commentsByPost = commentQueryService.findCommentsByPost(postId, token);
        return new Response<>(new CommentListResponse(commentsByPost));
    }

}