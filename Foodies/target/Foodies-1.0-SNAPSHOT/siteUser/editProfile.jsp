<%-- 
    Document   : editProfile
    Created on : Jul 9, 2024, 12:35:31 PM
    Author     : phuct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit my profile</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">

        <style>
            body {
                font-family: 'Lato', sans-serif;
            }

            h1 {
                margin-bottom: 40px;
            }

            label {
                color: #333;
            }

            .btn-send {
                font-weight: 300;
                text-transform: uppercase;
                letter-spacing: 0.2em;
                width: 80%;
                margin-left: 3px;
            }
            .help-block.with-errors {
                color: #ff5050;
                margin-top: 5px;

            }

            .card{
                margin-left: 10px;
                margin-right: 10px;
            }

            .link-change-password {
                color: #22aa00;
                text-decoration: none;
            }

            .link-change-password:hover {
                color: #22aa00;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class=" text-center mt-5 ">
                <h1 >Edit My Profile</h1>
            </div>
            <div class="row ">
                <div class="col-lg-7 mx-auto">
                    <div class="card mt-2 mx-auto p-4 bg-light">
                        <div class="card-body bg-light">

                            <div class = "container">
                                <form id="contact-form" role="form" method="post" action="/User">



                                    <div class="controls">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_name">Email *</label>
                                                    <input type="hidden" name="txtCheck" value="${sessionScope.profile.email}">
                                                    <input id="form_email" type="email" name="txtEmail" 
                                                           class="form-control" placeholder="Please enter your email *" 
                                                           required="required" data-error="Valid email is required."
                                                           value="${sessionScope.profile.email}"
                                                           pattern="[a-zA-Z0-9]+@[a-zA-Z0-9]+\.com"
                                                           title="Please enter a valid email in the format: example@example.com. No spaces allowed.">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_lastname">Phone *</label>
                                                    <input id="form_lastname" type="tel" pattern="[0-9]{10}" 
                                                           name="txtPhone" class="form-control" placeholder="Please enter your phone number *" 
                                                           required="required" data-error="Phone number is required."
                                                           title="Please enter a valid phone number"
                                                           value="${sessionScope.profile.phone}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_email">Username *</label>
                                                    <input type="hidden" name="txtCheckUser" value="${sessionScope.profile.username}">
                                                    <input id="form_name" type="text" name="txtUsername" class="form-control" 
                                                           placeholder="Please enter your firstname *" 
                                                           required="required" data-error="Username is required."
                                                           value="${sessionScope.profile.username}"
                                                           pattern="[a-zA-Z 0-9]+"
                                                           title="Username must contain only letters and numbers, and cannot be only spaces."
                                                           oninput="validateUsername(this)">

                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_need">Gender *</label>
                                                    <select id="form_need" name="txtGender" class="form-control" 
                                                            required="required" data-error="Please specify your need.">
                                                        <option name="txtGender" ${sessionScope.profile.gender == "male" ? "selected" : ""} value="male"a>Male</option>
                                                        <option name="txtGender" ${sessionScope.profile.gender == "female" ? "selected" : ""} value="female">Female</option>
                                                    </select>

                                                </div>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_message">Verify your password*</label>
                                                    <input id="form_name" type="password" name="txtPassword" class="form-control" 
                                                           placeholder="Please enter your password *" 
                                                           required="required" data-error="Password is required.">

                                                </div>

                                            </div>

                                            <p style="color:red;"> ${requestScope.regisError}</p>
                                            <div class="col-md-12">

                                                <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" 
                                                       name="btnEditProfile" value="Edit" >
                                                <a href="/User/ChangePassword" class="link-change-password">I want to change my password</a>
                                                <hr/>
                                                <a href="/" class="link-change-password">Return Home</a>
                                            </div>

                                        </div>


                                    </div>
                                </form>
                            </div>
                        </div>


                    </div>
                    <!-- /.8 -->

                </div>
                <!-- /.row-->

            </div>
        </div>
    </body>

    <script>
        function validateUsername(input) {
            // Trim the input value to remove leading and trailing spaces
            let trimmedValue = input.value.trim();

            // Check if the trimmed value is empty
            if (trimmedValue.length === 0) {
                input.setCustomValidity("Username cannot be only spaces.");
            } else {
                input.setCustomValidity("");
            }
        }
    </script> 
</html>
