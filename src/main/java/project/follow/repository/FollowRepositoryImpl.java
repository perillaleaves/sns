package project.follow.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import project.follow.domain.Follow;
import project.follow.response.FollowingUserListResponse;
import project.follow.response.QFollowingUserListResponse;
import project.user.domain.QUser;

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
                        follow.fromUser.id.eq(myId)))
                .from(follow)
                .leftJoin(follow.fromUser, user)
                .where(
                        follow.toUser.id.eq(userId),
                        follow.fromUser.id.ne(myId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Follow> countQuery = queryFactory
                .selectFrom(follow)
                .where(
                        follow.toUser.id.eq(userId),
                        follow.fromUser.id.ne(myId)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

}