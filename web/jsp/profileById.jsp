
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseTemplate title="">


    <jsp:attribute name="body_content">
       <!-- CONTENT -->
        <div class="col-8">
        	<div class="row w-100 h-100" id="inner-nav">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs ml-3">
					<li class="nav-item">
						<a class="nav-link active" data-toggle="tab" href="#home">profile</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-toggle="tab" href="#menu1">Otzivi</a>
					</li>
				</ul>
				<!--/Nav tabs-->

				<!-- Tab panes -->
                <div class="tab-content">
					<div id="home" class="container tab-pane active" >
						<div class="card">
							<img class="card-img-top" src="/static/pictures/default.png"
								 onerror="this.src='data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22723%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20723%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_166a9e47734%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A36pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_166a9e47734%22%3E%3Crect%20width%3D%22723%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22269.4140625%22%20y%3D%22105.9984375%22%3E723x180%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E'"
								 alt="Card image cap">
							<div class="card-body">
								<!-- Username -->
								<h3 class="card-title">

										${profile.username}

								</h3>

								<p class="card-text">
									<!-- name -->
									<b>

											${profile.name}

									</b>
									<br>
									<!-- phone number -->
									<i>+7 921 0000007</i>
									<br>
									<!-- email -->
									<i>

											${profile.email}

									</i>
								</p>
								<hr>

								<p class="card-text">

									age: ${profile.age}

									<br>

									working place: ${profile.job}

									<br>
									address: ${profile.address}

								</p>

								<hr>
								<!-- additional info -->
								<p class="card-text">

										${profile.additionalInfo}

								</p>
							</div>
						</div>
					</div>
					<div id="menu1" class="container tab-pane">
						<div class="card ml-0" id="request_${request.id}">
							<h5 class="card-header">
								<img src="../../static/pictures/default.png" width="50">
								<a href="/profile/${otziv.Profile.username}">Profile:${otziv.profile.username}</a>
								<span>17/08/2015</span>
							</h5>

							<div class="card-body">
								<h5 class="card-title">
									<p>Message:${otziv.text}</p>
								</h5>
							</div>
						</div>
					</div>
				</div>
	            
	        </div>
	    </div>
        <!-- /CONTENT -->
    </jsp:attribute>

</t:baseTemplate>