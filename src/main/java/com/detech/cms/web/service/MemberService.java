package com.detech.cms.web.service;

import java.util.Map;

public interface MemberService {

	Map<String, Object> getMemberInfo() throws Exception;

	void modifyAuth(Map<String, Object> reqParam) throws Exception;
	
}
