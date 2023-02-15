package project.reComment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.reComment.response.QReCommentSliceResponse;
import project.reComment.response.ReCommentSliceResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static project.reComment.domain.QReComment.reComment;

@Repository
public class ReCommentRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public ReCommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ReCommentSliceResponse> findReCommentList(Long lastReCommentId, Long commentId, Pageable pageable) {
        List<ReCommentSliceResponse> content = queryFactory
                .select(new QReCommentSliceResponse(
                        reComment.id,
                        reComment.user.userProfileImage.userProfileImageURL,
                        reComment.user.name,
                        reComment.user.nickName,
                        reComment.content,
                        reComment.reCommentLikeSize,
                        reComment.updatedAt))
                .from(reComment)
                .where(
                        ltReCommentId(lastReCommentId),
                        reComment.comment.id.eq(commentId)
                )
                .limit(pageable.getPageSize())
                .orderBy(
                        reComment.id.desc()
                )
                .fetch();

        return content;
    }

    private BooleanExpression ltReCommentId(Long lastReCommentId) {
        return lastReCommentId != null ? reComment.id.lt(lastReCommentId) : null;
    }

}