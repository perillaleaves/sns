package project.reComment.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.reComment.repository.ReCommentRepository;
import project.reComment.repository.ReCommentRepositoryImpl;
import project.reComment.response.ReCommentListDetailResponse;
import project.reComment.response.ReCommentListResponse;
import project.reComment.response.ReCommentSliceResponse;
import project.reCommentLike.repository.ReCommentLikeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReCommentQueryService {

    private final ReCommentRepository reCommentRepository;
    private final ReCommentRepositoryImpl reCommentRepositoryImpl;
    private final ReCommentLikeRepository reCommentLikeRepository;
    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public ReCommentQueryService(ReCommentRepository reCommentRepository, ReCommentRepositoryImpl reCommentRepositoryImpl, ReCommentLikeRepository reCommentLikeRepository) {
        this.reCommentRepository = reCommentRepository;
        this.reCommentRepositoryImpl = reCommentRepositoryImpl;
        this.reCommentLikeRepository = reCommentLikeRepository;
    }

    public ReCommentListResponse findReCommentList(Long lastReCommentId, Long commentId, Long loginUserId, Pageable pageable) {
        List<ReCommentSliceResponse> reCommentList = reCommentRepositoryImpl.findReCommentList(lastReCommentId, commentId, pageable);
        List<ReCommentListDetailResponse> reCommentDetailList = reCommentList.stream()
                .map(r -> new ReCommentListDetailResponse(
                        r.getReCommentId(),
                        s3Url + r.getUserProfileImageUrl(),
                        r.getUserName(),
                        r.getNickName(),
                        r.getContent(),
                        r.getReCommentLikeSize(),
                        reCommentLikeRepository.existsReCommentLikeByReCommentIdAndUserId(r.getReCommentId(), loginUserId),
                        reCommentRepository.existsReCommentByIdAndUserId(r.getReCommentId(), loginUserId),
                        r.getUpdatedAt()))
                .collect(Collectors.toList());

        boolean hasNext = reCommentList.size() >= pageable.getPageSize();

        return new ReCommentListResponse(reCommentDetailList, hasNext);
    }

}