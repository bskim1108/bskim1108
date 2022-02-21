package com.detech.cms.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LogManager.getLogger("egovLogger");

	@Resource
	LoginMapper loginMapper;
	@Resource
	MemberMapper memberMapper;
	
	@Override
	public int checkUser(Map<String, Object> reqParam) throws Exception {
		int checkUser = 0;
		
		try {
			checkUser = loginMapper.getUserId(reqParam);
			logger.debug(">>>> USERID CHECKED : {}", checkUser);
		}catch(SQLException e) {
			logger.error(">>>> USERID SERVICE ERROR");
			throw new Exception("ERROR ON CHECKING USERID");
		}
		return checkUser;
	}

	@Override
	public int checkPassword(Map<String, Object> reqParam) throws Exception {
		int checkUserInfo = 0;
		
		try {
			checkUserInfo = loginMapper.getUserInfo(reqParam);
			logger.debug(">>>> USERINFO CHECKED : {}", checkUserInfo);
		}catch(SQLException e) {
			logger.error(">>>> USERINFO SERVICE ERROR");
			throw new Exception("ERROR ON CHECKING USERINFO");
		}
		return checkUserInfo;
	}

	@Override
	public Map<String, Object> getUserAuth(Map<String, Object> reqParam) throws Exception {
		Map<String, Object> resMap = new HashMap<>();
		try {
			resMap = memberMapper.getMemberInfo(reqParam);
			logger.debug(">>>> USERAUTH SQL RESULT : {}", resMap.get("authCode"));
		}catch(SQLException e) {
			logger.error(">>>> USERAUTH SERVICE ERROR");
			throw new Exception("ERROR ON CONSULTING USERDETAILINFO");
		}
		return resMap;
	}

	@Override
	public Map<String, Object> getMenubyAuth(Map<String, Object> reqParam) throws Exception {
		logger.debug(">>>> LOGIN SERVICE STARTED");
		Map<String, Object> resMap = new HashMap<>();
		try {
			resMap.put("menuList", loginMapper.getMenuListbyAuth(reqParam));
			logger.debug(">>>> SQL RESULT : {}",resMap);
		}catch(SQLException e) {
			logger.error(">>>> MENULIST SERVICE ERROR");
			throw new Exception("ERROR ON GETTING INDIVISUAL MENULIST");
		}
		return resMap;
	}	
}
