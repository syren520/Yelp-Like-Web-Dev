$(document).ready(function(){
//web-resource-reference - fade in/out : http://www.w3schools.com/jquery/jquery_fade.asp
    $('#btn-login-form').off('click').on('click', function() {
        $("#login-form").fadeIn(50);
        $("#register-form").fadeOut(50);
        $('#btn-register-form').removeClass('active');
        $(this).addClass('active');
    });
    $('#btn-register-form').off('click').on('click', function(){
        $("#register-form").fadeIn(50);
        $("#login-form").fadeOut(50);
        $('#btn-login-form').removeClass('active');
        $(this).addClass('active');
    });
    $('.input-box').off('click').on('click', function(){
        $('.error-message').empty();
    });
});
