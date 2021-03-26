<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Student</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}

table,th,td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<br>
	<div align='center'>
		<div align='center'
			style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px;">

		</div>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Scholar Year</th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.email}</td>
					<td>${student.address}</td>
					<td>${student.scholarYear}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>