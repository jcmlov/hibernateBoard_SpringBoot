<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
  $(function(){
	 
	$("#logInMember").click(function() {
		location.href="/login/loginForm";
	});
	
	$("#logOutMember").click(function() {
		if(confirm("로그아웃 하시겠습니까?")) {
			location.href="/login/logOutAction";	
		}
	});
	
	$("#updateMemberInfo").click(function() {
		if(confirm("개인정보를 수정하시겠습니까?")) {
			location.href="/member/memberUpdateForm?memberNo=" + $("#uNo").val();	
		}
	});
  });
</script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">PortFolio</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
					<a href="/main">Home</a>
				</li>
				<li>
					<a href="#">About Us</a>
				</li>
				<li>
					<a href="/portfolio/portfolioList">포트폴리오</a>
				</li>
				<li>
					<a href="/board/boardList">게시판</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#" id="updateMemberInfo">개인정보 수정</a></li>
					</ul>
                </li>
                <li>
					<a href="#" id="logOutMember">로그아웃</a>
				</li>
				<li>
					<a href="/member/memberForm">회원가입</a>
				</li>
				<li>
					<a href="/login/loginForm">로그인</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-collapse -->
</nav>