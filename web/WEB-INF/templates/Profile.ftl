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
                    Profile
                </div>

                <div class="card-body">
                    <form action="/profile" method="POST">

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="profile_picture">profile picture</label>
                                <input class="disabled" type="file" name="profile_picture" id="profile_picture"><!--не былоid-->
                            </div>
                        </div>


                        <div class="form-row">
                            <div class="form-group">
                                <label for="username">username</label>
                                <input type="text" class="form-control disabled" id="username" placeholder="vasya1234"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="firstname">Name</label>
                                <input type="text" class="form-control disabled" id="firstname" value="Vitaly"
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="middlename">Middlename</label>
                                <input type="text" class="form-control disabled" id="middlename" value="Computer"
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="lastname">Lastname</label>
                                <input type="text" class="form-control disabled" id="lastname" value="Computer"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="password">Password</label>
                                <input type="password" name="password" class="form-control disabled" id="password"
                                       placeholder="qwerty" disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="confirm-password">Repeat password</label><!--//было не так: тип, айди-->
                                <input type="password" class="form-control disabled" id="confirm-password" value="qwerty"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="age">Age</label>
                                <input type="number" name="age" min="10" max="137" class="form-control disabled" id="age"
                                       value="38" disabled>
                            </div>

                            <div class="form-group col-auto">
                                <label for="working-place">Working place</label>
                                <input type="text" class="form-control disabled" id="working-place" value="McDonalds"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="inputAddress">Address</label>
                                <input type="text" name="address" class="form-control disabled" id="inputAddress" value="pushkina,kolotushkina"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-group col-auto">
                            <label for="bio">About me</label>
                            <textarea class="form-control disabled" id="bio" rows="3" disabled>I have a rodinka on my ear</textarea>
                        </div>

                        <div class="form-group">

                            <input type="submit" class="btn btn-primary disabled" value="submit" disabled>
                            <input type="reset" class="btn btn-warning disabled" value="reset" disabled>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="enable();">Change</button>
                    </form>
                </div>


            </div>
            <div class="col"></div>

            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>

