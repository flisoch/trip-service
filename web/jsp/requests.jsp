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
                        <%--<c:if test="${requestScope.requests.size() != 0}">--%>
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
                        <%--</c:if>--%>

                    </div>
                    <div id="menu1" class="container tab-pane fade">
                        <div class="card" id="request_${request.id}">
                            <h5 class="card-header">
                                <a href="/trips/${request.trip.id}">Trip #${request.trip.id}</a>
                            </h5>

                            <div class="card-body">
                                <h5 class="card-title">
                                    <p>From: Kazan<span> dateTime: 12/08/2015 13:00</span></p>
                                    <p>Where: Zeleno</p>
                                    <p>Free seats: 3</p>
                                </h5>
                    
                                <button onclick="cancel(${request.trip.id},${request.user.id}, ${request.id})"
                                class="btn btn-danger">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/Tab panes-->
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>