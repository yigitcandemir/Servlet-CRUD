<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="tr">
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/error.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sayfa Bulunamadı</title>
</head>
<body>
    <div>
            <form action="<%=request.getContextPath()%>/UniversityServlet"><button id="mainBtn">Ana Sayfa</button></form>
    </div>
    <div class="pageNotFound">
        <h1>Sayfa Bulunamadı</h1>
        <img src="<%=request.getContextPath()%>/images/pageNotFound.png" alt="404 Page Not Found" width="400" height="auto">
    </div>
</body>
</html>