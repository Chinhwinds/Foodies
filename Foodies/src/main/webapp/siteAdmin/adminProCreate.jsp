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
        <title>Create Product</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <link rel="stylesheet" href="/CSS/adminProCreate.css"/>

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
                                href="/admin/adminProduct">Products</a>
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
                                    <h2>Create Product</h2>
                                </div>

                                <div class="inner-body">
                                    <form method="post">
                                        <div class="form-group row">
                                            <label for="proName" class="col-sm-2 col-form-label">Product Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="proName" name="txtProName" required="">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="proDes" class="col-sm-2 col-form-label">Product Description</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="proDes" name="txtProDes" required="">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="proImg" class="col-sm-2 col-form-label">Product Image</label>
                                            <div class="col-sm-10">
                                                <input type="file" class="form-control" id="proImg" name="txtProPic" required="">
                                            </div>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.errorss}</p>

                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Category Name</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" name="txtCatID" required="">
                                                    <c:forEach items="${sessionScope.data}" var="i">
                                                        <option value="${i.catID}">${i.catName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label for="itemQuan" class="col-sm-2 col-form-label">Product Item Quantity</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="itemQuan" name="txtItemQuan" required="">
                                            </div>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.Error}</p>

                                        <div class="form-group row">
                                            <label for="itemPrice" class="col-sm-2 col-form-label">Product Item Price</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="itemPrice" name="txtItemPrice" required="">
                                            </div>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.Errors}</p>

                                        <div class="form-group row">
                                            <label for="itemPic" class="col-sm-2 col-form-label">Product Item Picture</label>
                                            <div class="col-sm-10">
                                                <input type="file" class="form-control" id="itemPic" name="txtItemPic" required="">
                                            </div>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.errorss}</p>

                                        <div class="col-xl-12 text-center">
                                            <button type="submit" class="button" name="btnProCreate">Create</button>
                                        </div>
                                        <p class="message-notify" style="color:red;">${requestScope.error}</p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>

        <style>
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
    </body>

</html>
