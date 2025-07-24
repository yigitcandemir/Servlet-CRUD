<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%-- Eğer admin giriş yapmadıysa Admin Giriş butonunu göster --%>
<%
    if (session.getAttribute("admin") == null) {
%>
    <form action="pages/login.jsp" method="get" style="margin-bottom:10px;">
        <button type="submit">Admin Girişi</button>
    </form>
<%
    } else {
%>
    <form action="pages/create-university.jsp" method="get" style="margin-bottom:10px;">
        <button type="submit">Üniversite Ekle</button>
    </form>
    <form action="logout.jsp" method="get" style="margin-bottom:10px;">
        <button type="submit">Çıkış Yap</button>
    </form>
<%
    }
%>
<%
    String contextPath = request.getContextPath();
%>
<head><title>Üniversiteler</title></head>
<body>
<h2>Üniversiteler</h2>

<form action="<%=contextPath%>/UniversityServlet" method="get">
    <input type="text" name="search" placeholder="Üniversite adı ara">
    <button type="submit">Ara</button>
</form>

<table border="1">
<tr><th>Ad</th><th>Website</th></tr>

<%-- API Üniversiteleri --%>
<%
    java.util.List<com.benim.api.University> apiUnis =
        (java.util.List<com.benim.api.University>) request.getAttribute("apiUniversities");
    for (com.benim.api.University u : apiUnis) {
%>
<tr>
<td><%=u.getName()%></td>
<td><a href="<%=u.getWebsite()%>" target="_blank"><%=u.getWebsite()%></a></td>
</tr>
<% } %>

<%-- Veritabanı Üniversiteleri --%>
<%
    java.util.List<com.benim.model.University> dbUnis =
        (java.util.List<com.benim.model.University>) request.getAttribute("dbUniversities");
    for (com.benim.model.University u : dbUnis) {
%>
<tr>
<td><%=u.getName()%></td>
<td><a href="<%=u.getWebsite()%>" target="_blank"><%=u.getWebsite()%></a></td>
</tr>
<% } %>
</table>
</body>
</html>
