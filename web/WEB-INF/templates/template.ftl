
<#macro title>
        <title>${title}</title>
</#macro>


<#macro page_head>
    

    <@title/>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="/static/js/jquery-3.3.1.min.js" defer></script>
    <script src="/static/js/bootstrap.bundle.min.js" defer></script>
    <script src="/static/js/bootstrap.min.js" defer></script>

    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/dateFormatter.js"></script>
</#macro>


<#macro import>
</#macro>

<#macro navbar_auth_panel>
    <ul class="navbar-nav ">
    <#--<a class="nav-link" href="#">Выйти</a>-->
        <li class="nav-item">
            <a class="nav-link" href="/logout" type="">Log out</a>

        </li>
        <li class="nav-item">
            <a class="nav-link" href="/registration">Registration</a>

        <#--<a class="nav-link" href="#">Зарегистрироваться</a>-->
        </li>
    </ul>
</#macro>

<#macro navbar>
    <!-- NAVBAR -->
    <div class="container fixed-top">
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <div class="container">

                <!--Logo-->
                <a class="navbar-brand" href="#">TripService</a>

                <!--Button to expand/collapse navbar if screen is too small-->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!--content that would be expanded/collapsed-->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/trips/search">Find</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/trips/new">Create</a>
                        </li>
                    </ul>


                    <ul class="navbar-nav ">
                    <#if user??>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                ${user.username}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/profile">Profile</a>
                                <a class="dropdown-item" href="/profile/trips">My trips</a>
                                <a class="dropdown-item" href="/profile/booked">Booked trips</a>
                                <a class="dropdown-item" href="/profile/requests">Requests</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout">Log out</a>
                            </div>
                        </li>

                        
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth">Sign in</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/registration">Sign up</a>
                        </li>
                    </#if>

                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <!-- /NAVBAR -->
</#macro>


<#macro left_sidebar>
    <!--Left SIDE BAR-->
    <div class="col-2">
        <div class="row  " id="sidebar">
            <ul class="nav nav-tabs flex-column w-100 pl-3">
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile/trips">My trips</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile/booked">Booked trips</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile/requests">Requests</a>
                </li>
            </ul>
        </div>
    </div>
    <!--/Left SIDE BAR-->
</#macro>


<#macro content>
    <div>
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
            <div class="container-fluid" id="page">
                <div class="row">
                        <@left_sidebar/>
                        <@content/>
                </div>
            </div>
        </body>
    </html>
</#macro>



