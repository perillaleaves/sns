package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse{

    private Long commentId;
    private String userProfileImage;
    private String nickName;
    private String commentContent;
    private LocalDateTime updatedAt;

    public CommentResponse(Long commentId, String userProfileImage, String nickName, String commentContent, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.userProfileImage = userProfileImage;
        this.nickName = nickName;
        this.commentContent = commentContent;
        this.updatedAt = updatedAt;
    }

}