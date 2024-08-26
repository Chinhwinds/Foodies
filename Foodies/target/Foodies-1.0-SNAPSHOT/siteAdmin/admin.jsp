<%-- 
    Document   : admin
    Created on : Jun 23, 2024, 10:25:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="/CSS/adminstyles.css" />
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
                        <h1>Home</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a>/ <a href="/adminDashboard">Dashboard</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <!-- Dashboard Widgets -->
                <section class="dashboard-widgets">
                    <div class="row">
                        <!--REVENUE-->
                        <div class="widget widget-1">
                            <div class="div-title">
                                <i class="fa-solid fa-dollar-sign icon-title"></i>
                                <p class="title">Revenue</p>
                            </div>
                            <div class="record-1">
                                <c:forEach items="${sessionScope.dashboardHomes}" var="h">
                                    <h3 class="result">$ ${h.revenue}</h3>
                                </c:forEach>
                            </div>
                        </div>

                        <!--COST-->
                        <div class="widget widget-2">
                            <div class="div-title">
                                <i class="fa-solid fa-credit-card icon-title"></i>
                                <p class="title">Cost</p>
                            </div>
                            <c:forEach items="${sessionScope.dashboardHomes}" var="h">
                                <h3 class="result">$ ${h.cost}</h3>
                            </c:forEach>
                        </div>

                        <!--PROFIT-->
                        <div class="widget widget-3">
                            <div class="div-title">
                                <i class="fa-solid fa-hand-holding-dollar icon-title"></i>
                                <p class="title">Profit</p>
                            </div>
                            <c:forEach items="${sessionScope.dashboardHomes}" var="h">
                                <h3 class="result">$ ${h.profit}</h3>
                            </c:forEach>
                        </div>

                        <!--SALES PRODUCT-->
                        <div class="widget widget-4">
                            <div class="div-title">
                                <i class="fa-solid fa-chart-simple icon-title"></i>
                                <p class="title">Total Sales Products</p>
                            </div>

                            <c:forEach items="${sessionScope.dashboardHomes}" var="h">
                                <h3 class="result">${h.sales_product}</h3>
                            </c:forEach>
                        </div>
                    </div>
                </section>

                <!-- Tables / Lists -->
                <section class="data-tables" style="margin-top: 20px;">
                    <h4>Active Users</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Order Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.dataUserTotal}" var="x" varStatus="cnt">
                                <tr>
                                    <td>${cnt.index + 1}</td>
                                    <td>${x.user_name}</td>
                                    <td>${x.user_email}</td>
                                    <td>${x.user_phone}</td>
                                    <td>$ ${x.order_Total}</td>
                                    <td><a href="/adminUser" class="btn btn-success">Manage</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

                <section class="data-tables color-table" style="margin-top: 30px;">
                    <h4>Latest Orders</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Order Date</th>
                                <th>Order Total</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.dataOrderTotal}" var="y" varStatus="cnt">
                                <tr>
                                    <td>${cnt.index + 1}</td>
                                    <td>${y.user_name}</td>
                                    <td>${y.user_email}</td>
                                    <td>${y.order_Date}</td>
                                    <td>$ ${y.order_Total}</td>
                                    <td>${y.order_status}</td>
                                    <td><a href="/admin/adminReport" class="btn btn-success">Manage</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
        </div>
        <style>
            body {
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", "Liberation Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
                margin: 0;
                padding: 0;
                display: flex;
                background-color: #f4f7fa;
            }

            .containers {
                display: flex;
                width: 100%;
                height: 100%;
                /*position: absolute;*/
            }

            .main-content {
                flex: 1;
                padding: 20px;
                display: flex;
                flex-direction: column;
                width: 90%;
            }

            .inner-head {
                background-color: #3498db;
                text-align: center;
                color: #fff;
                font-weight: 750;
                border-radius: 10px 10px 0 0;
                margin-bottom: 15px;
                display: flex;
                justify-content: space-between;
                padding: 10px;
                align-items: center;
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
                font-weight: 500;
            }

            .header .breadcrumbs a {
                text-decoration: none;
                color: #7f8c8d;
                margin-right: 5px;
                font-size: 17px;
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
            .data-tables {
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .data-tables tbody tr:hover {
                background-color: #ECECEC;
            }

            .data-tables table {
                width: 100%;
                border-collapse: collapse;
            }

            .data-tables table th, .data-tables table td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .color-table table thead tr th{
                background-color: #F2AE1D;
                color: white;
            }
            
            .data-tables table th {
                background-color: #3498db;
                color: white;
            }

            .data-tables table td button {
                background-color: #3498db;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
            }

            .data-tables table td button:hover {
                background-color: #2980b9;
            }

            .dashboard-widgets {
                display: flex;
                flex-direction: row;
                align-items: center;
                gap: 20px;
                justify-content: center;
            }

            .row {
                display: flex;
                gap: 50px;
            }
            .dashboard-widgets .widget {
                background-color: #ecf0f1;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                font-size: 18px;
                box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
            }

            .widget-1,
            .widget-2,
            .widget-3,
            .widget-4{
                width: 280px;
            }

            .dashboard-widgets .widget-1,
            .dashboard-widgets .widget-2,
            .dashboard-widgets .widget-3,
            .dashboard-widgets .widget-4
            {
                border-radius: 30px;
                background-color: white;
                color: white;
                transition: box-shadow 0.3s ease;
            }

            .dashboard-widgets .widget-1:hover,
            .dashboard-widgets .widget-2:hover,
            .dashboard-widgets .widget-3:hover,
            .dashboard-widgets .widget-4:hover
            {
                /* background-color: rgb(164, 251, 245); */
                /*color: white;*/
                box-shadow: 10px 10px 20px rgba(36, 36, 36, 0.5);
            }

            .dashboard-widgets .widget-1 {
                background-color: #a1c9f1;
                color: white;
            }

            .dashboard-widgets .widget-2 {
                background-color: #2f2f2f;
                color: white;
            }

            .dashboard-widgets .widget-3 {
                background-color: #42b5a6;
                color: white;
            }

            .dashboard-widgets .widget-4 {
                background-color: #efb0c9;
                color: white;
            }

            .div-title{
                text-align: start;
            }

            .icon-title,
            .title{
                display: inline-block;
                padding-right: 5px;
                font-size: 20px;
            }

            .result{
                font-size: 40px;
            }

            .tbl {
                width: 100%;
                border-collapse: collapse;
            }
        </style>
    </body>
</html>
