editReviews(userName,reviewId,rate,description)::= <<
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
                     <li><a href="/dashboard">Back home</a></li>
                    <li><a href="/viewBusinessList">View all Business List</a></li>
                    <li><a href="/searchReviews">Search reviews you want</a></li>
				    <li> <a href="/myReviews">My Reviews</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-8">
	        <h2>Edit reviews</h2>
	        <hr>
	        <div class="row">
	           <form action="/updateReviews?reviewId=$reviewId$" method="get">
	                <p class="search-bar"><b>rate: <input type="text" value="$rate$" name="rate" /></b></p>
	                <p class="search-bar"><b>description: <input type="text" value="$description$" name="description" / ></b></p>
	                <input type="text" value="$reviewId$" name="reviewId" style="display:none">
	                <input type="submit" value="submit" / >                        
               </form>
	        </div>
        </div>        
	</div>				        
$footerIn()$
>>