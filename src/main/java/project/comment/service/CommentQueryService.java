package project.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.comment.repository.CommentRepository;
import project.comment.response.CommentResponse;
import project.comment.response.PostCommentResponse;
import project.post.domain.Post;
import project.post.repository.PostRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentQueryService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentQueryService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public PostCommentResponse findCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return new PostCommentResponse(post.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getContent(), post.getUpdatedAt(),
                post.getComments().stream()
                        .map(comment -> new CommentResponse(comment.getId(),
                                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + comment.getUser().getUserProfileImage().getUserProfileImageURL(),
                                comment.getUser().getNickName(),
                                comment.getContent(),
                                comment.getUpdatedAt())).collect(Collectors.toSet()));
    }

}