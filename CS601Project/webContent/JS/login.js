$(document).ready(function(e){
    $('#btn-login-form').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#btn-register-form').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#btn-register-form').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#btn-login-form').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('.input-box').off('focus').on('focus', function(){
        $('.error-message').empty();
    });
});
