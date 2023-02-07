package project.s3;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class S3UploadList {

    private String postImageUrl1;
    private String postImageUrl2;
    private String postImageUrl3;
    private String postImageUrl4;
    private String postImageUrl5;
    private String postImageUrl6;
    private String postImageUrl7;
    private String postImageUrl8;
    private String postImageUrl9;
    private String postImageUrl10;

    public S3UploadList(String postImageUrl1, String postImageUrl2, String postImageUrl3, String postImageUrl4, String postImageUrl5, String postImageUrl6, String postImageUrl7, String postImageUrl8, String postImageUrl9, String postImageUrl10) {
        this.postImageUrl1 = postImageUrl1;
        this.postImageUrl2 = postImageUrl2;
        this.postImageUrl3 = postImageUrl3;
        this.postImageUrl4 = postImageUrl4;
        this.postImageUrl5 = postImageUrl5;
        this.postImageUrl6 = postImageUrl6;
        this.postImageUrl7 = postImageUrl7;
        this.postImageUrl8 = postImageUrl8;
        this.postImageUrl9 = postImageUrl9;
        this.postImageUrl10 = postImageUrl10;
    }
}