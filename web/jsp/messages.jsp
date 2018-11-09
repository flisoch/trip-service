
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="Messages">


    <jsp:attribute name="body_content">
    	<!-- CONTENT -->
        <div class="col-8">
            <div class="row w-100 h-100 pl-3" id="inner-nav">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="row">
                                <div class="col">
                                    <img src="../../static/pictures/default.png" width="50">
                                    <span>Vsya99</span>
                                </div>
                                <div class="col">
                                    last message
                                    <span class="badge badge-pill badge-primary">4</span>
                                </div>
                            </div>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="row">
                                <div class="col">
                                    <img src="../../static/pictures/default.png" width="50">
                                    <span>TOlya1337</span>
                                </div>
                                <div class="col">
                                    last message
                                    <span class="badge badge-pill badge-primary">1</span>
                                </div>
                            </div>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="row">
                                <div class="col">
                                    <img src="../../static/pictures/default.png" width="50">
                                    <span>Anton0_O</span>
                                </div>
                                <div class="col">
                                    last message
                                    <span class="badge badge-pill badge-primary invisible">0</span>
                                </div>
                            </div>
                        </a>

                      </div>
                </div>
            </div>
        </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>