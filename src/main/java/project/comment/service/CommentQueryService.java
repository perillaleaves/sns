package project.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.comment.domain.Comment;
import project.comment.repository.CommentRepository;
import project.comment.response.CommentResponse;
import project.comment.response.PostAndCommentsResponse;
import project.post.repository.PostRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;
import project.user.response.UserSimpleResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentQueryService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentQueryService(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public PostAndCommentsResponse findCommentsByPost(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserSimpleResponse userSimpleResponse = new UserSimpleResponse(
                user.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + user.getUserProfileImage().getUserProfileImageURL(),
                user.getNickName());

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentResponse> commentResponses = comments.stream()
                .map(c -> new CommentResponse(c.getId(),
                        "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + c.getUser().getUserProfileImage().getUserProfileImageURL(),
                        c.getUser().getNickName(),
                        c.getContent(),
                        commentRepository.existsCommentByIdAndUserId(c.getId(), user.getId()),
                        c.getUpdatedAt())).collect(Collectors.toList());

        return new PostAndCommentsResponse(userSimpleResponse, commentResponses);
    }

}