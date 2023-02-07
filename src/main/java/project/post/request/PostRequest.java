package project.post.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {

    private Long userId;

    private String content;

    private MultipartFile imgPath1;
    private MultipartFile imgPath2;
    private MultipartFile imgPath3;
    private MultipartFile imgPath4;
    private MultipartFile imgPath5;
    private MultipartFile imgPath6;
    private MultipartFile imgPath7;
    private MultipartFile imgPath8;
    private MultipartFile imgPath9;
    private MultipartFile imgPath10;

    public PostRequest(Long userId, String content, MultipartFile imgPath1, MultipartFile imgPath2, MultipartFile imgPath3, MultipartFile imgPath4, MultipartFile imgPath5, MultipartFile imgPath6, MultipartFile imgPath7, MultipartFile imgPath8, MultipartFile imgPath9, MultipartFile imgPath10) {
        this.userId = userId;
        this.content = content;
        this.imgPath1 = imgPath1;
        this.imgPath2 = imgPath2;
        this.imgPath3 = imgPath3;
        this.imgPath4 = imgPath4;
        this.imgPath5 = imgPath5;
        this.imgPath6 = imgPath6;
        this.imgPath7 = imgPath7;
        this.imgPath8 = imgPath8;
        this.imgPath9 = imgPath9;
        this.imgPath10 = imgPath10;
    }
}