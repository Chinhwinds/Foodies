<%-- 
    Document   : adminUserDelete
    Created on : Jul 5, 2024, 4:01:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete User</title>
        <link rel="stylesheet" href="/CSS/adminUserdelete.css" />

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
                        <h1>Users</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a
                                href="/adminUser">Users</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <a href="/adminUser" class="icon-one"><i class="fa-solid fa-circle-arrow-left inner-icon-one"></i></a>


                <div class="inner-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="inner-main">
                                <div class="inner-head">
                                    <h2>Delete User</h2>
                                </div>

                                <!--User form-->
                                <div class="inner-body">
                                    <form action="/adminUser" method="post">
                                        <div class="form-group row">
                                            <label for="catID" class="col-sm-2 col-form-label">User ID</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control-plaintext" id="catID" name="txtID" readonly value="${sessionScope.user.id}"/> 
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Username</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="catName" name="txtName" disabled value="${sessionScope.user.username}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Gender</label>
                                            <div class="col-sm-10">

                                                <select class="form-control" name="gender" id="gens" disabled>
                                                    <option name="gender" value="male" selected="">${sessionScope.user.gender}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Email</label>
                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" id="catName" name="txtEmail" value="${sessionScope.user.email}" disabled>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Phone</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="catName" name="txtPhone" value="${sessionScope.user.phone}" disabled>
                                            </div>
                                        </div>
                                        <div class="col-xl-12 text-center">
                                            <button type="submit" class="button" name="btnUserDelete" value="delete" onclick="return confirm('All of Order below will be deleted when you delete this user');">Delete</button>
                                        </div>
                                    </form>
                                </div>

                                <!--Order form-->
                                <section class="data-tabless">
                                    <div class="inner-head">
                                        <h2>User's Order</h2>
                                    </div>
                                    <table class="tbl">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Username</th>
                                                <th>Order Date</th>
                                                <th>Order Total</th>                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.dataOrder}" var="i" >
                                                <tr>
                                                    <td>${i.order_ID}</td>
                                                    <td>${i.user_name}</td>
                                                    <td>${i.orderDate}</td>
                                                    <td>${i.orderTotal} $</td>                                
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </section>
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
