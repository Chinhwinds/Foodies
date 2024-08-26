<%-- 
    Document   : adminReportDelete
    Created on : Jul 10, 2024, 7:50:45 PM
    Author     : asus1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Report List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.dataTables.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>

        <script>
            $(document).ready(function () {
                $('#table_1').DataTable();
            });
        </script>

        <link rel="stylesheet" href="/CSS/adminReportDelete.css"/>
    </head>
    <body>
        <sql:setDataSource var="conn" 
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true"
                           user="sa"
                           password="nchieu"
                           />

        <c:set var="deleteID" value="${sessionScope.delete.shopID}" />

        <sql:query var="rs" dataSource="${conn}">
            select s.id, u.username, ph.[name], ph.product_image, o.qty, o.price, p.qty_in_stock
            from shop_order s
            join order_line o on s.id = o.order_id
            join user_site u on s.user_id = u.id
            join product_item p on o.product_item_id = p.id
            join product ph on ph.id = p.id
            where s.id = ?
            <sql:param value="${deleteID}" />
        </sql:query>

        <div class="containers">
            <!-- Sidebar -->
            <jsp:include page="/Component/adminsidebar.jsp"/>

            <!-- Main Content -->
            <main class="main-content">
                <!-- Header -->
                <header class="header">
                    <div class="title">
                        <h1>Order Reports</h1>
                        <nav class="breadcrumbs">
                            <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a href="/admin/adminReport">Order Reports</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <section class="data-tables">
                    <a href="/admin/adminReport" class="icon-one" title="Back to Order Reports">
                        <i class="fa-solid fa-circle-arrow-left inner-icon-one"></i>
                    </a>
                    <div class="inner-title-table">
                        <h3 class="inner-title">Delete Reports Table</h3>
                    </div>
                    <form method="post" action="/admin/adminReport">
                        <table id="table_1">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>User Name</th>
                                    <th>Product Name</th>
                                    <th>Product Image</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Quantity In Stock</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="rows" items="${rs.rows}">
                                    <tr>
                                        <td>${rows.id}</td>
                                        <td>${rows.username}</td>
                                        <td>${rows.name}</td>
                                        <td>
                                            <img src="/images/${rows.product_image}" alt="Product Image" style="width: 100px; height: 80px; object-fit: cover;">
                                        </td>
                                        <td>${rows.qty}</td>
                                        <td>${rows.price}$</td>
                                        <td>${rows.qty_in_stock}</td>
                                    </tr>
                                </c:forEach>

                            <input value="${sessionScope.getID}" hidden="" name="txtID"/>
                            </tbody>
                        </table>

                        <div class="col-xl-12 text-center">
                            <button type="submit" class="button" name="btnReDelete" onclick="return confirm('Are you sure to remove this Order Report?')">Delete</button>
                        </div>
                    </form>

                </section>

            </main>
        </div>

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
