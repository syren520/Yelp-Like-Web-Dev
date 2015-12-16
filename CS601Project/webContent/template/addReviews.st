addReviews(userName,businessId,description,status)::= <<
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
        <div class="col-md-8">
	        <h2>Edit reviews</h2>
	        <hr>
	        <div class="row">
	        $if(status)$
        		<p class="error-message">Invalid input data</p>
        	$endif$
	           <form action="/addReviewsProcessor" method="post" enctype="multipart/form-data">
	                <p class="search-bar"><b> <input type="text" value="$businessId$" name="businessId" style="display:none" ></b></p>
	                <ul>
	                <li><p class="selectrate"><b>rate:</b></p>
	                <select name="rate">
	                <option class="select-list" value="1"/>1</option>
	                <option class="select-list" value="2"/>2</option>
	                <option class="select-list" value="3"/>3</option>
	                <option class="select-list" value="4"/>4</option>
	                <option class="select-list" value="5"/>5</option>
	                </select>
	                </li>
	                <li>
	                <p class="search-bar"><b>description: <input class="input-box" type="text" value="$description$" name="description" / ></b></p>
                    </li>
					<li>
					<tr> 
					<td height="30" align="right"><b>please select pictures you want to upload：</b></td> 
					<td><input type="file" name="uploadpicture" multiple accept="image/*"></td>
					</tr>
                    </li>
	                <input type="submit" value="submit" / >                        
               </form>
	        </div>
        </div>        
	</div>				        
$footerIn()$
>>