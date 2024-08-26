<%-- 
    Document   : changePassword
    Created on : Jul 11, 2024, 3:01:38 PM
    Author     : phuct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change My Password</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">

        <style>
            html,body {
                height: 100%;
            }

            body{
                display: -ms-flexbox;
                display: -webkit-box;
                display: flex;
                -ms-flex-align: center;
                -ms-flex-pack: center;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: center;
                justify-content: center;
                background-color: #f5f5f5;
            }

            form{
                padding-top: 10px;
                font-size: 13px;
                margin-top: 30px;
            }

            .card-title{
                font-weight:300;
            }

            .btn{
                font-size: 13px;
            }

            .login-form{
                width:320px;
                margin:20px;
            }

            .sign-up{
                text-align:center;
                padding:20px 0 0;
            }

            span{
                font-size:14px;
            }

            .submit-btn{
                margin-top:20px;
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
        <div class="card login-form">
            <div class="card-body">
                <h3 class="card-title text-center">Change password</h3>

                <!--Password must contain one lowercase letter, one number, and be at least 7 characters long.-->

                <div class="card-text">
                    <form method="post" action="/User">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Your new password *</label>
                            <input type="password" class="form-control form-control-sm"
                                   pattern="[a-zA-Z0-9]+" name="txtNewPass"
                                   title="New password must contain only letters and numbers, and cannot be only spaces."
                                   oninput="validateUsername(this)">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Repeat password *</label>
                            <input type="password" class="form-control form-control-sm" name="rptNewPass">
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Enter your old password *</label>
                            <input type="password" class="form-control form-control-sm" name="txtOldPass">
                        </div>
                        <hr/>
                        <p style="color:red;"> ${sessionScope.regisError}</p>
                        <button type="submit" class="btn btn-primary btn-block submit-btn" name="btnChangePass">Change Password</button>
                        <hr/>
                        <a href="/" class="link-change-password">Return Home</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        function validateUsername(input) {
            // Trim the input value to remove leading and trailing spaces
            let trimmedValue = input.value.trim();

            // Check if the trimmed value is empty
            if (trimmedValue.length === 0) {
                input.setCustomValidity("Password cannot be only spaces.");
            } else {
                input.setCustomValidity("");
            }
        }
    </script> 
</html>
