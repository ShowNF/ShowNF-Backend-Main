package com.shownf.reptile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.KakaoProfile;
import com.shownf.reptile.Model.OAuthToken;
import com.shownf.reptile.bean.SaveGoogleUserBean;
import com.shownf.reptile.bean.SaveKakaoUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class LoginService {
    private final RestTemplate restTemplate = new RestTemplate();
    SaveGoogleUserBean saveGoogleUserBean;
    SaveKakaoUserBean saveKakaoUserBean;

    @Autowired
    public LoginService(SaveGoogleUserBean saveGoogleUserBean, SaveKakaoUserBean saveKakaoUserBean) {
        this.saveGoogleUserBean = saveGoogleUserBean;
        this.saveKakaoUserBean = saveKakaoUserBean;
    }

    @Value("${GOOGLE_CLIENT_ID}")
    String google_clientId;

    @Value("${GOOGLE_CLIENT_SECRET}")
    String google_clientSecret;

    @Value("${GOOGLE_REDIRECT_URI}")
    String google_redirectUri;

    @Value("${GOOGLE_TOKEN_URI}")
    String google_tokenUri;

    @Value("${GOOGLE_RESOURCE_URI}")
    String google_resourceUri;

    @Value("${KAKAO_CLIENT_ID}")
    String kakao_client_id;

    @Value("${KAKAO_REDIRECT_URI}")
    String kakao_redirect_uri;

    @Value("${KAKAO_TOKEN_URL}")
    String kakao_token_url;

    @Value("${KAKAO_PROFILE_URL}")
    String kakao_profile_url;

    public String socialLogin(String code, String registrationId) {
        if ("google".equals(registrationId)) {
            return loginGoogle(code, registrationId);
        } else if ("kakao".equals(registrationId)) {
            return loginKakao(code);
        }

        return null;
    }


    private String loginGoogle(String code, String registrationId) {
        String accessToken = getGoogleAccessToken(code, registrationId);
        JsonNode userResourceNode = getGoogleUserResource(accessToken, registrationId);

        saveGoogleUserBean.exec(accessToken, userResourceNode);

        return accessToken;
    }

    private String loginKakao(String code) {
        OAuthToken oAuthToken = getKakaoAccessToken(code);

        KakaoProfile kakaoProfile = getKakaoProfile(oAuthToken.getAccess_token());

        saveKakaoUserBean.exec(oAuthToken.getAccess_token(), kakaoProfile);

        return oAuthToken.getAccess_token();
    }


    private String getGoogleAccessToken(String authorizationCode, String registrationId) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", google_clientId);
        params.add("client_secret", google_clientSecret);
        params.add("redirect_uri", google_redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(google_tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }


    private JsonNode getGoogleUserResource(String accessToken, String registrationId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(google_resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }


    public OAuthToken getKakaoAccessToken(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakao_client_id);
        params.add("redirect_uri", kakao_redirect_uri);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(kakao_token_url, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oAuthToken;
    }

    public KakaoProfile getKakaoProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                kakao_profile_url,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }
}