<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CMS MAINVIEW</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
	body {
		
	}
	.menu_wrap {
		position:relative;
		display:inline-block;
	}
	.auth_wrap {
		position:relative;
		display:inline-block;
	}
</style>
</head>
<body>
	<jsp:include page="mngrheader.jsp"/>

	<div class="menu_wrap cms_background">
		<h4>CMS MENUVIEW</h4>
		<table border="1" style="border-collapse:collapse;">
			<tr>
				<th>MENU_NAME</th>
				<th>사용여부</th>
			</tr>
			<c:forEach items="${cmsMap.menuList}" var="mlist">
			<tr>
				<td>${mlist.menuName}</td>
				<td style="text-align:center;">${mlist.useYn}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2" style="text-align:center;"><button onclick="changeScreen('menuChange_wrap');">메뉴 목록 변경</button></td>
			</tr>
		</table>
	</div>

	<div class="auth_wrap cms_background">
		<h4>CMS AUTH VIEW</h4>
		<table border="1" style="border-collapse:collapse;margin-left:50;">
			<tr>
				<th>AUTH_NAME</th>
				<th>사용여부</th>
			</tr>
			<c:forEach items="${cmsMap.authList}" var="alist">
			<tr>
				<td>${alist.authName}</td>
				<td style="text-align:center;">${alist.useYn}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2" style="text-align:center;"><button onclick="changeScreen('authChange_wrap');">권한 목록 변경</button></td>
			</tr>
		</table>
	</div>

	<div class="xTable cms_background">
		<h4></h4>
		<c:set value="${cmsMap.authXList}" var="colValue"/>
		<table border="1" style="border-collapse:collapse;">
			<tr>
				<th colspan="${fn:length(colValue)+1}">MENU TABLE BY AUTH</th>
			</tr>
			<tr>
				<td></td>
				<c:forEach items="${cmsMap.authXList}" var="alist">
				<td>${alist.authName}</td>
				</c:forEach>
			</tr>
			<c:forEach items="${cmsMap.menuXList}" var="mlist">
			<tr>
				<td>${mlist.menuName}</td>
				<c:forEach items="${cmsMap.authXList}" var="alist">
					<c:set value="N" var="useAM"/>
					<c:forEach items="${cmsMap.xTableList}" var="xlist">
						<c:if test="${mlist.menuCode eq xlist.menuCode && alist.authCode eq xlist.authCode}">
							<c:set value="Y" var="useAM"/>
						</c:if>
					</c:forEach>
					<c:if test="${useAM eq 'Y'}"><td style="text-align:center;">O</td></c:if>
					<c:if test="${useAM eq 'N'}"><td style="text-align:center;">X</td></c:if>
				</c:forEach>
			</tr>
			</c:forEach>
			<tr>
				<td style="text-align:center;"
					colspan="${fn:length(colValue)+1}"><button onclick="changeScreen('xtableChange_wrap');">권한별 메뉴 설정 변경</button></td>
			</tr>
		</table>
	</div>
	<div class="menuChange_wrap" style="display:none;">
		<button onclick="changeScreen('back');">돌아가기</button>
		<div class="sub_menuChange">
			<h4>MENU SETTING PAGE</h4>
			<table border="1" style="border-collapse:collapse;">
				<tr>
					<th>NO</th>
					<th>MENU_NAME</th>
					<th>MENU_PATH</th>
					<th>메뉴구분</th>
					<th>사용</th>
					<th>사용안함</th>
					<th>삭제</th>
					<th>변경</th>
				</tr>
				<c:forEach items="${cmsMap.menuList}" var="mlist" varStatus="mIndex">
					<input type="hidden" name="menuCode_${mIndex.count}" value="${mlist.menuCode}"/>
				<tr>
					<td>${mIndex.count}</td>
					<td><input type="text" name="menuName_${mIndex.count}" value="${mlist.menuName}"/></td>
					<td><input type="text" name="menuPath_${mIndex.count}" value="${mlist.menuPath}"/></td>
					<td>
						<select name="menuFlag_${mIndex.count}">
							<option value="F" <c:if test="${mlist.menuFlag eq 'F'}">selected</c:if>>사용자</option>
							<option value="B" <c:if test="${mlist.menuFlag eq 'B'}">selected</c:if>>관리자</option>
						</select>
					</td>
					<td style="text-align:center;">
						<input type="radio" name="menuUseYn_${mIndex.count}" id="" value="Y" <c:if test="${mlist.useYn eq 'Y'}">checked</c:if>/>
					</td>
					<td style="text-align:center;">
						<input type="radio" name="menuUseYn_${mIndex.count}" id="" value="N" <c:if test="${mlist.useYn eq 'N'}">checked</c:if>/>	
					</td>
					<td><input class="menu" data-index="${mIndex.count}" type="button" name="deleteButton" id="deleteButton_${mIndex.count}" value="삭제"/></td>
					<td><input data-index="${mIndex.count}" type="button" name="modifyMenu" id="modifyMenu_${mIndex.count}" value="변경"/></td>
				</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td colspan="6" style="text-align:center;"><button onclick="addColumn('sub_menuChange')">메뉴행 추가</button></td>
				</tr>
			</table>		
		</div>
	</div>

	<div class="authChange_wrap" style="display:none;">
		<button onclick="changeScreen('back');">돌아가기</button>
		<div class="sub_authChange">
			<h4>AUTH SETTING PAGE</h4>
			<table border="1" style="border-collapse:collapse;">
				<tr>
					<th>NO</th>
					<th>AUTH_NAME</th>
					<th>사용</th>
					<th>사용안함</th>
					<th>삭제</th>
					<th>변경</th>
				</tr>
				<c:forEach items="${cmsMap.authList}" var="alist" varStatus="aIndex">
					<input type="hidden" name="authCode_${aIndex.count}" value="${alist.authCode}"/>
				<tr>
					<td>${aIndex.count}</td>
					<td><input type="text" name="authName_${aIndex.count}" value="${alist.authName}"/></td>
					<td style="text-align:center;">
						<input type="radio" name="authUseYn_${aIndex.count}" id="" value="Y" <c:if test="${alist.useYn eq 'Y'}">checked</c:if>/>
					</td>
					<td style="text-align:center;">
						<input type="radio" name="authUseYn_${aIndex.count}" id="" value="N" <c:if test="${alist.useYn eq 'N'}">checked</c:if>/>	
					</td>
					<td><input class="auth" data-index="${aIndex.count}" type="button" name="deleteButton" id="deleteButton_${aIndex.count}" value="삭제"/></td>
					<td><input data-index="${aIndex.count}" type="button" name="modifyAuth" id="modifyAuth_${aIndex.count}" value="변경"/></td>
				</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td colspan="6" style="text-align:center;"><button onclick="addColumn('sub_authChange')">권한행 추가</button></td>
				</tr>
			</table>		
		</div>
	</div>

	<div class="xtableChange_wrap" style="display:none;">
		<button onclick="changeScreen('back');">돌아가기</button>
		<h4>MENU TABLE BY AUTH</h4>
		<c:set value="${cmsMap.authXList}" var="colValue"/>
		<div class="sub_Xtable">
			<table border="1" style="border-collapse:collapse;">
				<tr>
					<td></td>
					<c:forEach items="${cmsMap.authXList}" var="alist">
					<td>${alist.authName}</td>
					</c:forEach>
				</tr>
				<c:forEach items="${cmsMap.menuXList}" var="mlist">
				<tr>
					<td>${mlist.menuName}</td>
					<c:forEach items="${cmsMap.authXList}" var="alist">
						<c:set value="N" var="useAM"/>
						<c:forEach items="${cmsMap.xTableList}" var="xlist">
							<c:if test="${mlist.menuCode eq xlist.menuCode && alist.authCode eq xlist.authCode}">
								<c:set value="Y" var="useAM"/>
							</c:if>
						</c:forEach>
						<c:if test="${useAM eq 'Y'}"><td style="text-align:center;"><input type="checkbox" name="checkedAM" value="${mlist.menuCode}_${alist.authCode}" checked></td></c:if>
						<c:if test="${useAM eq 'N'}"><td style="text-align:center;"><input type="checkbox" name="checkedAM" value="${mlist.menuCode}_${alist.authCode}"></td></c:if>
					</c:forEach>
				</tr>
				</c:forEach>
				<tr>
					<td style="text-align:center;"
						colspan="${fn:length(colValue)+1}"><button onclick="changeCMS();">변경 적용하기</button></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
	//권한별 메뉴 설정 변경하기
	function changeCMS() {
		var listObj = new Array();
		
		$("input[name=checkedAM]").each(function(){
			if($(this).prop('checked') == true) {
				var obj = new Object;
 				obj.menuCode = $(this).val().substring(0,7);
 				obj.authCode = $(this).val().substring(8);
				listObj.push(obj);
			}
		});
		console.log(listObj);
		
		$.ajax({
			url	: "<c:url value='/xtable/insertXTable.do'/>",
			data : JSON.stringify(listObj),
			method : "POST",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : true,
			success : function(data){
				console.log(data);
				alert(data.answer);
			},
			error : function (){
			}
		});
	}

	//메뉴 변경
	$("input[name=modifyMenu]").on('click', function(){
		var dataIndex = $(this).data("index");
		var obj = {};
		obj["menuCode"] = $("input[name=menuCode_"+dataIndex+"]").val();
		obj["menuName"] = $("input[name=menuName_"+dataIndex+"]").val();
		obj["menuPath"] = $("input[name=menuPath_"+dataIndex+"]").val();
		obj["menuFlag"] = $("select[name=menuFlag_"+dataIndex+"]").val();
		obj["useYn"] = $("input[name=menuUseYn_"+dataIndex+"]:checked").val();
		console.log(obj);

		$.ajax({
			url	: "<c:url value='/menu/modifyMenu.do'/>",
			data : obj,
			method : "POST",
			dataType : "json",
			async : true,
			success : function(data){
				console.log(data);
				alert(data.answer);
			},
			error : function (){
			}
		});
	});
	//권한 변경
	$("input[name=modifyAuth]").on('click', function(){
		var dataIndex = $(this).data("index");
		var obj = {};
		obj["authCode"] = $("input[name=authCode_"+dataIndex+"]").val();
		obj["authName"] = $("input[name=authName_"+dataIndex+"]").val();
		obj["useYn"] = $("input[name=authUseYn_"+dataIndex+"]:checked").val();
		console.log(obj);
		$.ajax({
			url	: "<c:url value='/auth/modifyAuth.do'/>",
			data : obj,
			method : "POST",
			dataType : "json",
			async : true,
			success : function(data){
				console.log(data);
				alert(data.answer);
			},
			error : function (){
			}
		});
	});
	//삭제버튼
	$("input[name=deleteButton]").on('click', function(){
		var trgt = "";
		var url = "";
		
		if($(this).attr('class') == "menu") {
			trgt = "menu";
			url = "<c:url value='/"+trgt+"/deleteMenu.do'/>"
		}
		else if($(this).attr('class') == "auth") {
			trgt = "auth";
			url = "<c:url value='/"+trgt+"/deleteAuth.do'/>";
		}
		
		var dataIndex = $(this).data("index");
		var obj = {};

		obj[trgt+"Code"] = $("input[name="+trgt+"Code_"+dataIndex+"]").val();
		console.log(obj);
		
		
		$.ajax({
			url	: url,
			data : obj,
			method : "POST",
			dataType : "json",
			async : true,
			success : function(data){
				console.log(data);
				alert(data.answer);
				if(data.answer == "SUCCESS") {
			 		$("#deleteButton_"+dataIndex).attr('type','text');
			 		$("#deleteButton_"+dataIndex).css('border-style','none');
			 		$("#deleteButton_"+dataIndex).val('DELETED');
			 		$("#deleteButton_"+dataIndex).attr('size',7);
		 			$("#deleteButton_"+dataIndex).attr('disabled',true);
		 			$("#modifyMenu_"+dataIndex).attr("type","hidden");
		 			$("#modifyAuth_"+dataIndex).attr("type","hidden");
				}
			},
			error : function (request){
			}
		});
	});
	//화면변경
	function changeScreen(screenName){
		if(screenName == "back") {
			window.location.reload();
		}
		else if(screenName == "menuAjax" ) {
			updateMenu();
		}
		else {
 			$(".cms_background").hide();
 			$("."+screenName).show();
 		}
	}
	
	//메뉴행 추가
	function addColumn(objTable) {
		var indexNo = $("."+objTable+" > table > tbody > tr").length - 1;
		var addDiv = $("."+objTable+" > table > tbody").children(":last");
		var trgt = "";
		
		if(objTable == "sub_menuChange" ) {
			trgt = "menu";
			addDiv.before("<tr>"
					+"<td>"+indexNo+"</td>"
					+"<td><input type='text' name='"+trgt+"Name_"+indexNo+"' id='"+trgt+"Name_"+indexNo+"'></td>"
					+"<td><input type='text' name='"+trgt+"Path_"+indexNo+"' id='"+trgt+"Name_"+indexNo+"'></td>"
					+"<td><select name='"+trgt+"Flag_"+indexNo+"'><option value='F' selected>사용자</option><option value='B'>관리자</option></select></td>"
					+"<td style='text-align:center;'><input type='radio' name='"+trgt+"UseYn_"+indexNo+"' value='Y' checked></td>"
					+"<td style='text-align:center;'><input type='radio' name='"+trgt+"UseYn_"+indexNo+"' value='N'></td>"
					+"<td style='text-align:center;' colspan='2'><input data-index='"+indexNo+"' type='button' name='insertButton_"+indexNo+"' id='insertButton_"+indexNo+"' value='"+trgt+"목록 추가'/></td>"
					+"</tr>");
			
		}
		else if(objTable == "sub_authChange" ) {
			trgt = "auth";
			addDiv.before("<tr>"
					+"<td>"+indexNo+"</td>"
					+"<td><input type='text' name='"+trgt+"Name_"+indexNo+"' id='"+trgt+"Name_"+indexNo+"'></td>"
					+"<td style='text-align:center;'><input type='radio' name='"+trgt+"UseYn_"+indexNo+"' value='Y' checked></td>"
					+"<td style='text-align:center;'><input type='radio' name='"+trgt+"UseYn_"+indexNo+"' value='N'></td>"
					+"<td style='text-align:center;' colspan='2'><input data-index='"+indexNo+"' type='button' name='insertButton_"+indexNo+"' id='insertButton_"+indexNo+"' value='"+trgt+"목록 추가'/></td>"
					+"</tr>");
		}
		

		$("#insertButton_"+indexNo).on('click',function(){
			
			var obj = {};
			obj[trgt+"Name"] = $("input[name="+trgt+"Name_"+indexNo+"]").val();
			obj[trgt+"Path"] = $("input[name="+trgt+"Path_"+indexNo+"]").val();
			obj[trgt+"Flag"] = $("select[name="+trgt+"Path_"+indexNo+"]").val();
			obj["useYn"] = $("input[name="+trgt+"UseYn_"+indexNo+"]:checked").val();
			console.log(obj);
			var url = "";
			if( trgt == "menu") url = "<c:url value='/"+trgt+"/insertMenu.do'/>";
			else if (trgt == "auth") url = "<c:url value='/"+trgt+"/insertAuth.do'/>"
			$.ajax({
				url	: url,
				data : obj,
				method : "POST",
				dataType : "json",
				async : true,
				success : function(data){
					console.log(data);
					alert(data.answer);
					if(data.answer=="SUCCESS") {
						$("#insertButton_"+indexNo).attr('type','text');
						$("#insertButton_"+indexNo).css('border-style','none');
						$("#insertButton_"+indexNo).val('추가됨');
						$("#insertButton_"+indexNo).attr('size',7);
						$("#insertButton_"+indexNo).attr('disabled',true);
					}
				},
				error : function (){
				}
			});
		});
	
	}
</script>
</html>	