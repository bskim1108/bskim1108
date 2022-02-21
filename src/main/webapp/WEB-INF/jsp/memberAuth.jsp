<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBERINFO PAGE</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<jsp:include page="mngrheader.jsp"/>
	<div class="member_wrap">
	<h4>회원 권한 관리</h4>
	<table border="1" style="border-collapse:collapse;">
		<tr>
			<th>NO</th>
			<th>회원명</th>
			<th>회원 ID</th>
			<th>권한</th>
			<th>변경</th>
		</tr>
		<c:forEach items="${memberMap.memberList}" var="mlist" varStatus="noIndex">
		<tr>
			<td>${noIndex.count}</td>
			<td>${mlist.name}</td>
			<td>${mlist.userId}</td>
			<td><select id="authSelect_${mlist.userId}">
			<c:forEach items="${memberMap.comboList}" var="clist">
				<option value="${clist.authCode}" <c:if test="${mlist.authCode eq clist.authCode}">selected</c:if>>${clist.authName}</option>
			</c:forEach>
			</select></td>
			<td><input type="button" onclick="authChange('${mlist.userId}')" value="변경"/></td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
<script>
	function authChange(userInfo) {
		var obj = {};
		
		obj["userId"] = userInfo;
		obj["authCode"] = $("#authSelect_"+userInfo+" option:selected").val();
		
		console.log(obj);
		
		$.ajax({
			url	: "<c:url value='/member/changeAuth.do'/>",
			data : obj,
			method : "POST",
			dataType : "json",
			success : function(data){
				console.log(data);
				if(data.answer == "SUCCESS") {
					alert("권한이 변경 되었습니다.");
				}
			},
			error : function (request){
			}
		});

	}
</script>
</html>