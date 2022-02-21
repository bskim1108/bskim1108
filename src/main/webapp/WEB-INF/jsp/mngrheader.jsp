<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<a href="<c:url value='/back/managerMain.do'/>">관리자메인</a>
	<c:forEach items="${menuMap.menuList}" var="mlist">
	<c:if test="${mlist.menuFlag eq 'B'}">
		<a href="<c:url value='${mlist.menuPath}'/>">${mlist.menuName}</a>
	</c:if>
	</c:forEach>
	<a href="<c:url value='/login.jsp'/>">로그아웃</a>

</header>
   