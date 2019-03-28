<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body style="background-color: rgba(0,0,128,0.3);">
<div align="center">
		  <h3>!! -Register- !!</h3>
		<form action="Registration" method="post">
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>

				<tr>
					<td>Age</td>
					<td><input type="text" name="age" /></td>
				</tr>

				<tr>
					<td>City</td>
					<td><input type="text" name="city" /></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><input type="text" name="country" /></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNum" /></td>
				</tr>
				<tr>
					<td>Email Id</td>
					<td><input type="text" name="emailId" /></td>
				</tr>
			</table>
			<button type="submit">Submit</button>
		</form>
		</div>
</body>
</html>