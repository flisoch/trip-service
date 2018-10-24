<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">

    <script src="/static/js/trip.js" defer></script>
</#macro>


<#macro title>
    <title>Trip N</title>
</#macro>

<#macro content>
     <div class="container">
         <div class="row">

             <#if trip??>
                     <!-- CONTENT -->
                <div class="col">
                    <div class="container">
                        <div class="card text-center">
                            <div class="card-header">
                                Featured
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${trip.departurePoint} - ${trip.arrivalPoint}</h5>
                                <p class="card-text">
                                    <#if trip.info??>
                                        ${trip.info}
                                    </#if>

                                </p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                            <div class="card-footer text-muted">
                                ${trip.date}
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="container" id="comments-container">
                        <#if comments??>
                            <#list comments as comment>
                                <div class="card border-secondary mb-3">
                                    <div class="card-header">
                                        <a href="/profile/${comment.commentator.id}"> ${comment.commentator.username}</a>
                                    </div>
                                    <div class="card-body text-secondary">
                                        <h5 class="card-title">${comment.text}</h5>
                                        <p class="card-text" id="comment_${comment.id}_text">
                                            ${comment.date}
                                        </p>
                                        <#--<script>-->
                                            <#--&lt;#&ndash;alert("comment_${comment.id}_text");&ndash;&gt;-->
                                            <#--&lt;#&ndash;document.getElementById("comment_${comment.id}_text").innerHTML = (new Date(${comment.date}));&ndash;&gt;-->
                                        <#--</script>-->
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                    <div class="container" id="comment-send">
                        <div class="card border-info mb-3">
                            <div class="card-header">Leave comment</div>
                            <textarea class="card-body text-secondary" id="comment-text"></textarea>
                            <div class="card-footer text-right">
                                <button class="btn btn-sm" onclick="sendComment(#{trip.id});">Send</button>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /CONTENT -->
             <#else>
                 takoy poezdki ne sushestvuet
             </#if>

         </div>
     </div>
</#macro>

<@display_page/>