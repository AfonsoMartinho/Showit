$(document).ready(function () {
	

    var Initialload = true;


    if (Initialload == true) {
        $.ajax({
            url: "assets/pages/home.html",
            type: "GET",
            dataType: "text",
            success: function (response) {
                $(".content").html(response);
            },
            error: function (error) {
            },
            completed: function (xhr, status) {
            }
        });
    }
    
    
    
    // Click to call Page
    $('.single').on('click', function (e) {
        e.preventDefault();
        var pageRef = $(this).attr('href');
        $(".nav-item").removeClass('active');

        $(this).parent().addClass('active');
        callPage(pageRef)
    });
    
    $('.single2').on('click', function (e) {
    	sessionStorage.clear();
        e.preventDefault();
        $(".nav-item").removeClass('active');
        $("#homeNav").parent().addClass('active');
        
        if ($("#homeNav").is(':hidden')) {
	    	$('#homeNav').removeAttr('style');
	    } if($('#profileNav').is(':visible')) {
	    	$('#profileNav').attr( 'style','display:none' );
	    	$('#logoutNav').attr( 'style' ,'display:none');
	    }
	    alert("Your session was ended.");
        callPage("assets/pages/home.html")
    });
    
    //Load Pages
    function callPage(pageRefInput) {
        $.ajax({
            url: pageRefInput,
            type: "GET",
            dataType: "text",
            success: function (response) {
                $(".content").html(response);
            },
            error: function (error) {
            },
            completed: function (xhr, status) {
            }
        });
    }
    Initialload = false;
});