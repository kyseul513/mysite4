<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header, nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>

						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>			<!-- requestScope은 생략가능 -->
							<c:forEach items="${requestScope.pMap.boardList}" var="vo">
							<tbody>
								<tr>
									<td>${vo.no}</td>	<!-- vo상 이름 입력. -->
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/read?no=${vo.no}">${vo.title}</a></td>
									<td>${vo.name}</td>
									<td>${vo.hit}</td>
									<td>${vo.reg_date}</td>
									<c:if test="${authUser.no eq vo.user_no}">
									<td><a href="${pageContext.request.contextPath}/board/delete?no=${vo.no}">[삭제]</a></td>
									</c:if>
								</tr>
							</tbody>
							</c:forEach>
						</table>
						

						<div id="paging">
							<ul>
								<c:if test = "${requestScope.pMap.prev == true}">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.startPageBtnNo-1}">◀</a></li>
								</c:if>
																																			<!-- var: 변수. 돌아가고 있는 값을 넣어줌 -->
								<c:forEach begin = "${requestScope.pMap.startPageBtnNo}" end = "${requestScope.pMap.endPageBtnNo}" step="1" var="page">
																																  <!-- step:한번 돌 때 숫자가 얼마씩 올라가는지 -->	
									<li class="active"><a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page}</a></li><!-- 번호 클릭시 해당 페이지로 이동 -->
								</c:forEach>
					
								<c:if test = "${requestScope.pMap.next == true}">
								<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
								</c:if>
							</ul>


							<div class="clear"></div>
						</div>
						
						<c:choose>
							<c:when test="${authUser eq null}">
								
							</c:when>
							
							<c:otherwise>
								<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>