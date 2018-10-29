$(document).ready(function () {

    //Get localstorage user
    var username = sessionStorage.getItem("username");


    $("#uname").html(username);

    //Logout and clean Local Storage
    $("#logoutbut").click(function () {
        localStorage.clear();
        window.location.href = "index.html";
    });


    //Shows Spwcific Show
    $("#GetShow").click(function () {
        var category = $("#txtGetShow").val();
        var url = "http://localhost:8080/ShowIt/api/shows/" + category;


        var jqxhr = $.get(url, function () {

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


    //Shows All SHOWS
    $("#btnTodos").click(function () {


        var url = "http://localhost:8080/ShowIt/api/shows";


        var jqxhr = $.get(url, function () {

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