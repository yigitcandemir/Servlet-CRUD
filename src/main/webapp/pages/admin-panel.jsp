<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head><title>Admin Panel</title></head>
<body>
<h2>Hoşgeldiniz, <%=session.getAttribute("admin")%></h2>

<a href="../UniversityServlet">Üniversiteleri Listele</a> |
<a href="logout.jsp">Çıkış Yap</a>
</body>
</html>