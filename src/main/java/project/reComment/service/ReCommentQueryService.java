package project.reComment.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import project.reComment.repository.ReCommentRepository;
import project.reComment.repository.ReCommentRepositoryImpl;
import project.reComment.response.ReCommentListDetailResponse;
import project.reComment.response.ReCommentListResponse;
import project.reComment.response.ReCommentSliceResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReCommentQueryService {

    private final ReCommentRepository reCommentRepository;
    private final ReCommentRepositoryImpl reCommentRepositoryImpl;
    String S3Url = "https://s3.ap-northeast-2.amazonaws.com/mullae.com/";

    public ReCommentQueryService(ReCommentRepository reCommentRepository, ReCommentRepositoryImpl reCommentRepositoryImpl) {
        this.reCommentRepository = reCommentRepository;
        this.reCommentRepositoryImpl = reCommentRepositoryImpl;
    }

    public ReCommentListResponse findReCommentList(Long commentId, Long userId, Pageable pageable) {
        Slice<ReCommentSliceResponse> reCommentList = reCommentRepositoryImpl.findReCommentList(commentId, pageable);
        List<ReCommentListDetailResponse> reCommentDetailList = reCommentList.stream()
                .map(r -> new ReCommentListDetailResponse(
                        r.getReCommentId(),
                        S3Url + r.getUserProfileImageUrl(),
                        r.getUserName(),
                        r.getNickName(),
                        r.getContent(),
                        r.getReCommentLikeSize(),
                        reCommentRepository.existsReCommentByIdAndUserId(r.getReCommentId(), userId),
                        r.getUpdatedAt()))
                .collect(Collectors.toList());

        return new ReCommentListResponse(reCommentDetailList, reCommentList.hasNext());
    }

}