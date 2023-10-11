/*
package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@NoArgsConstructor
public class CustomFilterBean extends GenericFilterBean {
    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    GoogleUserRepositoryJPA googleUserRepositoryJPA;

    @Autowired
    public CustomFilterBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, GoogleUserRepositoryJPA googleUserRepositoryJPA) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
   }


    // 사용자 정의 인가 필터을 구현합니다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String userToken = httpRequest.getHeader("access-token"); // "Your-Header-Name"을 실제 헤더 이름으로 변경하세요.

        if (httpRequest.getRequestURI().substring(0, 13).equals("/login/oauth2")){
            chain.doFilter(request, response);
        }
        else {
            KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
            GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);

            String savedToken;

            if (kakaoUserDAO != null) savedToken = kakaoUserDAO.getAccessToken();
            else if (googleUserDAO != null) savedToken = googleUserDAO.getAccessToken();
            else savedToken = null;

            if (userToken != null && userToken.equals(savedToken)) {
                //logger.info("사용자 정의 인가 필터: 토큰이 유효합니다. API 호출 허용");

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        null, null, AuthorityUtils.createAuthorityList("ROLE_ADMIN")));

                chain.doFilter(request, response);
            } else {
                //logger.info("사용자 정의 인가 필터: 토큰이 유효하지 않습니다. API 호출 거부");
                // 여기서 필요에 따라 응답을 수정하거나 에러 처리를 할 수 있습니다.
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                // 예: 401 Unauthorized
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}*/
