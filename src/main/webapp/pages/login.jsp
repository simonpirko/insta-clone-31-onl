<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <jsp:include page="includes/importCss.jsp"/>
</head>
<body>
<div class="container-fluid mt-5">
    <div class="row justify-content-center">
        <div class="col-md-2">
            <form action="login" method="post">
                <div class="mb-3">
                    <input type="text" class="form-control" id="username" name="usName" placeholder="Введите имя пользователя">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" name="usPass" placeholder="Введите пароль">
                </div>
                <div>
                <input type="submit" class="btn btn-primary w-100 py-2" value="Вход">
                </div>
            </form>
            <a href="/registration" class="btn btn-outline-secondary w-100 py-2" tabindex="-1" role="button" aria-disabled="true">Регистрация</a>

        </div>
    </div>
</div>
<jsp:include page="includes/importJs.jsp"/>
</body>

</html>
