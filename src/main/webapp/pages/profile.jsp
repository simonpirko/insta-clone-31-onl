<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<jsp:include page="includes/import.jsp"/>
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
                    <div class="col-auto d-none d-lg-block">
                    </div>
                    <div class="col-auto">
                        <div class="col p-4 d-flex flex-column position-static">
                            <div class="p-4 mb-3 bg-body-tertiary rounded">
                                <p class="mb-0">Customize this section to tell your visitors a
                                    little
                                    bit
                                    about
                                    yourself or something else entirely. Totally up to you.</p>
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
</body>

</html>