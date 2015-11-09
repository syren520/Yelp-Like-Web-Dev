login(statusok, redirected, registrationInvalidData, isRegistrationError) ::= <<
$headerOut()$
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6">
                            <a href="#" class="active" id="btn-login-form">Login</a>
                        </div>
                        <div class="col-md-6">
                            <a href="#" id="btn-register-form">Register</a>
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
                                    <div class="error-message col-md-6 col-centered">
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
                                    <input type="text" name="username" id="username" class="form-control input-box" placeholder="Username" value="" required>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Password</label>
                                    <input type="password" name="password" id="password" class="form-control input-box" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" class="form-control btn btn-login btn-submit" value="Log In">
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
                                    <div class="error-message col-md-6 col-centered">
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
                                <!-Reference web resource - Validator checking: http://1000hz.github.io/bootstrap-validator/->
                                <div class="form-group">
									<label for="inputName" class="control-label">Username</label>
    								<input type="text" name="username" class="form-control input-box" id="inputName" placeholder="Username" required>  
								</div>
                                <div class="form-group">
									<label for="inputEmail" class="control-label">Email</label>
    								<input type="email" name="email" class="form-control input-box" id="inputEmail" placeholder="Email" data-error="That email address is invalid" required>
    								<div class="help-block with-errors"></div>                                
								</div>
                                <div class="form-group">
      								<label for="inputPassword" class="control-label input-box">Password</label>
      								<input type="password" name="password" data-minlength="6" class="form-control" id="inputPassword" placeholder="Password" required>
      								<span class="help-block">Minimum of 6 characters</span>
                                </div>
                                <div class="form-group">
                                    <label for="inputPasswordConfirm" class="control-label input-box">Confirm Password</label>
                                    <input type="password" name="confirmPassword" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Password doesn't match!" placeholder="Confirm Password" required>
      								<div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" class="form-control btn btn-register btn-submit" value="Register Now">
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