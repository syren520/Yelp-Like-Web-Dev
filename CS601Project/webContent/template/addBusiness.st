addBusiness(userName,statusok)::= <<
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
                    <li><a href="showUser">show user</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-6">
	        <h2>Add Business</h2>
	        <hr>
	        <div class="row">
	        <div class="error-message">
	                                    <label>
	                                    	$if(!statusok)$
	                                    		Content can not be null!
	                                    	$endif$
	                                    </label>
                                    </div>
	           <form action="/addBusinessProcessor" method="get">
	               <div class="form-group">
                                	<label>businessName</label>
                                    <input type="text" name="businessName" class="form-control input-box" placeholder="businessName" required>
                                </div>
                                 <div class="form-group">
                                	<label>address</label>
                                    <input type="text" name="address" class="form-control input-box" placeholder="address" required>
                                </div>
                                 <div class="form-group">
                                	<label>state</label>
                                    <input type="text" name="state" class="form-control input-box" placeholder="state" required>
                                </div>
                                 <div class="form-group">
                                	<label>city</label>
                                    <input type="text" name="city" class="form-control input-box" placeholder="city" required>
                                </div>
                                 <div class="form-group">
                                	<label>type</label>
                                    <input type="text" name="type" class="form-control input-box" placeholder="type" required>
                                </div>
	                <input type="submit" value="submit" / >                        
               </form>
	        </div>
        </div>        
	</div>				        
$footerIn()$
>>