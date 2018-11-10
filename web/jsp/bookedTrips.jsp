<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Booked Tips">


    <jsp:attribute name="body_content">
    	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100 " id="inner-nav">
                <c:forEach var="trip" items="${trips}">
                    <div class="card mx-3">
                        <div class="card-body">
                            <p>From: ${trip.departurePoint}<span> dateTime: ${trip.date} 12/08/2015 13:00</span></p>
                            <p>Where: ${trip.arrivalPoint}</p>
                            <p>Free seats: ${trip.freeSeats}</p>
                            <button class="btn btn-primary" onclick="location.href='/trips/${trip.id}'">View</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>