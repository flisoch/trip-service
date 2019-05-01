<#include "template.ftl">


<#macro import>
    <script src="/static/js/profile.js"></script>
</#macro>


<#macro title>
    <title>
        <#if user.username??>
            ${user.username}
        <#else > unnamed
        </#if>
    </title>
</#macro>

<#macro content>
    <!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100" id="inner-nav">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs ml-3">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">Edit profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">Comments</a>
                    </li>
                </ul>
                <!--/Nav tabs-->

                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="home" class="container tab-pane active">
                        <div class="card">
                            <div class="card-body">
                                <form action="/profile/edit" method="POST">

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="profile-picture">Profile photo</label>
                                            <p>
                                                <#if user.photo??>
                                                    <img class="img-fluid" src="${user.photo}">
                                                <#else>
                                                    <img class="img-fluid" src="/static/pictures/default.png">
                                                </#if>
                                            </p>
                                            <input class="disable" type="file" name="profile-picture"
                                                   id="profile-picture">
                                        </div>
                                    </div>


                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="username">Username</label>

                                            <#if user.username??>
                                                    <input type="text" class="form-control disable" name="username"
                                                           id="username"
                                                           disabled value="${user.username}">
                                            <#else>
                                                    <input type="text" class="form-control disable" name="username"
                                                           id="username"
                                                           value="" disabled>
                                            </#if>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="firstname">Name</label>

                                            <#if user.name??>
                                                    <input type="text" class="form-control disable"
                                                           name="name" id="firstname" value="${user.name}" disabled>
                                            <#else>
                                                     <input type="text" class="form-control disable" name="name"
                                                            id="firstname" disabled value="">
                                            </#if>
                                        </div>

                                        <div class="form-group col-3">

                                            <label for="middlename">Middlename</label>

                                            <#if user.middlename??>
                                                <input type="text" class="form-control disable" name="middlename"
                                                       id="middlename" value="${user.middlename}" disabled>
                                            <#else>
                                                    <input type="text" class="form-control disable" name="middlename"
                                                           id="middlename" value="" disabled>
                                            </#if>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="lastname">Lastname</label>
                                            <input type="text" class="form-control disable" name="lastname"
                                                   id="lastname"
                                                <#if user.lastname??>
                                                    value="${user.lastname}"
                                                <#else>
                                                    value=""
                                                </#if>
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="password">Password</label>
                                            <input type="password" name="password" class="form-control disable"
                                                   id="password"
                                                   placeholder="qwerty" disabled>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="confirm-password">Repeat password</label>
                                            <input type="password" class="form-control disable"
                                                   id="confirm-password" placeholder="qwerty"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="age">Age</label>

                                            <#if user.age??>
                                                    <input type="number" name="age" min="10" max="137"
                                                           class="form-control disable" id="age" value="${user.age}"
                                                           disabled>
                                            <#else>
                                                    <input type="number" name="age" min="10" max="137"
                                                           class="form-control disable" id="age" value="0" disabled>
                                            </#if>


                                        </div>

                                        <div class="form-group col-3">
                                            <label for="working-place">Working place</label>

                                            <#if user.job??>
                                                    <input type="text" class="form-control disable"
                                                           name="job" id="working-place" disabled
                                                           value="${user.job}">
                                            <#else>
                                                    <input type="text" class="form-control disable" name="job"
                                                           id="working-place" disabled value="">
                                            </#if>


                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="inputAddress">Address</label>

                                            <#if user.address??>
                                                    <input type="text" name="address" class="form-control disable"
                                                           id="inputAddress" disabled
                                                           value="${user.address}">
                                            <#else>
                                                    <input type="text" name="address" class="form-control disable"
                                                           id="inputAddress" disabled value="">
                                            </#if>

                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="bio">About me</label>

                                            <#if user.additionalInfo??>
                                                    <textarea class="form-control disable" name="bio" id="bio" rows="3"
                                                              disabled>${user.additionalInfo}</textarea>
                                            <#else>
                                                    <textarea class="form-control disable" name="bio" id="bio" rows="3"
                                                              disabled></textarea>
                                            </#if>


                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary disable" value="Save">
                                        <button type="button" class="btn btn-warning" onclick="enable();">Change
                                        </button>
                                    </div>


                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="menu1" class="container tab-pane fade">
                        <div id="comments-container">
                            <#if comments??>
                                <#list comments as comment>
                                    
                                            <div class="card border-secondary mb-3">
                                                <div class="card-header">
                                                    <img src="${comment.commentator.photo}" width="50">
                                                    <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                                </div>
                                                <div class="card-body text-secondary">
                                                    <h5 class="card-title">${comment.text}</h5>
                                                    <p class="card-text" id="comment_${comment.id}_text">
                                                    </p>

                                                    <script>
                                                        document.getElementById('comment_${comment.id}_text').innerText
                                                                = formatDate(new Date(${comment.date}));
                                                    </script>
                                                </div>
                                            </div>

                                </#list>

                            <#else>
                                <div class="card border-secondary mb-3" id="no_comments_card">

                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No Comments yet
                                        </p>
                                    </div>
                                </div>
                            </#if>

                        </div>

                    </div>
                    <!--/Tab panes-->
                </div>
            </div>
    <!-- /CONTENT -->
</#macro>

<@display_page/>