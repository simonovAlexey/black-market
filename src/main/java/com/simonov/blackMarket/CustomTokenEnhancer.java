package com.simonov.blackMarket;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", authentication.getName() + " Inc.");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
        //TODO  для добавления закодированной информации переконвертировать токен, добавить фильтр на ошибки авторизации для
//        использования refresh token-a

        /*DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        Map<String, ?> stringMap1 = defaultAccessTokenConverter.convertAccessToken(accessToken, authentication);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        OAuth2AccessToken enhance = jwtAccessTokenConverter.enhance(accessToken, authentication);
        Map<String, ?> stringMap2 = defaultAccessTokenConverter.convertAccessToken(enhance, authentication);
        return enhance;*/
    }

    public CustomTokenEnhancer() {
    }

}
