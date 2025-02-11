<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    Optional optionalUserId = Optional.ofNullable(request.getParameter("userId"));
%>
<html>
<head>
    <jsp:include page="includes/importCss.jsp"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Friends</title>
</head>
<body>
<main class="d-flex flex-nowrap">
    <jsp:include page="includes/_sidebar.jsp"/>
    <div class="container-fluid list-group list-group-flush border-bottom scrollarea">
        <div class="row">
            <form method="get" action="${pageContext.request.contextPath}/friendsearch" class="my-3 ms-4">
                <c:if test="<%=optionalUserId.isPresent()%>">
                    <input type="hidden" value="<%=optionalUserId.get()%>" name="userId">
                </c:if>
                <div class="row justify-content-end">
                    <div class="col-md-8 align-items-center">
                        <input name="text" class="form-control fs-5" placeholder="Введите никнейм друга"
                               aria-label="username" aria-describedby="button-addon2">
                    </div>
                    <div class="col-4">
                        <button class="btn btn-outline-secondary fs-5" type="submit" id="button-addon2">Поиск
                        </button>
                    </div>
                </div>
                <hr class="mt-2">
            </form>
        </div>
        <ul class="list-group list-group-flush">
            <li>
                <c:if test="${users != null}">
                    <c:forEach var="user" items="${users}">
                        <ul>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-4 my-3 d-flex">
                                        <div class="card" style="width: 18rem;">
                                            <c:if test="${user.getPhotos().size() != 0}">
                                                <img src="${user.getPhotos().getLast()}" class="card-img-top"
                                                     alt="profile photo">
                                            </c:if>
                                            <c:if test="${user.getPhotos().size() == 0}">
                                                <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"
                                                     class="card-img-top"
                                                     alt="profile photo">
                                            </c:if>
                                            <a href="${pageContext.request.contextPath}/profile?id=${user.getId()}"
                                               class="nav-link px-0 text-black">
                                                <span class="ms-2 d-none d-sm-inline fs-5">${user.getNickname()}</span>
                                            </a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </ul>
                    </c:forEach>
                </c:if>
                <c:if test="${users == null || users.size() < 1}">
                    <div class="text-center fs-4 mt-5">нет инфы</div>
                </c:if>
            </li>
        </ul>
    </div>
</main>
<jsp:include page="includes/importJs.jsp"/>
</body>
</html>