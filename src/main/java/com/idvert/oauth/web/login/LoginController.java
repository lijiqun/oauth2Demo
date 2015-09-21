package com.idvert.oauth.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idvert.oauth.pojo.User;

@Controller
public class LoginController {
	
	@RequestMapping(value={"login","/"})
	public String login(){
		return "index";
	}
	
	
	@RequestMapping("loginForm")
	public ModelAndView loginForm(HttpSession session,HttpServletRequest request,String username, String password, Model model){
		ModelAndView mav = new ModelAndView();
		System.out.println("" + username + password);
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		if(StringUtils.isEmpty(username)){
			mav.setViewName("forward:/login/login");
			return mav;
		}
		System.out.println(request.getParameter("client_id"));
		User favUser = new User();
		favUser.setUsername(username);
		favUser.setPassword(password);
		session.setAttribute("favUser", favUser);
		mav.addObject("favUser", favUser);
		String para = request.getParameter("requestUrl");
		mav.setViewName("forward:/oauth2/favauthorize"+para);
		return mav;
	}
	
	@RequestMapping("oauth2login")
	public ModelAndView oauth2Login(){
		ModelAndView mav = new ModelAndView();
		return mav;
	}

}
