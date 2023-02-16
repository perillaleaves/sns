package project.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.post.response.*;

import javax.persistence.EntityManager;
import java.util.List;

import static project.post.domain.QPost.post;
import static project.post.domain.QPostImage.postImage;
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
                        post.postImage.postImageUrl1,
                        post.postImage.postImageUrl2,
                        post.postImage.postImageUrl3,
                        post.postImage.postImageUrl4,
                        post.postImage.postImageUrl5,
                        post.postImage.postImageUrl6,
                        post.postImage.postImageUrl7,
                        post.postImage.postImageUrl8,
                        post.postImage.postImageUrl9,
                        post.postImage.postImageUrl10,
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
                        post.id,
                        post.postImage.id,
                        post.postImage.postImageUrl1
                ))
                .from(post)
                .leftJoin(post.postImage, postImage)
                .where(
                        ltPostId(lastPostId),
                        post.user.id.eq(loginUserId)
                )
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetch();

        return content;
    }

    private BooleanExpression ltPostId(Long lagtPostId) {
        return lagtPostId != null ? post.id.lt(lagtPostId) : null;
    }

}