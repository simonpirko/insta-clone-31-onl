<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 20.12.2024
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="/css/style.css" rel="stylesheet"/>
</head>
<body>
<header class="navbar navbar-expand-lg bd-navbar sticky-top">
    <nav class="nav">
        <a class="nav-link active" aria-current="page" href="#">Main</a>
        <a class="nav-link" href="#">My posts</a>
        <a class="nav-link" href="#">Profile</a>
        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
    </nav>
</header>
<div class="container text-center">
    <div class="row align-items-start h-100">
        <div class="col-2">

        </div>
        <div x-data="posts" class="col">
            <template x-if='!hasElements()'>
                <div>No post to view</div>
            </template>
            <template x-if='hasElements()'>

                <div class="card" style="width: 48rem">
                    <div class="border border-left border-right px-0">
                        
                        <div class="d-flex p-3 border-bottom">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (24).webp" class="rounded-circle"
                                 height="50" alt="Avatar" loading="lazy"/>
                            <div class="d-flex w-100 ps-3">
                                <div>
                                    <a href="">
                                        <h6 class="text-body">
                                            Anna Doe
                                            <span class="small text-muted font-weight-normal">@annadoe</span>
                                            <span class="small text-muted font-weight-normal"> • </span>
                                            <span class="small text-muted font-weight-normal">7h</span>
                                            <span><i class="fas fa-angle-down float-end"></i></span>
                                        </h6>
                                    </a>
                                    <p style="line-height: 1.2;">
                                        Error cumque temporibus eum pariatur ducimus facere? Obcaecati fugit, nobis
                                        eos <a href="">#deserunt</a> odit libero voluptatibus, iste laudantium,
                                        tempore ratione ut.
                                    </p>


                                    <div id="carouselExample" class="col carousel slide">
                                        <div class="carousel-inner">
                                            <div class="carousel-item active">
                                                <img src="https://mdbcdn.b-cdn.net/img/new/standard/nature/184.webp"
                                                     loading="lazy" class="d-block w-100 img-fluid" width="586"
                                                     height="731" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                                <img src="https://mdbcdn.b-cdn.net/img/new/standard/nature/184.webp"
                                                     loading="lazy" class="d-block w-100 img-fluid" width="586"
                                                     height="731" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                                <img src="https://mdbcdn.b-cdn.net/img/new/standard/nature/184.webp"
                                                     loading="lazy" class="d-block w-100 img-fluid" width="586"
                                                     height="731" alt="...">
                                            </div>
                                        </div>
                                        <button class="carousel-control-prev" type="button"
                                                data-bs-target="#carouselExample" data-bs-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Previous</span>
                                        </button>
                                        <button class="carousel-control-next" type="button"
                                                data-bs-target="#carouselExample" data-bs-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Next</span>
                                        </button>
                                    </div>


                                    <ul class="list-unstyled d-flex justify-content-between mb-0 pe-xl-5">
                                        <li>
                                            <i class="far fa-comment"></i>
                                        </li>
                                        <li><i class="fas fa-retweet"></i><span class="small ps-2">11</span></li>
                                        <li><i class="far fa-heart"></i><span class="small ps-2">65</span></li>
                                        <li>
                                            <i class="far fa-share-square"></i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </template>


        </div>
        <div class="col-2">
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/alpinejs" defer></script>
<script>
    document.addEventListener('alpine:init', () => {
        Alpine.data("posts", () => ({
            filtredPost: [],
            allPost: [],
            pager: {
                currentPage: 1,
                totalPages: 0,
                pageSize: 10,
                startIndex: 0,
                endIndex: 0,
            },
            init() {
                this.loaddata();
                window.onscroll = () => {
                    this.scrollFunction();
                };
            },
            scrollFunction(){
                if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
                    console.log("> 50");
                } else {
                    console.log("< 50")
                }
            },
            hasElements(){
                return this.filtredPost.length > 0;
            },
            loaddata() {
                fetch('/api/post', {
                    method: 'GET'
                })
                    .then(response => response.json())
                    .then(result => {
                        this.allPost = result.content;
                        console.log(this.allPost.length);
                    })
                    .catch(resons => {
                        console.log("error");
                    });
            }

        }));
    });

</script>
</body>
</html>
