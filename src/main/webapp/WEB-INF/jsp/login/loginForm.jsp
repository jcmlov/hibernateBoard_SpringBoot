<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/newNavbar.jsp"%>

<link href='/css/login/loginForm.css' rel='stylesheet' />
<script type="text/javascript">
	$(function(){
	});
</script>
<body>
	
	<div class="container">
		<div class="login-form">
		    <form action="/login/loginAction" method="post" id="loginForm" name="loginForm">
				<div class="avatar">
					<img src="http://cdn3.f-cdn.com/ppic/105311193/logo/26623052/TnqLe/profile_logo_.png" alt="yoatthmes"/>
				</div>
		        <h2 class="text-center">Member Login</h2>
				<div class="social-btn text-center">
					<a href="/login/facebook" class="btn btn-primary btn-block btn-lg"><i class="fa fa-facebook"></i> Sign in with <b>Facebook</b></a>
					<a href="#" class="btn btn-info btn-block btn-lg"><i class="fa fa-twitter"></i> Sign in with <b>Twitter</b></a>
					<a href="/login/google" class="btn btn-danger btn-block btn-lg"><i class="fa fa-google"></i> Sign in with <b>Google</b></a>
				</div>
				<!-- 
				<div class="or-seperator"><i>or</i></div>
				<div class="form-group">
					<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" required="required">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호" required="required">
				</div>
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary btn-lg btn-block login-btn" id="submitBtn">로그인</button>
		        </div>
				<p class="text-center small"><a href="#">Forgot Password?</a></p>
				-->
		    </form>
		    <!-- <p class="text-center small">Don't have an account? <a href="/member/memberForm">Sign up here!</a></p> -->
		</div>
	</div>
</body>
</html>