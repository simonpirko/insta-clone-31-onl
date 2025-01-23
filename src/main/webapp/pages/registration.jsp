<%@ page import="by.tms.instaclone31onl.core.constants.AttributeConstants" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="includes/importCss.jsp"/>
</head>
<body>
<div class="container-fluid mt-5">
    <jsp:include page="includes/_errors.jsp"/>
    <div class="row justify-content-center">
        <div class="col-md-2">
            <form action="registration" method="post">
                <div class="mb-3">
                    <input type="text" class="form-control" id="username" name="aName" placeholder="Введите имя пользователя">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" name="aPass" placeholder="Введите пароль">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="passwordCopy" name="aPassCopy" placeholder="Повторите пароль">
                </div>
                <input type="submit" class="btn btn-primary w-100 py-2" value="Зарегистрироваться">

            </form>
            <a href="/login" class="btn btn-outline-secondary w-100 py-2" tabindex="-1" role="button" aria-disabled="true">Вход</a>
        </div>
    </div>
</div>
<jsp:include page="includes/importJs.jsp"/>
</body>
</html>
