package project.controller.query;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.response.CommentListResponse;
import project.response.PostCommentResponse;
import project.response.Response;
import project.service.query.CommentQueryService;

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