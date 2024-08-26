<%-- 
    Document   : adminDashboardCost
    Created on : Jul 10, 2024, 7:09:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cost</title>
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
                        <h1>Cost</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a>/ <a href="/adminDashboard">Dashboard</a>/ <a href="/adminDashboard/Cost">Cost</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <a href="/adminDashboard" class="icon-one" title="Back to Dashboard">
                    <i class="fa-solid fa-circle-arrow-left inner-icon-one"></i>
                </a>

                <section class="dashboard-widgets">
                    <!--COST-->
                    <div class="widget widget-2">
                        <div class="div-title">
                            <i class="fa-solid fa-credit-card icon-title"></i>
                            <p class="title">Cost</p>
                        </div>
                        <table class="tbl">
                            <thead>
                                <tr>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Cost</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.dashboardCostInfo}" var="i">
                                    <tr>
                                        <td>${i.id}</td>
                                        <td>${i.name}</td>
                                        <td>${i.cost}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </section>
            </main>
        </div>

        <style>
            body {
                font-family:  -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", "Liberation Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
                margin: 0;
                padding: 0;
                /*display: flex;*/
                height: 100%;
                background-color: #f4f7fa;
            }


            .containers {
                display: flex;
                width: 100%;
                height: 100%;
                /*position: absolute;*/
            }


            .main-content {
                width: 90%;
                padding: 20px;
                display: flex;
                flex-direction: column;
                margin-left: 170px;
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
                flex-direction: column;
                align-items: center;
                gap: 20px;
            }

            .dashboard-widgets .widget {
                background-color: #ecf0f1;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                font-size: 18px;
                box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
            }

            .widget-2{
                width: 500px;
            }


            .dashboard-widgets .widget-2
            {
                background-color: white;
                color: white;
            }


            .dashboard-widgets .widget-2 {
                background-color: #f29186;
                /* color: white; */
            }


            .div-title{
                text-align: start;
            }

            .icon-title,
            .title{
                display: inline-block;
                padding-right: 5px;
                font-size: 23px;
            }

            .result{
                font-size: 50px;
            }

            .tbl {
                width: 100%;
                border-collapse: collapse;
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
    </body>
</html>
