package project.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.post.domain.Post;
import project.post.response.PostListResponse;
import project.post.response.QPostListResponse;

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

    public Slice<PostListResponse> getPostList(Pageable pageable) {
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .orderBy(
                        post.id.desc()
                )
                .fetch();

        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    private BooleanExpression ltPostId(Long postId) {
        return postId != null ? post.id.lt(postId) : null;
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

}