package com.shownf.reptile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.KakaoProfile;
import com.shownf.reptile.Model.OAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class LoginService {
    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();

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

    @Autowired
    public LoginService(Environment env) {
        this.env = env;
    }
    public String  socialLogin(String code, String registrationId) {
        if ("google".equals(registrationId)) {
            loginGoogle(code, registrationId);
        } else if ("kakao".equals(registrationId)) {
            loginKakao(code);
        }

        return registrationId;
    }


    private void loginGoogle(String code, String registrationId) {
        String accessToken = getGoogleAccessToken(code, registrationId);
        JsonNode userResourceNode = getGoogleUserResource(accessToken, registrationId);
        System.out.println("userResourceNode = " + userResourceNode);

        String id = userResourceNode.get("id").asText();
        String email = userResourceNode.get("email").asText();
        String nickname = userResourceNode.get("name").asText();
        System.out.println("id = " + id);
        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
    }


    private void loginKakao(String code) {
        OAuthToken oAuthToken = getKakaoAccessToken(code);
        System.out.println("oAuthToken = " + oAuthToken);
        System.out.println("oAuthToken.getAccess_token() = " + oAuthToken.getAccess_token());

        KakaoProfile kakaoProfile = getKakaoProfile(oAuthToken.getAccess_token());
        System.out.println(kakaoProfile);
        System.out.println(kakaoProfile.getId());
        System.out.println(kakaoProfile.kakao_account.getEmail());
    }


    private String getGoogleAccessToken(String authorizationCode, String registrationId) {
        /*String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");*/

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
        /*String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");*/

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(google_resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }


    public OAuthToken getKakaoAccessToken(String code) {
        /*String client_id = env.getProperty("kakao.client-id");
        String redirect_uri = env.getProperty("kakao.redirect-uri");
        String token_url = env.getProperty("kakao.token-url");*/

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

        /*String profile_url = env.getProperty("kakao.profile-url");*/

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