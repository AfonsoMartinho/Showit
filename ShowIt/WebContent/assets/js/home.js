  function getvalues(Uname){
	
		  $.ajax({

				method: "GET",
				url: "http://localhost:8080/ShowIt/api/users/"+Uname,
			})
				.done(function (data) {

					 $.each(data, function(i, obj) {	
							sessionStorage.setItem("name",obj.name);
							sessionStorage.setItem("username",obj.username);
					    	sessionStorage.setItem("email",obj.email);
					    });
				});

	  }
  var user="";

  
$(document).ready(function () {
		
	$("#btnRegister").click(function () {
			
			var name= $("#txtName").val();
			var username = $("#txtUsername").val();
			var password = $("#txtPassword").val();
			var confirm = $("#txtConfirm").val();
			var email = $("#txtEmail").val();			

			if(name == "" ||username=="" || password=="" || confirm=="" || email=="")
				{
				//Error Throw
				alert("Please fill all the fields");
				}
			if(password!=confirm)
				{
					//Error
					alert("Passwords doesnt Match!!");
				}
			else
			{
				$.ajax({
					method: "POST",
					url: "http://localhost:8080/ShowIt/api/users",
					data: {
						name: name,
						username:username,
						password:password,
						email:email,

					}
				})
					.done(function (msg) {
						alert("Registed with Success");
					});
			}
			
		}); 
	
	//Replace the "content" div with the page called in the pageRefInput
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

		$("#btnLogin").click(function () {
		
			user= $("#form-username").val();
			var pw = $("#form-password").val();
			
			if(user == "" || pw=="" || user==null || pw==null)
				{
				//Error Throw
				alert("Please fill all the required fields");
				}
			else
			{
				$.ajax({
					method: "POST",
					url: "http://localhost:8080/ShowIt/api/users/auth",
					data: {
						login: user,
						pass: pw,
					}
				}) //Show The Nav Bar Hidden Links and hide the home/Login link
					.done(function (msg) {
						sessionStorage.setItem("token", msg);
						    alert("Logged In");
						    getvalues(user);
						    
						    if ($("#homeNav").is(':visible')) {
						    	$('#homeNav').attr('style', 'display:none');
						    } if($('#profileHidden').is(':hidden')) {
						    	$('#profileHidden').removeAttr( 'style' );
						    	$('#logoutbut').removeAttr( 'style' );
						    	$('#logoutbut').attr('style', 'cursor:pointer');

						    }
						    //Change the active link to the shows page instead of the homee
						    callPage("assets/pages/shows.html");
					        $(".nav-item").removeClass('active');
					        $("#nav-activate").addClass('active');

					        

					})
					.fail(function (msg) {
	    				alert(msg.responseText);
	    			});
			}
			
		});	
		
	});