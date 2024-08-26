<%-- 
    Document   : adminProEdit
    Created on : Jul 5, 2024, 12:52:42 PM
    Author     : asus1
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AdminCategoryDAO"%>
<%@page import="Models.AdminProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/adminProEdit.css"/>
    </head>

    <body>

        <%
            AdminProduct obj = (AdminProduct) session.getAttribute("edit");
        %>

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
                            <a href="/admin">Home</a> / <a href="/adminDashboard">Dashboard</a> / <a
                                href="/admin/adminProduct">Products</a>
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
                                    <h2>Edit Product</h2>
                                </div>

                                <div class="inner-body">
                                    <form method="post">
                                        <div class="form-group row">
                                            <label for="proID" class="col-sm-2 col-form-label">Product ID</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control-plaintext" id="proID" name="txtID"
                                                       readonly value="<%= obj.getProID()%>"/> 
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="proName" class="col-sm-2 col-form-label">Product Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="proName" name="txtName" required="" value="<%= obj.getProName()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="proDes" class="col-sm-2 col-form-label">Product Description</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="proDes" name="txtDes"  required="" value="<%= obj.getProDes()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="proPic" class="col-sm-2 col-form-label">Product Picture</label>
                                            <div class="col-sm-10">
                                                <input type="file" class="form-control" id="proPic" name="txtProPic"/>
                                                <img src="/images/<%= obj.getProPic()%>" alt="Product Image " style="width: 100px; height: 80px; object-fit: cover"/>
                                            </div>
                                        </div> 

                                        <div class="form-group row">
                                            <label for="catName" class="col-sm-2 col-form-label">Category Name</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" name="txtCatID">
                                                    <%
                                                        AdminProduct daos = (AdminProduct) (session.getAttribute("edit"));
                                                        AdminCategoryDAO dao = new AdminCategoryDAO();
                                                        ResultSet rs = dao.getAllCate();
                                                        while (rs.next()) {
                                                            int catID = rs.getInt("id");
                                                            String catName = rs.getString("category_name");
                                                            boolean isSelected = (catID == daos.getCatID());
                                                    %>   
                                                    <option value="<%= catID%>" <%= isSelected ? "selected" : ""%>><%= catName%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>    

                                        <div class="form-group row">
                                            <label for="proQuan" class="col-sm-2 col-form-label">Product Quantity</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="proQuan" name="txtQuan"  required="" value="<%= obj.getItemQuan()%>">
                                            </div>
                                        </div>  

                                        <p class="message-notify" style="color:red;">${requestScope.Error}</p>

                                        <div class="form-group row">
                                            <label for="proPrice" class="col-sm-2 col-form-label">Product Price</label>
                                            <div class="col-sm-10">
                                                <input type="number" class="form-control" id="proPrice" name="txtPrice" required="" value="<%= obj.getItemPrice()%>">
                                            </div>
                                        </div>  

                                        <p class="message-notify" style="color:red;">${requestScope.Errors}</p>


                                        <div class="form-group row">
                                            <label for="proItemPict" class="col-sm-2 col-form-label">Product Item Image</label>
                                            <div class="col-sm-10">
                                                <input type="file" class="form-control" id="proItemPict" name="txtItemPic" >
                                                <img src="/images/<%= obj.getItemPic()%>" alt="Product Item Image " style="width: 100px; height: 80px; object-fit: cover"/>
                                            </div>
                                        </div>  



                                        <div class="col-xl-12 text-center">
                                            <button type="submit" class="button" name="btnProEdit">Edit</button>
                                        </div>

                                        <p class="message-notify" style="color:red;">${requestScope.error}</p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <style>
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
            }

            .main-content {
                margin-left: 170px;
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