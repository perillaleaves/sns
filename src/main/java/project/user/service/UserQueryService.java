package project.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.post.domain.Post;
import project.post.dto.PostImageDto;
import project.post.dto.PostListDto;
import project.post.repository.PostRepository;
import project.post.repository.PostRepositoryImpl;
import project.post.response.PostImagesResponse;
import project.user.domain.User;
import project.user.repository.UserRepository;
import project.user.response.ProfileResponse;
import project.user.response.UserDetailResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostRepositoryImpl postRepositoryImpl;

    public UserQueryService(UserRepository userRepository, PostRepository postRepository, PostRepositoryImpl postRepositoryImpl) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postRepositoryImpl = postRepositoryImpl;
    }

    public ProfileResponse findUserProfile(Long userId, Long myId, Long lastPostId, Pageable pageable) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserDetailResponse userDetailResponse = new UserDetailResponse(
                findUser.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                findUser.getPostSize(),
                findUser.getFollowerSize(),
                findUser.getFollowingSize(),
                findUser.getId().equals(myId));

        Slice<Post> postsByProfile = postRepositoryImpl.getPostsByProfile(userId, lastPostId, pageable);
        List<PostListDto> collect = postsByProfile.stream()
                .map(p -> new PostListDto(p.getId())).collect(Collectors.toList());

        return new ProfileResponse(userDetailResponse, collect);
    }

}