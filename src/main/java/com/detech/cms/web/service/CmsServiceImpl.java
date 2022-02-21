package com.detech.cms.web.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CmsServiceImpl implements CmsService {

	private Logger logger = LogManager.getLogger("egovLogger");
	
	@Resource(name="cmsMapper")
	CmsMapper cmsMapper;
	
	@Override
	public Map<String, Object> getMenuList() throws Exception{
		logger.debug(">>>> SERVICE ENTERED");
		Map<String, Object> cmsMap = new LinkedHashMap<>();

		try {
			cmsMap.put("menuList", cmsMapper.getMenuList(""));
			cmsMap.put("authList", cmsMapper.getAuthList(""));
			cmsMap.put("menuXList", cmsMapper.getMenuList("Y"));
			cmsMap.put("authXList", cmsMapper.getAuthList("Y"));
			cmsMap.put("xTableList", cmsMapper.getMenuListAcctoAuth());
			logger.debug(">>>> SQL RESULT {}", cmsMap);
		}catch(Exception e) {
			logger.debug(">>>> CMSSERVICE ERROR : ");
			e.printStackTrace();
			throw new Exception("CALLING MENULIST ERROR");
		}
		return cmsMap;
	}

	@Override
	public void deleteMenu(Map<String, Object> reqParam) throws Exception{
		logger.debug(">>>> DELETEMENU SERVICE");
		
		try {
			cmsMapper.deleteMenu(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> DELMENUSERVICE ERROR : ");
			throw new Exception("ERROR ON DELETING MENU");
		}
	}

	@Override
	public void modifyMenu(Map<String, Object> reqParam) throws Exception {
		logger.debug(">>>> MODIFYMENU SERVICE");
		
		try {
			cmsMapper.modifyMenu(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> MODMENUSERVICE ERROR : ");
			throw new Exception("ERROR ON MODIFYING MENU");
		}
	}

	@Override
	public void insertMenu(Map<String, Object> reqParam) throws Exception{
		logger.debug(">>>> INSERTMENU SERVICE");
		
		try {
			String menuCode = "";
			menuCode = cmsMapper.getNewMenuCode();
			if(!"".equals(menuCode) && menuCode != null) 
				reqParam.put("menuCode", menuCode);
			else
				reqParam.put("menuCode", "menu001");
			logger.debug(">>>> INSERTMENU DATA : {}", reqParam);
			cmsMapper.insertMenu(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> INSMENUSERVICE ERROR : ");
			throw new Exception("ERROR ON INSERTING MENU");
		}
	}

	@Override
	public void deleteAuth(Map<String, Object> reqParam) throws Exception{
		logger.debug(">>>> DELETEAUTH SERVICE");
		
		try {
			cmsMapper.deleteAuth(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> DELAUTHSERVICE ERROR : ");
			throw new Exception("ERROR ON DELETING AUTHORIZATION KEY");
		}
	}

	@Override
	public void modifyAuth(Map<String, Object> reqParam) throws Exception{
		logger.debug(">>>> MODIFYAUTH SERVICE");
		
		try {
			cmsMapper.modifyAuth(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> MODAUTHSERVICE ERROR : ");
			throw new Exception("ERROR ON MODIFYING AUTHORIZATION KEY");
		}
	}

	@Override
	public void insertAuth(Map<String, Object> reqParam) throws Exception {
		logger.debug(">>>> INSERTAUTH SERVICE");
		
		try {
			String authCode = "";
			authCode = cmsMapper.getNewAuthCode();
			if(!"".equals(authCode) && authCode != null) 
				reqParam.put("authCode", authCode);
			else
				reqParam.put("authCode", "auth01");
			logger.debug(">>>> INSERTAUTH DATA : {}", reqParam);
			cmsMapper.insertAuth(reqParam);
		}catch(Exception e) {
			logger.debug(">>>> INSAUTHSERVICE ERROR : ");
			throw new Exception("ERROR ON INSERTING NEW AUTHORIZATION KEY");
		}
	}

	@Override
	public void insertXTable(List<Map<String, Object>> reqParam) throws Exception {
		logger.debug(">>>> INSERTXTABLE SERVICE");
		
		try {
			cmsMapper.deleteXTable();
			Map<String,Object> tempMap = new HashMap<>();
			for(Map<String, Object> map : reqParam) {
				tempMap.put("menuCode", map.get("menuCode"));
				tempMap.put("authCode", map.get("authCode"));
				logger.debug(">>>> SQL PARAMETER : {}", tempMap);
				cmsMapper.insertXTable(tempMap);
			}
			
		}catch(Exception e) {
			logger.debug(">>>> INSXTABLESERVICE ERROR : ");
			throw new Exception("ERROR ON MODIFYING MENU_AUTH XTABLE");
		}
	}
}
