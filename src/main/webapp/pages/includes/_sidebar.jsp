<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="b-example-divider b-example-vr"></div>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary" style="width: 300px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
        <!-- add link -->
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
             class="bi" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 1 8 0a8 8 0 0 1 0 16M1 8a7 7 0 0 0 7 7 3.5 3.5 0 1 0 0-7 3.5 3.5 0 1 1 0-7 7 7 0 0 0-7 7"></path>
        </svg>
        <span class="ms-2 fs-4 d-none d-sm-inline"> Not Insta </span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="#" class="nav-link px-0 text-black">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                     class="bi" viewBox="0 0 16 16">
                    <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5z"></path>
                </svg>
                <span class="ms-2 d-none d-sm-inline fs-5">Главная страница</span>
            </a>
        </li>
        <li>
            <a href="#" class="nav-link px-0 text-black">
                <!-- add link -->
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                     class="bi" viewBox="0 0 16 16">
                    <path d="M6.5 4.482c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018"></path>
                    <path d="M13 6.5a6.47 6.47 0 0 1-1.258 3.844q.06.044.115.098l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1-.1-.115h.002A6.5 6.5 0 1 1 13 6.5M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11"></path>
                </svg>
                <span class="ms-2 d-none d-sm-inline fs-5">Поисковый запрос</span>
            </a>
        </li>
        <li>
            <a href="#" class="nav-link px-0 text-black">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                     class="bi" viewBox="0 0 16 16">
                    <path d="M5.5 2A3.5 3.5 0 0 0 2 5.5v5A3.5 3.5 0 0 0 5.5 14h5a3.5 3.5 0 0 0 3.5-3.5V8a.5.5 0 0 1 1 0v2.5a4.5 4.5 0 0 1-4.5 4.5h-5A4.5 4.5 0 0 1 1 10.5v-5A4.5 4.5 0 0 1 5.5 1H8a.5.5 0 0 1 0 1z"></path>
                    <path d="M16 3a3 3 0 1 1-6 0 3 3 0 0 1 6 0"></path>
                </svg>
                <span class="ms-2 d-none d-sm-inline fs-5">Уведомления</span>
            </a>
        </li>
        <li>
            <a href="#" data-bs-toggle="modal" data-bs-target="#postModal"
               class="nav-link px-0 align-middle text-black">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                     class="bi" viewBox="0 0 16 16">
                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"></path>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"></path>
                </svg>
                <span class="ms-2 d-none d-sm-inline fs-5">Опубликовать запись</span>
            </a>
            <ul class="collapse nav flex-column ms-1" id="submenu2" data-bs-parent="#menu"></ul>
            <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="postModalLabel">Создать новую публикацию</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="mb-3">
                                    <label for="formFile" class="form-label">Выберите изображение</label>
                                    <input class="form-control" type="file" id="formFile">
                                </div>

                                <div class="mb-3">
                                    <label for="caption" class="form-label">Подпись</label>
                                    <textarea class="form-control" id="caption" rows="3"
                                              placeholder="Добавьте подпись..."></textarea>
                                </div>

                                <button type="submit" class="btn btn-outline-secondary w-100">Опубликовать</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </li>

        <li>
            <a href="#" class="nav-link px-0 align-middle text-black">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                     class="bi" viewBox="0 0 16 16">
                    <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"></path>
                    <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"></path>
                </svg>
                <span class="ms-2 d-none d-sm-inline fs-5">Редактировать профиль</span> </a>
        </li>
    </ul>
    <hr>
    <div class="btn-group dropend">
        <button type="button" class="btn btn-outline-secondary text-start">
            <a href="#" class="nav-link px-0 align-self-end">
                <c:if test="${currentUser.getPhotos().size() == 0}">
                    <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"
                         class="rounded-circle"
                         alt="Фото профиля" width="50">
                </c:if>
                <c:if test="${currentUser.getPhotos().size() != 0}">
                    <img src="${currentUser.getPhotos().getLast()}"
                         class="rounded-circle"
                         alt="Фото профиля" width="50">
                </c:if>
                <span class="d-none d-sm-inline fs-5 p-4 align-middle">Профиль</span>
            </a>
        </button>
        <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="sr-only"></span>
        </button>
        <ul class="dropdown-menu align-items-end">
            <li>
                <a href="${pageContext.request.contextPath}/logout" class="nav-link px-0 align-middle text-black">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                         class="bi bi-box-arrow-right ms-3" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                              d="M6.354 3.646a.5.5 0 0 1 0 .708L3.207 7.5H13.5a.5.5 0 0 1 0 1H3.207l3.147 3.146a.5.5 0 0 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 0 1 .708 0z"></path>
                        <path fill-rule="evenodd"
                              d="M11.5 13a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 1 0v11a.5.5 0 0 1-.5.5z"></path>
                    </svg>
                    <span class="ms-3 d-none d-sm-inline fs-6">Выйти</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="b-example-divider b-example-vr"></div>