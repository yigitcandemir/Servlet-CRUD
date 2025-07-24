<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Admin Giriş</title></head>
<body>
<h2>Admin Panel Girişi</h2>

<form action="../login" method="post">
    Kullanıcı Adı: <input type="text" name="username"><br>
    Şifre: <input type="password" name="password"><br>
    <button type="submit">Giriş</button>
</form>

<% if ("true".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Hatalı kullanıcı adı veya şifre!</p>
<% } %>
</body>
</html>