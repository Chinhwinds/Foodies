<%-- 
    Document   : adminCateEdit
    Created on : Jul 4, 2024, 12:59:55 AM
    Author     : asus1
--%>

<%@page import="Models.AdminCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Category</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>

    <body>
        <div class="containers">
            <!-- Sidebar -->
            <jsp:include page="/Component/adminsidebar.jsp"/>

            <!-- Main Content -->
            <main class="main-content">
                <!-- Header -->
                <header class="header">
                    <div class="title">
                        <h1>Products</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a
                                href="/admin/adminProduct">Products</a> / <a href="/admin/adminProduct/categoryCreate">Category</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>


                <a href="/admin/adminProduct" class="icon-one" title="Back to Products">
                    <i class="fa-solid fa-circle-arrow-left inner-icon-one"></i>
                </a> 
                <div class="inner-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="inner-main">
                                <div class="inner-head">
                                    <h2>Create Category</h2>
                                </div>

                                <div class="inner-body">
                                    <form method="post">

                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Category ID</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="catName" name="txtCreateID" required="">
                                            </div>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.error}</p>
                                        <div class="form-group row">
                                            <label for="catID" class="col-sm-2 col-form-label">Category Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="catID" name="txtCreateName" required="">
                                            </div>
                                        </div>

                                        <div class="col-xl-12 text-center">
                                            <button type="submit" class="button" name="btnCatCreate">Create</button>
                                        </div>

                                        <p class="message-notify" style="color:red;">${requestScope.errors}</p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <style>
            body {
                font-family: 'Arial', sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                background-color: #f4f7fa;
                height: 100%;
            }

            .slidebar-item-list {
                position: relative;

            }

            .title-icon {
                position: absolute;
                top: 0;
                left: 45px;
                opacity: 0;
                transition: all ease-in 0.3s;
            }

            .slidebar-item-list:hover > .title-icon {
                display: block;
                opacity: 1;
                transition: all ease-in 0.3s;
            }

            .sidebar .icon{
                font-size: 25px;
                margin-bottom: 20px;
                transition: all ease-in 0.3s;
            }

            .sidebar .icon:hover {
                font-size: 30px;
                font-weight: 700;
                color: #fff;
                transition: all ease-in 0.3s;
            }

            .containers {
                display: flex;
                width: 100%;
                height: 100%;
                position: absolute;
            }

            .sidebar {
                width: 170px;
                background-color: #2c3e50;
                color: white;
                display: flex;
                flex-direction: column;
                padding: 20px;
                height: 100%;
            }

            .sidebar .logo {
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 30px;
            }

            .sidebar nav ul {
                list-style: none;
                padding: 0;
            }

            .sidebar nav ul li {
                margin: 20px 0;
            }

            .sidebar nav ul li a {
                color: white;
                text-decoration: none;
                font-size: 18px;
            }

            .sidebar nav ul li a:hover {
                text-decoration: underline;
            }

            .main-content {
                flex: 1;
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                border-bottom: 1px solid #ccc;
                padding-bottom: 10px;
            }

            .header .title h1 {
                margin: 0;
                font-size: 24px;
            }

            .header .breadcrumbs a {
                text-decoration: none;
                color: #7f8c8d;
                margin-right: 5px;
            }

            .header .breadcrumbs a:hover {
                text-decoration: underline;
            }

            .header .user-profile {
                display: flex;
                align-items: center;
            }

            .header .user-profile img {
                border-radius: 50%;
                margin-left: 10px;
                width: 40px;
                height: 40px;
            }

            .inner-form {
                background-color: #fff;
                width: 100%;
                height: auto;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .inner-form .inner-main .inner-head {
                background-color: #3498db;
                text-align: center;
                color: #fff;
                font-weight: 750;
                border-radius: 10px 10px 0 0;
                padding: 5px;
                margin-bottom: 15px;
            }

            .inner-form .inner-main .inner-head h2 {
                font-weight: 700;
            }

            .inner-form .inner-main .inner-body {
                padding: 10px;
                font-weight: 600;
            }

            .button {
                background: -webkit-linear-gradient(-30deg, #3498db, #0790ec 100%);
                border: 0;
                padding: 10px 30px;
                color: #fff;
                font-weight: 600;
                border-radius: 45px;
            }

            .footer {
                text-align: center;
                padding: 20px;
                background-color: #ecf0f1;
                margin-top: auto;
                box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
            }

            .inner-icon-one{
                font-size: 30px;
                color: #333;
                transition: 0.3s all ease-in;
                margin-bottom: 20px;
            }

            .inner-icon-one:hover {
                font-size: 35px;
                color: #48C9B0;
                transition: 0.3s all ease-in;
            }
        </style>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>
