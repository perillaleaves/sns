package project.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.post.response.PostListResponse;
import project.post.response.ProfilePostListResponse;
import project.post.response.QPostListResponse;
import project.post.response.QProfilePostListResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static project.post.domain.QPost.post;
import static project.user.domain.QUser.user;

@Repository
public class PostRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PostListResponse> getPostList(Long lastPostId, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(new QPostListResponse(
                        post.id,
                        post.user.id,
                        post.user.userProfileImage.userProfileImageURL,
                        post.user.name,
                        post.user.nickName,
                        post.content,
                        post.postLikeSize,
                        post.commentSize,
                        post.updatedAt))
                .from(post)
                .leftJoin(post.user, user)
                .where(
                        ltPostId(lastPostId)
                )
                .limit(pageable.getPageSize())
                .orderBy(
                        post.id.desc()
                )
                .fetch();

        return content;
    }

    public List<ProfilePostListResponse> getProfilePostList(Long lastPostId, Long loginUserId, Pageable pageable) {
        List<ProfilePostListResponse> content = queryFactory
                .select(new QProfilePostListResponse(
                        post.id
                ))
                .from(post)
                .leftJoin(post)
                .where(
                        ltPostId(lastPostId),
                        post.user.id.eq(loginUserId)
                )
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetch();

        return content;
    }

    private BooleanExpression ltPostId(Long lastPostId) {
        return lastPostId != null ? post.id.lt(lastPostId) : null;
    }

}