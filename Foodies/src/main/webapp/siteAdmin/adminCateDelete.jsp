<%-- 
    Document   : adminCateEdit
    Created on : Jul 4, 2024, 12:59:55 AM
    Author     : asus1
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AdminCategoryDAO"%>
<%@page import="Models.AdminCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Category</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/adminCateDelete.css"/>

    </head>

    <body>

        <sql:setDataSource var="conn" 
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true"
                           user="sa"
                           password="nchieu"
                           />

        <c:set var="categoryId" value="${sessionScope.catedelete.catID}" />

        <sql:query var="rs" dataSource="${conn}">
            SELECT p.id, p.name, p.description, p.product_image, c.category_name, i.qty_in_stock, i.price, i.product_image AS product_item_image
            FROM product p
            JOIN product_item i ON p.id = i.id
            JOIN product_category c ON p.category_id = c.id
            WHERE c.id = ?
            <sql:param value="${categoryId}" />
        </sql:query>

        <div class="containers">
            <!-- Sidebar -->
            <jsp:include page="/Component/adminsidebar.jsp"/>


            <!-- Main Content -->
            <main class="main-content">
                <!-- Header -->
                <div class="inner-content">
                    <header class="header">
                        <div class="title">
                            <h1>Products</h1>
                            <nav class="breadcrumbs">
                                <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a
                                    href="/admin/adminProduct">Products</a> / <a href="/admin/adminProduct/categoryDelete/">Category</a>
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
                                        <h2>Delete Category</h2>
                                    </div>

                                    <div class="inner-body">
                                        <form method="post">
                                            <div class="form-group row">
                                                <label for="catID" class="col-sm-2 col-form-label">Category ID</label>
                                                <div class="col-sm-10">
                                                    <input type="number" class="form-control-plaintext" id="catID" name="txtCatID"
                                                           readonly value="${sessionScope.catedelete.catID}"/> 
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="catName" class="col-sm-2 col-form-label">Category Name</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="catName" name="txtCatName" value="${sessionScope.catedelete.catName}" disabled="" >
                                                </div>
                                            </div>
                                            <div class="col-xl-12 text-center">
                                                <button type="submit" class="button" name="btnCatDelete" onclick="return confirm('This is a number of Products will be deleted when you remove this Category')">Delete</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="data-tables">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Product Name</th>
                                        <th>Product Description</th>
                                        <th>Product Image</th>
                                        <th>Category Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Product Item Image</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="rows" items="${rs.rows}">
                                        <tr>
                                            <td>${rows.id}</td>
                                            <td>${rows.name}</td>
                                            <td>${rows.description}</td>
                                            <td>
                                                <img src="/images/${rows.product_image}" alt="Products" style="width: 100px; height: 80px; object-fit: cover"/>
                                            </td>
                                            <td>${rows.category_name}</td>
                                            <td>${rows.qty_in_stock}</td>
                                            <td>${rows.price}$</td>
                                            <td>
                                                <img src="/images/${rows.product_item_image}" alt="Products Item" style="width: 100px; height: 80px; object-fit: cover"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div> 
                </div>
            </main>
        </div>

        <style>
            html{
                height: 100%;
                width: 100%;
                position: relative;
            }

            body {
                font-family: 'Arial', sans-serif;
                margin: 0;
                padding: 0;
                /*display: flex;*/
                background-color: #f4f7fa;
                height: 100%;
            }

            .containers {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                height: 100%;
            }

            /*Slidebar*/

            .inner-sidebar {
                background-color: #2c3e50;
            }


            .slidebar-item-list {
                position: relative;
            }

            .title-icon {
                position: absolute;
                top: 0;
                left: 45px;
                opacity: 0;
                transition: all ease-in 0.3s;
            }

            .slidebar-item-list:hover > .title-icon {
                display: block;
                opacity: 1;
                transition: all ease-in 0.3s;
            }

            .sidebar .icon{
                font-size: 25px;
                margin-bottom: 20px;
                transition: all ease-in 0.3s;
            }

            .sidebar .icon:hover {
                font-size: 30px;
                font-weight: 700;
                color: #fff;
                transition: all ease-in 0.3s;
            }

            .sidebar {
                width: 150px;
                padding: 20px;
                background-color: #2c3e50;
                color: white;
                display: flex;
                flex-direction: column;
                padding: 20px;
            }

            .sidebar .logo {
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 30px;
            }

            .sidebar nav ul {
                list-style: none;
                padding: 0;
            }

            .sidebar nav ul li {
                margin: 20px 0;
            }

            .sidebar nav ul li a {
                color: white;
                text-decoration: none;
                font-size: 18px;
            }

            .sidebar nav ul li a:hover {
                text-decoration: underline;
            }

            .main-content {
                flex: 1;
                width: 90%;
                padding: 20px;
                height: 100%;
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
            }

            .header .breadcrumbs a {
                text-decoration: none;
                color: #7f8c8d;
                margin-right: 5px;
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

            .inner-form {
                background-color: #fff;
                width: 100%;
                height: auto;
                border-radius: 10px;
                margin-bottom: 30px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .inner-form .inner-main .inner-head {
                background-color: #D40008;
                text-align: center;
                color: #fff;
                font-weight: 750;
                border-radius: 10px 10px 0 0;
                padding: 5px;
                margin-bottom: 15px;
            }

            .inner-form .inner-main .inner-head h2 {
                font-weight: 700;
            }

            .inner-form .inner-main .inner-body {
                padding: 10px;
                font-weight: 600;
            }

            .button {
                background: #D40008;
                border: 0;
                padding: 10px 30px;
                color: #fff;
                font-weight: 600;
                border-radius: 45px;
            }

            /*Table*/

            .data-tables {
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            .data-tables table {
                width: 100%;
                border-collapse: collapse;
                border-radius: 10px;
            }

            .data-tables table th, .data-tables table td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .data-tables table th {
                background-color: #D40008;
                color: white;

            }

            .data-tables tbody tr {
                margin-bottom: 10px;
                border: 1px solid #ddd;
                transition: 0.3s;
            }

            .data-tables tbody tr:hover {
                background-color: #ECECEC;
            }

            .data-tables tbody td a i {
                text-decoration: none;
                color: #333;
                transition: 0.5s;
            }

            .data-tables tbody td a .inner-icon-one:hover {
                font-size: 25px;
                color: #74B72E;
            }

            .data-tables tbody td a .inner-icon-two:hover {
                font-size: 25px;
                color: #D10000;
            }

            .footer {
                text-align: center;
                padding: 20px;
                background-color: #ecf0f1;
                margin-top: auto;
                box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
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

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>
