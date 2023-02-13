package project.comment.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.comment.repository.CommentRepository;
import project.comment.repository.CommentRepositoryImpl;
import project.comment.response.CommentListDetailResponse;
import project.comment.response.CommentSliceResponse;
import project.comment.response.CommentListResponse;
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
    private final PostRepository postRepository;

    public CommentQueryService(CommentRepository commentRepository, CommentRepositoryImpl commentRepositoryImpl, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentRepositoryImpl = commentRepositoryImpl;
        this.postRepository = postRepository;
    }

    String S3Url = "https://s3.ap-northeast-2.amazonaws.com/mullae.com/";

    public CommentListResponse findCommentsByPost(Long postId, Long userId, Pageable pageable) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        UserSimpleResponse userSimpleResponse = new UserSimpleResponse(
                post.getUser().getId(),
                S3Url + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getName(),
                post.getUser().getNickName());

        Slice<CommentSliceResponse> commentList = commentRepositoryImpl.findCommentList(postId, pageable);
        List<CommentListDetailResponse> commentDetailList = commentList.stream()
                .map(c -> new CommentListDetailResponse(
                        c.getCommentId(),
                        S3Url + c.getUserProfileImageUrl(),
                        c.getUserName(),
                        c.getNickName(),
                        c.getContent(),
                        c.getReCommentSize(),
                        c.getReCommentSize() > 0,
                        commentRepository.existsCommentByIdAndUserId(c.getCommentId(), userId),
                        c.getUpdatedAt()))
                .collect(Collectors.toList());

        return new CommentListResponse(userSimpleResponse, commentDetailList, commentList.hasNext());
    }

}