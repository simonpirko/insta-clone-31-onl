<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Page</title>
</head>
<body>
    <h2>
        Hello
        <%=request.getParameter("name")%>!
    </h2>
</body>
</html>
