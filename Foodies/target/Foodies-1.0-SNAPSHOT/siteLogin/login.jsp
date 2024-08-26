<%-- 
    Document   : login
    Created on : Jun 2, 2024, 10:09:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js" ></script>

        <style>

            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .section-one {
                background: #ffffff;
                border-radius: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                display: flex;
                flex-direction: row;
                max-width: 1000px;
                margin: 20px;
            }

            .inner-main {
                width: 50%;
                padding: 0 50px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .inner-body form {
                width: 100%;
            }

            .title-head {
                text-align: center;
                margin-bottom: 20px;
                font-size: 1.8em;
                font-weight: 500;
                color: #333333;
            }

            .inner-logo {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                align-items:   center;
                margin-bottom: 100px;
            }

            .inner-logo a {
                display: flex;
                justify-content: center;
                align-items: center;
                text-decoration: none;
                color: #709085;
                padding-bottom: 30px;
            }

            .inner-logo h2 {
                margin: 0;
                font-size: 1.5em;
                color: #333333;
            }

            .form-outline {
                margin-bottom: 20px;
            }

            .form-outline input {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 10px;
                font-size: 1em;
                width: 100%;
            }

            .btn {
                padding: 7px 20px;
                border: none;
                border-radius: 5px;
                background-color: #709085;
                color: #fff;
                font-size: 0.8em;
                cursor: pointer;
            }

            .btn:hover {
                background-color: #50ABCA;
            }

            .more-info {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }

            .more-info a {
                text-decoration: none;
            }

            .more-info a:hover {
                text-decoration: underline;
            }

            .image-wrap {
                width: 50%;
                overflow: hidden;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #f0f0f0;
            }

            .image-login {
                max-width: 100%;
                border-radius: 10px;
            }
            
            .ipt{
                box-shadow: rgba(0, 0, 0, 0.15) 0px 15px 25px, rgba(0, 0, 0, 0.05) 0px 5px 10px;
            }            

            @media (max-width: 768px) {
                .section-one {
                    flex-direction: column;
                    align-items: center;
                }

                .inner-main,
                .image-wrap {
                    width: 100%;
                }

                .inner-body form {
                    padding: 0;
                }

                .image-login {
                    width: 90%;
                    height: auto;
                }
                
                .inner-main{
                    margin-top: 600px;
                }
            }
        </style>
    </head>
    <body>
        <div class="section-one">

            <div class="inner-main">
                <div class="inner-body">
                    <form action="login" method="post">
                        <div class="inner-logo">
                            <a class="" href="/"><i class="bi bi-cart4 me-3 mt-xl-4 logo" style="color: #709085; font-size: 3em;"></i></a>

                            <h2 class="">Foodies</h2>
                        </div>

                        <h3 class="title-head" style="letter-spacing: 1px;">LOG IN</h3>

                        <div data-mdb-input-init class="form-outline mb-4">
                            Email address
                            <input name="txtUser" type="email" id="form2Example18" class="form-control form-control-lg ipt" placeholder="Enter your Email" required/>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            Password
                            <input name="txtPass" type="password" id="form2Example28" class="form-control form-control-lg ipt" placeholder="Enter your Password" required/>
                        </div>

                        <div class="pt-1 mb-4">
                            <input name="btnLogin" data-mdb-button-init data-mdb-ripple-init class="btn btn-info btn-lg btn-block" type="submit" value="Login" style='background-color: #50ABCA;'/>
                        </div>

                        <p style="color:red;"> ${sessionScope.error}</p>

                        <div class="more-info">
                            <p>Don't have an account? <a href="/register" name="register-in" class="link-info">Register here</a></p>
                            <a href="/adminlogin" class="btn btn-warning" style="background-image: linear-gradient(to right, #464646 0%, black 100%);">User</a>
                        </div>
                    </form>
                </div>
            </div>

            <div class="image-wrap">
                <img class="image-login" src="/background/login.png" alt="Login image"/>
            </div>
        </div>
    </body>
</html>

