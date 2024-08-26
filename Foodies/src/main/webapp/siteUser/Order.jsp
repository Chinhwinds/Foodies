<%-- 
    Document   : Order
    Created on : Jun 30, 2024, 10:54:17 PM
    Author     : Chinhwind
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ORDER</title>
        <link href="/src/style/style.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const descriptions = document.querySelectorAll('#description');

                descriptions.forEach(description => {
                    const maxChars = 30; // Adjust the maximum character length as needed
                    if (description.textContent.length > maxChars) {
                        const shortDescription = description.textContent.slice(0, maxChars) + "...";
                        description.textContent = shortDescription;
                    }
                });
            });
        </script>
        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="nchieu"
                           />


        <sql:query var="id" dataSource="${conn}">
            SELECT *
            FROM [shop_order] o
            WHERE user_id = ?
            <sql:param value="${sessionScope.userID}" />
        </sql:query>

        <section class="h-100 gradient-custom">
            <div class="container py-5 h-100">

                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-10 col-xl-8">
                        <div class="card" style="border-radius: 10px;">
                            <div class="card-header px-4 py-5">
                                <h5 class="text-muted mb-0">Thanks for your Order, <span style="color: #a8729a;"> ${sessionScope.fullname}</span>!</h5>
                            </div>                 
                            <div class="card-body p-4"> 
                                <c:forEach var="listOrder" items="${id.rows}">
                                    <div style="max-height: 80vh; overflow-y: auto; margin-bottom: 2rem;">
                                        <sql:query var="order" dataSource="${conn}">
                                            SELECT oi.id, p.name, pi.product_image, p.description, oi.qty, pi.price, o.order_date, o.order_total, os.status
                                            FROM [shop_order] o
                                            JOIN order_line oi ON o.id = oi.order_id
                                            JOIN product p ON oi.product_item_id = p.id
                                            JOIN product_item pi ON p.id = pi.id
                                            JOIN order_status os ON o.order_status_id = os.id
                                            WHERE o.id = ?
                                            <sql:param value="${listOrder.id}" />
                                        </sql:query>


                                        <c:forEach var="order_item" items="${order.rows}">

                                            <div class="card shadow-0 border mb-4">
                                                <div class="card-body">
                                                    <div class="row">

                                                        <div class="col-md-3">
                                                            <img src="/images/${order_item.product_image}"
                                                                 class="img-fluid" alt="Phone">
                                                        </div>
                                                        <div class="col-md-3 text-center d-flex justify-content-center align-items-center">
                                                            <p class="text-muted mb-0">${order_item.name}</p>
                                                        </div>
                                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                            <p id="description" class="text-muted mb-0 small">${order_item.description}</p>
                                                        </div>
                                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                            <p class="text-muted mb-0 small">Qty: ${order_item.qty}</p>
                                                        </div>
                                                        <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                            <p class="text-muted mb-0 small">${order_item.price} $</p>
                                                        </div>
                                                    </div>

                                                    <hr class="mb-4" style="background-color: #e0e0e0; opacity: 1;">
                                                    <div class="row d-flex align-items-center">
                                                        <div class="col-md-2">
                                                            <p class="text-muted mb-0 small">Track Order</p>
                                                        </div>
                                                        <div class="col-md-10">
                                                            <div class="progress" style="height: 6px; border-radius: 16px;">
                                                                <div class="progress-bar" role="progressbar"
                                                                     style="width: 65%; border-radius: 16px; background-color: #a8729a;" aria-valuenow="65"
                                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                            <div class="d-flex justify-content-around mb-1">
                                                                <p class="text-muted mt-1 mb-0 small ms-xl-5">Out for delivary</p>
                                                                <p class="text-muted mt-1 mb-0 small ms-xl-5">Delivered</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <sql:query var="detail" dataSource="${conn}">
                                            SELECT o.id, o.order_date, o.order_total, os.status
                                            FROM [shop_order] o 
                                            JOIN order_status os ON o.order_status_id = os.id
                                            WHERE o.id = ?
                                            <sql:param value="${listOrder.id}" />
                                        </sql:query>

                                        <c:forEach var="info" items="${detail.rows}">
                                            <div class="d-flex justify-content-between pt-2 summary-1">
                                                <p class="fw-bold mb-0">Order Details</p>
                                                <p class="text-muted mb-0"><span class="fw-bold me-4">Total: </span> ${info.order_total} $</p>
                                            </div>

                                            <div class="d-flex justify-content-between summary-2">
                                                <p class="text-muted mb-0">Invoice Date : ${info.order_date}</p>
                                                <p class="text-muted mb-0"><span class="fw-bold me-4">Status:</span> ${info.status}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:forEach>



                            </div>
                            <!--                            <div class="card-footer border-0 px-4 py-5"
                                                             style="background-color: #a8729a; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;">
                                                            <h5 class="d-flex align-items-center justify-content-end text-white text-uppercase mb-0">Total
                                                                paid: <span class="h2 mb-0 ms-2">$1040</span></h5>
                                                        </div>-->
                        </div>

                    </div>
                    <div class="" >
                        <h6 style="margin-left: 14vw; margin-top: 1.5rem;"><a href="/Home" class="btn btn-success"><i
                                    class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                    </div>
                </div>

            </div>

        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
        <style>
            .summary-1,
            .summary-2{
                background-color: #e0e0e0;
                padding: 0px 10px;
                border-radius: 20px 20px 0px 0px;
            }
            .summary-1{
                border-radius: 10px 10px 0px 0px;
            }
            .summary-2{
                border-radius: 0px 0px 10px 10px;
            }
            
        </style>
    </body>
</html>
