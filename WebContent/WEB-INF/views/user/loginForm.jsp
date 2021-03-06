<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	String result = request.getParameter("result");  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite2/assets/css/user.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">

		<div id="header">
			<h1>
				<a href="/mysite2/main">MySite</a>
			</h1>

			<ul>
				<li><a href="/mysite2/user?action=loginForm">로그인</a></li>
				<li><a href="/mysite2/user?action=joinForm">회원가입</a></li>
			</ul>
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="/mysite2/user?action=deleteForm">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<jsp:include page="/WEB-INF/views/include/asideUser.jsp"></jsp:include>
		<!-- //aside -->
		
	
		<div id="content">
			
			<div id="content-head">
            	<h3><a href="/mysite2/user?action=loginForm">로그인</a></h3>
            	<div id="location">
            		<ul>
            			<li><a href="/mysite2/main">홈</a></li>
            			<li>회원</li>
            			<li class="last"><a href="/mysite2/user?action=loginForm">로그인</a></li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
             <!-- //content-head -->

			<div id="user">
				<div id="loginForm">
					<form action="/mysite2/user" method="get">

						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
						</div>

						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">비밀번호</label> 
							<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
						</div>
						
						<% if ("fail".equals(result)) { %>
						<p>
						로그인에 실패했습니다. 다시 로그인해주세요
						</p>
						<% } %>
						<!-- 버튼영역 -->
		                <div class="button-area">
		                    <button type="submit" id="btn-submit">로그인</button>
		                </div>
		                <input type="text" name="action" value ="login">
						
					</form>
				</div>
				<!-- //loginForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>