dashboardContent(userName) ::= <<
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
                    <li><a href="#">Your reviews</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-8">
	        <h2>Introduction</h2>
	        <p>About our web</p>
	        <hr>
	        <p>How to use it</p>
	        <hr>
        </div>        
	</div>				        
$footerIn()$
>>