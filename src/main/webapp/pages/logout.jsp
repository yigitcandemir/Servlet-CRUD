<%
    session.invalidate(); 
    response.sendRedirect(request.getContextPath() + "/pages/login.jsp"); 
%>