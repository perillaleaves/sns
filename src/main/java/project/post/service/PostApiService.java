package project.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.advice.exception.APIError;
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
import java.util.ArrayList;
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

    public void create(PostRequest request, Long userId, List<MultipartFile> files, String dirName) throws IOException {
        validation(request);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Post post = Post.builder()
                .content(request.getContent())
                .commentSize(0L)
                .postLikeSize(0L)
                .user(user)
                .build();
        user.increasePostSize(user.getPostSize());
        postRepository.save(post);

        List<String> s3UploadList = new ArrayList<>();
        List<String> imageUrls = s3Service.multiUpload(files, dirName);
        for (String img : imageUrls) {
            s3UploadList.add(img);
        }

        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);
        s3UploadList.add(null);

        PostImage postImage = PostImage.builder()
                .postImageUrl1(s3UploadList.get(0))
                .postImageUrl2(s3UploadList.get(1))
                .postImageUrl3(s3UploadList.get(2))
                .postImageUrl4(s3UploadList.get(3))
                .postImageUrl5(s3UploadList.get(4))
                .postImageUrl6(s3UploadList.get(5))
                .postImageUrl7(s3UploadList.get(6))
                .postImageUrl8(s3UploadList.get(7))
                .postImageUrl9(s3UploadList.get(8))
                .postImageUrl10(s3UploadList.get(9))
                .post(post)
                .build();
        postImageRepository.save(postImage);
    }

    public void update(Long postId, PostRequest request, Long userId) {
        validation(request);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        loginValidate(userId, post);

        post.updatePostContent(request.getContent());
    }

    public void delete(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        loginValidate(userId, post);

        user.decreasePostSize(user.getPostSize());
        postRepository.delete(post);
    }

    private static void validation(PostRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }

    private static void loginValidate(Long userId, Post post) {
        if (!post.getUser().getId().equals(userId)) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
    }

}