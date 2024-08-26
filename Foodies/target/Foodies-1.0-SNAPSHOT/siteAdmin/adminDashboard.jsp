<%-- 
    Document   : adminDashboard
    Created on : Jul 9, 2024, 3:11:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="/CSS/admindashboard.css" />

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
                        <h1>Dashboard</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a>/ <a href="/adminDashboard">Dashboard</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <section class="dashboard-widgets">

                    <!--REVENUE-->
                    <div class="widget widget-1">
                        <div class="div-title">
                            <i class="fa-solid fa-dollar-sign icon-title"></i>
                            <p class="title-dash">Revenue</p>
                        </div>
                        <div class="record-1">
                            <c:forEach items="${sessionScope.dashboard}" var="i">
                                <h3 class="result-1">$ ${i.revenue}</h3>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="row">
                        <!--COST-->
                        <a href="/adminDashboard/Cost">
                            <div class="widget widget-2">
                                <div class="div-title">
                                    <i class="fa-solid fa-credit-card icon-title"></i>
                                    <p class="title-dash">Cost</p>
                                </div>
                                <c:forEach items="${sessionScope.dashboard}" var="i">
                                    <h3 class="result">$ ${i.cost}</h3>
                                </c:forEach>
                            </div>
                        </a>

                        <!--PROFIT-->
                        <div class="widget widget-3">
                            <div class="div-title">
                                <i class="fa-solid fa-hand-holding-dollar icon-title"></i>
                                <p class="title-dash">Profit</p>
                            </div>
                            <c:forEach items="${sessionScope.dashboard}" var="i">
                                <h3 class="result">$ ${i.profit}</h3>
                            </c:forEach>
                        </div>

                        <!--SALES PRODUCT-->
                        <a href="/adminDashboard/SalesProduct">
                            <div class="widget widget-4">
                                <div class="div-title">
                                    <i class="fa-solid fa-chart-simple icon-title"></i>
                                    <p class="title-dash">Total Sales Products</p>
                                </div>

                                <c:forEach items="${sessionScope.dashboard}" var="i">
                                    <h3 class="result">${i.sales_product}</h3>
                                </c:forEach>
                            </div>
                        </a>
                    </div>



                    <div class="row">
                        <!--TOP 5 MOST SALES PRODUCT-->
                        <div class="widget widget-5">
                            <div class="div-title">
                                <i class="fa-solid fa-chart-line icon-title"></i>
                                <p class="title-dash">Most 5 Sales Products</p>
                            </div>
                            <table class="tbl">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Sold</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.dashboardProduct}" var="i">
                                        <tr>
                                            <td>${i.proname}</td>
                                            <td>${i.number_of_pro}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <!--BOUGHT IN MONTH-->
                        <form action="adminDashboard" method="post">
                            <div class="widget widget-6">
                                <div class="div-title">
                                    <i class="fa-solid fa-money-bill icon-title"></i>
                                    <p class="title-dash">Bought Amount/Month</p> 

                                    <p name="Month" hidden="" value="${sessionScope.month}">${sessionScope.month}</p>
                                    <p name="Year" hidden="" value="${sessionScope.year}">${sessionScope.year}</p>


                                    <select name="salesMonth">
                                        <c:forEach begin="1" end="12" var="cnt">
                                            <option ${cnt == sessionScope.month ? "selected" : ""} value="${cnt}" >${cnt}</option>
                                        </c:forEach>
                                    </select>

                                    <select name="salesYear">
                                        <c:forEach begin="${sessionScope.earliestyear}" end="${sessionScope.latestyear}" var="cntY">
                                            <option ${cntY == sessionScope.year ? "selected" : ""} value="${cntY}" >${cntY}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <p>Bought Amount</p>
                                <c:forEach items="${sessionScope.dashboard}" var="i">
                                    <h3>${i.bought_in_month}</h3>
                                </c:forEach>
                                <p>Total Income</p>
                                <c:forEach items="${sessionScope.dashboard}" var="i">
                                    <h3>$ ${i.totalincome}</h3>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${sessionScope.latestyear - sessionScope.earliestyear == 0}">
                                        <button type="submit" name="btnCheck" value="checkmonth" class="btn btn-success" disabled="">Check</button>

                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="btnCheck" value="checkmonth" class="btn btn-success">Check</button>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </form>
                    </div>
                </section>
            </main>
        </div>

        <style>
            body {
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", "Liberation Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
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

            .row {
                display: flex;
                gap: 70px;
            }
            .dashboard-widgets .widget {
                background-color: #ecf0f1;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                font-size: 18px;
                box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
            }

            .widget-1{
                width: 90%;
                height: 250px;
            }

            .widget-1 > .div-title{
                align-items:start;
            }

            .record-1{
                height: 70%;
                text-align: center;
                align-content: center;
                margin-left: 40px;
            }

            .widget-2,
            .widget-3,
            .widget-4{
                width: 300px;
            }

            .widget-5{
                width: 390px;
            }

            .widget-6{
                width: 450px;
            }


            .dashboard-widgets .widget-1,
            .dashboard-widgets .widget-2,
            .dashboard-widgets .widget-3,
            .dashboard-widgets .widget-4,
            .dashboard-widgets .widget-5,
            .dashboard-widgets .widget-6
            {
                border-radius: 30px;
                background-color: white;
                color: white;
                transition: box-shadow 0.3s ease;
            }

            .dashboard-widgets .widget-1:hover,
            .dashboard-widgets .widget-2:hover,
            .dashboard-widgets .widget-3:hover,
            .dashboard-widgets .widget-4:hover,
            .dashboard-widgets .widget-5:hover,
            .dashboard-widgets .widget-6:hover
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

            .dashboard-widgets .widget-5 {
                background-color: #ffce54;
                color: #204177;
            }
            .dashboard-widgets .widget-6 {
                background-color: #204177;
                color: #ffce54;
            }

            .widget-6 select {
                background-color: #ffcc00;
                color: #2a3f5f;
                border: none;
                border-radius: 5px;
                padding: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            .div-title{
                text-align: start;
            }

            .title-dash{
                font-weight: bold;
            }

            .icon-title,
            .title,
            .title-dash{
                display: inline-block;
                padding-right: 5px;
                font-size: 20px;
            }

            .result-1{
                font-size: 100px;
            }

            .result{
                font-size: 50px;
            }

            .tbl {
                width: 100%;
                border-collapse: collapse;
            }

            a:hover{
                text-decoration:none;
            }
        </style>
    </body>
</html>
