package project.search.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.search.request.SearchRequest;
import project.search.response.QSearchUserListResponse;
import project.search.response.SearchUserListResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static project.user.domain.QUser.user;

@Repository
public class SearchRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public SearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<SearchUserListResponse> findUserListByNickName(Long lastUserId, Long loginUserId, SearchRequest request, Pageable pageable) {
        List<SearchUserListResponse> content = queryFactory
                .select(new QSearchUserListResponse(
                        user.id,
                        user.userProfileImage.userProfileImageURL,
                        user.name,
                        user.nickName,
                        user.followerSize))
                .from(user)
                .where(
                        ltUserId(lastUserId),
                        nickNameContains(request.getNickName()),
                        user.id.ne(loginUserId)
                )
                .limit(pageable.getPageSize())
                .orderBy(
                        user.id.desc()
                )
                .fetch();

        return content;
    }

    public List<SearchUserListResponse> findUserList(Long lastUserId, Long loginUserId, Pageable pageable) {
        List<SearchUserListResponse> content = queryFactory
                .select(new QSearchUserListResponse(
                        user.id,
                        user.userProfileImage.userProfileImageURL,
                        user.name,
                        user.nickName,
                        user.followerSize))
                .from(user)
                .where(
                        ltUserId(lastUserId),
                        user.id.ne(loginUserId)
                )
                .limit(pageable.getPageSize())
                .orderBy(
                        user.id.desc()
                )
                .fetch();

        return content;
    }

    private BooleanExpression ltUserId(Long lastUserId) {
        return lastUserId != null ? user.id.lt(lastUserId) : null;
    }

    private BooleanExpression nickNameContains(String nickName) {
        return hasText(nickName) ? user.nickName.contains(nickName) : null;
    }

}