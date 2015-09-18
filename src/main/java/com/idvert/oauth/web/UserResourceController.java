package com.idvert.oauth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idvert.oauth.service.IAuthService;

@Controller
public class UserResourceController {

    @Autowired
    IAuthService authService;

    @RequestMapping("favuserresource")
    public HttpEntity accessUserResource(HttpServletRequest request) throws OAuthSystemException {
        try {

            // 构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest =
                    new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            // 获取Access Token
            String accessToken = oauthRequest.getAccessToken();
            BasicOAuthToken oAuthToken = new BasicOAuthToken(accessToken);
            System.out.println(oAuthToken.getExpiresIn()+"<<<><>");
            // 验证Access Token
            if (!authService.checkAccessToken(accessToken)) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setRealm("Apache Oltu")
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN).buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,
                        oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }
            // 返回用户名
            String username = authService.getUsernameByAccessToken(accessToken);
            StringBuffer tmp = new StringBuffer("welcome [").append(username).append("]");
            return new ResponseEntity(tmp.toString(), HttpStatus.OK);
        } catch (OAuthProblemException e) {
            // 检查是否设置了错误码
            String errorCode = e.getError();
            e.printStackTrace();
        }
        return null;
    }
}
