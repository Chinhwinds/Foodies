<%-- 
    Document   : adminProduct
    Created on : Jul 3, 2024, 11:32:36 AM
    Author     : asus1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Product</title>
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
        
        <link rel="stylesheet" href="/CSS/adminProduct.css"/>
    </head>
    <body>

        <sql:setDataSource var="conn" 
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true"
                           user="sa"
                           password="nchieu"
                           />

        <sql:query var="rs" dataSource="${conn}">
            select p.id, p.name, p.description, p.product_image, c.category_name, i.qty_in_stock, i.price, i.product_image as [product_item_image]
            from product p
            join product_item i on p.id = i.id
            join product_category c on p.category_id = c.id
        </sql:query>

        <sql:query var="rus" dataSource="${conn}">
            select c.id, c.category_name
            from product_category c
        </sql:query>

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
                            <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a href="/admin/adminProduct">Products</a>
                        </nav>
                    </div>
                    <div class="user-profile">
                        <!--<span>Admin</span>-->
                        <img src="/avt_admin/${sessionScope.adIMG}" alt="Profile Picture" />
                    </div>
                </header>

                <!-- Tables / Lists -->
                <section class="data-tables">
                    <div class="inner-title-table">
                        <h3 class="inner-title">Product Table</h3>

                        <a href="/admin/adminProduct/Create">
                            <i class="fa-solid fa-square-plus table-icon-create"></i>
                        </a>
                    </div>
                    <form method="post" action="/admin/adminProduct">
                        <table id="table_1">
                            <thead>
                                <tr>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Product Description</th>
                                    <th>Product Picture</th>
                                    <th>Category Name</th>
                                    <th>Quantity In Stock</th>
                                    <th>Price</th>
                                    <th>Product Item Picture</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${rs.rows}">
                                    <tr>
                                        <td>${r.id}</td>
                                        <td>${r.name}</td>
                                        <td>${r.description}</td>
                                        <td>
                                            <img src="/images/${r.product_image}" alt="Products" style="width: 100px; height: 80px; object-fit: cover"/>
                                        </td>
                                        <td>${r.category_name}</td>
                                        <td>${r.qty_in_stock}</td>
                                        <td>${r.price}$</td>
                                        <td>
                                            <img src="/images/${r.product_item_image}" alt="Product Item Image" style="width: 100px; height: 80px; object-fit: cover"/>
                                        </td>
                                        <td>
                                            <a href="/admin/adminProduct/Edit/${r.id}" class="icon-one"/>
                                                <i class="fa-solid fa-pen-to-square inner-icon-one"></i>
                                            </a>
                                            | 
                                            <a href="/admin/adminProduct/Delete/${r.id}">
                                                <i class="fa-solid fa-trash inner-icon-two"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>

                </section>

                <section class="data-tables">
                    <div class="inner-head">
                        <h3 class="inner-head-title">Category Table</h3>
                        <a href="/admin/adminProduct/categoryCreate" class="inner-head-addicon" title="Create">
                            <i class="fa-solid fa-square-plus"></i>
                        </a>
                    </div>

                    <table id="table_1">
                        <thead>
                            <tr>
                                <th>Category ID</th>
                                <th>Category Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="rows" items="${rus.rows}">
                                <tr>
                                    <td>${rows.id}</td>
                                    <td>${rows.category_name}</td>
                                    <td>
                                        <a href="/admin/adminProduct/categoryEdit/${rows.id}" class="icon-one">
                                            <i class="fa-solid fa-pen-to-square inner-icon-one" name="btnEdit"></i>
                                        </a>
                                        | 
                                        <a href="/admin/adminProduct/categoryDelete/${rows.id}">
                                            <i class="fa-solid fa-trash inner-icon-two" name="btnDelete"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
        </div>


    </body>
</html>
