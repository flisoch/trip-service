<#include "template.ftl">


<#macro import>
     <link rel="stylesheet" href="./static/css/style.css">
</#macro>


<#macro title>
    <title>Авторизация</title>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->
            <div class="col"></div>

            <div class="card text-center" style="width: 20rem;">
                <div class="card-header h4">
                    Авторизация
                </div>

                <div class="card-body">
                    <form action="fill_this" method="POST">
                        <div class="form-group">
                            <label for="username">Имя пользователя</label>
                            <input type="text" name="username" class="form-control" id="username" placeholder="vasya1234"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" name="password" class="form-control" id="password" placeholder="qwerty"
                                   required>
                        </div>

                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="remember_me" type="checkbox" id="remember-me">
                                <label class="form-check-label" for="remember-me">
                                    Запомнить меня
                                </label>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-primary col" value="Войти">
                    </form>
                </div>


            </div>

            <div class="col"></div>
            <!-- /CONTENT -->

        </div>
    </div>
</#macro>


<@display_page/>