viewBusinessList(userName,businessesList,reviewsList,search) ::= <<
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
                    <p><b>select business by:</b></p>
                       <form action="/searchBusiness" method="get">
                        <select name="selection" >
					  <option class="select-list"  value="state"/>state</option>
					  <option class="select-list"  value="city"/>city</option>
					  <option class="select-list"  value="type"/>type</option>
					  <option class="select-list"  value="businessname"/>businessname</option>
					</select>
                        <p class="search-bar"><b>Please input the keywords of your select: <input type="text" name="searchname" /></b></p>
                        <input type="submit" value="Submit" />
                       </form>
                       $if(!search)$
				        <label class="business-list-error">Can't find the business you want,please try again! </label>
				       $endif$				       
				            $businessesList.keys:{k|
				            <div class="panel panel-default">
						            <div class="panel-heading">
						            <h4 class="panel-title">
			                  				<a data-toggle="collapse" href="#$k$">
			                  				<span class="glyphicon glyphicon-home"></span>
			                  				$businessesList.(k).businessName$ - AvgRate: $businessesList.(k).businessAveRating$ 
			                  				</a>
			                  				<a class = "btn-add-reviews" href="/addReviews?businessId=$k$"><span class="glyphicon glyphicon-plus"></span>Add reviews</a>
			                  				<a class = "btn-view-business" href="/viewSpecialBusinessList?businessid=$k$"><span class="glyphicon glyphicon-eye-open"></span>View</a>
		                				</h4>
		              				</div>
		              				<div id="$k$" class="panel-collapse collapse in">
			              				<ul class="list-group">
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
$footerIn()$
>>
