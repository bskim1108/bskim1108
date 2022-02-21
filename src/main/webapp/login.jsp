<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
	.mainView{ padding-top:100px; font-size:8em; text-align:center;}
	.loginView{ display:none; padding-top:100px;}
</style>
</head>
<body>
	<jsp:include page='/WEB-INF/jsp/header.jsp'/>
	<div class="mainView">
		회사소개				
	</div>

	<div class="loginView">
		<table align="center">
			<tr>
				<th colspan="2">로그인</th>
			</tr>
			<tr>
				<td>아이디(ID)</td>
				<td><input type="text" name="id" value=""/></td>
			</tr>
			<tr>
				<td>비밀번호(PW)</td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input style="font-size:4em;" type="button" onclick="javascript:checkUser();" value="LOGIN"/></td>
			</tr>
		</table>
	</div>
</body>

<script>
	function loginPopup() {
		$(".mainView").hide();
		$(".loginView").css('display','block');
	}
	
	function checkUser(){
		let obj = {};
		let id = $('input[name=id]').val();
		let password = $("input[name=password]").val();
		
		if( !id || id.lenght === 0 || id == undefined) {
			alert("아이디를 입력하세요");
			return false;
		}else if(!password || password.length === 0 || id == undefined) {
			alert("비밀번호를 입력하세요");
			return false;
		}else {
			obj["userId"] = id;
			obj["password"] = password;
		}

		$.ajax({
			url	: "<c:url value='/login/loginCheck.do'/>",
			data : obj,
			method : "POST",
			dataType : "json",
			async : true,
			success : function(data){
				console.log(data);
				if( data.answer == "SUCCESS") {
					window.location = "<c:url value='/login/main.do?userId="+id+"&authCode="+data.userAuth+"'/>";
					return true;
				}else{
					alert(data.answer);
				}
			},
			error : function (){
				alert("로그인 에러");
			}
		});
	};
</script>
</html>