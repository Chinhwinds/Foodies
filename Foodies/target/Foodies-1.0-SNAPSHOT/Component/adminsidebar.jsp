<%-- 
    Document   : adminsidebar
    Created on : Jul 9, 2024, 3:26:04 PM
    Author     : asus1
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/sidebar.css" />
    </head>

    <body>
        <!--<div class="containers">-->
        <!-- Sidebar -->
        <div class="inner-sidebar">
            <aside class="sidebar">
                <div class="logo">
                    <h4>Admin ${sessionScope.adfullname}</h4>
                </div>
                <nav>
                    <ul class="sidebar-list">
                        <li class="slidebar-item-list">
                            <a href="/adminDashboard" class="slidebar-link">
                                <i class="fa-solid fa-table-columns icon" title="Dashboard"></i>
                                <span class="title-icon">Dashboard</span>
                            </a>

                        </li>
                        <li class="slidebar-item-list">
                            <a href="/adminUser" class="slidebar-link">
                                <i class="fa-regular fa-circle-user icon" title="Users"></i>
                                <span class="title-icon">Users</span>
                            </a>

                        </li>
                        <li class="slidebar-item-list">
                            <a href="/admin/adminProduct" class="slidebar-link">
                                <i class="fa-solid fa-boxes-packing icon" title="Products"></i>
                                <span class="title-icon">Products</span>
                            </a>

                        </li>
                        <li class="slidebar-item-list">
                            <a href="/admin/adminReport" class="slidebar-link">
                                <i class="fa-regular fa-folder-open icon" title="Order Reports"></i>
                                <span class="title-icon">Order Reports</span>
                            </a>
                        </li>
                        <li class="slidebar-item-list">
                            <a href="/adminlogout" class="slidebar-link">
                                <i class="fa-solid fa-arrow-right-from-bracket icon" title="Logout"></i>
                                <span class="title-icon">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>
        </div>
        <!--</div>-->
        <style>
            .containers {
                display: flex;
                height: 100%;
                flex-direction: row;
            }

            * {
                padding: 0;
                margin: 0;
            }

            html {
                height: 100%;
                width: 100%;
                position: relative;
            }

            .inner-main-body {
                position: absolute;
                width: 100%;
                height: 100%;
            }

            .inner-sidebar {
                background: #2c3e50;
                position: fixed;
            }


            .slidebar-item-list:last-child {
                position: absolute;
                bottom: 0;
            }

            .slidebar-item-list {
                position: relative;
            }

            .title-icon {
                position: absolute;
                top: 0;
                left: 45px;
                transition: all ease-in 0.2s;
            }

            .slidebar-item-list:hover {
                width: 100%;
            }

            .slidebar-item-list:hover>.title-icon {
                display: block;
                transition: all ease-in 0.2s;
            }

            .sidebar .icon {
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
                width: 170px;
                background-color: #2c3e50;
                color: white;
                display: flex;
                flex-direction: column;
                padding: 20px;
                top: 0;
                bottom: 0;
                position: fixed;
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
                width: 90%;
                padding: 20px;
                display: flex;
                flex-direction: column;
                margin-left: 170px;
            }

        </style>
    </body>
</html>


