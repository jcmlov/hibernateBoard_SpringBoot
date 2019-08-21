package org.hibernateBoard.security.social.kakao;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import lombok.Getter;

@Getter
public class KakaoUserDetails {

	private String id;
    private String nickname;
    private String given_name;
    private String family_name;
    private String profile;
    private String thumbnail_image;
    private String email;
    private boolean is_email_valid;
    private String custom_field1;
    private String gender;
    private String birthday;

    private long expires_in;
    private String access_token;


    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.access_token = accessToken.getValue();
        this.expires_in = accessToken.getExpiration().getTime();
    }
}
