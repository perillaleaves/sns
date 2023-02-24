package project.comment.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.comment.repository.CommentRepository;
import project.comment.repository.CommentRepositoryImpl;
import project.comment.response.CommentListDetailResponse;
import project.comment.response.CommentListResponse;
import project.comment.response.CommentSliceResponse;
import project.commentLike.repository.CommentLikeRepository;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.user.response.UserSimpleResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final CommentRepositoryImpl commentRepositoryImpl;
    private final CommentLikeRepository commentLikeRepository;
    private final PostRepository postRepository;
    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public CommentQueryService(CommentRepository commentRepository, CommentRepositoryImpl commentRepositoryImpl, CommentLikeRepository commentLikeRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentRepositoryImpl = commentRepositoryImpl;
        this.commentLikeRepository = commentLikeRepository;
        this.postRepository = postRepository;
    }

    public CommentListResponse findCommentsByPost(Long lastCommentId, Long postId, Long loginUserId, Pageable pageable) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        UserSimpleResponse userSimpleResponse = new UserSimpleResponse(
                post.getUser().getId(),
                s3Url + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getName(),
                post.getUser().getNickName());

        List<CommentSliceResponse> commentList = commentRepositoryImpl.findCommentList(lastCommentId, postId, pageable);
        List<CommentListDetailResponse> commentDetailList = commentList.stream()
                .map(c -> new CommentListDetailResponse(
                        c.getCommentId(),
                        s3Url + c.getUserProfileImageUrl(),
                        c.getUserName(),
                        c.getNickName(),
                        c.getContent(),
                        c.getCommentLikeSize(),
                        c.getReCommentSize(),
                        commentLikeRepository.existsCommentLikeByCommentIdAndUserId(c.getCommentId(), loginUserId),
                        commentRepository.existsCommentByIdAndUserId(c.getCommentId(), loginUserId),
                        c.getUpdatedAt()))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (commentList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }
        return new CommentListResponse(userSimpleResponse, commentDetailList, hasNext);
    }

}