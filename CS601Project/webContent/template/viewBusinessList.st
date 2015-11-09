dashboardContent(userName,businessesList,reviewsList) ::= <<
$headerIn()$
    <div class="row">
        <h1 class="home_title" style="display: block;width:350px;margin: 50px auto 50px auto;font-size: 40px;">Welcome to Yap!</h1>
    </div>
    <div class="row">
        <div class="col-xs-4">
            <div class="side-menu well sidebar-nav">
                <ul class="nav nav-list">
                    <li class="nav-header">User Info</li>
                    <li class="active"><a style="color: #696969">$userName$</a></li>
                    <li class="nav-header">User Menu</li>
                    <li><a href="#">Business List</a></li>
                    <li><a href="#">Your reviews</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-8">
            <div class="main-content">
                <div class="row">
                    <div class="col-xs-4">
                        <div class="input-group">
                            <div class="input-group-btn search-panel">
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span id="search_concept">Filter by</span> <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#contains">Business Name</a></li>
                                    <li><a href="#its_equal">State</a></li>
                                    <li><a href="#greather_than">City</a></li>
                                    <li><a href="#less_than">Neighborhood</a></li>
                                </ul>
                            </div>
                            <input type="hidden" name="search_param" value="all" id="search_param">
                            <input type="text" class="form-control" name="x" placeholder="Search term...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="content-info">
                    <div class="row">
				        <div class="col-xs-10">
				            $businessesList.keys:{k|
				            <div class="panel panel-default">
						            <div class="panel-heading">
		                				<h4 class="panel-title">
			                  				<a data-toggle="collapse" data-parent="#accordion" href="#$k$">
			                  				<span class="glyphicon glyphicon-home"></span>
			                  				$businessesList.(k).businessName$ -$businessesList.(k).businessAveRating$- Address: $businessesList.(k).businessAddress$, $businessesList.(k).businessCity$, $businessesList.(k).businessState$
			                  				</a>
		                				</h4>
		              				</div>
		              				<div id="$k$" class="panel-collapse collapse in">
			              				<ul class="list-group">
				              				$businessesList.(k).reviewList:{r| 
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
