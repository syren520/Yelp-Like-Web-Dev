searchReviews(userName,businessesList,reviewsList,search)::= <<
$headerIn()$
    <div class="row">
        <h1 class="home-title">Welcome to Yap!</h1>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="side-menu well sidebar-nav">
                <ul class="nav nav-list">
                    <li class="nav-header"><b>Welcome</b></li>
                    <li class="active"><a style="color: #696969">$userName$</a></li>
                    <li class="nav-header">User Menu</li>
                    <li><a href="/viewBusinessList">Business List</a></li>
                    <li><a href="/searchReviews">Search reviews</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-8">
	        <h2>Searchreviews</h2>
	        <hr>
	        <div class="row">
               <form action="/showReviews" method="get">
                	<p class="search-bar"><b>Please input the keywords of the review you want: <input type="text" name="keywords" /></b></p>
                	<input type="submit" value="Submit" />
               </form>
               $if(!search)$
		        	<label class="review-search-error">Can't find the reviews contains the keywords you want. </label>
		       $endif$				       
		            $businessesList.keys:{k|
		            <div class="panel panel-default">
			            <div class="panel-heading">
				            <h4 class="panel-title">
                  				<span class="glyphicon glyphicon-home"></span>
                  				$businessesList.(k).businessName$ - AvgRate: $businessesList.(k).businessAveRating$ 
                  				</a>
                  				<a class = "btn-view-business" href="/viewSpecialBusinessList?businessid=$k$"><span class="glyphicon glyphicon-eye-open"></span>View</a>
                			</h4>
              			</div>
          				<div>
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
$footerIn()$
>>