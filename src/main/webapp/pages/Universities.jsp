<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Kurum Listesi</title></head>
<body>
<h2>Üniversiteler</h2>
<form action="UniversityServlet" method="get">
    Üniversite Adı:
    <input type="text" name="name" placeholder="Üniversite adı girin">
    <button type="submit">Ara</button>
</form>
<table border="1">
<tr>
<th>Ad</th>
<th>Website</th>
</tr>
<%
    java.util.List<com.benim.api.University> websites = (java.util.List<com.benim.api.University>) request.getAttribute("universities");
    for (com.benim.api.University u : websites) {
%>
<tr>
<td><%=u.getName()%></td>
<td><a href="<%=u.getWebsite()%>" target="_blank"><%=u.getWebsite()%></a></td>
</tr>
<% } %>
</table>
</body>
</html>