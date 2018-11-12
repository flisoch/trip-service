<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Search">

    <jsp:attribute name="head_area">
        <script src="/static/js/trip.js"></script>
    </jsp:attribute>
    <jsp:attribute name="body_content">
    	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100 " id="inner-nav">
                <div class="card">
                    <div class="alert alert-info alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                         Choose on or more parameters to find a trip
                    </div>
                    <div class="card-body">
                        <form class="form-row align-items-center" action="/trips/search" method="GET">

                            <div class="col-sm-3 my-1">
                                <label for="departure">From</label>
                                <input type="text" class="form-control" id="departure" name="departure" placeholder="Point A">
                            </div>
                            <div class="col-sm-3 my-1">
                                <label for="destination">Where</label>
                                <input type="text" class="form-control" id="destination" name="destination" placeholder="Point B">
                            </div>
                            <div class="col-sm-1 my-1">
                                <label for="seats">Seats </label>
                                <input type="number" class="form-control" id="seats" name="seats">
                            </div>
                            <div class="col-sm-3 my-1">
                                <label for="timeToInputField">Date and time</label>
                                <input name="time_to" type="datetime-local" class="form-control" <%--value="1970-01-01T23:00"--%>
                                    id="timeToInputField" >
                            </div>
                            <div class="col-1 my-1" style="margin-top: 5rem;">
                                <div style="color: white;">
                                    search
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary">Search</button>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="list-group">
                    <c:forEach var="trip" items="${trips}">
                        <a <%--href="/trips/${trip.id}"--%> class="list-group-item list-group-item-action"
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
                                    <img src="${trip.iniciator.photo}" width="50">
                                    <span>${trip.iniciator.username}</span>
                                </div>
                                <div class="col">
                                    <c:choose>
                                        <c:when test="${trip.iniciator.id == user.id}">
                                            <span class="btn btn-xs btn-info" onclick="location.href='/trips/${trip.id}/edit';event.stopPropagation();">
                                        Edit
                                    </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="btn btn-xs btn-primary" onclick="apply(${trip.id});event.stopPropagation();">
                                        Apply
                                    </span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>