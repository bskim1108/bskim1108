<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<a href="">회사소개</a>
	<a href="">게시판</a>
	<c:forEach items="${menuMap.menuList}" var="mlist">
		<c:choose>
		<c:when test="${mlist.menuFlag eq 'F' }">
			<a href="<c:url value='${mlist.menuPath}'/>">${mlist.menuName}</a>
		</c:when>
		<c:when test="${mlist.menuFlag eq 'B' }">
			<c:set value="Y" var="Manager"/>
		</c:when>
		</c:choose>
	</c:forEach>
	<c:if test="${Manager eq 'Y'}">
		<a href="<c:url value='/back/managerMain.do'/>">관리자</a>
	</c:if>
	<c:choose>
		<c:when test="${not empty menuMap.menuList}">
			<a href="<c:url value='/login.jsp'/>">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:void(0);" onclick="loginPopup()"/>로그인</a>
		</c:otherwise>
	</c:choose>
</header>
   