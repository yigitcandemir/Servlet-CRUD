<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Kurum Listesi</title></head>
<body>
<h2>Kurumlar</h2>
<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Type</th>
<th>City</th>
<th>Website</th>
</tr>
<%
    java.util.List<com.benim.model.Kurum> liste =
        (java.util.List<com.benim.model.Kurum>) request.getAttribute("liste");
    for (com.benim.model.Kurum k : liste) {
%>
<tr>
<td><%=k.getId()%></td>
<td><%=k.getName()%></td>
<td><%=k.getType()%></td>
<td><%=k.getCity()%></td>
<td><a href="<%=k.getWebsite()%>" target="_blank"><%=k.getWebsite()%></a></td>
</tr>
<% } %>
</table>
</body>
</html>