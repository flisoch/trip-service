<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Edit tagdirip">
    <jsp:attribute name="head_area">
        <script src="/static/js/registration.js"></script>
        <script src="/static/js/trip.js"></script>
        <script src="/static/js/profile.js"></script>
    </jsp:attribute>
    <jsp:attribute name="body_content">
          	<div class="col-8">
                <div class="row w-100 h-100" id="inner-nav">
                    <!-- CONTENT -->

                    <div class="card mx-3">

                        <div class="card-header">
                            Trip N
                        </div>

                        <div class="card-body">

                            <form action="/profile" method="POST">

                                <div class="form-row">
                                    <div class="form-group col-4">
                                        <label for="departure">departure</label>
                                        <input type="text" name="departure" class="form-control disable" id="departure"
                                        value="${trip.departurePoint}" disabled>

                                    </div>

                                    <div class="form-group col-4">
                                        <label for="destination">destination</label>
                                        <input type="text" name="destination" class="form-control disable" id="destination"
                                        value="${trip.arrivalPoint}" disabled>
                                     </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-4">
                                        <label for="seats">seats</label>
                                        <input type="number" name="seats" class="form-control disable" id="seats" required
                                               value="${trip.freeSeats}" disabled>
                                    </div>

                                    <div class="form-group col-4">
                                        <label for="timeToInputField">Date and time</label>
                                        <input name="time_to" type="datetime-local" class="form-control disable"
                                               id="timeToInputField" required
                                               value="1970-01-01T23:00" disabled>
                                        <!--LocalDateTime.ofInstant(Instant.ofEpochMilli(trip.date), ZoneId.systemDefault());-->
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-8">
                                    <label for="info">Info</label>
                                    <textarea type="text" name="info" class="form-control disable" id="info"
                                              value="${trip.info}" disabled></textarea>
                                    </div>
                                </div>

		                        <div class="form-group">
                                    <input type="button" onclick="submitTripChanges(${trip.id});"
                                           class="btn btn-primary disable" value="Submit" disabled>
                                    <button type="button" class="btn btn-warning" onclick="enable();">Change</button>
                                </div>

						    </form>
		                </div>

                    </div>

		            <!-- /CONTENT -->
            	</div>
            </div>
    </jsp:attribute>

</t:baseTemplate>