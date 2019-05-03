
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="New Trip">

    <jsp:attribute name="body_content">
        <!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100 pl-3" id="inner-nav">
                <div class="card text-center mx-auto" style="width: 30rem;">

                    <div class="card-body">

                        <form method="POST" action="/trips">

                            <div class="form-group">
                                <label for="departure">departure</label>

                                <input type="text" name="departure" class="form-control" id="departure"
                                       placeholder="zelenodolsk"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="destination">destination</label>

                                <input type="text" name="destination" class="form-control" id="destination" placeholder="kazan"
                                       required>

                            </div>

                            <div class="form-group">
                                <label for="seats">seats</label>

                                <input type="number" name="seats" class="form-control" id="seats" placeholder="1" required>

                            </div>

                            <div class="form-group">
                                <label for="timeToInputField">Date and time</label>
                                <input name="date" type="datetime-local" class="form-control" value="2018-01-01T23:00"
                                       id="timeToInputField" required>
                            </div>

                            <div class="form-group">
                                <label for="info">About Trip</label>
                                <textarea name="info" class="form-control" id="info" rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <input type="submit" class="btn btn-primary col" value="Create">
                            </div>

                        </form>

                    </div>


                </div>


            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>
