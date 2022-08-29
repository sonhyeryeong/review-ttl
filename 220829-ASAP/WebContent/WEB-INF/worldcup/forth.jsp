<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script>
  /* 버튼에 클릭 이벤트를 준다. 버튼을 클릭할 때, 버튼의 id가 콘솔에 출력된다.  */
    $(document).ready(function(){
        $(".click-btn").on('click', function(e){
            console.log(e.target.value)
        });
    });
   
</script>
</head>
<body>
<form action=" " method="POST" onsubmit="return jbSubmit();">
	<c:forEach var ="i" begin="0" end="${question.size()-1}">
		<!-- 라디오 버튼으로 두 개씩 묶어야 함 !! 이름을 name이라는 리스트로 넘겨 받았다. -->
		<input type="radio" name="${name[i]}" class="click-btn" value="${question[i].question_id}" required="required"/>${question[i].question_text}
		<!-- 질문 두 개씩 나눈다.  -->
		<c:if test="${(i+1)%2==0}">
			<hr/>
		</c:if>
	</c:forEach>
	
	                                                                                                                               
	<p>부전승: "${walkOver.question_text}"</p>
	<input type="hidden" value="${walkOver.question_id }" name="walkOver"/>
	<input type ="submit" value="제출"/>
</form>
</body>
</html>