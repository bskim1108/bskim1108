package com.detech.cms.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.detech.cms.web.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	private Logger logger = LogManager.getLogger("egovLogger");

	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginCheck(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> LOGIN PAGE ENTERED");
		Map<String, Object> loginMap = new HashMap<>();
		int checkUserId, checkPwd = 0;
		
		try {
			checkUserId = loginService.checkUser(reqParam);
			if(checkUserId > 0) {
				checkPwd = loginService.checkPassword(reqParam);
				if(checkPwd > 0) {
					loginMap.put("answer","SUCCESS");
					loginMap.put("userAuth", loginService.getUserAuth(reqParam).get("authCode"));
				}else {
					loginMap.put("answer","비밀번호가 일치하지 않습니다.");	
				}
			}else {
				loginMap.put("answer", "일치하는 아이디가 없습니다.");
			}
		}catch(Exception e) {
			loginMap.put("answer", "서버오류입니다.");
		}
		return loginMap;
	}

	@RequestMapping(value="/login/main.do", method=RequestMethod.GET)
	public String mainPage(HttpSession session, @RequestParam String userId, @RequestParam String authCode, ModelMap model) {
		logger.debug(">>>> MAIN PAGE ENTERED");
		
		session.setAttribute("userId", userId);
		session.setAttribute("authCode", authCode);
		
		try {
			Map<String, Object> reqParam = new HashMap<>();
			reqParam.put("userId", userId);
			reqParam.put("authCode", authCode);
				
			model.addAttribute("menuMap", loginService.getMenubyAuth(reqParam));
			session.setAttribute("menuInfo", model.get("menuMap"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "mainPage";
	}

	@RequestMapping(value="/back/managerMain.do")
	public String managerPage(HttpSession session, ModelMap model) {
		logger.debug(">>>> MANAGER PAGE ENTERED");
		model.addAttribute("menuMap", session.getAttribute("menuInfo"));
		return "manager";
	}

}
