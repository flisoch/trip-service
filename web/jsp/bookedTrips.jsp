<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Booked Tips">


    <jsp:attribute name="body_content">
    	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100 " id="inner-nav">
                <div class="list-group" id="trips-container">

                    <c:choose>
                            <c:when test="${empty trips}">
                                <div class="card mb-3" id="no_requests_from_me_card">
                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No trips yet
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="trip" items="${trips}">
                                    <a href="/trips/${trip.id}" class="list-group-item list-group-item-action">
                                        <div class="row">
                                            <div class="col">
                                                <p>From: ${trip.departurePoint}</p>
                                            </div>
                                            <div class="col">
                                                <p>Date: ${trip.date} 04/03/19 23:00</p>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <p>To: ${trip.arrivalPoint}</p>
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
                                        </div>
                                    </a>

                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                </div>
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>