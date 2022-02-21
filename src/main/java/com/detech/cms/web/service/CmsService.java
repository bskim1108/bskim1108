package com.detech.cms.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface CmsService {

	Map<String, Object> getMenuList() throws Exception;

	void deleteMenu(Map<String, Object> reqParam) throws Exception;

	void modifyMenu(Map<String, Object> reqParam) throws Exception;

	void insertMenu(Map<String, Object> reqParam) throws Exception;

	void deleteAuth(Map<String, Object> reqParam) throws Exception;

	void modifyAuth(Map<String, Object> reqParam) throws Exception;

	void insertAuth(Map<String, Object> reqParam) throws Exception;

	void insertXTable(List<Map<String, Object>> reqParam) throws Exception;

}
