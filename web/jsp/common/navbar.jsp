<%--
  Created by IntelliJ IDEA.
  User: flisoch
  Date: 08.11.18
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<!-- NAVBAR -->
<div class="container fixed-top">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <div class="container">

            <!--Logo-->
            <a class="navbar-brand" href="#">TripService</a>

            <!--Button to expand/collapse navbar if screen is too small-->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!--content that would be expanded/collapsed-->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/trips/search">Find</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/trips/new">Create</a>
                    </li>
                </ul>


                <ul class="navbar-nav ">
                    <!-- IF NOT AUTHORIZED-->
                    <!-- <li class="nav-item">
                        <a class="nav-link" href="#">Sign in</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Sign up</a>
                    </li> -->

                    <!--IF AUTHORIZED-->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Anatoly
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/profile">Profile</a>
                            <a class="dropdown-item" href="/profile/trips">My trips</a>
                            <a class="dropdown-item" href="/profile/booked">Booked trips</a>
                            <a class="dropdown-item" href="/profile/requests">Requests</a>
                            <a class="dropdown-item" href="#">Messages</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Log out</a>
                        </div>
                    </li>
                </ul>


            </div>
        </div>
    </nav>
</div>
<!-- /NAVBAR -->
