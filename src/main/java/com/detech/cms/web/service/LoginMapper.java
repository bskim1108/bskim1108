package com.detech.cms.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface LoginMapper {

	int getUserId(Map<String, Object> reqParam) throws SQLException;
	int getUserInfo(Map<String, Object> reqParam) throws SQLException;
	List<Map<String, Object>> getMenuListbyAuth(Map<String, Object> reqParam) throws SQLException;
}
