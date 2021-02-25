package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.User;
import com.yukoon.dmfls.Services.UserService;
import com.yukoon.dmfls.Utils.EncodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Map;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	//跳转至后台登陆页
	@GetMapping("/login")
	public String toBackend() {
		return "backend/login.html";
	}


	@PostMapping("/login")
	public String login(User user, String flag, String url) {
		//获得subject
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			//把用户名密码封装为Token对象
			String username = user.getUsername();
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, EncodeUtil.encodePassword(user.getPassword(),username));
			//设置token的rememberme
			usernamePasswordToken.setRememberMe(true);
			try {
				//执行登录
				currentUser.login(usernamePasswordToken);
			}catch (AuthenticationException ae){
				System.out.println("登陆失败:"+ae.toString());
				if (null != flag && flag.equals("bg")) {
					//若是从后台登录，返回backend登录
					return "redirect:/login";
				}else {
					//若是从前台登录，返回前台登录
					return "redirect:/login";
				}
			}
		}
		user = userService.findByUsername(user.getUsername());
		if (user.getRole().getRoleName().equals("admin") ) {
			return "redirect:/allSC";
		}
		return "redirect:"+url;
	}

}
