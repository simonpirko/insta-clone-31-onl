<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <jsp:include page="includes/importCss.jsp"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
</head>
<body>
<main class="d-flex flex-nowrap">

    <jsp:include page="includes/_sidebar.jsp"/>
    <div class="container-fluid list-group list-group-flush border-bottom scrollarea">
        <ul class="list-group list-group-flush">
            <li>
                <span class="container align-items-end d-flex p-1 ms-2 fs-4 mt-2"> ${currentUser.getNickname()} </span>
            </li>
            <li class="ms-2">
                <hr>
            </li>
            <li class="container mb-3 list-group list-group-flush">
                <div class="row">
                    <div class="col-auto d-none d-lg-block ">
                        <c:if test="${currentUser.getPhotos().size() == 0}">
                            <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"
                                 class="rounded-circle"
                                 alt="Фото профиля" width="250">
                        </c:if>
                        <c:if test="${currentUser.getPhotos().size() != 0}">
                            <img src="${currentUser.getPhotos().getLast()}"
                                 class="rounded-circle"
                                 alt="Фото профиля" width="250">
                        </c:if>
                    </div>
                    <div class="col-auto">
                        <div class="col p-4 d-flex flex-column position-static">

                            <div class="p-4 mb-3 bg-body-tertiary rounded">
                                <p class="mb-0">${currentUser.getDescription() == null || currentUser.getDescription().isBlank()? "Add your description" : currentUser.getDescription()}</p>
                            </div>
                            <div class="container">
                                <div class="row row-cols-auto">
                                    <div class="col align-self-end">
                                        <button type="button" class="btn btn-outline-secondary"
                                                disabled>
                                            Публикаций
                                        </button>
                                        <!-- add counter -->
                                    </div>
                                    <div class="col align-self-end">
                                        <button type="button" class="btn btn-outline-secondary">
                                            Подписчики
                                        </button>
                                        <!-- add counter +modal -->
                                    </div>
                                    <div class="col align-self-end">
                                        <button type="button" class="btn btn-outline-secondary">
                                            Подписки
                                        </button>
                                        <!-- add counter +modal -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="ms-2">
                <hr>
            </li>
            <li>
                <c:if test="${currentUser.getPhotos().size() == 0}">
                    <div class="container align-items-center mt-3">
                        <div class="row justify-content-center">
                            <div class="col-4 text-center">
                                <img src="https://cdn.vectorstock.com/i/500p/97/22/no-picture-vector-739722.jpg"
                                     class="rounded-circle mb-2"
                                     alt="Создать первый пост" width="200">
                                <h2>Поделиться фото</h2>
                                <h5 class="my-2">Фото, которыми вы делитесь, будут показываться в вашем профиле.</h5>
                                <a data-bs-toggle="modal" data-bs-target="#postModal"
                                   class="px-0 align-middle icon-link">
                                    <span class="ms-2 d-none d-sm-inline fs-4">Поделиться первым фото</span>
                                </a>
                                <ul class="collapse nav flex-column ms-1" id="submenu2" data-bs-parent="#menu"></ul>
                                <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="postModalLabel">Создать новую
                                                    публикацию</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/filecreate"
                                                      enctype="multipart/form-data">
                                                    <div class="mb-3">
                                                        <label for="formFileMultiple" class="form-label">Выберите
                                                            изображение</label>
                                                        <input class="form-control" type="file" name="file"
                                                               accept="image/*" id="formFileMultiple" multiple>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="caption" class="form-label">Подпись</label>
                                                        <textarea class="form-control" id="caption" rows="3"
                                                                  placeholder="Добавьте подпись..."></textarea>
                                                    </div>
                                                    <button type="submit" class="btn btn-outline-secondary w-100">
                                                        Опубликовать
                                                    </button>
                                                </form>
                                                <script>
                                                    document.getElementById('formFileMultiple').addEventListener('change', function () {
                                                        var files = this.files;
                                                        if (files.length > 5) {
                                                            alert('Можно загрузить не более 5 файлов');
                                                        }
                                                    });
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${currentUser.getPhotos().size() != 0}">
                    <ul>
                        <div class="container">
                            <div class="row justify-content-start">
                                <c:forEach var="photo" items="${currentUser.getPhotos()}">
                                    <div class="col-4 my-3 d-flex">
                                        <div class="card" style="width: 18rem;">
                                            <img src="${currentUser.getPhotos().get(1)}" class="card-img-top"
                                                 alt="post photo">
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </ul>
                </c:if>
            </li>
        </ul>
    </div>
</main>
<jsp:include page="includes/importJs.jsp"/>
</body>

</html>