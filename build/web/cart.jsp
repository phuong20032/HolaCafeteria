<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Cart</title>
        <link rel="icon" href="img/simple-house-logo.png"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet" />
        <link href="css/templatemo-style.css" rel="stylesheet" />
        <link href="css/all.min.css" rel="stylesheet" />
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
    </head>


    <body> 
        <script >
            CKEDITOR.replace('edit');
        </script>


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
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "manager" : "shop"} " class="tm-nav-link  ">${a.role == 1 && a.id!=null ? "Manager" : "Shop"}</a></li>
                                        <c:set var="size" value="${requestScope.size}"/>
                                    <li class="tm-nav-li"><a href="${a.role == 1 ? "revenue" : "cart"}" class="tm-nav-link active">${a.role == 1 && a.id!=null ? "Revenue statistics" : "Cart"} <span style="font-size: 12px">(${size})</span></a></li>
                                    <li class="tm-nav-li"><a href="${a.id == null ? "login.jsp" : "profile"} " class="tm-nav-link">${a.id == null ? "Login":"Profile"}</a></li>
                                    <li class="tm-nav-li"><a href="logout" class="tm-nav-link">${a.id == null ? "" : "Log Out"}</a></li>
                                </ul>
                            </nav>	
                        </div>
                    </div>
                </div>
            </div>

            <main> 
                <div class="container px-3 my-5 clearfix">
                    <!-- Shopping cart table -->
                    <div class="card">
                        <div class="card-header">
                            <h2>Shopping Cart</h2>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered m-0">
                                    <thead>
                                        <tr>
                                            <!-- Set columns width -->
                                            <th class="text-center py-3 px-4" style="width: 40px;" ></th>
                                            <th class="text-center py-3 px-4" style="min-width: 400px;">Product Name </th>
                                            <th class="text-right py-3 px-4" style="width: 100px;">Price</th>
                                            <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
                                            <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
                                            <th class="text-center align-middle py-3 px-0" style="width: 40px;"><a href="#" class="shop-tooltip float-none text-light" title="" data-original-title="Clear cart"><i class="ino ion-md-trash"></i></a></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="o" value="${requestScope.cart}" />
                                        <c:set var="sum" value="0"/>
                                        <c:forEach  items="${o.items}" var="i" >

                                            <tr>
                                                <td><input type="checkbox" name="checkBuy" value="${i.product.id}" ${requestScope.buyNow.equals(i.product.id)?"checked":""}></td>
                                                <td class="p-4">
                                                    <div class="media align-items-center product_name">
                                                        <img src="img/${i.product.image}" height="20%" width="20%"   alt="">
                                                        <div class="media-body">
                                                            <a href="detail?id=${i.product.id}" class="d-block text-dark">${i.product.name}</a>
                                                        </div>
                                                    </div>
                                                </td>



                                                <td class="align-middle p-4"> <button>${i.product.price} </button> </td>
                                                <td class="align-middle p-4">
                                                    <div class="p_quantity">
                                                        <a href="process?num=-1&id=${i.product.id}"><button>-</button></a>
                                                        <span>${i.quantity}</span>
                                                        <a <c:if test="${i.quantity >= 30}" >onclick="wait()" </c:if> href="process?num=1&id=${i.product.id}"><button>+</button></a>
                                                        <c:if test="${i.quantity >= 30}" > <c:set var="highQuantity" value="${i.quantity}"/> </c:if> 
                                                        </div>


                                                    </td>
                                                    <td  class="text-right font-weight-semibold align-middle p-4" >${i.quantity * i.product.price}đ  </td>
                                                <c:set var="total" value="${ i.quantity * i.product.price}" />
                                                <c:set var="sum" value="${total+sum}"/>
                                                <td>


                                                    <form action="process" method="post">
                                                        <input type="hidden" name="id" value="${i.product.id}">
                                                        <input class="shop-tooltip close float-none text-danger" data-original-title="Remove" type="submit" value="×">
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>



                                    </tbody>
                                </table>
                            </div>
                            <!-- / Shopping cart table -->

                            <div class="flex-cart d-flex flex-wrap justify-content-between align-items-center pb-4">
                                <div class="info-cart">
                                    <div class="form-group">
                                        <label class="text-muted font-weight-normal m-0">Name</label>
                                        <input type="text" name="address" class="form-control" value="${sessionScope.account.fullname}"   required="" />
                                    </div>
                                    <div class="form-group">
                                        <label class="text-muted font-weight-normal m-0">Phone Number</label>
                                        <input type="number" name="address" class="form-control" value="${sessionScope.account.phonenum}"   required="" />
                                    </div>  
                                </div>


                                <div class="text-right mt-4 total_price">
                                    <label class="text-muted font-weight-normal m-0">Total price</label>
                                    <div class="text-large"><strong>${sum}đ</strong></div>
                                </div>

                            </div>
                            <div class="other-option">
                                <div class="tm-faq place_eat" style="margin-left: 0px">
                                    <div class="d-flex flex-wrap justify-content-between align-items-center pb-4" >

                                        <button class="accordion" >Choose place to eat</button>
                                        <div class="panel"> 
                                            <div class="form-group">
                                                <input type="radio" name="placeToEat" value="1" checked/>
                                                <label class="text-muted font-weight-normal m-0">Eat in site</label>
                                            </div> 
                                            <div class="form-group">
                                                <input type="radio" name="placeToEat" value="2" />
                                                <label class="text-muted font-weight-normal m-0">Ship to: </label>
                                                <input type="text" name="address" class="form-control" value="${sessionScope.account.address}"  placeholder="Enter your address" required="" />
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                                <div class="col-8 note">
                                    <label class="text-muted font-weight-normal m-0">Note</label>
                                    <div class="form-group">
                                        <textarea id="edit" rows="5" name="message" class="form-control" placeholder="Message" required=""></textarea>
                                    </div>

                                </div>
                            </div>

                            <div class="float-right s_button">

                                <a href="shop"><button type="submit"  class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Back to shopping</button></a>

                                <form id="checkoutForm" action="checkout" method="POST" onclick="overtime()">

                                    <input onclick="prepareCheckout()" class="btn btn-lg btn-primary mt-2" type="submit" name="" value="Checkout" />
                                </form>

                            </div>

                        </div>
                    </div>
                </div>
            </main>
            <script>
                function prepareCheckout() {
                    // Lấy danh sách các giá trị checkbox checkBuy đã chọn
                    var selectedCheckboxes = document.querySelectorAll('input[name="checkBuy"]:checked');

                    // Tạo một hidden input để chứa các giá trị đã chọn
                    var hiddenInput = document.createElement("input");
                    hiddenInput.type = "hidden";
                    hiddenInput.name = "selectedCheckboxes";

                    // Gán giá trị của các checkbox đã chọn vào hidden input
                    var selectedValues = [];
                    selectedCheckboxes.forEach(function (checkbox) {
                        selectedValues.push(checkbox.value);
                    });
                    hiddenInput.value = selectedValues.join(",");

                    // Thêm hidden input vào biểu mẫu Checkout
                    document.getElementById("checkoutForm").appendChild(hiddenInput);
                }
            </script>
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
                                <i class="fas fa-phone tm-contact-icon"></i><span>0971742946</span>
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
                    var acc = document.getElementsByClassName("accordion");
                    var i;

                    for (i = 0; i < acc.length; i++) {
                        acc[i].addEventListener("click", function () {
                            this.classList.toggle("active");
                            var panel = this.nextElementSibling;
                            if (panel.style.maxHeight) {
                                panel.style.maxHeight = null;
                            } else {
                                panel.style.maxHeight = panel.scrollHeight + "px";
                            }
                        });
                    }
                });
        </script>
        <style type="text/css">

            .product_name {
                display: flex;
                align-items: center;
            }

            .product_name img {
                width: 80px;
                height: 80px;
                object-fit: cover;
                border-radius: 4px;
                margin-right: 10px;
            }

            .product_name .media-body a {
                font-weight: 500;
                font-size: 16px;
                color: #333;
                text-decoration: none;
            }

            .product_name .media-body a:hover {
                color: #0056b3;
            }
            /* Chung cho cả + và - */
            .table td button a{
                text-decoration: none;
            }
            .table td button {
                background-color: #ddd;
                border: none;
                color: black;
                padding: 5px 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
            }

            /* Riêng cho nút + */
            .table td a[href*="num=1"] button {
                color: green;
            }

            /* Riêng cho nút - */
            .table td a[href*="num=-1"] button {
                color: red;
            }

            /* Hover */
            .table td button:hover {
                opacity: 0.7;
            }
            .close {
                background-color: #f44336;
                border: none;
                color: white;
                padding: 5px 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                cursor: pointer;
                border-radius: 5px;
            }

            .close:hover {
                opacity: 0.7;
            }
            .p_quantity{
                display: flex;
                justify-content: space-around;
            }
            .p_quantity span{
                margin:auto;
            }
            .card {
                margin: 2rem;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                transition: 0.3s;
                border-radius: 5px;
            }

            .card:hover {
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            }

            .card-header {
                background-color: #2F956D;
                color: white;
                padding: 10px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
            }

            .card-body {
                padding: 20px;
            }

            table {
                border-collapse: collapse;
                width: 100%;
                margin-bottom: 1.5rem;
            }

            th, td {
                text-align: left;
                padding: 8px;
                border: 1px solid #ddd;
            }

            tr:nth-child(even){
                background-color: #f2f2f2
            }

            th {
                background-color: #2F956D;
                color: white;
            }

            .btn {
                border: none;
                outline: 0;
                padding: 12px;
                color: white;
                background-color: #000;
                text-align: center;
                cursor: pointer;
                width: 100%;
                font-size: 18px;
            }

            .btn:hover {
                opacity: 0.7;
            }
            .tm-nav-link:hover{
                color: white;
                text-decoration: none;
            }

            .tm-contact-link{
                margin-right:16px;
            }
            .tm-contact-icon{
                margin-right:8px;
            }

            .card{
                box-shadow: 0 1px 15px 1px rgba(52,40,104,.08);
            }

            .ui-product-color {
                display: inline-block;
                overflow: hidden;
                margin: .144em;
                width: .875rem;
                height: .875rem;
                border-radius: 10rem;
                -webkit-box-shadow: 0 0 0 1px rgba(0,0,0,0.15) inset;
                box-shadow: 0 0 0 1px rgba(0,0,0,0.15) inset;
                vertical-align: middle;
            }
            .placeholder {
                width: 100%;
                min-height: 460px;
                background-color: #556E5B;
            }
            .parallax-window {
                min-height: 460px;
                background: transparent;
                position: relative;
            }
            .parallax-mirror {
                z-index: 999 !important;
            }
            .s_button{
                display: flex;
                justify-content: space-evenly;
            }

            .s_button a button{
                background-color: #F0F0F0;
                color:black;
                border-radius: 8px;
                border: 2px solid black;


            }
            .s_button a button:hover{
                background-color: #A7BEAE;
            }

            .s_button form input{
                background-color:#2F956D;
                border: 2px solid black;
                border-radius: 8px;
            }
            .s_button form input:hover{
                background-color: #A7BEAE;
            }

            .flex-cart{
                display: flex;
                justify-content: space-between;
            }

            .info-cart{
                display: flex;
            }

            .info-cart .form-group{
                margin: 1rem;
            }

            .info-cart .form-group input{
                padding: 0;
                border:1px solid black;
                border-radius: 4px;
            }

            .total_price{
                margin: auto 0;
            }

            .total_price {
                text-align: right;
            }

            .total_price .text-large {
                display: block;
                margin-top: 10px;
                font-size: 2rem;
                font-weight: bold;
                color: #2F956D;
            }

            .total_price label {
                font-size: 1.1rem;
                color: #6c757d;
            }
            .other-option{
                display: flex;
                justify-content: space-between
            }
            .place_eat{
                width: 20rem;
            }

        </style>

        <script type="text/javascript">
            function buy(id) {
                document.f.action = "buy?id=" + id;
                document.f.submit();
            }


        </script>
        <script type="text/javascript">
            function wait() {
                if (confirm("Because of high quantity of products, you have to wait about 1-2 hours for making and shipping, are you sure to add more!?")) {
                    window.location = "cart";
                }
            }
            function waitCheckOut() {
                if (confirm("Because of high quantity of products, you have to wait about 1-2 hours for making and shipping, are you sure to checkout!?")) {
                    window.location = "checkout";
                }
            }
//            function overtime() {
//                var now = new Date();
//                var hour = (now.getHours());
//                if (hour < 6 || hour >= 21) {
//                    if (confirm("We only sell from 6:00 to 21:00, please comeback in time!")) {
//                        window.location = "cart";
//                    }
//                }
//            }
        </script>

    </body>
    <script >

        CKEDITOR.replace('edit', {
            filebrowserBrowseUrl: 'ckfinder/ckfinder.html',
            filebrowserUploadUrl: 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files'
        });
    </script>
</html>
