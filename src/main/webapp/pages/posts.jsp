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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>
<body>
<div class="container text-center">
    <div class="row align-items-start h-100">
        <div class="col-2">

        </div>
        <div x-data="posts" class="col">
            <template x-if="!showPosts()">
                <div>No post to view</div>
            </template>
            <template x-if="showPosts()">

                <div class="card" style="width: 48rem">
                    <template x-for="post in pagedPosts">
                        <div class="border border-left border-right px-0">
                        
                        <div class="d-flex p-3 border-bottom">
                            <img :src="post.user.photos[0]" class="rounded-circle"
                                 height="50" alt="Avatar" loading="lazy"/>
                            <div class="d-flex w-100 ps-3">
                                <div>
                                    <a :href="'/profile/'+ post.user.id" style="text-align:left">
                                        <h6 class="text-body">
                                            <span x-text="post.user.nickname"></span>
                                            <span class="small text-muted font-weight-normal"> • </span>
                                            <span x-text="howManyHours(post.date)" class="small text-muted font-weight-normal"></span>
                                            <span><i class="fas fa-angle-down float-end"></i></span>
                                        </h6>
                                    </a>
                                    <p x-text="post.description" style="line-height: 1.2;font-size:13px; text-align:left"></p>


                                    <div :id="'carouselExample'+post.id" class="col carousel slide">
                                        <div class="carousel-inner">
                                            <template x-if="post.images.length">
                                                <template x-for="(image, index) in post.images">
                                                    <div class="carousel-item"  :class="{ 'active': index === 0 }">
                                                        <img :src="image"
                                                             loading="lazy"
                                                             class="d-block w-100 img-fluid" width="586"
                                                             height="731" alt="..."/>
                                                    </div>
                                                </template>
                                            </template>
                                                <template x-if="!post.images.length">
                                                    <div>
                                                        NOT images to display
                                                    </div>
                                                </template>
                                        </div>
                                        <button class="carousel-control-prev" type="button"
                                                :data-bs-target="'#carouselExample'+post.id" data-bs-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Previous</span>
                                        </button>
                                        <button class="carousel-control-next" type="button"
                                                :data-bs-target="'#carouselExample'+post.id" data-bs-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="visually-hidden">Next</span>
                                        </button>
                                    </div>


                                    <div style="margin-top: 10px">
                                    <ul class="list-unstyled d-flex justify-content-between mb-0 pe-xl-5">
                                        <li><i @click="alert('like')" class="bi bi-hand-thumbs-up"></i><span x-text="howManyReactions(post.likes, true)" class="small ps-2"></span>
                                            / <i @click="alert('dislike')" class="bi bi-hand-thumbs-down"></i><span x-text="howManyReactions(post.likes, false)" class="small ps-2"></span>
                                        </li>
                                        <li @click="alert('hello')"><i class="bi bi-chat-left-dots"></i><span x-text="post.comments.length" class="small ps-2"></span></li>
                                    </ul>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    </template>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"
        crossorigin="anonymous"></script>

<script src="https://unpkg.com/alpinejs" defer></script>
<script>
    document.addEventListener('alpine:init', () => {
        Alpine.data("posts", () => ({
            pagedPosts: [],
            pager: {
                start:0,
                count:50,
                isEnd:false,
            },
            init() {
                this.loaddata();
                window.onscroll = () => {
                    this.scrollFunction();
               };
            },
            howManyReactions(likes, like){
                return likes.filter(function (x){
                    return x.likeIt == like;
                }).length;
            },
            scrollFunction(){
                if(this.pager.isEnd)
                {
                    return;
                }
                const height = document.body.offsetHeight
                const screenHeight = window.innerHeight

                // Сколько пикселей уже проскроллили
                const scrolled = window.scrollY

                // Порог
                const threshold = height - screenHeight / 4

                // Низ экрана относительно страницы
                const position = scrolled + screenHeight

                if (position >= threshold && !this.pager.isEnd) {
                    this.pager.start++;
                    this.loaddata(this.pager.start);
                }
            },
            showPosts(){
                return this.pagedPosts.length > 0
            },
            isUserParticipationIn(id, userArray){
                return userArray.some(x => x.id == id);
            },
            howManyHours(date){

                var dateMomentObject = moment(date, "DD-MM-YYYY HH:mm:ss");
                var dateObject = dateMomentObject.toDate();
                const seconds = Math.floor((new Date().getTime() - new Date(dateObject).getTime()) / 1000)
                let interval = seconds / 31536000
                const rtf = new Intl.RelativeTimeFormat("en", { numeric: 'auto' })
                if (interval > 1) { return rtf.format(-Math.floor(interval), 'year') }
                interval = seconds / 2592000
                if (interval > 1) { return rtf.format(-Math.floor(interval), 'month') }
                interval = seconds / 86400
                if (interval > 1) { return rtf.format(-Math.floor(interval), 'day') }
                interval = seconds / 3600
                if (interval > 1) { return rtf.format(-Math.floor(interval), 'hour') }
                interval = seconds / 60
                if (interval > 1) { return rtf.format(-Math.floor(interval), 'minute') }
                return rtf.format(-Math.floor(interval), 'second')
            },
            loaddata(page, count) {
               let start = page === undefined ? 0 : page;
               let elements = count === undefined ? 50 :this.pager.count;

               let url = '/api/post?start='+start+'&count=' +elements;

                fetch(url, {
                    method: 'GET'
                })
                    .then(response => response.json())
                    .then(result => {
                        this.pager.isEnd = !result.content || !result.content.length

                        if(!this.pager.isEnd)
                        {
                            this.pagedPosts = [ ...this.pagedPosts, ...result.content ];
                        }
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
