<#include "template.ftl">


<#macro import>
     <link rel="stylesheet" href="./static/css/style.css">
</#macro>


<#macro title>
    <title>Authorization</title>
</#macro>


<#macro navbar_auth_panel>

    <ul class="navbar-nav ">
        <li class="nav-item">
        <#--<a class="nav-link" href="#">Войти</a>-->
            <a class="nav-link" href="/auth">Log in</a>

        </li>
        <li class="nav-item">
            <a class="nav-link" href="/registration">Registration</a>

        <#--<a class="nav-link" href="#">Зарегистрироваться</a>-->
        </li>
    </ul>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->
            <div class="col"></div>

            <div class="card text-center" style="width: 20rem;">
                <div class="card-header h4">
                    <!--Авторизация-->
                    Authorization
                </div>

                <div class="card-body">
                    <form action="/auth" method="POST">
                        <div class="form-group">
                            <#--<label for="username">Имя пользователя</label>-->
                            <label for="username">Username</label>

                            <input type="text" name="username" class="form-control" id="username" placeholder="vasya1234"
                                   required>
                        </div>
                        <div class="form-group">
                            <#--<label for="password">Пароль</label>-->
                            <label for="password">Password</label>

                            <input type="password" name="password" class="form-control" id="password" placeholder="qwerty"
                                   required>
                        </div>

                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="remember_me" type="checkbox" id="remember-me">
                                <label class="form-check-label" for="remember-me">
                                    <!--Запомнить меня-->
                                    Remember me
                                </label>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-primary col" value="Log in">
                    </form>
                </div>


            </div>

            <div class="col"></div>
            <!-- /CONTENT -->

        </div>
    </div>
</#macro>


<@display_page/>