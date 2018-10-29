$(document).ready(function () {

    //Get localstorage user
    var username = sessionStorage.getItem("username");
    $("#uname").html(username);
   

    //Logout and clean Local Storage
    $("#logoutbut").click(function () {
        localStorage.clear();
            window.location.href = "index.html";
    });

    //Add Show
    $("#btnaddShow").click(function () {
        var name = $("#txtname").val();
        var desc = $("#txtdesc").val();
        var gender = $("#txtgender").val();
        var release = $("#txtrelease").val();
        var seasons = $("#txtseasons").val();
        var episodes = $("#txtepisodes").val();
        var imdb = $("#txtimdb").val();
        var cusers = $("#txtcusers").val();

        $.ajax({
                method: "POST",
                url: "http://localhost:8080/ShowIt/api/shows",
                data: {
                    name: name,
                    desc: desc,
                    gender: gender,
                    release: release,
                    seasons: parseInt(seasons),
                    episodes: parseInt(episodes),
                    imdb: parseInt(imdb),
                    cusers: parseInt(cusers)
                }
            })
            .done(function (msg) {
                alert("Show Created");
            })
            .fail(function (msg) {
                alert(msg.responseText);
            });


    });
});