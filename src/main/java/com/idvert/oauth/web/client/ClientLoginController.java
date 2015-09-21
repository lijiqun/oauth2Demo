package com.idvert.oauth.web.client;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.idvert.oauth.service.IAuthService;

@Controller
@RequestMapping("client")
public class ClientLoginController {

    @Autowired
    IAuthService favAuthService;
    
    private static final String GET_ACCESS_TOKEN_URL = "http://localhost:8080/oauth2/favaccesstoken";

    @RequestMapping("clientlogin")
    public ModelAndView clientLogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        String authCode = request.getParameter(OAuth.OAUTH_CODE);
        if (StringUtils.isEmpty(authCode)) {
            mav.setViewName("forward:/oauth2/getoAuth2loginapp");
            return mav;
        }
        // 使用令牌换取
        return mav;
    }


    
    @RequestMapping("clienthome")
    @ResponseBody
    public Object clientHome(HttpServletRequest request)
            throws OAuthSystemException, OAuthProblemException {

        ModelAndView mav = new ModelAndView();
        String authCode = request.getParameter(OAuth.OAUTH_CODE);
        if (StringUtils.isEmpty(authCode)) {
            mav.setViewName("client/home_client");
            return mav;
        }
        // 使用授权码去服务端获取令牌
        if (favAuthService.checkAuthCode(authCode)) {

            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(GET_ACCESS_TOKEN_URL)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setScope(request.getParameter(OAuth.OAUTH_SCOPE))
                    .setClientId(request.getParameter(OAuth.OAUTH_CLIENT_ID))
                    .setClientSecret(request.getParameter(OAuth.OAUTH_CLIENT_SECRET)).setCode(authCode)
                    .setRedirectURI(request.getParameter(OAuth.OAUTH_REDIRECT_URI)).buildQueryMessage();
            
            OAuthAccessTokenResponse oAuthResponse =
                    oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);

//            String accessToken = oAuthResponse.getAccessToken();
//            Long expiresIn = oAuthResponse.getExpiresIn();
//            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(request.getParameter(OAuth.OAUTH_REDIRECT_URI))
//                    .setAccessToken(accessToken).buildQueryMessage();
//
//            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
//            String username = resourceResponse.getBody();
            return oAuthResponse.getBody();
        }

        return mav;
    }

}
