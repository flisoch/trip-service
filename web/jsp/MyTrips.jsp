<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:baseTemplate title="My Trips">


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
                        <c:forEach var="trip" items="${activeTrips}">
                            <div class="card">
                                <div class="card-body">
                                    <p>From: ${trip.departurePoint}<span> dateTime: ${trip.date} 12/08/2015 13:00</span></p>
                                    <p>Where: ${trip.arrivalPoint}</p>
                                    <p>Free seats: ${trip.freeSeats}</p>
                                    <button class="btn btn-primary" onclick="location.href='/trips/${trip.id}'">View</button>
                                    <button onclick="location.href='/trips/${trip.id}/edit'"class="btn btn-primary">Edit</button>
                                </div>
                            </div>
                        </c:forEach>
                        <%--<c:if test="${activeTrips}"></c:if>--%>
                    </div>

                    <div id="expired" class="container tab-pane">
                        <c:forEach var="trip" items="${expiredTrips}">
                            <div class="card">
                                <div class="card-body">
                                    <p>From: ${trip.departurePoint}<span> dateTime:${trip.date} 12/08/2015 13:00</span></p>
                                    <p>Where: ${trip.arrivalPoint}</p>
                                    <p>Free seats: ${trip.freeSeats}</p>
                                    <button class="btn btn-primary" onclick="location.href='/trips/${trip.id}'">View</button>
                                    <button onclick="location.href='/trips/${trip.id}/edit'"class="btn btn-primary">Edit</button>
                                </div>
                            </div>
                        </c:forEach>
                        <%--<p>You don't have any for now</p>--%>
                    </div>
                    <!--/Tab panes-->
                </div>
            </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>