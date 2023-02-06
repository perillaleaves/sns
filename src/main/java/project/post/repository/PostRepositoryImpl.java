package project.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import project.post.domain.Post;

import javax.persistence.EntityManager;

import java.util.List;

import static project.post.domain.QPost.*;
import static project.user.domain.QUser.user;

@Repository
public class PostRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Slice<Post> getPostsByProfile(Long userId, Long lastPostId, Pageable pageable) {

        List<Post> content = queryFactory
                .selectFrom(post)
                .where(
                        ltPostId(lastPostId),
                        userIdEq(userId)
                )
                .orderBy(post.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;

        if (content.size() == pageable.getPageSize() + 1) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanExpression ltPostId(Long postId) {
        return postId != null ? post.id.lt(postId) : null;
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

}