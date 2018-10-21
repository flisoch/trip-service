<#include "template.ftl">


<#macro title>
    <title>Регистрация</title>
</#macro>


<#macro import>
    <link rel="stylesheet" href="./static/css/style.css">
    <script src="./static/js/registration.js"></script>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->
            <div class="col"></div>

            <div class="card text-center" style="width: 20rem;">
                <div class="card-header h4">
                    Регистрация
                </div>

                <div class="card-body">

                    <form method="POST">
                        <div class="form-group">
                            <label for="username">Имя пользователя</label>
                            <input type="text" name="username" class="form-control" id="username" onchange="validUsername('username');"
                                   placeholder="vasya1234" required>
                        </div>
                        <div class="form-group">
                            <label for="useremail">Электронная почта</label>
                            <input type="email" name="email" class="form-control" id="useremail" placeholder="user@example.com"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" name="password" class="form-control" id="password" onchange="validatePassword();"
                                   placeholder="qwerty" required>

                            <label for="confirm-password">Повторите пароль</label>  <!--//было for="password2"-->
                            <input type="password" class="form-control" id="confirm-password" onkeyup="validatePassword();"
                                   placeholder="qwerty" required>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-primary col" value="Регистрация">
                        </div>

                        Уже зарегистрированы?<br>
                        <a href="/auth" class="card-link">Войти</a>
                    </form>

                </div>


            </div>

            <div class="col"></div>
            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>