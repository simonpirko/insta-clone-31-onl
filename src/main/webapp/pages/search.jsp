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
            <li>
                <div class="container align-bottom mt-2">
                    <form method="get" action="${pageContext.request.contextPath}/search" class="align-bottom">
                        <div class="row justify-content-end">
                            <div class="col-md-8 align-items-center">
                                <input name="text" class="form-control fs-5" placeholder="Введите никнейм пользователя"
                                       aria-label="username" aria-describedby="button-addon2">
                            </div>
                            <div class="col-4">
                                <button class="btn btn-outline-secondary fs-5" type="submit" id="button-addon2">Поиск
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <hr class="ms-2">
            </li>
            <li>
                <c:if test="${users != null}">
                    <div class="d-flex justify-content-evenly ms-4">
                        <div class="row">
                            <c:forEach var="user" items="${users}">
                                <div class="col-4  my-3 d-flex justify-content-evenly p-3">
                                    <a href="${pageContext.request.contextPath}/profile?id=${user.getId()}"
                                       class="nav-link px-0 text-black align-middle">
                                        <div class="card p-2">
                                            <c:if test="${user.getPhotos().size() != 0}">
                                                <img src="${user.getPhotos().getLast()}"
                                                     class="card-img-top"
                                                     alt="profile photo">
                                            </c:if>
                                            <c:if test="${user.getPhotos().size() == 0}">
                                                <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"
                                                     class="card-img-top"
                                                     alt="profile photo">
                                            </c:if>
                                            <div class="card-body align-bottom">
                                                <h5 class="card-text text-center ">${user.getNickname()}</h5>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </li>
        </ul>
    </div>
</main>
<jsp:include page="includes/importJs.jsp"/>
</body>
</html>