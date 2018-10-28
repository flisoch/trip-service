<#include "template.ftl">


<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>


<#macro title>
    <title>
        <#if profile.username??>
            ${profile.username}
        <#else > unnamed
        </#if>
    </title>
</#macro>


<#macro content>
	<div class="container">
        <div class="row">

            <!-- CONTENT -->
            <div class="col"></div>
            <div class="card">
                <img class="card-img-top" src="https://static4.depositphotos.com/1000350/352/i/950/depositphotos_3520650-stock-photo-man-lying-down-on-a.jpg"
                    onerror="this.src='data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22723%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20723%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_166a9e47734%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A36pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_166a9e47734%22%3E%3Crect%20width%3D%22723%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22269.4140625%22%20y%3D%22105.9984375%22%3E723x180%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E'"
                    alt="Card image cap">
                <div class="card-body">
                    <!-- username -->
                    <h3 class="card-title">
	                    <#if profile.username??>
							${profile.username}
	                    <#else>""
	                  	</#if>
                  	</h3>

                    <p class="card-text">
                        <!-- name -->
                        <b>
                       		<#if profile.name??>
								${profile.name}
	                    	<#else>"unnamed"
	                  		</#if>
                    	</b>
                        <br>
                        <!-- phone number -->
                        <i>+7 921 0000007</i>
                        <br>
                        <!-- email -->
                        <i>
                        	<#if profile.email??>
								${profile.email}
	                    	<#else>""
	                  		</#if>
                    	</i>
                    </p>
                    <hr>

                    <p class="card-text">
                        <#if profile.age??>
							age: ${profile.age}
	                    <#else>age:
	                  	</#if>
                        <br>
                        	<#if profile.job??>
								working place: ${profile.job}
		                    <#else> working place:
		                  	</#if> 
                        <br>
                        <#if profile.address??>
							address: ${profile.address}
		                <#else> address: 
		                </#if> 
                    </p>

                    <hr>
                    <!-- additional info -->
                    <p class="card-text">
                    	<#if profile.additionalInfo??>
							${profile.additionalInfo}
		                <#else> 
		                </#if> 
                    </p>
                </div>
            </div>
            <div class="col"></div>
            <!-- /CONTENT -->


        </div>
    </div>
</#macro>

<@display_page/>