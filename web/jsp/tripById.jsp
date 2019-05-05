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
                            <%--<script>
                                document.getElementById('trip_${trip.id}_date').innerText
                                    = "Date: " + formatDate(new Date(${trip.date}));
                            </script>--%>

                            <c:choose>
                                <c:when test="${trip.iniciator.id == user.id}">
                                    <a href="/trips/${trip.id}/edit" class="btn btn-primary">Edit</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/trips/${trip.id}/apply" class="btn btn-primary">Apply</a>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="card-footer text-muted">
                            <c:choose>
                                <c:when test="${empty trip.passangers}">
                                    <div id="passangers-container"></div>
                                </c:when>
                                <c:otherwise>
                                    <div id="passangers-container">
                                        <c:forEach var="comment" items="${comments}">
                                            <p>
                                                <c:choose>
                                                    <c:when test="${empty passanger.photo}">
                                                        <a href="/profile/${passanger.id}">
                                                            <img class="img-fluid" src="/static/pictures/default.png">
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/profile/${passanger.id}">
                                                            <img class="img-fluid" src="${passanger.photo}">
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>

                                         </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${empty comments}">
                            <div id="comments-container"></div>
                        </c:when>
                        <c:otherwise>
                            <div id="comments-container">
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
                                                    ${comment.date}
                                            </p>
                                            <script>
                                                document.getElementById('comment_${comment.id}_text').innerText
                                                    = formatDate(new Date(${comment.date}));
                                            </script>
                                        </div>
                                    </div>
                                 </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>


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