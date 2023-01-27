package project.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.comment.response.CommentResponse;
import project.comment.response.PostCommentResponse;
import project.post.domain.Post;
import project.advice.exception.PostNotFoundException;
import project.post.repository.PostRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {

    private final PostRepository postRepository;

    public PostCommentResponse findCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return new PostCommentResponse(post.getId(), post.getUser().getUserProfileImage().getUserProfileImageURL(), post.getUser().getNickName(), post.getContent(),
                post.getComments().stream()
                        .map(comment -> new CommentResponse(comment.getId(), comment.getUser().getUserProfileImage().getUserProfileImageURL(), comment.getUser().getNickName(), comment.getContent(), comment.getUpdatedAt())).collect(Collectors.toList()));
    }

}