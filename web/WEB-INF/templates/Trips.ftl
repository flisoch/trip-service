<#include "template.ftl">


<#macro import>
    <script src="/static/js/trip.js"></script>

    <link rel="stylesheet" href="./static/css/style.css">

    <style>
        .card {
            display: inline-block;
            max-width: 32%;
        }
    </style>
</#macro>


<#macro title>
    <title>Trips</title>
</#macro>

<#macro content>
     <div class="container">
         <div class="row">

             <!-- CONTENT -->

             <div class="container" style="display: block;">

                 <#if trips?has_content>
                     <#list trips as trip>
                        <div class="card">
                            <h5 class="card-header">
                                <a href="/trips/${trip.id}">Kazan</a>
                            </h5>

                              <div class="card-body">
                                  <h5 class="card-title">${trip.departurePoint} - ${trip.arrivalPoint}</h5>
                                  <p class="card-text">
                                      <#if trip.info??>
                                          ${trip.info}
                                      </#if>
                                  </p>
                                  <button onclick="apply(${trip.id})" class="btn btn-primary">Apply</button>
                              </div>
                        </div>
                     </#list>
                 <#else>
                    Poka net postov
                 </#if>

             </div>
             <!-- /CONTENT -->


         </div>
     </div>
</#macro>

<@display_page/>