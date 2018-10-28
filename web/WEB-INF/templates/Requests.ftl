<#include "template.ftl">


<#macro import>
    <script src="/static/js/requests.js"></script>

    <link rel="stylesheet" href="/static/css/style.css">

    <style>
        .card {
            display: inline-block;
            max-width: 32%;
        }
    </style>
</#macro>


<#macro title>
    <title>Requests</title>
</#macro>

<#macro content>
     <div class="container">

         <!-- CONTENT -->

         <div class="row">
             <div class="container" style="display: block;">

                 <#if requests??>
                     <#list requests as request>
                        <div class="card" id="request_${request.id}">
                            <h5 class="card-header">
                                <a href="/trips/${request.trip.id}">Trip N${request.trip.id}</a>
                            </h5>

                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="/profile/${request.user.id}">User:${request.user.username}</a>
                                </h5>
                        
                                <button onclick="accept(${request.trip.id},${request.user.id}, ${request.id})"
                                class="btn btn-primary">accept</button>
                                <button onclick="reject(${request.trip.id},${request.user.id}, ${request.id})"
                                class="btn btn-primary">reject</button>


                            </div>
                        </div>
                     </#list>
                 <#else>
                    Poka net zaprosov
                 </#if>

             </div>
             <!-- /CONTENT -->


         </div>
     </div>
</#macro>

<@display_page/>