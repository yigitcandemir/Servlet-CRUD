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
    <form action="pages/logout.jsp" method="get" style="margin-bottom:10px;">
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

<%-- Veritabanı Üniversiteleri --%>
<%
    java.util.List<com.benim.model.University> dbUnis =
        (java.util.List<com.benim.model.University>) request.getAttribute("dbUniversities");
    for (com.benim.model.University u : dbUnis) {
%>
<tr>
<td><%=u.getName()%></td>
<td><a href="<%=u.getWebsite()%>" target="_blank"><%=u.getWebsite()%></a></td>
<% if (session.getAttribute("admin") != null) { %>
<td>
<form action="pages/update-university.jsp" method="get" style="display:inline;">
    <input type="hidden" name="id" value="<%=u.getId()%>">
    <input type="hidden" name="name" value="<%=u.getName()%>">
    <input type="hidden" name="website" value="<%=u.getWebsite()%>">
    <button type="submit">Güncelle</button>
</form>
<form action="DeleteUniversity" method="get" style="display:inline;"
        onsubmit="return confirm('Bu üniversiteyi silmek istediğinize emin misiniz?');">
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <button type="submit">Sil</button>
</form>
</td>
<% } %>
</tr>
<% } %>
</table>
</body>
</html>
