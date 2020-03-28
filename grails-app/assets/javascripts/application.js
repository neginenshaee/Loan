// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-3.3.1.min
//= require bootstrap
//= require popper.min
//= require_self

/*
* Preloader
* */
$(window).on('load',function () {
    $('#status').fadeOut();
    $('#preloader').delay(350).fadeOut('slow');
})


/*===========================
        LOGIN
=============================*/
document.addEventListener("DOMContentLoaded", function(event) {
    document.forms['loginForm'].elements['username'].focus();
});
function passwordDisplayToggle() {
    var toggleEl = document.getElementById("passwordToggler");
    var eyeIcon = '\u{1F441}';
    var xIcon = '\u{2715}';
    var passEl = document.getElementById("password");
    if (passEl.type === "password") {
        toggleEl.innerHTML = xIcon;
        passEl.type = "text";
    } else {
        toggleEl.innerHTML = eyeIcon;
        passEl.type = "password";
    }
}

/*===========================
        NAVIGATION
=============================*/
$(function () {
    showHideNav();
    $(window).scroll(function () {
        showHideNav();
    });

    function showHideNav() {
        if($(window).scrollTop()>50){
            $("nav").addClass("black-nav-top");

            // $("#back-to-top").fadeIn();
        }else{
            // $("nav").removeClass("black-nav-top");

            // $("#back-to-top").fadeOut();
        }
    }

});

/*===========================
        SMOOTH SCROLLING
=============================*/
$(function () {
    $("a.smooth-scroll").click(function (event) {

        event.preventDefault();

        var section_id = $(this).attr("href");
        $("html, body").animate({
            scrollTop: $(section_id).offset().top -64
        }, 1250);

    });
})


/*===========================
        AJAX
=============================*/
function changeStatus() {
    var URL="/user/onChange";
    $.ajax({
        url: URL,
        type: 'POST',
        data:{ status: $('input[name="radioGroup"]:checked').val(), id: $('input[name="hidid"]').val()},
        success: function(data, textStatus, jqXHR) {
            alert('Done!')
        },
        error: function(data,  textStatus,  errorThrown) {
            alert ("Error occurred : " + textStatus);
        }

    });

}

