package project.domain.post;

import project.common.BaseEntity;

import javax.persistence.*;

@Entity
public class PostImage extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "postImageId")
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private Post post;

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Post getPost() {
        return post;
    }


    public PostImage() {
    }
}
