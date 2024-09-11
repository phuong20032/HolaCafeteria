<%-- 
    Document   : index
    Created on : 14-Oct-2022, 21:47:53
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Profile</title>
        <link rel="icon" href="img/simple-house-logo.png"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400" rel="stylesheet" />    
        <link href="css/templatemo-style.css" rel="stylesheet" />
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
                                    <li class="tm-nav-li"><a href="${a.id == null ? "login.jsp" : "profile"} " class="tm-nav-link active">${a.id == null ? "Login":"Profile"}</a></li>
                                    <li class="tm-nav-li"><a href="logout" class="tm-nav-link">${a.id == null ? "" : "Log Out"}</a></li>
                                </ul>
                            </nav>	
                        </div>
                    </div>
                </div>
            </div>

            <main>
                <header class="row tm-welcome-section">
                    <c:set var="a" value="${sessionScope.account}"/>
                    <h2 class="col-12 text-center tm-section-title">User ${a.fullname}</h2>
                    <h3 style="color: red">${requestScope.ms}</h3>
                    <a href="profile" target="_parent" class="tm-register">
                        <i> <-Back </i>
                    </a>
                    <h2 class="col-12 text-center tm-section-title"></h2>
                    <form action="change_profile" method="post" >

                        <div class="form-group">
                            <input type="text" name="fullname" class="form-control" value="${a.fullname}"  required="" />
                        </div>
                        <div class="form-group">
                            <input type="text" name="email" class="form-control" value="${a.email}"  required="" />
                        </div>
                        <div class="form-group">
                            <input type="text" name="phonenum" class="form-control" value="${a.phonenum}"  required="" />
                        </div>
                        <div class="form-group">
                            <input type="text" name="address" class="form-control" value="${a.address}"  required="" />
                        </div>
                        <input type="hidden" name="user" value="${sessionScope.account.username}"/>
                        <input type="hidden" name="pass" value="${sessionScope.account.password}"/>
                        <input type="submit" class="tm-btn tm-btn-success tm-btn" value="Change Profile">
                    </form>

                    <h2 class="col-12 text-center tm-section-title"></h2>
                    <h2 class="col-12 text-center tm-section-title"></h2> 
                    <h2 class="col-12 text-center tm-section-title"></h2>  
                </header>

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
            </script>
    </body>
</html>
