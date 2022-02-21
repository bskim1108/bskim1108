package com.detech.cms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.detech.cms.web.service.CmsService;


@Controller
public class CmsController {

	@Autowired
	CmsService cmsService;
	
	private Logger logger = LogManager.getLogger("egovLogger");

	@RequestMapping("/cms/cmsMain.do")
	public String getCmsMain(HttpSession session, ModelMap model) throws Exception {
		logger.debug(">>>> CONTROLLER ENTERED");
		model.addAttribute("menuMap", session.getAttribute("menuInfo"));
		model.addAttribute("cmsMap", cmsService.getMenuList());
		
		return "cmsMain";
	}

	@RequestMapping(value="/menu/deleteMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMenu(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.deleteMenu(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> DELETEMENU ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/menu/modifyMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyMenu(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.modifyMenu(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> MODIFYMENU ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/menu/insertMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMenu(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.insertMenu(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> INSERTMENU ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/auth/deleteAuth.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAuth(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.deleteAuth(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> DELETEAUTH ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/auth/modifyAuth.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyAuth(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.modifyAuth(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> MODIFYAUTH ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/auth/insertAuth.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertAuth(@RequestParam Map<String, Object> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();
		try {
			cmsService.insertAuth(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> INSERTAUTH ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

	@RequestMapping(value="/xtable/insertXTable.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertXTable(@RequestBody List<Map<String, Object>> reqParam) {
		logger.debug(">>>> AJAX DATA : {}", reqParam);
		Map<String,Object> resMap = new HashMap<>();

		try {
			cmsService.insertXTable(reqParam);
			resMap.put("answer", "SUCCESS");
		}catch(Exception e) {
			logger.error(">>>> INSERTXTABLE ERROR : ");
			e.printStackTrace();
			resMap.put("answer", "FAIL");
		}
		return resMap;
	}

}
