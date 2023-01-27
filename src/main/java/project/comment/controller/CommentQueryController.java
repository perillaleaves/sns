package project.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.comment.response.CommentListResponse;
import project.comment.response.PostCommentResponse;
import project.comment.service.CommentQueryService;
import project.response.Response;

@RestController
@RequiredArgsConstructor
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @GetMapping("/post/{postId}/comments")
    public Response<CommentListResponse> getComments(@PathVariable("postId") Long postId) {
        PostCommentResponse commentsByPost = commentQueryService.findCommentsByPost(postId);
        return new Response<>(new CommentListResponse(commentsByPost));
    }

}