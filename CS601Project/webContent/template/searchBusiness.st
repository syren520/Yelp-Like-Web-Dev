searchBusiness(userName,businessesList,reviewsList) ::= <<
$headerIn()$
    <div class="row">
        <h1 class="home-title">Welcome to Yap!</h1>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="well">
                <ul class="nav">
                    <li class="nav-header">Welcome</li>
                    <li class="active"><a style="color: #696969">$userName$</a></li>
                    <li class="nav-header">User Menu</li>
                    <li><a href="/dashboard">Back home</a></li>
                    <li><a href="/viewBusinessList">View all Business List</a></li>
                    <li><a href="/searchReviews">Search reviews you want</a></li>
				    <li> <a href="/myReviews">My Reviews</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-7">
            <div class="main-content">
                <!--Reference web resource - panel with heading: http://getbootstrap.com/components/#panels-->
                <!--Reference web resource - collapse: http://getbootstrap.com/javascript/#collapse-->
                <div class="content-info">
                    <div class="row">
				         $businessesList.keys:{k|
			               <div class="panel panel-default">
						          <div class="panel-heading">
						            <h4 class="panel-title">
			                  				<a data-toggle="collapse" href="#$k$">
			                  				<span class="glyphicon glyphicon-home"></span>
			                  				$businessesList.(k).businessName$
			                  				</a>
			                  				<a class = "btn-view-business" href="/viewSpecialBusinessList?businessid=$k$"><span class="glyphicon glyphicon-eye-open"></span>View</a>
		                				</h4>
		              				</div>
		              				<div id="$k$" class="panel-collapse collapse in">
			              				<ul class="list-group">
			              					<li class="list-group-item">
							                  	<span class="glyphicon glyphicon-heart text-primary"></span>
							                  	<span class="review">
													Averagerating:$businessesList.(k).businessAveRating$
							                </li>
							                <li class="list-group-item">
							                  	<span class="glyphicon glyphicon-heart text-primary"></span>
							                  	<span class="review">
													Address: <span class="address">$businessesList.(k).businessAddress$,$businessesList.(k).businessState$, $businessesList.(k).businessCity$</span> 
							    				</span>
							                </li>
				              				$businessesList.(k).reviewIdList:{r| 
							                  <li class="list-group-item">
							                  	<span class="glyphicon glyphicon-pencil text-primary"></span>
							                  	<span class="review">
													$reviewsList.(r).reviewUser$ - $reviewsList.(r).reviewRating$ - $reviewsList.(r).reviewContent$
							    				</span>
							                  </li>
				              				}$  
			              			 	</ul>
						           	 </div>
					         </div>
					         }$
				          </div>
				        </div>
				    </div>
                </div>
            </div>
        </div>
    </div>
$footerIn()$
>>
