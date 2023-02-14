package project.reComment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.reComment.domain.ReComment;
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

    public Slice<ReCommentSliceResponse> findReCommentList(Long commentId, Pageable pageable) {
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
                        reComment.comment.id.eq(commentId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        reComment.id.desc()
                )
                .fetch();

        JPAQuery<ReComment> countQuery = queryFactory
                .selectFrom(reComment)
                .where(
                        reComment.comment.id.eq(commentId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    private BooleanExpression ltReCommentId(Long reCommentId) {
        if (reCommentId == null) {
            return null;
        }
        return reComment.id.lt(reCommentId);
    }

}