<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                <c:choose>

                    <c:when test="${empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="/auth">Sign in</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/registration">Sign up</a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                ${user.username}
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
                    </c:otherwise>
                </c:choose>

                </ul>


            </div>
        </div>
    </nav>
</div>
<!-- /NAVBAR -->
