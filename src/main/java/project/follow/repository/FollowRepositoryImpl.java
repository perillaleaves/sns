package project.follow.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.follow.domain.Follow;
import project.follow.response.follower.FollowerUserListResponse;
import project.follow.response.follower.QFollowerUserListResponse;
import project.follow.response.following.FollowingUserListResponse;
import project.follow.response.following.QFollowingUserListResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static project.follow.domain.QFollow.follow;
import static project.user.domain.QUser.user;

@Repository
public class FollowRepositoryImpl {

    public final JPAQueryFactory queryFactory;

    public FollowRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Slice<FollowingUserListResponse> getFollowingUserList(Long userId, Long myId, Pageable pageable) {
        List<FollowingUserListResponse> content = queryFactory
                .select(new QFollowingUserListResponse(
                        follow.id,
                        follow.fromUser.id,
                        follow.fromUser.userProfileImage.userProfileImageURL,
                        follow.fromUser.name,
                        follow.fromUser.nickName,
                        follow.toUser.id.eq(myId)))
                .from(follow)
                .leftJoin(follow.fromUser, user)
                .where(
                        follow.toUser.id.eq(userId),
                        follow.fromUser.id.ne(myId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        JPAQuery<Follow> countQuery = queryFactory
                .selectFrom(follow)
                .where(
                        follow.toUser.id.eq(userId),
                        follow.fromUser.id.ne(myId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    public Slice<FollowerUserListResponse> getFollowerUserList(Long userId, Long myId, Pageable pageable) {
        List<FollowerUserListResponse> content = queryFactory
                .select(new QFollowerUserListResponse(
                        follow.id,
                        follow.toUser.id,
                        follow.toUser.userProfileImage.userProfileImageURL,
                        follow.toUser.name,
                        follow.toUser.nickName,
                        follow.fromUser.id.eq(myId)
                ))
                .from(follow)
                .leftJoin(follow.toUser, user)
                .where(
                        follow.fromUser.id.eq(userId),
                        follow.toUser.id.ne(myId)
                )
                .orderBy(follow.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        JPAQuery<Follow> countQuery = queryFactory
                .selectFrom(follow)
                .where(
                        follow.fromUser.id.eq(userId),
                        follow.toUser.id.ne(myId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

//    private BooleanExpression ltPageNo(Long follow, Pageable pageable) {
//        if (pageNo == null) {
//            return null;
//        }
//
//        return
//    }

}