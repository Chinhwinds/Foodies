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
        <title>Admin User</title>
        <link rel="stylesheet" href="/CSS/adminUsermanage.css" />
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
    </head>
    <body>

        <div class="containers">
            <!-- Sidebar -->
            <jsp:include page="/Component/adminsidebar.jsp"/>

            <!-- Main Content -->
            <main class="main-content">
                <!-- Header -->
                <header class="headerr">
                    <div class="titlee">
                        <h1>Users</h1>
                        <nav class="breadcrumbss">
                            <a href="/admin">Home</a>/ <a href="/adminDashboard">Dashboard</a>/ <a href="/adminUser">Users</a>
                        </nav>
                    </div>
                    <div class="user-profilee">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <!-- Tables / Lists -->
                <section class="data-tabless">
                    <div class="inner-head">
                        <h2>User Management</h2>
                        <a href="/adminUser/Create"><i class="fa-solid fa-user-plus inner-icon"></i></i></a>
                    </div>
                    <table id="table_1">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Email</th>                                
                                <th>Phone</th>
                                <th>Total Payment</th>
                                <th>Total Bill</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.dataUser}" var="i" >
                                <tr>
                                    <td>${i.userID}</td>
                                    <td>${i.username}</td>
                                    <td>${i.gender}</td>
                                    <td>${i.email}</td>                                
                                    <td>${i.phone}</td>
                                    <td>${i.total_pay} $</td>
                                    <td>${i.total_bill}</td>
                                    <td>
                                        <a href="/adminUser/User/${i.userID}"><i class="fa-solid fa-circle-info inner-icon-1"></i></a> | <a href="/adminUser/Delete/${i.userID}"><i class="fa-solid fa-trash inner-icon-2"></i></a> | <a href="/adminUser/Detail/${i.userID}"><i class="fa-solid fa-bars inner-icon-3"></i></a> 
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
        </div>

<!--        <style>
            
        </style>-->
    </body>
</html>
