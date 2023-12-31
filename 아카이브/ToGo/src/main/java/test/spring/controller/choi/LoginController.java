package test.spring.controller.choi;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.choi.KakaoDTO;
import test.spring.service.choi.LoginService;
import test.spring.service.choi.TestService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	public LoginService ls;
	
	@Autowired
	public TestService testService;
	
	@Autowired
	private HttpSession session;
		
		@RequestMapping("login")
		public String login() {
			return "/choi/login";
		}
//		@RequestMapping("insert")
//		public String insert(LoginDTO dto,Model model) {
//			service.insert(dto);
//			return "/choi/login";
//		}
//		@Autowired
//		private LoginService service;

//		@Controller
//		@RequestMapping(value="/choi/*")
//		public class LoginController {

			
//			@RequestMapping(/login)
//			public String login{
//				return "/choi/login";
//			}
			

			@RequestMapping(value="/kakaologin", method=RequestMethod.GET)
			public String kakaoLogin(@RequestParam(value = "code", required = false) String code)  {
				String access_Token = ls.getAccessToken(code);
				HashMap<String, String> userInfo = ls.getUserInfo(access_Token);
				String nick = userInfo.get("nickname");
				String email = userInfo.get("email");
				ls.kakaoInsert(nick,email);
				return "choi/login";
				
		    	}
		
		}
