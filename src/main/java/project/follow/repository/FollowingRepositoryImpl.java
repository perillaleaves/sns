//package project.follow.repository;
//
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.stereotype.Repository;
//import project.follow.domain.QFollow;
//import project.follow.response.FollowingUserListResponse;
//import project.follow.response.QFollowingUserListResponse;
//
//import javax.persistence.EntityManager;
//
//import static project.follow.domain.QFollow.follow;
//
//@Repository
//public class FollowingRepositoryImpl {
//
//    public final JPAQueryFactory queryFactory;
//    private final FollowRepository followRepository;
//
//    public FollowingRepositoryImpl(EntityManager em,
//                                   FollowRepository followRepository) {
//        this.queryFactory = new JPAQueryFactory(em);
//        this.followRepository = followRepository;
//    }
//
//    public Slice<FollowingUserListResponse> findByToUserId(Long userId, Long myId, Pageable pageable) {
//        queryFactory
//                .select(new QFollowingUserListResponse(
//                        follow.id.as("followId"),
//                        follow.fromUser.userProfileImage.userProfileImageURL,
//                        follow.fromUser.name,
//                        follow.fromUser.nickName,
//                        followRepository.existsFollowByFromUserIdAndToUserId(follow.fromUser.id, myId),
//                        followRepository.existsFollowByFromUserIdAndToUserId(myId, userId)))
//                .from()
//
//    }
//
//}