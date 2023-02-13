package project.comment.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.comment.domain.Comment;
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

    public Slice<CommentSliceResponse> findCommentList(Long postId, Pageable pageable) {
        List<CommentSliceResponse> content = queryFactory
                .select(new QCommentSliceResponse(
                        comment.id,
                        comment.user.userProfileImage.userProfileImageURL,
                        comment.user.name,
                        comment.user.nickName,
                        comment.content,
                        comment.updatedAt))
                .from(comment)
                .where(
                        comment.post.id.eq(postId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Comment> countQuery = queryFactory
                .selectFrom(comment)
                .where(
                        comment.post.id.eq(postId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

}