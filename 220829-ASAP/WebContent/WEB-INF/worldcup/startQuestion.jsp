<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토너먼트 형식 보여주는 테이블</title>
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script>
  /* 버튼에 클릭 이벤트를 준다. 버튼을 클릭할 때, 버튼의 id가 콘솔에 출력된다.  */
    $(document).ready(function(){
        $(".click-btn").on('click', function(e){
            console.log(e.target.value)
        });
    });
   /*  //버튼을 모두 선택하지 않으면, 제출을 막고 메세지창을 띄운다. 
    function jbSubmit() {//제출을 했을 때 이벤트를 건다. 
    	//el이 안먹음....문법 오류인듯,.....
	  	for( var j = 0 ; j< '${name.size()-1}' ; j++){
    	let name = 'name'+j;
    	console.log(name); 이름 제대로 된다. 
		    if(!document.getElementsByName(name).checked) {
					alert("전부 체크해주세요");
					return false; // 제출을 막는다. 
					break;
					 j='${name.size()-1}'; //메세지창이 여러개 뜨지 않도록 막는다. 
		    }
	  	}
    } */

</script>
</head>
<body>
	<!-- 페이지는 넘겨진다 . 값도 넘겨짐! 이거를 하나씩 출력하는거 해야함   -->
	<h1>질문지 폼</h1>
	<!-- 출력이 페이징 메소드 연결해서 그만큼 출력되어야 함.  -->
<form action="first.do" method="POST" onsubmit="return jbSubmit();">
	<c:forEach var="i" begin="0" end="${question.size()-1}"><!-- 시작이랑 끝 숫자도 값을 받아와서 바꿔야한다.  -->
		
		<!-- 라디오 버튼으로 두 개씩 묶어야 함 !! 이름을 name이라는 리스트로 넘겨 받았다. -->
		<input type="radio" name="${name[i]}" class="click-btn" value="${question[i].question_id}"  required="required"/>${question[i].question_text}
		
		<!-- 질문 두 개씩 나눈다.  -->
		<c:if test="${(i+1)%2==0}">
			<hr/>
		</c:if>
	</c:forEach>
	<input type ="submit" value="제출"/>
</form>
</head>

</body>
</html>