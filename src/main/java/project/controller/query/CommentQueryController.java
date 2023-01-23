package project.controller.query;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.response.CommentResponse;
import project.response.PostCommentResponse;
import project.response.Response;
import project.service.query.CommentQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @GetMapping("/post/{postId}/comments")
    public Response<PostCommentResponse> getComments(@PathVariable("postId") Long postId) {
        PostCommentResponse commentsByPost = commentQueryService.findCommentsByPost(postId);
        List<CommentResponse> commentList = commentsByPost.getCommentResponses();
        return new Response<>(new PostCommentResponse(commentsByPost.getPostId(), commentsByPost.getUserProfileImage(), commentsByPost.getUserName(), commentsByPost.getPostContent(),
                commentList));
    }

}