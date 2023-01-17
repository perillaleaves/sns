package project.domain.member;

import project.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "memberId")
    private Long id;

    private String profileImage;
    private String email;
    private String name;
    private String password;
    private String content;
    private Long postSize;
    private Long followSize;
    private Long followingSize;


    public Long getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContent() {
        return content;
    }

    public Long getPostSize() {
        return postSize;
    }

    public Long getFollowSize() {
        return followSize;
    }

    public Long getFollowingSize() {
        return followingSize;
    }



    public Member() {
    }

}
