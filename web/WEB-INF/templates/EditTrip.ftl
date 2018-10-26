<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/registration.js"></script>
    <script src="/static/js/trip.js"></script>
    <script src="/static/js/profile.js"></script>

</#macro>


<#macro title>
    <title>
        Edit Trip
    </title>
</#macro>


<#macro content>
    <div class="container">
        <div class="row">

            <!-- CONTENT -->

            <div class="col"></div>
            <div class="card">

                <div class="card-header">
                    Profile
                </div>

                <div class="card-body">
                    <#--<form action="/profile" method="POST">-->

                

                    

                        <div class="form-row">
                            <div class="form-group">
                                <label for="info">Info</label>
                                <input type="text" name = "info" class="form-control disable" id="info"
                                        <#if trip.info??>
                                            value="${trip.info}"
                                        <#else>value=""
                                        </#if>
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="departure">departure</label>
                                <input type="text" name = "departure" class="form-control disable" id="departure"
                                        <#if trip.departurePoint??>
                                            value="${trip.departurePoint}"
                                        <#else>value="departure"</#if>
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="destination">destination</label>
                                <input type="text" name = "destination" class="form-control disable" id="destination"
                                        <#if trip.arrivalPoint??>
                                            value="${trip.arrivalPoint}"
                                        <#else>value="destination"</#if>
                                       disabled>
                            </div>
                            <div class="form-group col-auto">
                                <label for="seats">seats</label>
                                <input type="number" name="seats" class="form-control disable" id="seats" required 
                                    <#if trip.freeSeats??>
                                        value="${trip.freeSeats}"
                                    <#else>value="1"
                                    </#if>
                                   disabled>
                            </div>

                            <div class="form-group col-auto">
                              <label for="timeToInputField">Date and time</label>
                              <input name="time_to" type="datetime-local" class="form-control disable" 
                              id="timeToInputField" required
                                 <#if trip.date??>
                                    value="1970-01-01T23:00"
                                    <!--LocalDateTime.ofInstant(Instant.ofEpochMilli(trip.date), ZoneId.systemDefault());-->
                                <#else>value="1970-01-01T23:00"
                                </#if>
                                disabled>
                            </div>
                        </div>

                        <div class="form-group">

                            <input type="button" onclick="submitTripChanges(${trip.id});" class="btn btn-primary disable" value="submit" disabled>
                            <input type="reset" class="btn btn-warning disable" value="reset" disabled>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="enable();">Change</button>
                    <#--</form>-->
                </div>


            </div>
            <div class="col"></div>

            <!-- /CONTENT -->


        </div>
    </div>
</#macro>


<@display_page/>

