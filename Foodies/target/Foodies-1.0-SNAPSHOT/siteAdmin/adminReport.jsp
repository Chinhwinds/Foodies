<%-- 
    Document   : adminReport
    Created on : Jul 7, 2024, 4:30:17 PM
    Author     : asus1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Report</title>
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

        <link rel="stylesheet" href="/CSS/adminReport.css"/>
    </head>
    <body>

        <sql:setDataSource var="conn" 
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true"
                           user="sa"
                           password="nchieu"
                           />
        <sql:query var="rs" dataSource="${conn}">
            select s.id, u.username, s.order_total, s.order_date, u.email, u.phone, os.status
            from shop_order s
            join order_status os on s.order_status_id = os.id
            join user_site u on s.user_id = u.id
            order by s.user_id
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
                    <div class="inner-title-table">
                        <h3 class="inner-title">Order Reports Table</h3>
                    </div>
                    <form method="post" action="/admin/adminReport">
                        <table id="table_1">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>User Name</th>
                                    <th>Total of bill</th>
                                    <th>Order Date</th>
                                    <th>User Email</th>
                                    <th>User Phone</th>
                                    <th>Order Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${rs.rows}">
                                    <tr>
                                        <td>${r.id}</td>
                                        <td>${r.username}</td>
                                        <td>${r.order_total}$</td>
                                        <td>${r.order_date}</td>
                                        <td>${r.email}</td>
                                        <td>${r.phone}</td>
                                        <td>${r.status}</td>
                                        <td>
                                            <a href="/admin/adminReport/List/${r.id}" class="icon-one" title="More Information">
                                                <i class="fa-solid fa-list-ul inner-icon-one"></i>
                                            </a>
                                            |
                                            <a href="/admin/adminReport/Delete/${r.id}" title="Delete">
                                                <i class="fa-solid fa-trash inner-icon-two"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>

                </section>

            </main>
        </div>
    </body>
</html>
