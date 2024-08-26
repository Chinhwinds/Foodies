<%-- 
    Document   : Cart
    Created on : Jun 30, 2024, 8:38:53 PM
    Author     : Chinhwind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My Cart</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/29d5a74656.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .btn-link {
            display: block;         /* Make the link take up the full button width */
            text-decoration: none;  /* Remove underline */
            color: inherit;         /* Inherit color from the button */
        }

        .btn-link:hover,
        .btn-link:focus {
            text-decoration: none;  /* Prevent underline on hover/focus */
            color: inherit;         /* Maintain color on hover/focus */
        }
    </style>
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
</head>
<body>

    <main>
        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://NGUYENCONGHIEU\\SQLEXPRESS:1433;databaseName=FOODIES;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="nchieu"
                           />

        <sql:query var="cart" dataSource="${conn}">
            SELECT pc.id as cart_id, pi.qty_in_stock as stock, pc.qty, p.category_id, p.name, p.description, pi.product_image, pi.price, pc.product_item_id
            FROM user_site us 
            JOIN shopping_cart c ON us.id = c.user_id
            JOIN shopping_cart_item pc ON c.id = pc.cart_id
            JOIN product p ON p.id = pc.product_item_id
            JOIN product_item pi ON p.id = pi.id
            WHERE us.username = ?
            <sql:param value="${sessionScope.fullname}" />
        </sql:query>

        <section class="h-100 h-custom" style="background-color: #d2c9ff;">

            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                            <div class="card-body p-0">
                                <div class="row g-0">
                                    <div class="col-lg-8">
                                        <div class="p-5">
                                            <div class="d-flex justify-content-between align-items-center mb-5">
                                                <h1 class="fw-bold mb-0">Shopping Cart</h1>
                                                <c:set var="countItem" value="0" />

                                                <c:forEach var="product" items="${cart.rows}" >
                                                    <c:set var="countItem" value="${countItem + 1}" />
                                                </c:forEach>

                                                <h6 class="mb-0 text-muted">${countItem} items</h6>
                                            </div>
                                            <hr class="my-4">
                                            <form action="/OrderController" method="post">
                                                <div style="max-height: 90vh; overflow-y: auto;">
                                                    <c:forEach items="${cart.rows}" var="product">
                                                        <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                            <div class="col-md-2 col-lg-2 col-xl-2">
                                                                <img
                                                                    src="/images/${product.product_image}"
                                                                    class="img-fluid rounded-3" alt="${product.description}">
                                                            </div>
                                                            <div class="col-md-3 col-lg-3 col-xl-3">
                                                                <h6 class="text-muted">${product.name}</h6>
                                                                <h6 id="description" class="mb-0">${product.description}</h6>
                                                            </div>
                                                            <div class="col-md-3 col-lg-3 col-xl-3 d-flex">

                                                                <div data-mdb-button-init data-mdb-ripple-init class="btn btn-link px-2"
                                                                     onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                                    <a href="/Cart/Decrease/${product.product_item_id}"><i class="fa-solid fa-minus"></i></a>
                                                                </div>
                                                                <input id="form1" min="0" id="numberInput" name="quantity_${product.product_item_id}" value="${product.qty}" type="number" max="${product.stock}"
                                                                       class="form-control" oninput="validateNumber()" disabled/>
                                                                <span id="error-message" style="color: red; display: none;">Number cannot be greater than ${product.stock}</span>
                                                                <script>
                                                                    function validateNumber() {
                                                                        const input = document.getElementById('numberInput');
                                                                        const errorMessage = document.getElementById('error-message');

                                                                        if (input.value > ${product.stock}) {
                                                                            errorMessage.style.display = 'inline';
                                                                            input.value = ${product.stock};
                                                                        } else {
                                                                            errorMessage.style.display = 'none';
                                                                        }
                                                                    }
                                                                </script>
                                                                <div data-mdb-button-init data-mdb-ripple-init class="btn btn-link px-2"
                                                                     onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                                    <a href="/Cart/Increase/${product.product_item_id}"><i class="fa-solid fa-plus"></i></a>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                <h6 class="mb-0">${product.price} $</h6>
                                                            </div>
                                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                <a href="/Cart/Delete/${product.cart_id}" class="text-muted"><i class="fa-solid fa-xmark"></i></a>
                                                            </div>
                                                        </div>

                                                        <hr class="my-4">
                                                    </c:forEach>
                                                </div>

                                                <div class="" >
                                                    <h6 class="mb-0" style=""><a href="/Home" class="btn btn-success"><i
                                                                class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                                                </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 bg-body-tertiary">
                                        <div class="p-5">
                                            <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                            <hr class="my-4">
                                            <!-- chua add user id -->
                                            <sql:query var="total" dataSource="${conn}">
                                                SELECT SUM(pi.price * pc.qty) AS total_price
                                                FROM user_site us 
                                                JOIN shopping_cart c ON us.id = c.user_id
                                                JOIN shopping_cart_item pc ON c.id = pc.cart_id
                                                JOIN product p ON p.id = pc.product_item_id
                                                JOIN product_item pi ON p.id = pi.id
                                            </sql:query>
                                            <div class="d-flex justify-content-between mb-4">
                                                <h5 class="text-uppercase">items ${countItem}</h5>
                                                <c:forEach items="${total.rows}" var="the_total">
                                                    <h5>${the_total.total_price} $</h5>
                                                </c:forEach>

                                            </div>

                                            <h5 class="text-uppercase mb-3">Shipping</h5>

                                            <div class="mb-4 pb-2">
                                                <select data-mdb-select-init>
                                                    <option value="1">Standard-Delivery 5$</option>
                                                    <option value="2">Fast-Delivery 7$</option>
                                                    <option value="3">Ultra-Speed 10$</option>
                                                    <option value="4">Save 3$</option>
                                                </select>
                                            </div>

                                            <h5 class="text-uppercase mb-3">Give code</h5>

                                            <div class="mb-5">
                                                <div data-mdb-input-init class="form-outline">
                                                    <input type="text" id="form3Examplea2" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Examplea2">Enter your code</label>
                                                </div>
                                            </div>

                                            <hr class="my-4">

                                            <div class="d-flex justify-content-between mb-5">
                                                <h5 class="text-uppercase">Total price</h5>
                                                <c:forEach items="${total.rows}" var="the_total">
                                                    <h5>${the_total.total_price} $</h5>
                                                </c:forEach>
                                            </div>


                                            <input type="hidden" name="userID" value="${sessionScope.userID}">
                                            <button type="submit" name="order" value="paid" data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark">
                                                Order now
                                            </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer>
    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="script.js"></script> </body>
</html>