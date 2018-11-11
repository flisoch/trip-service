    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Requests">
    <jsp:attribute name="head_area">
        <script src="/static/js/requests.js"></script>
    </jsp:attribute>
    <jsp:attribute name="body_content">
      	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100" id="inner-nav">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs ml-3">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">To me</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">From me</a>
                    </li>
                </ul>
                <!--/Nav tabs-->

                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="home" class="container tab-pane active">

                        <c:choose>
                            <c:when test="${empty requests_to_me}">
                                <div class="card mb-3" id="no_requests_to_me_card">

                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No requests yet
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="request" items="${requests_to_me}">
                                    <div class="card" id="request_${request.id}">
                                        <h5 class="card-header">
                                            <a href="/trips/${request.trip.id}">Trip #${request.trip.id}</a>
                                        </h5>

                                        <div class="card-body">
                                            <h5 class="card-title">
                                                <a href="/profile/${request.user.id}">User:${request.user.username}</a>
                                            </h5>

                                            <button onclick="accept(${request.trip.id},${request.user.id}, ${request.id})"
                                                    class="btn btn-success">accept</button>
                                            <button onclick="reject(${request.trip.id},${request.user.id}, ${request.id})"
                                                    class="btn btn-danger">reject</button>
                                        </div>
                                    </div>
                                 </c:forEach>
                            </c:otherwise>
                            </c:choose>
                    </div>

                    <div id="menu1" class="container tab-pane fade">
                        <div id="requests-container">
                        <c:choose>
                            <c:when test="${empty requests_from_me}">
                                <div class="card mb-3" id="no_requests_from_me_card">

                                    <div class="card-body text-secondary">
                                        <p class="card-text">
                                            No requests yet
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="request" items="${requests_from_me}">
                                    <div class="card" id="request_${request.id}">
                                        <h5 class="card-header">
                                            <a href="/trips/${request.trip.id}">Trip #${request.trip.id}</a>
                                        </h5>

                                        <div class="card-body">
                                            <h5 class="card-title">
                                                <a href="/profile/${request.user.id}">User:${request.user.username}</a>
                                            </h5>

                                            <button onclick="cancel(${request.id})"
                                                    class="btn btn-danger">Cancel</button>
                                        </div>
                                    </div>
                                 </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </div>
                </div>
                <!--/Tab panes-->
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>