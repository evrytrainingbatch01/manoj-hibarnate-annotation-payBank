<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>
</head>
<body style="background-color: rgba(0,0,128,0.3);">
 <div align="center" >
      <h3>!! -Login- !!</h3>
     <%--  <p style="color: red;">${errorString}</p> --%>
 
 
      <form method="POST" action="login">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                 
               </td>
            </tr>
         </table>
      </form>
 </div>


</body>
</html>