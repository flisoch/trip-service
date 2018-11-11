<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="">
	<jsp:attribute name="head_area">
        <script src="/static/js/trip.js"></script>
    </jsp:attribute>

    <jsp:attribute name="body_content">
          <!-- CONTENT -->
            <div class="col-8">
                <div class="row w-100 h-100" id="inner-nav">
                    <div class="card text-center">
                        <div class="card-header">
                            <a href="/profile/${trip.iniciator.id}">${trip.iniciator.username}</a>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">From:${trip.departurePoint} <br> To: ${trip.arrivalPoint}</h5>
                            <p class="card-text">
                                Additional info: ${trip.info}

                            </p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                        <div class="card-footer text-muted">
                            Date: ${trip.date}
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${empty comments}">
                        </c:when>
                        <c:otherwise>
                            <div id="comments-container">
                                <c:forEach var="comment" items="${comments}">
                                    <div class="card border-secondary mb-3">
                                        <div class="card-header">
                                            <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                        </div>
                                        <div class="card-body text-secondary">
                                            <h5 class="card-title">${comment.text}</h5>
                                            <p class="card-text" id="comment_${comment.id}_text">
                                                    ${comment.date}
                                            </p>
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