<%-- 
    Document   : home
    Created on : Jun 17, 2024, 2:10:01 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
<%@page import="Models.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->
        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">-->
        <!--        <link rel="stylesheet" href="./CSS/headerstyles.css" />-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./CSS/homestyle.css" />
        <style>
            .product_card:hover {
                border: 2px solid #22aa00;
                box-shadow: 0 0 17px 3px #22aa00;
            }

            .product_card {
                transition: border 1s, box-shadow .5s;
            }

            .product_image {
                object-fit: cover;
                height: 175px;
            }

            .bloc_left_price {
                color: #c01508;
                text-align: center;
                font-weight: bold;
                font-size: 125%;
            }

            .bloc_left_title {
                margin-top: 10px;
                color: #2ecc71;
                text-align: center;
                font-weight: bold;
                font-size: 150%;
            }

            .category_block li:hover {
                background-color: #76a158;
            }

            .category_block li.active {
                background-color: #76a158;
                border: none;
            }


            .category_block li:hover a {
                color: #ffffff;
            }
            .category_block li a {
                color: #435536;
            }

            .add_to_cart_block .price {
                color: #c01508;
                text-align: center;
                font-weight: bold;
                font-size: 200%;
                margin-bottom: 0;
            }
            .add_to_cart_block .price_discounted {
                color: #343a40;
                text-align: center;
                text-decoration: line-through;
                font-size: 140%;
            }
            .product_rassurance {
                padding: 10px;
                margin-top: 15px;
                background: #ffffff;
                border: 1px solid #6c757d;
                color: #6c757d;
            }
            .product_rassurance .list-inline {
                margin-bottom: 0;
                text-transform: uppercase;
                text-align: center;
            }
            .product_rassurance .list-inline li:hover {
                color: #343a40;
            }
            .reviews_product .fa-star {
                color: gold;
            }
            .pagination {
                margin-top: 20px;
            }
            footer {
                background: #343a40;
                padding: 40px;
                margin-top: 20px;
            }
            footer a {
                color: #f8f9fa!important
            }

            .show_txt{
                display: inline-block;
                width: 100%;
                white-space: nowrap;
                overflow: hidden !important;
                text-overflow: ellipsis;
            }
            a .active{
                color: white;
            }

            .page-numbers {
                white-space: nowrap; /* Prevent line breaks */
            }

            .page-numbers a {
                display: inline-block; /* Display anchors inline but occupy space */
                margin-right: 5px;  /* Add some space between numbers (optional) */
                text-decoration: none; /* Remove underline from links */
            }

            .page-numbers a.active {
                color: green;
                font-weight: bold;
                border: 1px solid #6c757d;
            }

            .page-link:hover {
                border: 1px solid #22aa00;
                box-shadow: 0 0 10px 2px #22aa00;
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
            }

            .custom-search .input-group-append .btn-search:hover {
                background-color: #e0e0e0;
            }

            .custom-search .input-group-append .btn-search i {
                font-size: 1.2em;
            }

            .category-card {
                background-color: #f0f0f0;
                border-radius: 5px;
                text-align: center;
                display: inline-block;
            }

            #banner .carousel {
                height: 70vh; /* Set the height of the carousel to 25% of the viewport height */
            }

            #banner .carousel-item img {
                object-fit: cover;
                height: 70vh;
            }
            #banner{
                margin-bottom: 21px;
            }

            button {
                width: 120px; /* Set a fixed width */
                height: 40px; /* Set a fixed height */
                line-height: 20px; /* Adjust line height */
                padding: 0.8em 1.8em;
                border: 2px solid #17C3B2;
                position: relative;
                overflow: hidden;
                background-color: transparent;
                text-align: center;
                text-transform: uppercase;
                font-size: 16px;
                transition: .3s;
                z-index: 1;
                font-family: inherit;
                color: #17C3B2;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .hover{
                position: relative;
                overflow: hidden;
                background-color: #ffffff; /* White background */
                color: #14a44d; /* Text color */
                border: 2px solid #14a44d; /* Border color */
                transition: color .5s ease;
                border-radius: 5px;
            }

            .hover::before {
                content: '';
                width: 0;
                height: 350%;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%) rotate(45deg);
                background: #14a44d;
                transition: .5s ease;
                display: block;
                z-index: -1;
            }

            .hover:hover::before {
                width: 105%;
            }

            .hover:hover {
                color: #ffffff;
            }

        </style>
    </head>
    <body>

        <jsp:include page="/Component/header.jsp"/>


        <div class="container" style="margin-top: 100px;">
            <div id="banner">
                <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">

                        <c:forEach items="${listBanners}" var="b" varStatus="cnt">
                            <div class="carousel-item ${cnt.index == 0?"active":""}">
                                <img src="/banner_img/${b.image}" class="d-block w-100 " alt="...">
                            </div>
                        </c:forEach>

                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="sticky-top">
                        <div class="card category-card bg-light mb-3">
                            <div class="card-header bg-success text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                                <c:forEach items="${listCate}" var="o">
                                    <li class="list-group-item text-white ${CategoryId == o.id?"active":""}">
                                        <a class="${CategoryId == o.id?"text-white":""}" href="Home?CategoryID=${o.id}">
                                            ${o.category_name}
                                        </a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-warning text-white text-uppercase text-center" style="background-color: orange;">Best Seller</div>
                            <div class="card-body">
                                <c:forEach items="${list3MostOrdered}" var="o">
                                    <img class="img-fluid" style="object-fit: cover;
                                         border-radius: 10px;
                                         width:250px;
                                         height: 150px;" src="../images/${o.product_image}" />

                                    <c:if test="${o.qty_in_stock == 0}">
                                        <h4 class="card-title show_txt bloc_left_title"><p  title="View Product">${o.name}</p></h4>
                                        <p class="bloc_left_price">OUT OF STOCK</p>
                                    </c:if>

                                    <c:if test="${o.qty_in_stock != 0}">
                                        <h4 class="card-title show_txt bloc_left_title"><a href="Product?ProductID=${o.id}" title="View Product">${o.name}</a></h4>
                                        <p class="bloc_left_price">Price: ${o.price}$</p>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${listProduct}" var="o">
                            <c:if test="${o.qty_in_stock != 0}">
                                <div class="col-12 col-md-6 col-lg-4">
                                    <div class="card product_card" style="margin-bottom: 16px;">
                                        <img class="card-img-top product_image" src="/images/${o.product_image}" alt="/images/${o.product_image}">
                                        <div class="card-body">
                                            <h4 class="card-title show_txt"><a href="Product?ProductID=${o.id}" title="View Product">${o.name}</a></h4>
                                            <p class="card-text show_txt">In stock: ${o.qty_in_stock}</p>
                                            <p class="card-text show_txt" style="font-weight: bold;">Price: ${o.price}$</p>
                                            <div class="d-flex align-items-center gap-3">  
                                                <form action="/AddToCart" method="post" class="flex-grow-1">
                                                    <input type="hidden" name="userID" value="${sessionScope.userID}">
                                                    <input type="hidden" name="productId" value="${o.id}"> 
                                                    <input type="hidden" name="formUrl" value="${pageContext.request.requestURI}">
                                                    <button class="hover w-100 button" name="action" value="add">Add to Cart</button>
                                                </form>
                                                <form action="/AddToCart" method="post" class="flex-grow-1">
                                                    <input type="hidden" name="userID" value="${sessionScope.userID}">
                                                    <input type="hidden" name="productId" value="${o.id}">

                                                    <button type="submit" class="btn btn-success btn-block w-100" name="order" value="add">Order</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </c:if>                        
                        </c:forEach>

                        <span class="page-numbers d-flex justify-content-center">
                            <c:if test="${CategoryId == null && Search == null}">
                                <ul class="pagination">
                                    <c:if test="${tag > 1}">
                                        <li class="page-item"><a class="page-link" href="Home?page=${tag-1}"> < Previous </a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="Home?page=${i}"> ${i} </a></li>
                                        </c:forEach>
                                        <c:if test="${tag < endPage}">
                                        <li class="page-item"><a class="page-link" href="Home?page=${tag+1}"> Next > </a></li>
                                        </c:if>
                                </ul>
                            </c:if>
                            <c:if test="${CategoryId != null}">
                                <ul class="pagination">
                                    <c:if test="${tag > 1}">
                                        <li class="page-item"><a class="page-link" href="Home?CategoryID=${CategoryId}&page=${tag-1}"> < Previous </a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="Home?CategoryID=${CategoryId}&page=${i}"> ${i} </a></li>
                                        </c:forEach>
                                        <c:if test="${tag < endPage}">
                                        <li class="page-item"><a class="page-link" href="Home?CategoryID=${CategoryId}&page=${tag+1}"> Next > </a></li>
                                        </c:if>
                                </ul>
                            </c:if>
                            <c:if test="${Search != null}">
                                <ul class="pagination">
                                    <c:if test="${tag > 1}">
                                        <li class="page-item"><a class="page-link" href="Home?SearchName=${Search}&page=${tag-1}"> < Previous </a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="Home?SearchName=${Search}&page=${i}"> ${i} </a></li>
                                        </c:forEach>
                                        <c:if test="${tag < endPage}">
                                        <li class="page-item"><a class="page-link" href="Home?SearchName=${Search}&page=${tag+1}"> Next > </a></li>
                                        </c:if>
                                </ul>
                            </c:if>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/Component/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    </body>
</html>
