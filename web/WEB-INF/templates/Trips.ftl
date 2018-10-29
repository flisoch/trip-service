<#include "template.ftl">


<#macro import>
    <script src="/static/js/trip.js"></script>

    <link rel="stylesheet" href="/static/css/style.css">

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

             <!-- CONTENT -->
    
        <div class="row">
            <div class="card">

              <div class="card-body">
                  <form class="form-row align-items-center" action="/trips/search" method="GET">

                      <div class="col-sm-3 my-1">
                          <label for="departure">From</label>
                          <input type="text" class="form-control" id="departure" name="departure" placeholder="point A">
                      </div>
                      <div class="col-sm-3 my-1">
                          <label for="destination">Where</label>
                          <input type="text" class="form-control" id="destination" name="destination" placeholder="point B">
                      </div>
                      <div class="col-sm-4 my-1">
                          <label for="timeToInputField">Date and time</label>
                          <input name="time_to" type="datetime-local" class="form-control" value="1970-01-01T23:00"
                              id="timeToInputField" required>
                      </div>
                      <div class="col-auto my-1" style="margin-top: 5rem;">
                          <div style="color: white;">
                              search
                          </div>
                          <div>
                              <button type="submit" class="btn btn-primary">search</button>

                          </div>
                      </div>

                  </form>
              </div>
          </div>
        </div>

      
          
        <div class="row">
             <div class="container" style="display: block;">

                 <#if trips?has_content>
                     <#list trips as trip>
                        <div class="card">
                            <h5 class="card-header">
                                <a href="/trips/${trip.id}">Trip N${trip.id}</a>
                            </h5>

                              <div class="card-body">
                                  <h5 class="card-title">${trip.departurePoint} - ${trip.arrivalPoint}</h5>
                                  <p class="card-text">
                                      <#if trip.info??>
                                          ${trip.info}
                                      </#if>
                                  </p>
                                  <p class="card-text">
                                      <#if trip.freeSeats??>
                                          Free seats: ${trip.freeSeats}
                                      </#if>
                                  </p>
                                  <#if trip.iniciator.id != user.id>
                                    <button onclick="apply(${trip.id})" class="btn btn-primary">Apply</button>
                                  <#else>
                                    <button onclick="location.href='/trips/${trip.id}/edit'"class="btn btn-primary">Edit</button>
                                  </#if>
                                  <#--<#if уже отправлял или уже в поездке>

                                  </#if>-->
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