<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="My Profile">

    <jsp:attribute name="head_area">
        <script src="/static/js/profile.js"></script>
    </jsp:attribute>
    <jsp:attribute name="body_content">
      <!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100" id="inner-nav">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs ml-3">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">Edit profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">Comments</a>
                    </li>
                </ul>
                <!--/Nav tabs-->

                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="home" class="container tab-pane active">
                        <div class="card">
                            <div class="card-body">
                                <form action="/profile" method="POST">

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="profile-picture">Profile photo</label>
                                            <p>
                                                <c:choose>
                                                <c:when test="${empty user.photo}">
                                                    <img class="img-fluid" src="/static/pictures/default.png">
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="img-fluid" src="${user.photo}">
                                                </c:otherwise>
                                            </c:choose>
                                            </p>
                                            <input class="disable" type="file" name="profile-picture"
                                                   id="profile-picture">
                                        </div>
                                    </div>


                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="username">Username</label>

                                            <c:choose>
                                                <c:when test="${empty user.username}">
                                                    <input type="text" class="form-control disable" name="username"
                                                           id="username"
                                                           value="" disabled>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="text" class="form-control disable" name="username"
                                                           id="username"
                                                           disabled value="${user.username}">
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="firstname">Name</label>
                                            <c:choose>
                                            <c:when test="${empty user.name}">
                                                    <input type="text" class="form-control disable" name="name"
                                                           id="firstname" disabled value="">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" class="form-control disable"
                                                       name="name" id="firstname" value="${user.name}" disabled>
                                            </c:otherwise>
                                            </c:choose>

                                        </div>

                                        <div class="form-group col-3">
                                            <label for="middlename">Middlename</label>
                                            <c:choose>
                                            <c:when test="${empty user.middlename}">
                                                   <input type="text" class="form-control disable" name="middlename"
                                                          id="middlename" value="" disabled>
                                            </c:when>
                                                   <c:otherwise>
                                                       <input type="text" class="form-control disable" name="middlename"
                                                              id="middlename" value="${user.middlename}" disabled>
                                                   </c:otherwise>
                                            </c:choose>

                                        </div>
                                        <div class="form-group col-3">
                                            <label for="lastname">Lastname</label>
                                            <input type="text" class="form-control disable" name="lastname"
                                                   id="lastname"
                                            <c:choose>
                                            <c:when test="${empty user.lastname}">
                                                   value=""
                                            </c:when>
                                                   <c:otherwise>value="${user.lastname}"</c:otherwise>
                                            </c:choose>
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="password">Password</label>
                                            <input type="password" name="password" class="form-control disable"
                                                   id="password"
                                                   placeholder="qwerty" disabled>
                                        </div>
                                        <div class="form-group col-3">
                                            <label for="confirm-password">Repeat password</label>
                                            <input type="password" class="form-control disable"
                                                   id="confirm-password" placeholder="qwerty"
                                                   disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-3">
                                            <label for="age">Age</label>
                                            <c:choose>
                                            <c:when test="${empty user.age}">
                                            <input type="number" name="age" min="10" max="137"
                                                   class="form-control disable" id="age" value="0" disabled>
                                            </c:when>
                                           <c:otherwise>
                                               <input type="number" name="age" min="10" max="137"
                                                      class="form-control disable" id="age" value="${user.age}"
                                                      disabled>
                                           </c:otherwise>
                                            </c:choose>

                                        </div>

                                        <div class="form-group col-3">
                                            <label for="working-place">Working place</label>
                                            <c:choose>
                                            <c:when test="${empty user.job}">
                                            <input type="text" class="form-control disable" name="job"
                                                   id="working-place" disabled value="">
                                            </c:when>
                                                   <c:otherwise><input type="text" class="form-control disable"
                                                                       name="job" id="working-place" disabled
                                                                       value="${user.job}"></c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="inputAddress">Address</label>

                                            <c:choose>
                                            <c:when test="${empty user.address}">
                                            <input type="text" name="address" class="form-control disable"
                                                   id="inputAddress" disabled value="">
                                            </c:when>
                                                   <c:otherwise>
                                            <input type="text" name="address" class="form-control disable"
                                                   id="inputAddress" disabled value="${user.address}"></c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col">
                                            <label for="additionalInfo">About me</label>
                                            <c:choose>
                                                <c:when test="${empty user.additionalInfo}">
                                                    <textarea class="form-control disable" name="additionalInfo"
                                                              rows="3"
                                                              disabled></textarea>
                                                </c:when>
                                                       <c:otherwise>
                                                           <textarea class="form-control disable" name="additionalInfo"
                                                                     rows="3"
                                                                     disabled>${user.additionalInfo}</textarea>
                                                       </c:otherwise>
                                                </c:choose>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary disable" value="Save">
                                        <button type="button" class="btn btn-warning" onclick="enable();">Change
                                        </button>
                                    </div>


                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="menu1" class="container tab-pane fade">
                        <div id="comments-container">
                            <c:choose>
                                <c:when test="${empty comments}">
                                    <div class="card border-secondary mb-3" id="no_comments_card">

                                        <div class="card-body text-secondary">
                                            <p class="card-text">
                                                No Comments yet
                                            </p>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    
                                        <c:forEach var="comment" items="${comments}">
                                            <div class="card border-secondary mb-3">
                                                <div class="card-header">
                                                    <img src="${comment.commentator.photo}" width="50">
                                                    <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                                </div>
                                                <div class="card-body text-secondary">
                                                    <h5 class="card-title">${comment.text}</h5>
                                                    <p class="card-text" id="comment_${comment.id}_text">${comment.dateTime}
                                                    </p>

                                                        <%--<script>
                                                            document.getElementById('comment_${comment.id}_text').innerText
                                                                = formatDate(new Date(${comment.dateTime}));
                                                        </script>--%>
                                                </div>
                                            </div>
                                         </c:forEach>

                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
                    <!--/Tab panes-->
                </div>
            </div>
            <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>