<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var ="i" begin="0" end="${list.size()-1}">
		<p>${list[i].question_id} ${list[i].question_text}</p>
	</c:forEach>

</body>
</html>