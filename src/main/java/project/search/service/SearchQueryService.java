package project.search.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.follow.repository.FollowRepository;
import project.search.repository.SearchRepositoryImpl;
import project.search.request.SearchRequest;
import project.search.response.SearchListResponse;
import project.search.response.SearchUserListDetailResponse;
import project.search.response.SearchUserListResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SearchQueryService {

    private final SearchRepositoryImpl searchRepositoryImpl;
    private final FollowRepository followRepository;
    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public SearchQueryService(SearchRepositoryImpl searchRepositoryImpl, FollowRepository followRepository) {
        this.searchRepositoryImpl = searchRepositoryImpl;
        this.followRepository = followRepository;
    }

    public SearchListResponse findUserListByName(Long lastUserId, Long loginUserId, SearchRequest request, Pageable pageable) {
        List<SearchUserListResponse> searchUserList = searchRepositoryImpl.findUserListByNickName(lastUserId, request, pageable);
        List<SearchUserListDetailResponse> searchList = searchUserList.stream()
                .map(s -> new SearchUserListDetailResponse(
                        s.getUserId(),
                        s3Url + s.getUserProfileImageUrl(),
                        s.getUserName(),
                        s.getNickName(),
                        s.getFollowerSize(),
                        followRepository.existsFollowByFromUserIdAndToUserId(loginUserId, s.getUserId())))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (searchUserList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new SearchListResponse(searchList, hasNext);
    }

    public SearchListResponse findUserList(Long lastUserId, Long loginUserId, Pageable pageable) {
        List<SearchUserListResponse> searchUserList = searchRepositoryImpl.findUserList(lastUserId, pageable);
        List<SearchUserListDetailResponse> searchList = searchUserList.stream()
                .map(s -> new SearchUserListDetailResponse(
                        s.getUserId(),
                        s3Url + s.getUserProfileImageUrl(),
                        s.getUserName(),
                        s.getNickName(),
                        s.getFollowerSize(),
                        followRepository.existsFollowByFromUserIdAndToUserId(loginUserId, s.getUserId())))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (searchUserList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new SearchListResponse(searchList, hasNext);
    }

}