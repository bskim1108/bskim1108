package com.detech.cms.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("memberMapper")
public interface MemberMapper {

	List<Map<String, Object>> getMemberInfo() throws SQLException;
	Map<String, Object> getMemberInfo(Map<String, Object> reqParam) throws SQLException;
	List<Map<String, Object>> getComboAuth() throws SQLException;
	void updateAuth(Map<String, Object> reqParam) throws SQLException;

}
