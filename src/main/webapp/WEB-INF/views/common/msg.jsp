<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지</title>
</head>
<body>

	<script type="text/javascript">
		alert("${msg}");
		location.replace("${pageContext.request.contextPath}${loc}");
		/* (유의)"" 빼먹지 않기 */
	</script>

</body>
</html>