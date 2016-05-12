<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Create Account</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
	<div id="wrapper">
		<h1>Create Account</h1>
		<form:form
			action="${f:h(pageContext.request.contextPath)}/account/create" method="POST"
			modelAttribute="accountCreateForm" enctype="multipart/form-data">
			<table>
				<tr>
					<th>Username</th>
					<td><form:input path="username"/><form:errors path="username"/></td>
				</tr>
				<tr>
					<th>First name</th>
					<td><form:input path="firstName"/><form:errors path="firstName"/></td>
				</tr>
				<tr>
					<th>Last name</th>
					<td><form:input path="lastName"/><form:errors path="lastName"/></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td><form:input path="email"/><form:errors path="email"/></td>
				</tr>
				<tr>
					<th>E-mail(Confirmation)</th>
					<td><form:input path="confirmEmail"/><form:errors path="confirmEmail"/></td>
				</tr>
				<tr>
					<th>URL</th>
					<td><form:input path="url"/><form:errors path="url"/></td>
				</tr>
				<tr>
					<th>Image</th>
					<td><form:input type="file" path="image"/><form:errors path="image"/></td>
				</tr>
			</table>

			<input type="submit" id="confir" name="confirm" value="Go to confirmation page"/>
		</form:form>
		<a href="${f:h(pageContext.request.contextPath)}/login">Back to login page</a>
	</div>
</body>
</html>