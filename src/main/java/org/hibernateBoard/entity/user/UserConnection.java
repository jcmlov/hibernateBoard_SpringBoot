package org.hibernateBoard.entity.user;

import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.hibernateBoard.security.social.facebook.FacebookUserDetails;
import org.hibernateBoard.security.social.google.GoogleUserDetails;
import org.hibernateBoard.security.social.kakao.KakaoUserDetails;
import org.hibernateBoard.security.userConnection.ProviderType;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "user_connection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserConnection implements Serializable {
	
	private static final long serialVersionUID = -7375024978245951302L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    @Column(name = "provider_id", unique = true, nullable = false)
    private String providerId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "access_token")
    private String accessToken;


    @Column(name = "expire_time")
    private long expireTime;

    @Builder
    private UserConnection(String email, ProviderType provider, String providerId, String displayName, String profileUrl, String imageUrl, String accessToken, long expireTime) {
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public static UserConnection valueOf(GoogleUserDetails userDetails) {
        return UserConnection.builder()
                .expireTime(userDetails.getExpiration())
                .accessToken(userDetails.getAccess_token())
                .providerId(userDetails.getSub())
                .email(userDetails.getEmail())
                .displayName(userDetails.getName())
                .imageUrl(userDetails.getPicture())
                .provider(ProviderType.GOOGLE)
                .profileUrl(userDetails.getProfile())
                .build();
    }

    public static UserConnection valueOf(FacebookUserDetails userDetails) {
        return UserConnection.builder()
                .expireTime(userDetails.getExpiration())
                .accessToken(userDetails.getAccess_token())
                .providerId(userDetails.getId())
                .email(userDetails.getEmail())
                .displayName(userDetails.getName())
                .imageUrl(userDetails.getImageUrl())
                .provider(ProviderType.FACEBOOK)
                .build();
    }

	public static UserConnection valueOf(KakaoUserDetails userDetails) {
		return UserConnection.builder()
                .expireTime(userDetails.getExpires_in())
                .accessToken(userDetails.getAccess_token())
                .providerId(userDetails.getId())
                .email(userDetails.getEmail())
                .displayName(userDetails.getNickname())
                .imageUrl(userDetails.getThumbnail_image())
                .provider(ProviderType.KAKAO)
                .build();
	}

}
