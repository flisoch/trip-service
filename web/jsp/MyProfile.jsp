
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="My Profile">


    <jsp:attribute name="body_content">
      <!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100" id="inner-nav">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs ml-3">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">Edit profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">Otzivi</a>
                    </li>
                </ul>
                <!--/Nav tabs-->

                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="home" class="container tab-pane active" >
                        <div class="card">
                            <div class="card-body">
                                <form action="/profile" method="POST">

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="profile-picture">Profile photo</label>
                                            <p><img class="img-fluid" src="/static/pictures/default.png"></p>
                                            <input class="disable" type="file" name="profile-picture" id="profile-picture">
                                        </div>
                                    </div>


                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="username">Username</label>
                                            <input type="text" class="form-control disable" id="username" placeholder="vasya1234"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="firstname">Name</label>
                                            <input type="text" class="form-control disable" id="firstname" value="Виталий"
                                                   disabled>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="middlename">Middlename</label>
                                            <input type="text" class="form-control disable" id="middlename" value="Компьютер"
                                                   disabled>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="lastname">Lastname</label>
                                            <input type="text" class="form-control disable" id="lastname" value="Компьютер"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="password">Пароль</label>
                                            <input type="password" name="password" class="form-control disable" id="password"
                                                   placeholder="qwerty" disabled>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="confirm-password">Repeat password</label>
                                            <input type="confirm-password" class="form-control disable" id="confirm-password" value="qwerty"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="age">Age</label>
                                            <input type="number" name="age" min="10" max="137" class="form-control disable" id="age"
                                                   value="38" disabled>
                                        </div>

                                        <div class="form-group col-3">
                                            <label for="working-place">Working place</label>
                                            <input type="text" class="form-control disable" id="working-place" value="McDonalds"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="inputAddress">Address</label>
                                            <input type="text" name="address" class="form-control disable" id="inputAddress" value="ул. Пушкина, д. Колотушкина"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="bio">О себе</label>
                                            <textarea class="form-control disable" id="bio" rows="3" disabled>У меня есть родинка на ухе</textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary disable" value="Сохранить изменения">
                                        <input type="reset" class="btn btn-warning disable" value="Вернуть, как было">
                                    </div>

                                    <button type="button" class="btn btn-primary" onclick="enable();">Изменить</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="menu1" class="container tab-pane fade">
                        <div class="card ml-0" id="request_${request.id}">
                            <h5 class="card-header">
                                <img src="../../static/pictures/default.png" width="50">
                                <a href="/profile/${otziv.user.username}">User:${otziv.user.username}</a>
                                <span>17/08/2015</span>
                            </h5>

                            <div class="card-body">
                                <h5 class="card-title">
                                    <p>Message:${otziv.text}</p>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <!--/Tab panes-->
                </div>
            </div>
            <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>