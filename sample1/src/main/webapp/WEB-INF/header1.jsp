<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<title>Insert title here</title>
<style>
header {
	color: #fff;
	padding: 20px;
}

#headermenu a {
	float: right;
	font-weight: bold;
	list-style-type: none;
	text-decoration: none;
	padding-right: 15px;
	color: black;
	text-decoration: none;
}

.headermenu span {
	margin-right: 15px;
}
</style>
</head>
<body>
<div id="headerApp">
<header>

	<span id="headermenu">
			<span v-if=' ${sessionId} != ""'><a href="login.do">로그아웃</a></span>
			<span class="loginbtn" v-else><a href="login.do">로그인</a></span>
			<span class="#"><a href="#">장바구니</a></span>
			<span class="#"><a href="#">관심상품</a></span>
			<span class="#"><a href="mypage.do">마이페이지</a></span>
	</span>

</header>
</div>
</body>
</html>
<script>
var app = new Vue({
	el : '#headerApp',
	data : {
		loginOut : "${sessionId}"
	},// data
	methods : {
		fnCheck : function(){
			var self = this;
			var nparmap = {}
			  $.ajax({
	                url:"list.dox", 
	                dataType:"json", 
	                type : "POST",  
	                data : nparmap, 
	                success : function(data) {  
						
	                }
	            });  
		},
	}, // methods
	created : function() {
		var self = this;
		
	}// created
});
</script>