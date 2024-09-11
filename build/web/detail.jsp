<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <!--
    
    Simple House
    
    https://templatemo.com/tm-539-simple-house
    
    -->
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
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "" : "home"}" class="tm-nav-link active ">${a.role == 1 && a.id!=null ? "" : "Home"}</a></li>
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "manager" : "shop"} " class="tm-nav-link ">${a.role == 1 && a.id!=null ? "Manager" : "Shop"}</a></li>
                                        <c:set var="size" value="${sessionScope.size==null?0:sessionScope.size}"/>
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
                <c:set var="p" value="${requestScope.prod}" />

                <h3></h3>
                <div class="col-md-4" style="margin-left: 50px">
                    <a href="shop"  target="_parent" class="tm-register"  >
                        <i> <-Back </i>
                    </a>
                </div>
                <input type="hidden" name="cid" value="${p.cid}" readonly="readonly" />
                <h3></h3>
                <form action="buy" method="post" id="myform">
                    <div class="tm-container-inner-2 tm-contact-section">
                        <div class="row">

                            <div class="col-md-4">

                                <figure>
                                    <img src="img/${p.image}" alt="Image" class="img-fluid tm-gallery-img" />
                                </figure>
                            </div>

                            <div class="col-md-6">
                                <div class="tm-address-box">

                                    <h4 class="tm-info-title tm-text-success">${p.name}</h4>
                                    <address>
                                        Price: ${p.price}
                                    </address>
                                    <address>
                                        Describe: ${p.describe}
                                    </address>

                                    <div class="form-group tm-d-flex">
                                        <div class="form-group">
                                            <input type="number" name="num" value="" class="form-control" placeholder="Quantity" min="1" required="" />
                                            <input type="hidden" name="id" value="${p.id}"/>
                                        </div>
                                        <input type="submit" class="tm-btn tm-btn-success tm-btn" onclick="buy('${p.id}')"  value="Buy now!">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
                <h2 class="tm-register" style="margin-left: 50px">Relative</h2>





                <div class="row tm-gallery">
                    <div style="justify-content:space-around;" id="tm-gallery-page-pizza" class="tm-gallery-page">
                        <c:forEach items="${requestScope.relativeproducts}" var="n">
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
                                                    <input type="number" name="num" value="" class="form-control" placeholder="Quantity" required="" min="0" />
                                                    <input type="hidden" name="id" value="${n.id}"/>
                                                </div>
                                            </div>
                                            <div class="form-group tm-d-flex">
                                                <div class="button-group">
                                                    <button class="btn btn-secondary">
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

                <style>
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
                                            function buy(id) {
                                                document.f.action = "buy?id=" + id;
                                                document.f.submit();
//                                                          document.getElementById('myform').submit();
                                            }
        </script>
    </body>
</html>
