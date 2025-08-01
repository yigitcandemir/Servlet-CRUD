<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Kampüs Listesi</title>
</head>
<body>
    <h2>Kampüs Bilgileri</h2>

    <c:forEach var="campus" items="${campusList}">
        <h3>${campus.name}</h3>
        <p>${campus.city} / ${campus.district} - ${campus.address}</p>

        <ul>
            <c:forEach var="faculty" items="${campus.faculties}">
                <li>
                    <strong>${faculty.name}</strong> (Dekan: ${faculty.dean}, Tel: ${faculty.telephone})
                    <ul>
                        <c:forEach var="dept" items="${faculty.departments}">
                            <li>${dept.name}</li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
        <hr>
    </c:forEach>

    <br>
    <a href="<%=request.getContextPath()%>/UniversityServlet">← Üniversite listesine dön</a>
</body>
</html>
