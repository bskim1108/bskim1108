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

import com.detech.cms.web.service.MemberService;

@Controller
public class MemberController {

	private Logger logger = LogManager.getLogger("egovLogger");

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/memberInfo.do")
	public String getMemberInfo(HttpSession session, ModelMap model) {
		logger.debug(">>>> MEMBERINFO PAGE ENTERED");
		model.addAttribute("menuMap", session.getAttribute("menuInfo"));
		
		try {
			model.addAttribute("memberMap", memberService.getMemberInfo());
		}catch(Exception e) {
			logger.error(">>>> MEMBERINFO PAGE ERROR :");
		}
		return "memberAuth";
	}
	
	@RequestMapping(value="/member/changeAuth.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyAuth(@RequestParam Map<String, Object> reqParam) {
		Map<String, Object> resMap = new HashMap<>();
		
		try {
			logger.debug(">>>> AJAX DATA : {}",reqParam);
			memberService.modifyAuth(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			resMap.put("answer", "FAIL");
		}
		return resMap; 
	}
}
