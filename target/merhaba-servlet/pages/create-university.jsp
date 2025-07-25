<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head><title>Yeni Üniversite Ekle</title></head>
<body>
<h2>Yeni Üniversite Ekle</h2>
<form action="../CreateUniversity" method="post">
    Ad: <input type="text" name="name"><br>
    Website: <input type="text" name="website"><br>
    <button type="submit">Ekle</button>
</form>
</body>
</html>