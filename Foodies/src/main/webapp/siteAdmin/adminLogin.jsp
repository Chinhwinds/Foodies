<%-- 
    Document   : adminLogin
    Created on : Jun 23, 2024, 9:00:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js" ></script>
    </head>
    <body>
        <style>
            .ipt{
                box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset, rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;
            }
            .mb-5 {
                margin-bottom: 1rem !important;
            }
        </style>
        <!-- Section: Design Block -->
        <section class="text-center">
            <!-- Background image -->
            <div class="p-5 bg-image" style="
                 background-image: url('/background/FOODIE.png');
                 width: auto;
                 height: 450px;
                 object-fit: cover;
                 background-repeat: no-repeat;
                 ">
            </div>      
            <!-- Background image -->

            <div class="card mx-4 mx-md-5 shadow-5-strong bg-body-tertiary" style="
                 margin-top: -100px;
                 backdrop-filter: blur(30px);
                 ">
                <div class="card-body py-5 px-md-5">
                    <div class="row d-flex justify-content-center">
                        <div class="col-lg-4 mx-auto">
                            <h2 class="fw-bold mb-5">ADMIN</h2>
                            <form action="adminlogin" method="post">
                                <!-- Email input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label for="form3Example3">Username</label>
                                    <input name="txtAdName" type="text" id="form3Example3" class="form-control ipt" required/>
                                </div>

                                <!-- Password input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label for="form3Example4">Password</label>
                                    <input name="txtAdPass" type="password" id="form3Example4" class="form-control ipt" required/>
                                </div>

                                <p style="color:red;"> ${sessionScope.adError}</p>

                                <!-- Submit button -->
                                <input name="btnAdLogin" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4" type="submit" value="Log in"/>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="/login" class="btn btn-secondary">Admin</a>
            </div>
        </section>
        <!-- Section: Design Block -->
    </body>
</html>
