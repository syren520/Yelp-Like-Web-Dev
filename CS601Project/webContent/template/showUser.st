showUser(userName,userList) ::= <<
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
			<ul>$userList.keys:{k|<li>$userList.(k)$</li>}$</ul>                             			                         			
            </div>
        </div>
    </div>
$footerIn()$
>>
