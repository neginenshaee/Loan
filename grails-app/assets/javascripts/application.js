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
function changeStatus(enteredVal) {
    var URL="/user/activation";
    $.ajax({
        url: URL,
        type: 'POST',
        data:{ status: enteredVal, id: $('input[name="hidid"]').val()},
        success: function(data, textStatus, jqXHR) {
            alert(data)
        },
        error: function(data,  textStatus,  errorThrown) {
            alert ("Error occurred : " + textStatus);
        }

    });

}
function calculateAmortization(){
    var URL="/loanRequest/calculate";
    $.ajax({
        url: URL,
        type:"GET",
        data:{amount:$('input[name="amount"]').val(), months:$('input[name="months"]').val(), interest:$('input[name="interest"]').val()},
        success:function(data) {
            $('#updateCalculation').html(data);
            if($("#calculatorAmortizationLink").text()==='Show amortization schedule') {
                $("#calculatorAmortizationLink").trigger('click');
            }
            calculateSchedule();
        }
    });
}

function calculateSchedule(){
    var URL="/loanRequest/calculatePayments";
    if($('input[name="startDate"]').val()== ""){
        var now = new Date();
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today =  now.getFullYear()+"-" + (month) + "-" + (day);
        $('#startDate').val(today);
    }
    $.ajax({
        url: URL,
        type:"GET",
        data:{amount:$('input[name="amount"]').val(), months:$('input[name="months"]').val(), interest:$('input[name="interest"]').val(), startDate:$('input[name="startDate"]').val()},
        success:function(data) {
            $('#updateSchedule').html(data);
        }
    });
}



/*===========================
        amortization
=============================*/
// $(document).ready(function() {
//     $("#calculate").trigger('click');
// });

$('#years').keyup(function(){
    $('#months').val($("#years").val()*12);
});

$('#months').keyup(function(){
    $('#years').val($("#months").val()/12);
});


$("#calculatorAmortizationLink").click(function () {
    if($("#calculatorAmortizationLink").text()==='Show amortization schedule'){
        $("#calculatorAmortizationLink").text('Hide amortization schedule');
        $("#amortizationDiv").show(500);
    }else{
        $("#calculatorAmortizationLink").text('Show amortization schedule');
        $("#amortizationDiv").hide(500);
    }
});



/*function calculatePayOffs() {
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    $('#startDate').val(today);

    var thead = ['Payment Date','Payment','Principal','Interest','Total Interest','Balance']


    var balance = $('#mortgageamount').val();
    var interestVal = $('#interest').val();
    var monthNum = $('#month').val();
    var monthly = balance*[(interestVal/1200)*Math.pow((1+(interestVal/1200)),monthNum)/((Math.pow((1+(interestVal/1200)),monthNum))-1)];


    $('table').remove();
    var table = $('<table>').addClass('table border calculatorAcumin');
    for(var th in thead) {
        var head = $('<th>').text(thead[th]);
        table.append(head);
    }
    var totalInterest = 0.00;

    var totalRounded = 0;
    for(i=1; i<=monthNum; i++){
        var row = $('<tr>');

        var amortDate = addMonths(new Date(),i);
        if(i == monthNum){
            $('#estimate').text(amortDate);
        }

        var col = $('<td>').text(amortDate);
        row.append(col);
        var col = $('<td>').text(round(monthly,2));
        row.append(col);

        var minterest = balance * interestVal / 1200.00;

        var principal = monthly - minterest;

        var col = $('<td>').text(round(principal,2));
        row.append(col);


        var col = $('<td>').text(round(minterest,2));
        row.append(col);

        totalInterest = totalInterest + minterest;
        var col = $('<td>').text(round(totalInterest,2));
        row.append(col);

        balance = balance - principal;
        if(balance<0){
            balance = 0.00
        }
        var col = $('<td>').text(round(balance,2));
        row.append(col);

        table.append(row);
    }

    $('#here_table').append(table);

}*/

// function round(value, decimals) {
//     return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
// }

// function addMonths(date, months) {
//     var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
//     var d = date.getDate();
//     date.setMonth(date.getMonth() + +months);
//     if (date.getDate() != d) {
//         date.setDate(0);
//     }
//
//     var month = date.getMonth()
//     return monthNames[month] + ' '+ date.getFullYear();
// }
