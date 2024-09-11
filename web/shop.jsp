<%-- 
    Document   : shop
    Created on : Oct 18, 2023, 1:04:13 PM
    Author     : ngoba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Hola Cafeteria</title>
        <link rel="icon" href="img/simple-house-logo.png"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400" rel="stylesheet" />    
        <link href="css/templatemo-style.css" rel="stylesheet" />
        <link href="css/all.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <!-- Top box -->
            <!-- Logo & Site Name -->
            <div class="placeholder">
                <div class="parallax-window" data-parallax="scroll" data-image-src="img/360_F_652123212_nGtoAqRmPtLHyPaAnrbNz6Hxttm0cmAN.jpg">
                    <div class="tm-header">
                        <div class="row tm-header-inner">
                            <div class="col-md-6 col-12">
                                <img src="img/simple-house-logo.png" alt="Logo" class="tm-site-logo" /> 
                                <div class="tm-site-text-box">
                                    <h1 class="tm-site-title">Hola Cafeteria</h1>
                                    <h6 class="tm-site-description">Best for students in HOLA</h6>	
                                </div>
                            </div>

                            <nav class="col-md-8 col-12 tm-nav">
                                <ul class="tm-nav-ul">
                                    <c:set var="a" value="${sessionScope.account}"/>
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "" : "home"}" class="tm-nav-link  ">${a.role == 1 && a.id!=null ? "" : "Home"}</a></li>
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "manager" : "shop"} " class="tm-nav-link active ">${a.role == 1 && a.id!=null ? "Manager" : "Shop"}</a></li>
                                        <c:set var="size" value="${requestScope.size}"/>
                                    <li class="tm-nav-li"><a href="${a.role == 1 ? "revenue" : "cart"}" class="tm-nav-link ">${a.role == 1 && a.id!=null ? "Revenue statistics" : "Cart"} <span style="font-size: 12px">(${size})</span></a></li>
                                    <li class="tm-nav-li"><a href="${a.id == null ? "login.jsp" : "profile"} " class="tm-nav-link">${a.id == null ? "Login":"Profile"}</a></li>
                                    <li class="tm-nav-li"><a href="logout" class="tm-nav-link">${a.id == null ? "" : "Log Out"}</a></li>
                                </ul>
                            </nav>	
                        </div>
                    </div>
                </div>
            </div>
            <main>

                <div class="tm-paging-links">
                    <nav>
                        <ul>

                            <c:forEach items="${requestScope.categories}" var="c">
                                <li style="border-radius:5px"    class="tm-paging-item cate-item" > <a style="padding: 10px 30px;
                                                                                                       display: flex;
                                                                                                       align-items: center;
                                                                                                       justify-content: center;
                                                                                                       text-decoration: none;
                                                                                                       color: white;
                                                                                                       font-weight: bold;
                                                                                                       background-color: #2F956D;" href="shop?cid=${c.id}" class="btn-link active">${c.name}</a></li>

                            </c:forEach>
                        </ul>
                    </nav>
                </div>

                <c:set var="o_cid" value="${requestScope.s_cid}"/>
                <c:set var="o_price" value="${requestScope.s_price}"/>
                <c:set var="o_keyword" value="${requestScope.s_keyword}"/>
                <div class="tm-container-inner tm-contact-section form-container">
                    <form action="shop">
                        <div class="select-row">
                            <select name="cid" class="searchSelect" >
                                <option value="0" ${o_cid ==0?"selected":""}>Category</option>
                                <c:forEach items="${requestScope.categories}" var="cate">
                                    <option value="${cate.id}" ${o_cid.equals(cate.id)?"selected":""}>${cate.name}</option>
                                </c:forEach>
                            </select>

                            <select name="price" class="searchSelect">
                                <option value="" selected disabled>Price</option>
                                <option value="1" ${o_price == 1 ?"selected":""}>10000→30000</option>
                                <option value="2" ${o_price == 2 ?"selected":""}>30000→50000</option>
                                <option value="3" ${o_price == 3 ?"selected":""}>50000↑</option>
                            </select>
                            <input  name="keyword" type="text" class="form-control searchInput" placeholder="Search by name" />
                            <input type="submit" class="tm-btn tm-btn-success tm-btn searchBtn" value="Search">
                        </div>
                        <div class="button-row">
                        </div>
                    </form>
                </div>
                <!--sortby-->
                <c:set var="o_sortby" value="${s_sortby}"/>
                <div style="justify-content: flex-end; margin-right: 6rem;" class="row">
                    <div class="col-lg-2">
                        <form action="shop" class="tm-contact-form" id="sortForm">
                            <input type="hidden" name="cid" value="${o_cid}">
                            <input type="hidden" name="price" value="${o_price}">
                            <input type="hidden" name="keyword" value="${o_keyword}">
                            <div class="tm-d-flex" >
                                <select class="select" name="sortby" onchange="change()"> 
                                    <option value="feature" ${o_sortby.equals("feature")?"selected":""}>Featured</option> 
                                    <option value="new" ${o_sortby.equals("new")?"selected":""}>Newest</option>
                                    <option value="pHL" ${o_sortby.equals("pHL")?"selected":""}>Price: High-Low</option>
                                    <option value="pLH" ${o_sortby.equals("pLH")?"selected":""}>Price: Low-High</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>

                <!--product-->
                <div class="row tm-gallery">
                    <div style="justify-content:space-around;" id="tm-gallery-page-pizza" class="tm-gallery-page">
                        <c:forEach items="${requestScope.products}" var="n">
                            <form action="buy" method="post" id="myform">
                                <article class="col-lg-3 col-md-4 col-sm-6 col-3 tm-gallery-item card" >
                                    <figure><a cursor-p href="detail?id=${n.id}&?cid=${n.cid}">
                                            <img src="img/${n.image}" height="250px" width="250px" alt="Image" class=" tm-gallery-img" /></a>
                                        <figcaption class="card-body"> 
                                            <h4 class="tm-gallery-title name">${n.name}</h4>
                                            <div class="price-quantity"">
                                                <div class="price">
                                                    <span class="tm-gallery-price product-price">${n.price} đ</span>
                                                </div>
                                                <div class="quantity">
                                                    <input type="number" name="num" value="" id="qty${n.id}" class="form-control" placeholder="Quantity" required="" min="0" />
                                                    <input type="hidden" name="id" value="${n.id}"/>
                                                </div>
                                            </div>
                                            <div class="form-group tm-d-flex">
                                                <div class="button-group">
                                                    <button type="button" class="btn btn-secondary"  onclick="addToCart(${n.id})">
                                                        Add to cart <i class="fas fa-shopping-cart"></i> 
                                                    </button>
                                                    <input class="btn btn-primary" type="submit" value="Buy now!">
                                                </div>
                                            </div>
                                        </figcaption>
                                    </figure>
                                </article>
                            </form>
                        </c:forEach>   
                    </div> 
                </div>

                <!--paging-->
                <div class="pagination">
                    <div class="page-link">
                        <a href="shop?cid=${o_cid}&price=${o_price}&keyword=${o_keyword}&sortby=${o_sortby}&index=${1}">Home</a>
                    </div>
                    <div class="page-link">
                        <a href="shop?cid=${o_cid}&price=${o_price}&keyword=${o_keyword}&sortby=${o_sortby}&index=${index - 1 > 1?index-1:1}">Pre</a>
                    </div>
                    <c:forEach begin="${index - 2 > 1? index - 2 : 1}" end="${index + 2 < endP? index + 2: endP}" var="i">
                        <div class="page-link ${i == index ? "page-active":""}">

                            <a href="shop?cid=${o_cid}&price=${o_price}&keyword=${o_keyword}&sortby=${o_sortby}&index=${i}">${i}</a>
                        </div>
                    </c:forEach>
                    <div class="page-link">

                        <a href="shop?cid=${o_cid}&price=${o_price}&keyword=${o_keyword}&sortby=${o_sortby}&index=${index+1 < endP?index+1:endP}">Next</a>
                    </div>
                    <div class="page-link">

                        <a href="shop?cid=${o_cid}&price=${o_price}&keyword=${o_keyword}&sortby=${o_sortby}&index=${endP}">End</a>
                    </div>
                </div>

                <style>
                    .page-link {
                        padding: 0.375rem 0.75rem;
                    }
                    .pagination {
                        display: flex;
                        padding-left: 0;
                        list-style: none;
                        justify-content: center;
                        margin: 4rem;
                    }
                    .page-link{
                        background: #F0F0F0;
                        padding: 4px 8px;
                        margin: 0 8px;
                        border: 2px solid black;
                        border-radius: 6px;
                    }
                    .page-link a {
                        text-decoration: none;
                        color: black;
                    }

                    .page-link:hover{
                        box-shadow: 0 2px 8px 0 rgba(0,0,0,0.3);
                        background-color: #A7BEAE;
                    }
                    .page-active {
                        background-color: #319966;
                    }
                    .page-active a{
                        color:white;
                    }
                    .page-item.disabled .page-link {

                        pointer-events: none;
                        color: #319966;
                        background-color: transparent;
                        border-color: #dee2e6;
                    }
                    .pagination-lg .page-item:first-child .page-link {
                        border-top-left-radius: 0.3rem;
                        border-bottom-left-radius: 0.3rem;
                    }
                    .pagination-lg .page-item:last-child .page-link {
                        border-top-right-radius: 0.3rem;
                        border-bottom-right-radius: 0.3rem;
                    }
                    .tm-contact-link{
                        margin-right:16px;
                    }
                    .tm-contact-icon{
                        margin-right:8px;
                    }
                    .select{
                        padding: 12px 16px;
                        background-color: #f2f2f2;
                        border: 2px solid #333;
                        border-radius: 4px;
                        font-size: 16px;

                    }
                    .select:focus {
                        outline: none;
                        border-color: #007bff;
                    }
                    .select option {
                        padding: 12px 16px;
                    }
                    .select option:hover {
                        background: #f2f2f2;
                    }

                    .select option:focus {
                        background: #007bff;
                        color: #fff;
                    }

                    .tm-paging-links{
                        margin: 4rem;
                    }

                    /*                    card-item*/
                    .card {
                        box-shadow: 0 8px 20px 0 rgba(0,0,0,0.4);
                        transition: 0.3s;
                        border-radius: 5px;
                        margin: 2rem;
                    }

                    .card:hover {
                        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
                    }

                    .card img {
                        border-radius: 5px 5px 0 0;
                        margin-bottom: 0px;
                        margin-top: 12px;
                    }

                    .card-body {
                        padding: 10px;
                        margin-top: 10px;
                        text-align: center;
                    }

                    .card-body h4 {
                        font-size: 1.2em;
                        font-weight: bold;
                    }

                    .btn {
                        border-radius: 8px;
                        box-shadow: 0 2px 4px rgba(0,0,0,0.2);
                        padding: 10px 8px;
                        transition: background 0.3s ease;
                    }

                    .btn:hover {
                        background: #A7BEAE;
                    }

                    .btn:active {
                        background: #0062cc;
                        box-shadow: inset 0 3px 5px rgba(0,0,0,0.125);
                    }


                    .btn-primary {
                        background: #319966;
                        color: #fff;
                    }

                    .btn-secondary {
                        background: #F0F0F0;
                        color: black;
                    }
                    .button-group {
                        display: flex;
                        justify-content: space-between;
                        margin-top: 10px;
                    }

                    .button-group .btn {
                        width: 45%;
                    }

                    .price-quantity {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }

                    .product-price {
                        font-weight: bold;
                        margin: auto;
                    }
                    .price, .quantity {
                        flex: 1;
                    }
                    .quantity {
                        border: 1px solid #ccc;
                        border-radius: 4px;
                        padding: 8px;
                        margin: auto;
                    }
                    .price {
                        display: flex;
                        justify-content: center;
                    }
                    .quantity {
                        display: flex;
                        justify-content: center;
                    }

                    .searchSelect:hover,
                    .searchInput:hover,
                    .searchBtn:hover {
                        box-shadow: 0 4px 12px 0 rgba(0,0,0,0.4);
                    }
                    .tm-btn {
                        transition: background 0.3s;
                        border-radius: 5px;
                        font-weight: bold;
                    }

                    .tm-btn:hover {
                        background: #666;
                    }
                    .form-container {
                        display: flex;
                        flex-direction: column;
                        margin:2rem 10rem;
                        padding:0;

                    }

                    .select-row {
                        display: flex;
                        justify-content: space-between;
                        width: 100%;

                    }


                    .button-row {
                        display: flex;
                        margin-top: 10px;
                        width: 400px;
                        justify-content: center;
                    }

                    .searchSelect {
                        width: 150px; /* Điều chỉnh chiều rộng của phần select theo ý muốn của bạn */
                        padding: 10px;
                        border: 2px solid #ccc;
                        border-radius: 5px;
                        background-color: #fff;
                        font-size: 16px;
                        color: #333;
                    }

                    /* Tùy chỉnh phần option */
                    .searchSelect option {
                        font-size: 16px;
                        background-color: #fff;
                        color: #333;
                    }

                    .searchInput{
                        width: 45%;
                        border: 2px solid #ccc;
                        border-radius: 5px;
                    }
                    .cate-item:hover{
                        box-shadow: 0 4px 12px 0 rgba(0,0,0,0.4);
                    }
                    .float-end {
                        float: right !important;
                    }
                </style>
            </main>



            <div class="tm-container-inner tm-featured-image">
                <div class="row">
                    <div class="col-12">
                        <div class="placeholder-2">
                            <div class="parallax-window-2" data-parallax="scroll" data-image-src="img/simple-house-01.jpg"></div>		
                        </div>
                    </div>
                </div>
            </div>
            <footer class="tm-footer">
                <div class="container-fluid">
                    <h1 class="text-center">Contact Address</h1>
                    <hr>
                    <div class="row">

                        <div class="col-md-6">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d59584.16344816163!2d105.55262083359368!3d21.03227725056066!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBGUFQ!5e0!3m2!1svi!2s!4v1665770215510!5m2!1svi!2s" width="100%" height="320" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>

                        <div class="col-md-6" id="contact2">
                            <h3>Our Address</h3>
                            <hr align="left" width="50%">
                            <address>
                                Somewhere in HOLA
                            </address>
                            <a href="tel:0971364203" class="tm-contact-link">
                                <i class="fas fa-phone tm-contact-icon"></i><span>0971742946    </span>
                            </a><br>
                            <a href="mailto:nbdung0000@gmail.com" class="tm-contact-link">
                                <i class="fas fa-envelope tm-contact-icon"></i><span>phuongnmhe186354@fpt.edu.vn</span>
                            </a>
                            <div class="tm-contact-social">
                                <a href="https://www.facebook.com/nbdung.nepo" class="tm-social-link"><i class="fab fa-facebook tm-social-icon"></i></a>
                                <a href="https://www.instagram.com/nbdung00/" class="tm-social-link"><i class="fab fa-instagram tm-social-icon"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/parallax.min.js"></script>
        <script>
                                                        $(document).ready(function () {
                                                            // Handle click on paging links
                                                            $('.tm-paging-link').click(function (e) {
                                                                e.preventDefault();

                                                                var page = $(this).text().toLowerCase();
                                                                $('.tm-gallery-page').addClass('hidden');
                                                                $('#tm-gallery-page-' + page).removeClass('hidden');
                                                                $('.tm-paging-link').removeClass('active');
                                                                $(this).addClass("active");
                                                            });
                                                        });
                                                        function buy(id) {
                                                            document.f.action = "buy?id=" + id;
                                                            document.f.submit();
                                                            //                                                          document.getElementById('myform').submit();
                                                        }
                                                        function change() {
                                                            document.getElementById("sortForm").submit();

                                                        }
                                                        function getCookie(cname) {
                                                            var name = cname + "=";
                                                            var ca = document.cookie.split(';');
                                                            for (var i = 0; i < ca.length; i++) {
                                                                var c = ca[i];
                                                                while (c.charAt(0) === ' ')
                                                                    c = c.substring(1);
                                                                if (c.indexOf(name) === 0)
                                                                    return c.substring(name.length, c.length);
                                                            }
                                                            return "";
                                                        }
                                                     function addToCart(id) {
//                                                            var id = document.querySelector("input[name='id']").value;
                                                                var qty = document.getElementById("qty" + id).value;
                                                                    var cart = getCookie("cart");
                                                                    qty = qty && parseInt(qty) || 0;
                                                                if (qty === null) {
                                                                    alert("Select quantity before Add to Cart");
                                                                } else if(qty===0){
                                                                    alert("Quantity must be greated than 0");
                                                                }
                                                                else if(qty>=1&&qty<=10){
                                                                    var newProduct = id + ":" + qty;
                                                                    if (cart === "") {
                                                                        cart = newProduct;
                                                                    } else {
                                                                        cart = cart + "-" + newProduct;
                                                                    }
                                                                    document.cookie = "cart=" + cart;
                                                                    window.window.alert("Add to cart successfully");
                                                                }
                                                                else if(qty>=11){
                                                                    alert("Quantity must be smaller than 11");
                                                                }
                                                            }


        </script>                        
    </body>
</html>
