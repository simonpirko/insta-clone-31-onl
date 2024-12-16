<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid mt-5">
    <div class="row justify-content-center">
        <div class="col-md-2">
            <form action="login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="usName" placeholder="Введите имя пользователя">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="usPass" placeholder="Введите пароль">
                </div>
                <div>
                <input type="submit" class="btn btn-primary w-100 py-2" value="Вход">
                </div>
            </form>
            <form method="get" action="authorization">
                <input type="submit" class="btn btn-outline-secondary w-100 py-2" value="Регистрация">
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
