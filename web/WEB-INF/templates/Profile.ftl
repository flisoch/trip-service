<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="./static/css/style.css">
    <script src="./static/js/registration.js"></script>
    <script src="./static/js/profile.js"></script>
</#macro>


<#macro title>
    <title>Профиль</title>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->

            <div class="col"></div>
            <div class="card">

                <div class="card-header">
                    Профиль
                </div>

                <div class="card-body">
                    <form action="/profile" method="POST">

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="profile_picture">Фото профиля</label>
                                <input class="disabled" type="file" name="profile_picture" id="profile_picture"><!--не былоid-->
                            </div>
                        </div>


                        <div class="form-row">
                            <div class="form-group">
                                <label for="username">Имя пользователя</label>
                                <input type="text" class="form-control disabled" id="username" placeholder="vasya1234"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="firstname">Имя</label>
                                <input type="text" class="form-control disabled" id="firstname" value="Виталий"
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="middlename">Отчество</label>
                                <input type="text" class="form-control disabled" id="middlename" value="Компьютер"
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="lastname">Фамилия</label>
                                <input type="text" class="form-control disabled" id="lastname" value="Компьютер"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="password">Пароль</label>
                                <input type="password" name="password" class="form-control disabled" id="password"
                                       placeholder="qwerty" disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="confirm-password">Повторите пароль</label><!--//было не так: тип, айди-->
                                <input type="password" class="form-control disabled" id="confirm-password" value="qwerty"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="age">Возраст</label>
                                <input type="number" name="age" min="10" max="137" class="form-control disabled" id="age"
                                       value="38" disabled>
                            </div>

                            <div class="form-group col-auto">
                                <label for="working-place">Место работы</label>
                                <input type="text" class="form-control disabled" id="working-place" value="McDonalds"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="inputAddress">Адрес</label>
                                <input type="text" name="address" class="form-control disabled" id="inputAddress" value="ул. Пушкина, д. Колотушкина"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-group col-auto">
                            <label for="bio">О себе</label>
                            <textarea class="form-control disabled" id="bio" rows="3" disabled>У меня есть родинка на ухе</textarea>
                        </div>

                        <div class="form-group">

                            <input type="submit" class="btn btn-primary disabled" value="Сохранить изменения" disabled>
                            <input type="reset" class="btn btn-warning disabled" value="Вернуть, как было" disabled>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="enable();">Изменить</button>
                    </form>
                </div>


            </div>
            <div class="col"></div>

            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>

