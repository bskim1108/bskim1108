package com.detech.cms.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MemverServiceImpl implements MemberService {

	private Logger logger = LogManager.getLogger("egovLogger");

	@Resource
	MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> getMemberInfo() throws Exception {
		logger.debug(">>>> MEMBERINFO SERVICE ENTERED");
		Map<String,Object> memberMap = new HashMap<>();
		
		try {
			memberMap.put("memberList", memberMapper.getMemberInfo());
			memberMap.put("comboList", memberMapper.getComboAuth());
		}catch(SQLException e) {
			logger.error(">>>> MEMBERINFO SERVICE ERROR");
			throw new Exception("ERROR ON GETTING MEMBERINFO");
		}
		return memberMap;
	}

	@Override
	public void modifyAuth(Map<String, Object> reqParam) throws Exception {
		logger.debug(">>>> AUTHCHANGE SERVICE ENTERED");
		
		try {
			memberMapper.updateAuth(reqParam);
		}catch(SQLException e) {
			logger.error(">>>> MEMBERINFO SERVICE ERROR");
			throw new Exception("ERROR ON MODIFYING MEMBER'S AUTHORIZATION KEY ");
		}
		
	}

}
