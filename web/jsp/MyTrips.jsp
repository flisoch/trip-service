<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:baseTemplate title="My Trips">

    <jsp:attribute name="head_area">
        <script src="/static/js/trip.js"></script>
    </jsp:attribute>

    <jsp:attribute name="body_content">
    	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100" id="inner-nav">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs ml-3">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#active">active</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#expired">expired</a>
                    </li>
                </ul>
                <!--/Nav tabs-->

                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="active" class="container tab-pane active">
                        <c:choose>
                            <c:when test="${empty activeTrips}">
                                <div class="card mb-3" id="no-active-trips-card">
                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No active trips yet
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="list-group">
                                    <c:forEach var="trip" items="${activeTrips}">
                                        <a class="list-group-item list-group-item-action" id="trip_${trip.id}"
                                           onclick="location.href='/trips/${trip.id}'">
                                            <div class="row">
                                                <div class="col">
                                                    <p>From: ${trip.departurePoint}</p>
                                                </div>
                                                <div class="col">
                                                    <p id="trip_${trip.id}_date"></p>
                                                    <script>
                                                        document.getElementById('trip_${trip.id}_date').innerText
                                                            = "Date: " + formatDate(new Date(${trip.date}));
                                                    </script>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <p>Where: ${trip.arrivalPoint}</p>
                                                </div>
                                                <div class="col">
                                                    <p>free seats: ${trip.freeSeats}</p>
                                                </div>
                                            </div>

                                            <div class="row">

                                                <div class="col">
                                                    <span class="btn btn-xs btn-primary" onclick="location.href='/trips/${trip.id}/edit';event.stopPropagation();">
                                                        Edit
                                                    </span>
                                                    <span class="btn btn-xs btn-danger" onclick="deleteTrip(${trip.id});event.stopPropagation();">
                                                        Remove
                                                    </span>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>

                            </c:otherwise>
                        </c:choose>

                        <%--<c:if test="${activeTrips}"></c:if>--%>
                    </div>

                    <div id="expired" class="container tab-pane">
                        <c:choose>
                            <c:when test="${empty expiredTrips}">
                                <div class="card mb-3" id="no-active-trips-card">
                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No expired trips yet
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="list-group">
                                    <c:forEach var="trip" items="${expiredTrips}">
                                        <a class="list-group-item list-group-item-action"
                                           onclick="location.href='/trips/${trip.id}'">
                                            <div class="row">
                                                <div class="col">
                                                    <p>From: ${trip.departurePoint}</p>
                                                </div>
                                                <div class="col">
                                                    <p id="trip_${trip.id}_date"></p>
                                                    <script>
                                                        document.getElementById('trip_${trip.id}_date').innerText
                                                            = "Date: " + formatDate(new Date(${trip.date}));
                                                    </script>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <p>Where: ${trip.arrivalPoint}</p>
                                                </div>
                                                <div class="col">
                                                    <p>free seats: ${trip.freeSeats}</p>
                                                </div>
                                            </div>

                                            <div class="row">

                                                <div class="col">
                                                    <span class="btn btn-xs btn-danger" onclick="alert('asd');event.stopPropagation();">
                                                        Remove
                                                    </span>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>

                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!--/Tab panes-->
                </div>
            </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>