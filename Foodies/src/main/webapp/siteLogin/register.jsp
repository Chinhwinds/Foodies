<%-- 
    Document   : register
    Created on : Jun 3, 2024, 1:01:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>

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
                min-height: 100vh;
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
                display: flex;
                width: 100%;
            }

            .inner-body {
                width: 50%;
                padding: 0 50px;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .inner-head {
                display: flex;
                flex-direction: row;
                align-items: center;
                margin-bottom: 30px;
                justify-content: space-between;
            }


            .inner-head a {
                display: flex;
                justify-content: center;
                align-items: center;
                text-decoration: none;
                color: #709085;
                padding-bottom: 30px;
            }

            .inner-head span {
                margin-left: 10px;
                font-size: 1.5em;
                color: #333333;
            }
            

            h3 {
                text-align: center;
                margin-bottom: 20px;
                font-size: 1.8em;
                font-weight: 500;
                color: #333333;
            }

            .bg-image-vertical {
                position: relative;
                overflow: hidden;
                background-repeat: no-repeat;
                background-position: right center;
                background-size: auto 100%;
            }

            .form-outline {
                margin-bottom: 20px;
            }

            .form-outline label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
            }

            .form-outline input,
            .form-outline select {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 10px;
                font-size: 1em;
                width: 100%;
                box-shadow: rgba(0, 0, 0, 0.15) 0px 15px 25px, rgba(0, 0, 0, 0.05) 0px 5px 10px;
            }

            .btn {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #709085;
                color: #fff;
                font-size: 1em;
                cursor: pointer;
            }

            .btn:hover {
                background-color: #5a726a;
            }

            .more-info {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .more-info a {
                text-decoration: none;
                color: #709085;
            }

            .more-info a:hover {
                text-decoration: underline;
            }

            .inner-wrap {
                width: 50%;
                overflow: hidden;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .inner-image {
                max-width: 100%;
                border-radius: 10px;
                height: 840px;
            }

            @media (max-width: 768px) {
                .section-one {
                    flex-direction: column;
                    align-items: center;
                }

                .inner-body,
                .inner-wrap {
                    width: 100%;
                }

                .inner-image {
                    width: 90%;
                    height: auto;
                }
            }

            .ipt{
                box-shadow: rgba(0, 0, 0, 0.15) 0px 15px 25px, rgba(0, 0, 0, 0.05) 0px 5px 10px;
            }

            .gender-choice{
                padding: 7px;
                border: 1px solid #fff;
                border-radius: 5px;
                box-shadow: rgba(0, 0, 0, 0.15) 0px 15px 25px, rgba(0, 0, 0, 0.05) 0px 5px 10px;
            }

        </style>
    </head>
    <body>
        <section class="section-one">
            <div class="">


                <div class="inner-main">

                    <div class="inner-body">
                        <div class="inner-head">
                            <a class="navbar-brand" href="/">
                                <i class="bi bi-cart4 me-3 mt-xl-4" style="color: #709085; font-size: 3em;"></i>
                            </a>

                            <span class="h1 fw-bold mb-0">Foodies</span>
                        </div>  
                        <form style="width: 23rem;" method="post" action="register">

                            <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">REGISTER</h3>

                            <div data-mdb-input-init class="form-outline mb-4">
                                Username
                                <input name="txtUser" type="text" id="form2Example18" class="form-control form-control-lg ipt" required/>
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <label for="gens">Choose your gender</label>
                                <select class="form-control form-control-lg ipt" id="gens" name="gender" required>
                                    <option name="gender" value="" selected disabled>Your gender</option>
                                    <option name="gender" value="male">Male</option>
                                    <option name="gender" value="female">Female</option>
                                </select>
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                Email address
                                <input name="txtMail" type="email" id="form2Example18" class="form-control form-control-lg ipt" required/>
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                Phone
                                <input name="txtPhone" class="form-control form-control-lg ipt" type="tel" pattern="[0-9]{10}" required>
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                Password
                                <input name="txtPass" type="password" id="form2Example28" class="form-control form-control-lg ipt" required/>
                            </div>

                            <p style="color:red;"> ${sessionScope.regisError}</p>


                            <div class="pt-1 mb-4">
                                <input name="btnRegister" data-mdb-button-init data-mdb-ripple-init class="btn btn-info btn-lg btn-block" type="submit" value="Register" style="background-color: #50ABCA"/>
                            </div>
                            <p>You already have an account? <a href="/login" class="link-info" name="sign_in">Sign in</a></p>
                        </form>

                    </div>

                    <div class="inner-wrap">
                        <img src="/background/login.png"
                             alt="Login image" class="inner-image">
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
