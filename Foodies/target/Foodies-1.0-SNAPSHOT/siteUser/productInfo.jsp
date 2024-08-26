<%-- 
    Document   : productInfo
    Created on : Jul 5, 2024, 8:16:41 PM
    Author     : phuct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${product.name}</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <!--        <link rel="stylesheet" href="./CSS/headerstyles.css" />-->
        <link rel="stylesheet" href="./CSS/homestyle.css" />

        <style>
            .product_card:hover {
                border: 2px solid #22aa00;
                box-shadow: 0 0 10px 2px #22aa00;
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
                font-size: 150%;
            }
            .category_block li:hover {
                background-color: #007bff;
            }
            .category_block li:hover a {
                color: #ffffff;
            }
            .category_block li a {
                color: #343a40;
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
            }

            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/Component/header.jsp"/>
        <div class="container" style="margin-top: 120px;
             margin-bottom: 200px;">
            <div class="card">
                <div class="row">
                    <aside class="col-sm-5 border-right">
                        <article class="gallery-wrap"> 
                            <div class="img-big-wrap">
                                <div><img src="../images/${product.product_image}"></div>
                            </div> <!-- slider-product.// -->
                            <div class="img-small-wrap">
                            </div> <!-- slider-nav.// -->
                        </article> <!-- gallery-wrap .end// -->
                    </aside>
                    <aside class="col-sm-7">
                        <article class="card-body p-5">
                            <h3 class="title mb-3">${product.name}</h3>

                            <p class="price-detail-wrap"> 
                                <span class="price h3 text-warning"> 
                                    <span class="currency">US </span><span class="num">${product.price}$</span>
                                </span> 
                            </p> <!-- price-detail-wrap .// -->
                            <dl class="item-property">
                                <dt>Description</dt>
                                <dd><p>
                                        ${product.description}
                                    </p></dd>
                            </dl>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <dl class="param param-inline">
                                        <dt>In Stock: ${product.qty_in_stock}</dt>   
                                    </dl>  <!-- item-property .// -->
                                </div> <!-- col.// -->

                            </div> <!-- row.// -->
                            <hr>
                            <div class="d-flex align-items-center gap-3">  
                                <form action="/AddToCart" method="post" class="flex-grow-1">
                                    <input type="hidden" name="userID" value="${sessionScope.userID}">
                                    <input type="hidden" name="productId" value="${product.id}"> 
                                    <input type="hidden" name="formUrl" value="${pageContext.request.requestURI}">
                                    <button class="btn btn btn-outline-success btn-block" 
                                            ${product.qty_in_stock == 0 ? "disabled":""}
                                            name="action" value="add">Add to Cart</button>
                                </form>
                                <form action="/AddToCart" method="post" class="flex-grow-1">
                                    <input type="hidden" name="userID" value="${sessionScope.userID}">
                                    <input type="hidden" name="productId" value="${product.id}">

                                    <button type="submit" class="btn btn-success btn-block w-100"
                                            ${product.qty_in_stock == 0 ? "disabled":""}
                                            name="order" value="add">Order</button>
                                </form>
                            </div>
                        </article> <!-- card-body.// -->
                    </aside> <!-- col.// -->
                </div> <!-- row.// -->
            </div> <!-- card.// -->
        </div>
        <jsp:include page="/Component/footer.jsp"/>
    </body>
</html>
