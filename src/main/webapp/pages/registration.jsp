<%@ page import="by.tms.instaclone31onl.core.constants.AttributeConstants" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid mt-5">
    <%
        List<String> errors = (List<String>)request.getAttribute(AttributeConstants.ERRORS);

        if(errors != null && !errors.isEmpty()){
            out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"> <ul>");
            for(String error : errors){
                out.println("<li>"+error+"</li>");
            }
            out.println(" </ul><button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
        }
    %>
    <div class="row justify-content-center">
        <div class="col-md-2">
            <form action="registration" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="aName" placeholder="Введите имя пользователя">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="aPass" placeholder="Введите пароль">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="passwordCopy" name="aPassCopy" placeholder="Повторите пароль">
                </div>
                <input type="submit" class="btn btn-primary w-100 py-2" value="Зарегистрироваться">

            </form>
            <form method="get" action="login">
                <input type="submit" class="btn btn-outline-secondary w-100 py-2" value="Вход">
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
