<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/mysite4/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header nav영역 -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header nav영역 -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<!--<form action="${pageContext.request.contextPath}/guestbook/write" method="get"> -->
						<!--위 형태를 사용하게 되면 파라미터값으로 넘어가기 때문에 여기서는 사용하지 않음. -->
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="pw"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit" type="submit">등록</button><!--위에서 form action을 막았기 때문에 등록버튼 눌러도 작동하지 않음-->
									</td>
								</tr>
							</tbody>
						</table>
					<%--</form>--%>
					<!-- //guestWrite -->
					
					
					
					<div id="listArea">	
					<!-- 리스트 출력을 위한 영역지정을 위해 껍데기 만들기 -->
					<!-- 아래 js영역에서 이곳에 들어갈 리스트 작성 -->
					</div>
				
				
				
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

		<!-- //footer영역 -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	
	//1. 화면 로드되기 전에 요청
	$(document).ready(function(){		// 문서가 준비되면 해당 function 실행	{}: 객체 []:배열
	
		// 1-1. 리스트 그리기(실행) (화면이 로드되기 전에 리스트 그리기)
		fetchList();	//아래에 function fetchList()로 정의해두기
	
	});	
	
	
	//2. 저장버튼이 클릭될때 (화면 로드 후)
	$("#btnSubmit").on("click", function(){
		console.log("클릭")
		
		//2-1. 폼에 입력된 데이터를 하나로 모음
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();	//속성으로 가져오기
		
		//2-2. 객체 만들기 (자바스크립트 객체)
		var guestbookVo = {
			name: name,		// 필드명(vo이름): 위에서 모은 실제 데이터(var name)
			pw: password,
			content: content
		};
		
		//확인
		console.log(guestbookVo);
		
		//2-3. 요청
		$.ajax({
			//해당 url로 데이터 보내기 (파라미터 형태로 감)
			url : "${pageContext.request.contextPath }/api/guestbook/write",		
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,		//위에서 name, password, content를 모은 guestbookVo 넘기기
									//{name: name, password: password} 형태로도 사용 가능	
									//위에서 변수 만들지 말고 name: $("#input-uname").val()로 입력 가능
			dataType : "json",
			success : function(guestbookVo){	//vo객체형태로 데이터가 오니 이름을 vo로 맞춰줌.
				/*성공시 처리해야될 코드 작성*/
				render(guestbookVo, 'up');
				
				//값 입력 후 폼에서 입력란 값 없애기
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	
	//1-1. 데이터 가져와서 리스트 출력까지 시키기...(정의)
	function fetchList(){
		$.ajax({
			//요청할때 쓰는 항목			
			url : "${pageContext.request.contextPath }/api/guestbook/List",		//리스트 주소		
			type : "post",		//get으로 보낼지 post로 보낼지
			//contentType : "application/json",
			//data : {name: "홍길동"},
			
			//응답할때 쓰는 항목
			dataType : "json",
			success : function(guestbookList){		//json -> js 변환되어 들어감.
				
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);
				//console.log(guestbookList[0].name);		//받아온 리스트의 첫번째 데이터의 이름 출력
				
				
				//여기에 리스트를 그리면 코드가 너무 길어지니 아래에 메소드로 뺌.(function render)
				
				
				//리스트가 여러개이니 for문으로 돌림 -> 1-1. 리스트 그리기를 시킴
				for(var i=0; i<guestbookList.length; i++){
					render(guestbookList[i], 'down');		//1-2. 방명록리스트 그리기. guestbookList에서 i번째에 있는 값 뽑아내기	
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	}
	
	
	//1-2. 리스트 그리기	(function fetchList의 render() 밑으로 뺀거)
	function render(guestbookVo, updown){	
		
		var str = '';	//표 그리는 html코드 가져와 str에 넣기
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+guestbookVo.no+'</td>';		// 자바스크립트의 변수
		str += '		<td>'+guestbookVo.name+'</td>';		//no, name, regDate는 vo의 필드명.
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><a href="${pageContext.request.contextPath}/guestbook/deleteForm?no=${guestVo.no}">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(updown == 'down'){		//
			$("#listArea").append(str);		//위에 있는 id=listArea에 위 문장(str) 넣기. html로 넣으면 계속 새로고침되니 append 사용해 뒤에 붙여주기.
		}else if(updown == 'up'){
			$("#listArea").prepend(str);
		}else{
			console.log("방향오류");
		}
		
	};
	
	
	
</script>

</html>