package com.detech.cms.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("cmsMapper")
public interface CmsMapper {
	//메인 SQL Mapper
	public List<Map<String, Object>> getMenuList(String str) throws SQLException;
	public List<Map<String, Object>> getAuthList(String str) throws SQLException;
	public List<Map<String, Object>> getMenuListAcctoAuth() throws SQLException;

	//메뉴 설정 SQL Mapper
	public void deleteMenu(Map<String, Object> reqParam) throws SQLException;
	public void modifyMenu(Map<String, Object> reqParam) throws SQLException;
	public String getNewMenuCode() throws SQLException;
	public void insertMenu(Map<String, Object> reqParam) throws SQLException;

	//권한 설정 SQL Mapper
	public void deleteAuth(Map<String, Object> reqParam) throws SQLException;
	public void modifyAuth(Map<String, Object> reqParam) throws SQLException;
	public String getNewAuthCode() throws SQLException;
	public void insertAuth(Map<String, Object> reqParam) throws SQLException;

	//Xtable 변경 Mapper
	public void deleteXTable() throws SQLException;
	public void insertXTable(Map<String, Object> tempMap) throws SQLException;
	
}
