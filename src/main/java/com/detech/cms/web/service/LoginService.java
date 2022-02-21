package com.detech.cms.web.service;

import java.util.Map;

public interface LoginService {

	int checkUser(Map<String, Object> reqParam) throws Exception;
	int checkPassword(Map<String, Object> reqParam) throws Exception;
	Map<String, Object> getUserAuth(Map<String, Object> reqParam) throws Exception;
	Map<String, Object> getMenubyAuth(Map<String, Object> reqParam) throws Exception;
}
