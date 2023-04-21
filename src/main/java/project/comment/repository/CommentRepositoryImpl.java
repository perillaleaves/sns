package project.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.comment.response.CommentSliceResponse;
import project.comment.response.QCommentSliceResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static project.comment.domain.QComment.comment;

@Repository
public class CommentRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<CommentSliceResponse> findCommentList(Long lastCommentId, Long postId, Pageable pageable) {
        List<CommentSliceResponse> content = queryFactory
                .select(new QCommentSliceResponse(
                        comment.id,
                        comment.user.userProfileImage.userProfileImageURL,
                        comment.user.name,
                        comment.user.nickName,
                        comment.content,
                        comment.commentLikeSize,
                        comment.reCommentSize,
                        comment.createdAt))
                .from(comment)
                .where(
                        ltCommentId(lastCommentId),
                        comment.post.id.eq(postId)
                )
                .limit(pageable.getPageSize())
                .orderBy(
                        comment.id.desc()
                )
                .fetch();

        return content;
    }

    private BooleanExpression ltCommentId(Long lastCommentId) {
        return lastCommentId != null ? comment.id.lt(lastCommentId) : null;
    }

}