package project.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Comment;
import project.domain.post.Post;
import project.repository.CommentRepository;
import project.repository.PostRepository;
import project.response.CommentResponse;
import project.response.PostCommentResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public PostCommentResponse findCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<Comment> comments = commentRepository.findByPost(post);
        return new PostCommentResponse(post.getId(), post.getUser().getProfileImage(), post.getUser().getName(), post.getContent(),
                comments.stream()
                        .map(comment -> new CommentResponse(comment.getId(), comment.getUser().getProfileImage(), comment.getUser().getName(), comment.getContent(), comment.getUpdatedAt())).collect(Collectors.toList()));
    }

}