<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <jsp:include page="includes/importCss.jsp"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit profile</title>
</head>
<body>
<main class="d-flex flex-nowrap">
    <jsp:include page="includes/_sidebar.jsp"/>
    <div class="container-fluid list-group list-group-flush border-bottom scrollarea">
        <ul class="list-group list-group-flush">
            <li><span class="container align-items-end d-flex p-1 ms-2 fs-4 mt-2"> ${currentUser.getNickname()} </span>
            </li>
            <li class="ms-2">
                <hr>
            </li>
            <li>
                <!-- элементы изменения профиля (пароль, никнейм, фото, описание профиля) -->
                <form action="${pageContext.request.contextPath}/edit" method="post" enctype="multipart/form-data" class="p-4">
                    <!-- кнопка загрузки нового фото профиля -->
                        <div class="mb-3 text-center">
                        <label for="profilePhoto" class="form-label">
                            <img id="profilePreview" src="${currentUser.getPhotos()}" alt="Profile photo" class="rounded-circle"
                                 style="width: 100px; height: 100px; cursor: pointer;">
                        </label>
                        <input type="file" class="form-control d-none" id="profilePhoto" name="namePhoto" accept="image/*" multiple>
                    </div>

                    <!-- поле для изменения ника -->
                    <div class="mb-3">
                        <label for="nickname" class="form-label">Никнейм</label>
                        <input type="text" class="form-control" id="nickname" name="nickname" value="${currentUser.getNickname()}" required>
                    </div>

                    <!-- поле для описания профиля -->
                    <div class="mb-3">
                        <label for="description" class="form-label">Описание профиля</label>
                        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Расскажите о себе...">${currentUser.getDescription()}</textarea>
                    </div>

                    <!-- кнопка сохранения -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                    </div>
                </form>
            </li>
        </ul>
    </div>
</main>
<jsp:include page="includes/importJs.jsp"/>
</body>
</html>