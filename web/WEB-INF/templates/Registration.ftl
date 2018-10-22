<#include "template.ftl">


<#macro title>
    <#--<title>Регистрация</title>-->
    <title>Registration</title>

</#macro>


<#macro import>
    <link rel="stylesheet" href="./static/css/style.css">
    <script src="/static/js/registration.js"></script>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->
            <div class="col"></div>

            <div class="card text-center" style="width: 20rem;">
                <div class="card-header h4">
                    <!--Регистрация-->
                    Registration
                </div>

                <div class="card-body">

                    <form method="POST">
                        <div class="form-group">
                            <label for="username">Username <!--Имя пользователя--></label>
                            <input type="text" name="username" class="form-control" id="username" onchange="validUsername('username');"
                                   placeholder="vasya1234" required>
                        </div>
                        <div class="form-group">
                            <#--<label for="useremail">Электронная почта</label>-->
                            <label for="useremail">Email</label>

                            <input type="email" name="email" class="form-control" id="useremail" placeholder="user@example.com"
                                   required>
                        </div>
                        <div class="form-group">
                            <#--<label for="password">Пароль</label>-->
                            <label for="password">Password</label>

                            <input type="password" name="password" class="form-control" id="password" onchange="validatePassword();"
                                   placeholder="qwerty" required>

                            <#--<label for="confirm-password">Повторите пароль</label>  <!--//было for="password2"&ndash;&gt;-->
                                <label for="confirm-password">Repeat password</label>
                                <input type="password" class="form-control" id="confirm-password" onkeyup="validatePassword();"
                                   placeholder="qwerty" required>
                        </div>

                        <div class="form-group">
                            <#--<input type="submit" class="btn btn-primary col" value="Регистрация">-->
                            <input type="submit" class="btn btn-primary col" value="Registration">
                        </div>

                        <#--Уже зарегистрированы?<br>-->
                        already have an account?<br>

                    <#--<a href="/auth" class="card-link">Войти</a>-->
                        <a href="/auth" class="card-link">Log in</a>

                    </form>

                </div>


            </div>

            <div class="col"></div>
            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>