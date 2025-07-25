<p>ID Test: <%=request.getParameter("id")%></p>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("admin") == null) {
%>
    <p style="color:red;">Bu sayfaya sadece adminler erişebilir!</p>
    <a href="<%=request.getContextPath()%>/UniversityServlet">Geri Dön</a>
<%
        return; 
    }
%>
<html>
<head><title>Üniversite Güncelle</title></head>
<body>
<h2>Üniversite Güncelle</h2>

<form action="<%=request.getContextPath()%>/UpdateUniversity" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">

    <label>Ad:</label><br>
    <input type="text" name="name" value="<%=request.getParameter("name")%>" required><br><br>

    <label>Web Sitesi:</label><br>
    <input type="text" name="website" value="<%=request.getParameter("website")%>" required><br><br>

    <button type="submit">Kaydet</button>
</form>
</body>
</html>
