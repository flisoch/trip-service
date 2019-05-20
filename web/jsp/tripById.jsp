<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="">
	<jsp:attribute name="head_area">
        <script src="/static/js/trip.js"></script>
    </jsp:attribute>

    <jsp:attribute name="body_content">
          <!-- CONTENT -->
            <div class="col-8">
                <div class="row w-100 h-100" id="inner-nav">
                    <div class="card">
                        <div class="card-header">
                            <a href="/profile/${trip.iniciator.id}">${trip.iniciator.username}</a>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">From:${trip.departurePoint} <br> To: ${trip.arrivalPoint}</h5>
                            <p class="card-text">
                                Additional info: ${trip.info}

                            </p>

                            <p id="trip_${trip.id}_date">${trip.date}</p>
                                <script>
                                    document.getElementById('trip_${trip.id}_date').innerText
                                        = "Date: " + formatDate(new Date(${trip.date}));
                                </script>

                            <span id="trip-${trip.id}-status" class="col">
                                    <c:choose>
                                        <c:when test="${trip.status == 'MY'}">
                                            <div class="btn btn-xs btn-info"
                                                 onclick="location.href='/trips/${trip.id}/edit';event.stopPropagation();">
                                                Edit
                                            </div>
                                        </c:when>
                                        <c:when test="${trip.status == 'BOOKED'}">
                                            <div>Status: Booked</div>
                                            <div class="btn btn-xs btn-info"
                                                 onclick="leaveTrip(${trip.id});event.stopPropagation();">
                                                Leave group
                                            </div>
                                        </c:when>
                                        <c:when test="${trip.status == 'WISHED'}">
                                            <div>Status: request sent</div>
                                            <div class="btn btn-xs btn-danger"
                                                 onclick="cancelRequest(${trip.id});event.stopPropagation();">
                                                Cancel request
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="btn btn-xs btn-primary"
                                                 onclick="apply(${trip.id});event.stopPropagation();">
                                                Apply
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                            </span>
                        </div>
                        <div class="card-footer text-muted">
                            <c:if test="${trip.status == 'MY'}">
                                <c:choose>
                                <c:when test="${empty trip.passangers}">
                                    <div id="passangers-container"></div>
                                </c:when>
                                <c:otherwise>
                                    <div id="passangers-container">
                                        <c:forEach var="passanger" items="${trip.passangers}">
                                            <p>
                                                <span>Passangers:</span>
                                                <c:choose>
                                                    <c:when test="${empty passanger.photo}">
                                                        <a href="/profile/${passanger.id}">
                                                            <img class="img-fluid" src="/static/pictures/default.png">
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/profile/${passanger.id}">
                                                            <img class="img-fluid" width="40" height="40"
                                                                 src="${passanger.photo}">
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>

                                         </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            </c:if>
                        </div>
                    </div>
                    <div id="comments-container">
                        <c:choose>
                        <c:when test="${empty comments}">
                            <div>No comments yet</div>
                        </c:when>
                        <c:otherwise>

                        <c:forEach var="passanger" items="${trip.passangers}">
                                    <div class="card border-secondary mb-3" id="passanger_${passanger.id}">
                                        <div class="card-header">
                                            <c:if test="${passanger.commentator.id == user.id}">
                                                <button onclick="deleteTripComment(${comment.trip.id},${comment.id})"
                                                        class="btn-sm btn btn-danger float-right">&times;
                                                </button>
                                            </c:if>
                                            <img src="${comment.commentator.photo}" width="50">
                                            <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                        </div>
                                        <div class="card-body text-secondary">
                                            <h5 class="card-title">${comment.text}</h5>
                                            <p class="card-text" id="comment_${comment.id}_text">
                                                    ${comment.dateTime}
                                            </p>
                                            <script>
                                                document.getElementById('comment_${comment.id}_text').innerText
                                                    = formatDate(new Date(${comment.dateTime}));
                                            </script>
                                        </div>
                                    </div>
                                 </c:forEach>
                    </div>
                    </c:otherwise>
                    </c:choose>
                </div>


                <div class="card border-info mb-3">
                    <div class="card-header">Leave comment</div>
                    <textarea class="card-body text-secondary" id="comment-text"></textarea>
                    <div class="card-footer text-right">
                        <button class="btn btn-sm" onclick="sendComment(${trip.id});">Send</button>
                    </div>
                </div>
            </div>
            </div>

                <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>