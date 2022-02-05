<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩먼저 먼저 사용 후 내가 정해준 것 사용하는 순서로 진행 -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- 부트스트랩은 jquery 하단에 위치해야 함. -->
<script type="text/javascript" src="/mysite4/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

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
										<button id="btnSubmit2" type="submit">등록</button><!--위에서 form action을 막았기 때문에 등록버튼 눌러도 작동하지 않음-->
									</td>
								</tr>
							</tbody>
						</table>
					<!--</form>--%>
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



<!------------------------------------------------------------------>
<!-- 삭제 모달창 --><!-- 필요한 시기에 보임.평소에는 숨겨져있음. -->

<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호 입력 모달창</h4>
      </div>
      <div class="modal-body">
        
        비밀번호:
      	<input id="modalPassword" type="password" name="password" value=""><br>
        <input id="modalNo" type="text" name="no" value="">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- //삭제 모달창 -->

</body>




<script type="text/javascript">
	
	//1. 화면 로드되기 전에 요청(돔이 완성되었을 때)
	$(document).ready(function(){		// 문서가 준비되면 해당 function 실행	{}: 객체 []:배열
	
		// 1-1. 리스트 그리기(실행) (화면이 로드되기 전에 리스트 그리기)
		fetchList();	//아래에 function fetchList()로 정의해두기
	
	});	
	
	
	//2. 저장버튼이 클릭될때 (화면 로드 후)	- 파라미터 방식 요청
	$("#btnSubmit").on("click", function(){
		//console.log("클릭")
		
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
			dataType : "json",	//받는 형태
			success : function(guestbookVo){	//vo객체형태로 데이터가 오니 이름을 vo로 맞춰줌.
				/*성공시 처리해야될 코드 작성*/
				render(guestbookVo, 'up');	//새로 입력된 값은 위에 붙어야하니 up
				
				//값 입력 후 폼에서 입력란 값 없애기
				$("#input-uname").val("");	//아무것도 없는 문자열 넣기
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	//2. 저장버튼이 클릭될때 (화면 로드 후)	- json 방식 요청
	$("#btnSubmit2").on("click", function(){
		console.log("json 전송 클릭");
		
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
			//해당 url로 데이터 보내기
			url : "${pageContext.request.contextPath }/api/guestbook/write2",		
			type : "post",
			contentType : "application/json",	//보낼때 json으로 보냄
			data : JSON.stringify(guestbookVo),	//JS객체를 json형식으로 변경	
			
			dataType : "json",	//받는 형태
			success : function(guestbookVo){
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
	
	
	//3. 삭제팝업 버튼을 눌렀을 때
	//리스트영역은 돔형성 후에 추가된 것이라 click은 부모(listArea)에게 전달하고 자녀(.btnDelPop)에게 시킴	
	$("#listArea").on("click", ".btnDelPop", function(){	
		//3-1. 데이터 수집
		var $this = $(this);	// js 변수(다른 이름도 가능..) = jquery 형태.
		var no = $this.data("no");	//하단 button data-no의 no (이부분은 항상 소문자로 입력)
		console.log(no);
		
		//3-2. 회색바탕 & 팝업창 띄우기
		//초기화
		$("#modalPassword").val("");	//비밀번호 입력창 비우기
		$("#modalNo").val(no);	//해당건 번호 불러오기
		
		$("#delModal").modal("show");	//show는 생략 가능
		
	});
	
	//4. 모달창의 삭제버튼을 눌렀을 때
	$("#modalBtnDel").on("click", function(){
		console.log("모달창 삭제버튼 클릭")
		
		//4-1. 데이터 수집(no, password)
		var no = $("#modalNo").val();
		var pw = $("#modalPassword").val();
		
		var delInfoVo = {
				no: no,
				pw: pw	//(vo필드값: var pw)
		}
		
		console.log(delInfoVo);
		
		//4-2 ajax 요청 no, password
		$.ajax({	
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : delInfoVo,
			
			dataType : "json",
			success : function(result){
				console.log(result);
				
				if(result === 'success'){
					/*성공시 처리해야될 코드 작성*/
					//4-3. 화면에서 변경된 부분 반영
					
					//4-3-1. 해당 테이블 html 삭제
					$("#t"+no).remove();	//no는 위 delInfoVo로 모은 no임
						
					//4-3-2. 모달창 닫기
					$("#delModal").modal("hide");
					
				}else{
					$("#delModal").modal("hide");
					alert("비밀번호를 확인하세요");
				}				
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
				}							 //리스트 뿌리는거는 down으로...
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	}
	
	
	//1-2. 리스트 그리기	(function fetchList의 render() 밑으로 뺀거)
	function render(guestbookVo, updown){	
		
		var str = '';	//표 그리는 html코드 가져와 str에 넣기
		str += '<table id="t' +guestbookVo.no+ '" class="guestRead">';
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
		str += '		<td><button class = "btnDelPop" type = "button" data-no="'+guestbookVo.no+'">삭제</button></td>';
		str += '	</tr>';												//data-내가 정한 이름="데이터"(버튼에 값 숨겨두기)
		str += '	<tr>';												//여기는 대소문자 구분x. 그러니 이왕이면 소문자로 적기...
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
					  
		if(updown == 'down'){		
			$("#listArea").append(str);		//위에 있는 id=listArea에 위 문장(str) 넣기. html로 넣으면 계속 새로고침되니 append 사용해 뒤에 붙여주기.
		}else if(updown == 'up'){
			$("#listArea").prepend(str);
		}else{
			console.log("방향오류");
		} 
		//down, up: 변수이름
	};
	
	
	
</script>

</html>