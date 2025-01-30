<%@ page import="by.tms.instaclone31onl.core.models.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    boolean isOwner = (boolean)request.getAttribute("isOwner");
    User profile = (User)request.getAttribute("profileUser");
    boolean isFriendOrSendRequest = (boolean)request.getAttribute("hasRequestToFriendship");
    String friendLink = "/friends%s".formatted(isOwner ? "" : "?userId="+profile.getId());
%>
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
                <span class="container align-items-end d-flex p-1 ms-2 fs-4 mt-2"> ${profileUser.getNickname()} </span>
            </li>
            <li class="ms-2">
                <hr>
            </li>
            <li class="container mb-3 list-group list-group-flush">
                <div class="row">
                    <div class="col-auto d-none d-lg-block ">
                        <c:if test="${profileUser.getPhotos().size() == 0}">
                            <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"
                                 class="rounded-circle"
                                 alt="Фото профиля" width="250">
                        </c:if>
                        <c:if test="${profileUser.getPhotos().size() != 0}">
                            <img src="${profileUser.getPhotos().getLast()}"
                                 class="rounded-circle"
                                 alt="Фото профиля" width="250">
                        </c:if>
                    </div>
                    <div class="col-auto">
                        <div class="col p-4 d-flex flex-column position-static">
                            <c:if test="${!isOwner && profileUser.getDescription() != null && !profileUser.getDescription().isBlank()}">
                                <div class="p-4 mb-3 bg-body-tertiary rounded">
                                    <p class="mb-0">${profileUser.getDescription()}</p>
                                </div>
                            </c:if>
                            <c:if test="${isOwner}">
                                <div class="p-4 mb-3 bg-body-tertiary rounded">
                                    <p class="mb-0">${profileUser.getDescription() == null || profileUser.getDescription().isBlank() ? "Add your description" : profileUser.getDescription()}</p>
                                </div>
                            </c:if>
                            <div class="container">
                                <div class="row row-cols-auto">
                                    <div class="col align-self-start">
                                        <a class="btn btn-outline-secondary btn-lg" href="/post?userId=${profileUser.getId()}"
                                           role="button">Публикации</a>
                                    </div>
                                    <div class="col align-self-between">
                                        <a href ='<%=friendLink%>' type="button" class="btn btn-outline-secondary btn-lg">
                                            Друзья
                                        </a>
                                        <!-- add counter +modal -->
                                    </div>

                                    <div class="col align-self-between">
                                        <c:if test="${isOwner }">
                                            <a href="#" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#myRequestToFriendshipModal">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                                                     class="bi" viewBox="0 0 16 16">
                                                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"></path>
                                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                                                </svg>
                                                <span class="ms-2 d-none d-sm-inline fs-5">Мои заявки</span>
                                            </a>
                                            <div class="modal fade" id="myRequestToFriendshipModal" tabindex="-1" aria-labelledby="myRequestToFriendshipModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="myRequestToFriendshipModalLabel">Мои заявки</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <c:if test="${myRequests.isEmpty()}">
                                                                Нет запросов
                                                            </c:if>
                                                            <c:forEach var="myRequest" items="${myRequests}">
                                                                <div class="row">
                                                                    <div class="col-1">
                                                                        <c:if test="${myRequest.photos().size() != 0}">
                                                                            <img src="${myRequest.photos().getLast()}" class="rounded-circle" height="30" alt="Avatar" loading="lazy">
                                                                        </c:if>
                                                                        <c:if test="${myRequest.photos().size() == 0}">
                                                                            <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg" class="card-img-top"
                                                                                 class="rounded-circle" height="30" alt="Avatar" loading="lazy">
                                                                        </c:if>
                                                                    </div>
                                                                    <div class="col-4">
                                                                        <a href="${"/profile?id=%s".formatted(myRequest.id().toString())}">${myRequest.nickname()}</a>
                                                                    </div>
                                                                    <div class="col">
                                                                        На рассмотрении
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%--                                            <form action="${pageContext.request.contextPath}/profile" method="post">--%>
                                            <%--                                                <button type="button" class="btn btn-outline-secondary">--%>
                                            <%--                                                    Заявка в друзья--%>
                                            <%--                                                </button>--%>
                                            <%--                                            </form>--%>
                                        </c:if>
                                        <c:if test="${!isOwner}">
                                            <form method="post" action="/friends">
                                                <input type="hidden" value="${profileUser.getId()}" name="userId"/>
                                                <c:if test="<%=!isFriendOrSendRequest%>">
                                                    <button class="btn btn-outline-secondary btn-lg" type="submit">В друзья</button>
                                                </c:if>
                                                <c:if test="<%=isFriendOrSendRequest%>">
                                                    <button class="btn btn-outline-secondary btn-lg" disabled>${underСonsideration ? "На рассмотрении":"В друзьях"}</button>
                                                </c:if>
                                            </form>
                                        </c:if>
                                        <!-- add counter +modal -->
                                    </div>
                                    <div class="col align-self-end">
                                        <c:if test="${isOwner }">
                                            <a href="#" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#requestToFriendshipModal">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                                                     class="bi" viewBox="0 0 16 16">
                                                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"></path>
                                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                                                </svg>
                                                <span class="ms-2 d-none d-sm-inline fs-5">Заявки в друзья</span>
                                            </a>
                                            <div class="modal fade" id="requestToFriendshipModal" tabindex="-1" aria-labelledby="requestToFriendshipModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="requestToFriendshipModalLabel">Заявки в друзья</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">

                                                            <c:if test="${requestsToMy.isEmpty()}">
                                                            Нет запросов
                                                            </c:if>
                                                            <c:forEach var="myRequest" items="${requestsToMy}">
                                                            <div class="row">
                                                                <div class="col-1">
                                                                    <c:if test="${myRequest.photos().size() != 0}">
                                                                        <img src="${myRequest.photos().getLast()}" class="rounded-circle" height="30" alt="Avatar" loading="lazy">
                                                                    </c:if>
                                                                    <c:if test="${myRequest.photos().size() == 0}">
                                                                        <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg" class="card-img-top"
                                                                             class="rounded-circle" height="30" alt="Avatar" loading="lazy">
                                                                    </c:if>
                                                                </div>
                                                                <div class="col-4">
                                                                    <a href="${"/profile?id=%s".formatted(myRequest.id().toString())}">${myRequest.nickname()}</a>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                                                        <form method="post" action="/request">
                                                                            <input type="hidden" value="false" name="rejected"/>
                                                                            <input type="hidden" value="${myRequest.id()}" name="requesterId"/>
                                                                        <button type="submit" class="btn btn-success">Принять</button>
                                                                        </form>
                                                                        <form method="post" action="/request">
                                                                            <input type="hidden" value="true" name="rejected"/>
                                                                            <input type="hidden" value="${myRequest.id()}" name="requesterId"/>
                                                                        <button type="submit" class="btn btn-danger">Отказать</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
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
                <c:if test="${profileUser.getPhotos().size() == 0 && isOwner}">
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
                <c:if test="${profileUser.getPhotos().size() != 0}">
                    <ul>
                        <div class="container">
                            <div class="row justify-content-start">
                                <c:forEach var="photo" items="${profileUser.getPhotos()}">
                                    <div class="col-4 my-3 d-flex">
                                        <div class="card" style="width: 18rem;">
                                            <img src="${photo}" class="card-img-top"
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