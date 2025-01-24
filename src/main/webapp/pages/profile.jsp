<%@ page import="java.util.Optional" %>
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
            <li><span class="container align-items-end d-flex p-1 ms-2 fs-4 mt-2"> ${currentUser.getNickname()} </span>
            </li>
            <li class="ms-2">
                <hr>
            </li>
            <li class="container mb-3">
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
            <li><!-- posts --></li>

        </ul>
    </div>
</main>
<jsp:include page="includes/importJs.jsp"/>
</body>

</html>