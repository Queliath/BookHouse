$('#toggle-link').click(function(e) {
    var $message = $('.hidden-login');

    if ($message.css('display') != 'block') {
        $message.show();

        var firstClick = true;
        $(document).bind('click.myEvent', function(e) {
            if (!firstClick && $(e.target).closest('.hidden-login').length == 0) {
                $message.hide();
                $(document).unbind('click.myEvent');
            }
            firstClick = false;
        });
    }

    e.preventDefault();
});
$('#toggle-link-user').click(function(e) {
    var $message = $('.hidden-user');

    if ($message.css('display') != 'block') {
        $message.show();

        var firstClick = true;
        $(document).bind('click.myEvent', function(e) {
            if (!firstClick && $(e.target).closest('.hidden-user').length == 0) {
                $message.hide();
                $(document).unbind('click.myEvent');
            }
            firstClick = false;
        });
    }

    e.preventDefault();
});
var currentYear = (new Date).getFullYear();

$(document).ready(function () {
    $("#copyright-year").text((new Date).getFullYear());
});

jQuery(document).ready(function(e){
    /* prepend menu icon */
    $('#main-nav').prepend('<div id="menu"><img src="img/menu-icon.png"></div>');
    /* toggle nav */
    $("#menu").on("click", function(){
        $("#nav").slideToggle();
        $(this).toggleClass("active");
        var firstClick = true;
        $(document).bind('click.myEvent', function(e) {
            if (!firstClick && $(e.target).closest('#nav').length == 0) {
                $("#nav").hide();
                $(document).unbind('click.myEvent');
            }
            firstClick = false;
        });
    });
});

$('.to-top').click(function(e){
    e.preventDefault();

    $('html, body').animate({
        scrollTop: 0
    });
    var $message = $('.hidden-login');

    if ($message.css('display') != 'block') {
        $message.show();

        var firstClick = true;
        $(document).bind('click.myEvent', function(e) {
            if (!firstClick && $(e.target).closest('.hidden-login').length == 0) {
                $message.hide();
                $(document).unbind('click.myEvent');
            }
            firstClick = false;
        });
    }
    e.preventDefault();
});
$('.to-top-mobile').click(function(e){

    $('html, body').animate({
        scrollTop: 0
    });
    var $message = $('.hidden-login');
    $("#nav").slideToggle();
    $(this).toggleClass("active");
    var firstClick = true;

    if ($message.css('display') != 'block') {
        $message.show();

        $(document).bind('click.myEvent', function(e) {
            if (!firstClick && $(e.target).closest('.hidden-login').length == 0 &&!firstClick && $(e.target).closest('#nav').length == 0) {
                $message.hide();
                $("#nav").hide();
                $(document).unbind('click.myEvent');
            }
            firstClick = false;
        });
    }
    e.preventDefault();
});
