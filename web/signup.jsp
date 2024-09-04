<%-- 
    Document   : signup
    Created on : 09-Oct-2023, 4:19:12 PM
    Author     : Abhay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    </head>
    <body style="background:url(img/op.jpg);background-size: cover;background-attachment: fixed">
        <div class="container">
            <div class="row">
                <div class="col m8 offset-m2">
                    <div class="card">
                        <div class="card-content">
                            <h3>Register Here!!!!!</h3>
                            <h5 id="msg"></h5>
                            <div class="form center-align">
                                <form action="Register" method="post" id="myform">
                                    <input type="text" name="full_name" placeholder="Enter Fullname">
                                    <input type="text" name="user_name" placeholder="Enter Username">
                                    <input type="password" name="user_password" placeholder="Enter Password">
                                    <input type="email" name="user_email" placeholder="Enter email">
                                    <input type="checkbox" value="checked" name="robot">

                                    <div class="file-field input-field">
                                        <div class="btn">
                                            <span>File</span>
                                            <input name="image" type="file">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                        
                                    <button type="submit" class="btn">Submit</button>
                                </form>
                            </div>
                            <div class="loader center-align" style="margin-top: 10px;display: none;">
                                <div class="progress">
                                    <div class="indeterminate"></div>
                                </div>

                                <h5>Please Wait...</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>

        <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

        <script>
            $(document).ready(function () {
                $("#myform").on('submit', function (event) {
                    event.preventDefault();
//                    var f = $(this).serialize();

                    let f=new FormData(this);
                    console.log(f);

                    $(".loader").show();
                    $(".form").hide();

                    $.ajax({
                        url: "Register",
                        data: f,
                        type: 'POST',
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            console.log("Success.......")
                            $(".loader").hide();
                            $(".form").show();

                            if (data.trim() === 'Done') {
                                $('#msg').html("Successfully Registered");
                            } else {
                                $('#msg').html("Something Went Wrong :(");
                            }

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(data);
                            console.log("Error........")
                            $(".loader").hide();
                            $(".form").show();
                            $('#msg').html("Something Went Wrong :(");
                        },
                        processData:false,
                        contentType:false
                    })
                })
            })
        </script>

    </body>
</html>
