<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/registration.js"></script>
    <script src="/static/js/profile.js"></script>
</#macro>


<#macro title>
    <title>${user.username}</title>
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
                    <#--<form action="/profile" method="POST">-->

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="profile_picture">profile picture</label>
                                <input class="disabled" type="file" name="profile_picture" id="profile_picture"><!--не былоid-->
                            </div>
                        </div>


                        <div class="form-row">
                            <div class="form-group">
                                <label for="username">username</label>
                                <input type="text" class="form-control disable" id="username" value="${user.username}"
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="firstname">Name</label>
                                <input type="text" class="form-control disable" id="firstname"
                                        value=<#if user.name??>${user.name}<#else>name</#if>
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="middlename">Middlename</label>
                                <input type="text" class="form-control disable" id="middlename"
                                       value=<#if user.middleName??>${user.middleName}<#else>middlename</#if>
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="lastname">Lastname</label>
                                <input type="text" class="form-control disable" id="lastname"
                                       value=<#if user.lastname??>${user.lastname}<#else>lastname</#if>
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="password">Password</label>
                                <input type="password" name="password" class="form-control disable" id="password"
                                       onchange="validatePassword();" placeholder="qwerty" disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="confirm-password">Repeat password</label><!--//было не так: тип, айди-->
                                <input type="password" class="form-control disable" id="confirm-password"
                                       onkeyup="validatePassword();" placeholder="qwerty" disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="age">Age</label>
                                <input type="number" name="age" min="10" max="137" class="form-control disable" id="age"
                                       value=<#if user.age??>${user.age}<#else>0</#if> disabled>
                            </div>

                            <div class="form-group col-auto">
                                <label for="working-place">Working place</label>
                                <input type="text" class="form-control disable" id="working-place"
                                       value=<#if user.job??>${user.job}<#else>name</#if>
                                       disabled>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-auto">
                                <label for="inputAddress">Address</label>
                                <input type="text" name="address" class="form-control disable" id="inputAddress"
                                       value=<#if user.address??>${user.address}<#else>"pushkina,kolotushkina"</#if>
                                       disabled>
                            </div>
                        </div>

                        <div class="form-group col-auto">
                            <label for="bio">About me</label>
                            <textarea class="form-control disable" id="bio" rows="3" disabled>
                                <#if user.additionalInfo??>${user.additionalInfo}<#else>I have a rodinka on my ear</#if>
                            </textarea>
                        </div>

                        <div class="form-group">

                            <input type="button" onclick="submitChanges();" class="btn btn-primary disable" value="submit" disabled>
                            <input type="reset" class="btn btn-warning disable" value="reset" disabled>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="enable();">Change</button>
                    <#--</form>-->
                </div>


            </div>
            <div class="col"></div>

            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>

