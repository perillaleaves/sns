package project.post.domain;

import lombok.*;
import project.common.BaseEntity;
import project.post.domain.Post;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postImageId")
    private Long id;

    private String postImageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private Post post;

    @Builder
    public PostImage(Long id, String postImageUrl, Post post) {
        this.id = id;
        this.postImageUrl = postImageUrl;
        this.post = post;
    }

}