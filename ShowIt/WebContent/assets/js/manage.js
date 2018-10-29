$(document).ready(function () {
        
        
        //Gets All Shows by Category
        $("#GetShow").click(function () {
            var category = $("#txtGetShow").val();
            var url = "http://localhost:8080/ShowIt/api/shows/" + category;
            
                
            var jqxhr = $.get(url, function() {
                  
            })
                .done(function (data) {
                console.log(data);
                $("#tbl").empty();
                var trTemp = "";
                trTemp += "<tr><th colspan='8'>Shows</th></tr>";
                trTemp += "<tr><th>Name</th><th>Description</th><th>Gender</th><th>Release</th><th>Seasons</th><th>Episodes</th><th>IMDB</th><th>Users Rate</th></tr>";
                $("#tbl").append(trTemp);

                $.each(data, function (i, obj) {
                   
                        trTemp = "<tr><td>" + obj.name + "</td><td>" +
                        obj.desc + "</td><td>" +
                        obj.gender + "</td><td>" +
                        obj.release + "</td><td>" +
                        obj.seasons + "</td><td>" +
                        obj.episodes + "</td><td>" +
                        obj.imdb + "</td><td>" +
                        obj.cusers + "</td><tr>";
                        
                        $("#tbl").append(trTemp);
                });

            })
                
            .fail(function (e) {
                alert("The Show doesnt exists");
            });
        });
        

        //Delete Show By NAme
        $("#DelShow").click(function(){
            var show = $("#txtDelShow").val();
            console.log(show)
        $.ajax({
              method: "DELETE",
              url: "http://localhost:8080/ShowIt/api/shows/" + show,
              data: { 
                  name: show
        
              }
            })
          .done(function( msg ) {
              console.log(show)
              
              
          });
    });


        //Shows All Shows
        $("#btnTodos").click(function () {
        

            var url = "http://localhost:8080/ShowIt/api/shows";
            
                
            var jqxhr = $.get(url, function() {
                  
            })
             .done(function (data) {
                 console.log(data);
                 $("#tbl").empty();
                 var trTemp = "";
                 trTemp += "<tr><th colspan='8'>All Shows</th></tr>";
                 trTemp += "<tr><th>Name</th><th>Description</th><th>Gender</th><th>Release</th><th>Seasons</th><th>Episodes</th><th>IMDB</th><th>Users Rate</th></tr>";
                $("#tbl").append(trTemp);

                 $.each(data, function (i, obj) {
                    
                    trTemp = "<tr><td>" + obj.name + "</td><td>" +
                        obj.desc + "</td><td>" +
                        obj.gender + "</td><td>" +
                        obj.release + "</td><td>" +
                        obj.seasons + "</td><td>" +
                        obj.episodes + "</td><td>" +
                        obj.imdb + "</td><td>" +
                        obj.cusers + "</td><tr>";
                   
                         $("#tbl").append(trTemp);
                 });

             })
             .fail(function () {
                 alert("error");
             });
        });
       
    });
