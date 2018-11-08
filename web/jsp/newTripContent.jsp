<%--
  Created by IntelliJ IDEA.
  User: flisoch
  Date: 08.11.18
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<!-- CONTENT -->
<div class="col-8">
    <div class="row w-100 h-100 pl-3" id="inner-nav">
        <div class="card text-center mx-auto" style="width: 30rem;">

            <div class="card-body">

                <form method="POST">

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
                        <input name="time_to" type="datetime-local" class="form-control" value="1970-01-01T23:00"
                               id="timeToInputField" required>
                    </div>

                    <div class="form-group">
                        <label for="bio">About Trip</label>
                        <textarea name="bio" class="form-control" id="bio" rows="3">Morning trip from zelenodolsk to kzan
                                        </textarea>
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
