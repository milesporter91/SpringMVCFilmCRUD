<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Site</title>
</head>
<body>

	<h1>Welcome to Film Site</h1>
	<form action="GetFilmById.do" method="GET">
		Film ID: <input type="text" name="filmid" /> <input type="submit"
			value="Find Film by ID" />
	</form>
	<form action="GetFilmByKeyword.do">
		Keyword: <input type="text" name="keyword" /> <input type="submit"
			value="Find Film by Keyword" />
	</form>
	<form action="addFilmForm.do" method="GET">
		<input type="submit" value="Add New Film">
	</form>

</body>
</html>