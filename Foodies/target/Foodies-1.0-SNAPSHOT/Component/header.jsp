<%-- 
    Document   : header
    Created on : Jun 17, 2024, 2:10:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js" ></script>

        <link rel="stylesheet" href="./CSS/headerstyles.css" />
    </head>
    <body>
        <style>
            .navbar-scroll .nav-link,
            .navbar-scroll .navbar-toggler-icon,
            .navbar-scroll .navbar-brand {
                color: white;
            }

            /* Color of the navbar BEFORE scroll */
            .navbar-scroll {
                background-color: greenyellow;
            }

            /* Color of the links AFTER scroll */
            .navbar-scrolled .nav-link,
            .navbar-scrolled .navbar-toggler-icon,
            .navbar-scroll .navbar-brand {
                color: white;
            }

            /* Color of the navbar AFTER scroll */
            .navbar-scrolled {
                background-color: greenyellow;
            }

            /* An optional height of the navbar AFTER scroll */
            .navbar.navbar-scroll.navbar-scrolled {
                padding-top: auto;
                padding-bottom: auto;
            }
            .navbar-brand {
                font-size: unset;
                height: 3.5rem;
            }
            .custom-size{
                font-size: 3rem;
            }

            .nav-link:hover, .navbar-brand:hover{
                transition: 0.5s;
                color: #000;
            }

            .btn-profile i {
                margin-right: 10px;
                font-size: 1.2rem;
            }

            .btn-profile:hover {
                background-color: #004d00; /* Darker Green */
            }

            .custom-search .form-control {
                border: none;
                border-radius: 20px 0 0 20px;
                box-shadow: none;
            }

            .custom-search .input-group-append .btn-search {
                background-color: #fff;
                border: none;
                border-radius: 0 20px 20px 0;
                color: #000;
                height:34.7px;
            }

            .custom-search .input-group-append .btn-search:hover {
                background-color: #e0e0e0;
            }

            .custom-search .input-group-append .btn-search i {
                font-size: 1.2em;
            }

            .nav-item{
                align-content: center;
            }

            .nav-item > p{
                align-content: center;
                margin-bottom: 0px;
            }
            .button-color{
                background-color: #bee1cf;
                color: black;
            }
        </style>
        <!-- Navbar -->
        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="nchieu"
                           />

        <sql:query var="cart" dataSource="${conn}">
            SELECT *
            FROM shopping_cart_item
            WHERE cart_id = ?
            <sql:param value="${sessionScope.userID}" />
        </sql:query>
        <c:set var="countItem" value="0" />

        <c:forEach var="product" items="${cart.rows}" >
            <c:set var="countItem" value="${countItem + 1}" />
        </c:forEach>
        <header>
            <nav class="navbar navbar-expand-lg navbar-scroll fixed-top shadow-0 bg-success">
                <div class="container">
                    <a class="navbar-brand" href="/"><i class="bi bi-cart4 custom-size"></i></a>
                    <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
                            data-mdb-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <i class="fas fa-bars"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/Cart">Cart

                                    <c:if test="${not empty countItem}">
                                        <span class="translate-middle badge rounded-pill bg-danger" style="top: 0.75rem; left: 47%;">
                                            ${countItem}
                                        </span>

                                    </c:if>
                                </a>
                            </li>
                            <%
                                String user = (String) session.getAttribute("usermail");
                                if (user != null) {
                            %>
                            <li class="nav-item">
                                <a class="nav-link" href="/Order">Order</a>
                            </li>
                            <%
                                }
                            %>
                            <li class="nav-item">
                                <form action="Home" method="get" class="form-inline ml-auto" style="margin-left: 16px;
                                      margin-right: 16px;">
                                    <div class="input-group custom-search">
                                        <input name="txtSearch" class="form-control search-form" type="search" placeholder="Search..." aria-label="Search">
                                        <div class="input-group-append">
                                            <button class="btn btn-search" type="submit">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </li>
                            <%
                                if (user != null) {
                            %>
                            <li class="nav-item">
                                <p style="margin-right:20px; z-index: 10;"><a href="/User" class="btn btn-profile ml-3" style="background-color: #ffffff;"><i class="fa fa-user"></i>${sessionScope.fullname}</a>
                                </p>
                            </li>


                            <%
                            } else {
                            %>
                            <button type="button" class="btn btn-dark ms-3 button-color" onclick="location.href = '/login';" >Sign In</button>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <!--</form>-->
                </div>
            </nav>
        </header>
        <!-- Navbar -->

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var navbar = document.querySelector('.navbar');
                var navbarCollapse = document.querySelector('#navbarSupportedContent');
                var navbarToggler = document.querySelector('.navbar-toggler');

                navbarToggler.addEventListener('click', function () {
                    navbarCollapse.classList.toggle('show');
                });

                window.addEventListener('scroll', function () {
                    if (window.scrollY > 50) {
                        navbar.classList.add('navbar-scrolled');
                    } else {
                        navbar.classList.remove('navbar-scrolled');
                    }
                });
            });
        </script>
    </body>
</html>