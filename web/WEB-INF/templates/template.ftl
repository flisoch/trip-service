<#macro title>
        <title>Document</title>
</#macro>


<#macro page_head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <@title/>

    <!-- web -->
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" defer></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" defer></script> -->

    <!-- local -->
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="./static/js/jquery-3.3.1.min.js" defer></script>
    <script src="./static/js/bootstrap.bundle.min.js" defer></script>
    <script src="./static/js/bootstrap.min.js"    defer></script>
</#macro>


<#macro import>
        <link rel="stylesheet" href="./static/css/style.css">
</#macro>


<#macro navbar>
<!-- NAVBAR -->
    <div class="container fixed-top">
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">TripService</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">
                                Поездки
                                <!-- <span class="sr-only">(current)</span> -->
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Ссылка</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                Меню
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Раз</a>
                                <a class="dropdown-item" href="#">Два</a>
                                <a class="dropdown-item" href="#">Три</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Просто магия какаяя-то!</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#">Только для авторизованных</a>
                        </li>
                    </ul>

                    <form class="form-inline my-2 my-lg-0 mr-auto">
                        <input class="form-control mr-sm-2" type="search" placeholder="например, Казань" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Поиск</button>
                    </form>

                    <ul class="navbar-nav ">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Войти</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Зарегистрироваться</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
<!-- /NAVBAR -->
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->

            <!-- /CONTENT -->


        </div>
    </div>
</#macro>



<#macro display_page>
    <html lang="ru">

    <head>
        <@page_head/>
        <@import/>
    </head>
    <body>
        <@navbar/>
        <@content/>
    </body>
    </html>
</#macro>

