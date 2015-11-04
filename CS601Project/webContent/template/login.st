login(statusok, redirected, registrationInvalidData, isRegistrationError) ::= <<
$headerOut()$
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="verifyuser" method="post" role="form" 
                                            $if(!isRegistrationError && !registrationInvalidData)$
	                                    		style="display: block;"
	                                    	$else$
	                                    		style="display: none;"
	                                    	$endif$>
                                <div class="row">
                                    <div class="error-message col-lg-6 col-centered">
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
                                	<label class="control-label">Username</label>
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control input-box" placeholder="Username" value="" required>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Password</label>
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control input-box" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
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
                                    <div class="error-message col-lg-6 col-centered">
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
									<label for="inputName" class="control-label">Username</label>
    								<input type="text" name="username" class="form-control input-box" id="inputName" placeholder="Username" required>  
								</div>
                                <div class="form-group">
									<label for="inputEmail" class="control-label">Email</label>
    								<input type="email" name="email" class="form-control input-box" id="inputEmail" placeholder="Email" data-error="Bruh, that email address is invalid" required>
    								<div class="help-block with-errors"></div>                                
								</div>
                                <div class="form-group">
      								<label for="inputPassword" class="control-label input-box">Password</label>
      								<input type="password" name="password" data-minlength="6" class="form-control" id="inputPassword" placeholder="Password" required>
      								<span class="help-block">Minimum of 6 characters</span>
                                </div>
                                <div class="form-group">
                                    <label for="inputPasswordConfirm" class="control-label input-box">Confirm Password</label>
                                    <input type="password" name="confirmPassword" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Whoops, these don't match" placeholder="Confirm Password" required>
      								<div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
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