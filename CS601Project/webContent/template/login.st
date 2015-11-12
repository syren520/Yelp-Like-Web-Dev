login(statusok, redirected, registrationInvalidData, isRegistrationError) ::= <<
$headerOut()$
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-form">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6 btn-form">
                            <span class="active" id="btn-login-form">Login</span>
                        </div>
                        <div class="col-md-6 btn-form">
                            <span id="btn-register-form">Register</span>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                <!--Reference web resource - form group: http://getbootstrap.com/css/#forms-->
                    <div class="row">
                        <div class="col-md-12">
                            <form id="login-form" action="verifyuser" method="post" role="form" 
                                            $if(!isRegistrationError && !registrationInvalidData)$
	                                    		style="display: block;"
	                                    	$else$
	                                    		style="display: none;"
	                                    	$endif$>
                                <div class="row">
                                    <div class="error-message col-md-6 col-md-offset-3">
	                                    <label>
	                                    	$if(!statusok)$
	                                    		Username/Password is not match!
	                                    	$elseif(redirected)$
	                                    		You need log in first!
	                                    	$endif$
	                                    </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                	<label>Username</label>
                                    <input type="text" name="username" class="form-control input-box" placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="password" class="form-control input-box" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <input type="submit" class="form-control btn btn-login btn-submit" value="login">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" data-toggle="validator" action="registration" method="post" role="form" 
                            				$if(isRegistrationError || registrationInvalidData)$
	                                    		style="display: block;"
	                                    	$else$
	                                    		style="display: none;"
	                                    	$endif$>
	                           <div class="row">
                                    <div class="error-message col-md-6 col-md-offset-3">
	                                    <label>
	                                    	$if (registrationInvalidData)$
	                                    		Invalid input data!
	                                    	$endif$
	                                    	$if(isRegistrationError)$
		        								Username/email may already been used!
	                        				$endif$
	                                    </label>
                                    </div>
                                </div>
                                <div class="form-group">
									<label>Username</label>
    								<input type="text" name="username" class="form-control input-box" placeholder="Username" required>  
								</div>
                                <div class="form-group">
									<label>Email</label>
    								<input type="email" name="email" class="form-control input-box" placeholder="Email" required>
								</div>
                                <div class="form-group">
      								<label>Password</label>
      								<input type="password" name="password" class="form-control input-box" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <input type="submit" class="form-control btn btn-register btn-submit" value="register">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
$footerOut()$
>>