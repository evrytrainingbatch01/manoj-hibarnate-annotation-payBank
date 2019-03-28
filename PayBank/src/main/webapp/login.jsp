<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>
</head>
<body>
<%--       <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include> --%>
 
      <h3>Login Page</h3>
     <%--  <p style="color: red;">${errorString}</p> --%>
 
 
      <form method="POST" action="login">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                 <%--  <a href="${pageContext.request.contextPath}/">Cancel</a> --%>
               </td>
            </tr>
         </table>
      </form>
 
<!--       <p style="color:blue;">User Name: tom, password: tom001 or jerry/jerry001</p> -->
 
     <%--  <jsp:include page="_footer.jsp"></jsp:include> --%>

</body>
</html>