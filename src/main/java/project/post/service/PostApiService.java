package project.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.advice.error.APIError;
import project.advice.exception.PostNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.post.domain.Post;
import project.post.domain.PostImage;
import project.post.repository.PostImageRepository;
import project.post.repository.PostRepository;
import project.post.request.PostRequest;
import project.s3.S3Service;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class PostApiService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    public PostApiService(PostRepository postRepository, PostImageRepository postImageRepository, UserRepository userRepository, S3Service s3Service) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    public void create(Long loginUserID, PostRequest request, List<MultipartFile> files, String dirName) throws IOException {
        validation(request);
        User user = userRepository.findById(loginUserID)
                .orElseThrow(() -> new UserNotFoundException());

        Post post = Post.builder()
                .content(request.getContent())
                .commentSize(0L)
                .postLikeSize(0L)
                .user(user)
                .build();
        user.increasePostSize(user.getPostSize());
        postRepository.save(post);

        List<String> imageUrls = s3Service.multiUpload(files, dirName);
        for (String imageUrl : imageUrls) {
            PostImage postImage = PostImage.builder()
                    .postImageUrl(imageUrl)
                    .post(post)
                    .build();
            postImageRepository.save(postImage);
        }
    }

    public void update(Long postId, Long loginUserId, PostRequest request) {
        validation(request);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        loginValidate(loginUserId, post);

        post.updatePostContent(request.getContent());
    }

    public void delete(Long postId, Long loginUserID) {
        User user = userRepository.findById(loginUserID)
                .orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        loginValidate(loginUserID, post);

        user.decreasePostSize(user.getPostSize());
        postRepository.delete(post);
    }

    private static void validation(PostRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }

    private static void loginValidate(Long loginUserId, Post post) {
        if (!post.getUser().getId().equals(loginUserId)) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
    }

}