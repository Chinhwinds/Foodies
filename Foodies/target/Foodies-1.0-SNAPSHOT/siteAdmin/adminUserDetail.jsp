<%-- 
    Document   : adminUserDetail
    Created on : Jul 5, 2024, 4:01:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>User Detail</title>
        <link rel="stylesheet" href="/CSS/adminUserdetail.css" />

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
            integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
            integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
            crossorigin="anonymous"
            />
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
                            <a href="/admin">Home</a> /
                            <a href="/adminDashboard">Dashboard</a> /
                            <a href="/adminUser">Users</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <a href="/adminUser" class="icon-one"
                   ><i class="fa-solid fa-circle-arrow-left inner-icon-one"></i
                    ></a>

                <div class="inner-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="inner-main">
                                <!--Order form-->
                                <section class="data-tabless">
                                    <div class="inner-head">
                                        <h2>User's Order</h2>
                                    </div>
                                    <c:forEach items="${sessionScope.dataTotal}" var="i" varStatus="cnt">
                                        <h2 class="order">Order ${cnt.index + 1}</h2>
                                        <table class="tbl">
                                            <thead>
                                                <tr>
                                                    <th>Product Name</th>
                                                    <th>Order Amounts</th>
                                                    <th>Price</th>
                                                    <th>Total Price</th>
                                                    <th>Order Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${sessionScope.dataDetail}" var="j">
                                                    <c:if test="${i.order_ID == j.order_ID}">
                                                        <tr>
                                                            <td>${j.pro_name}</td>
                                                            <td>${j.quantity}</td>
                                                            <td>${j.price} $</td>
                                                            <td>${j.total_price} $</td>
                                                            <td>${j.orderDate}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="total-price">
                                            <h2>Total ${i.orderTotal}$</h2>
                                        </div>
                                        <hr/>
                                    </c:forEach>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"
        ></script>
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

