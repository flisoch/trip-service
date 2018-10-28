<#include "template.ftl">


<#macro title>
    <#--<title>Регистрация</title>-->
    <title>new Trip</title>

</#macro>


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/trip.js"></script>
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
                    <!--Регистрация-->
                    new trip
                </div>

                <div class="card-body">

                    <form method="POST">
                        <div class="form-group">
                            <label for="info">trip info <!--trip info--></label>
                            <input type="text" name="info" class="form-control" id="info"
                                   placeholder="morning trip from zelenodolsk to Kazan downtown" required>
                        </div>
                        <div class="form-group">
                            <label for="departure">departure</label>

                            <input type="text" name="departure" class="form-control" id="departure" placeholder="zelenodolsk"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="destination">destination</label>

                            <input type="text" name="destination" class="form-control" id="destination" placeholder="kazan" required>

                        </div>

                        <div class="form-group">
                            <label for="seats">seats</label>

                            <input type="number" name="seats" class="form-control" id="seats" placeholder="1" required>

                        </div>

                        <div class="form-group">
                          <label for="timeToInputField">Date and time</label>
                          <input name="time_to" type="datetime-local" class="form-control" value="1970-01-01T23:00"
                              id="timeToInputField" required>
                        </div>

                        <div class="form-group">
                            <#--<input type="submit" class="btn btn-primary col" value="Регистрация">-->
                            <input type="submit" class="btn btn-primary col" value="Create">
                        </div>

                    </form>

                </div>


            </div>

            <div class="col"></div>
            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>