<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.Cookie" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="dal.*" %>
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

                            <%List<Product> prd = ProductDAO.INSTANCE.getAllProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, prd);
        int size = cart.getItems().size();
                            %>
                            <nav class="col-md-8 col-12 tm-nav">
                                <ul class="tm-nav-ul">
                                    <c:set var="a" value="${sessionScope.account}"/>
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "" : "home"}" class="tm-nav-link  ">${a.role == 1 && a.id!=null ? "" : "Home"}</a></li>
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "manager" : "shop"} " class="tm-nav-link ">${a.role == 1 && a.id!=null ? "Manager" : "Shop"}</a></li>
                                        <c:if test="${a.role == 1}">
                                        <li class="tm-nav-li"><a href="${a.role == 1 ? "getorderbydate" : ""} " class="tm-nav-link  ">${a.role == 1 && a.id!=null ? "Statistic detail" : ""}</a></li>
                                        </c:if>
                                    <li class="tm-nav-li"><a href="${a.role == 1 ? "revenue" : "cart"}" class="tm-nav-link ">${a.role == 1 && a.id!=null ? "Revenue statistics" : "Cart"} <span ${a.role==1?"hidden":""} style="font-size: 12px">(<%=size%>)</span></a></li>
                                    <li class="tm-nav-li"><a href="${a.id == null ? "login.jsp" : "profile"} " class="tm-nav-link active" >${a.id == null ? "Login":"Profile"}</a></li>
                                    <li class="tm-nav-li"><a href="logout" class="tm-nav-link">${a.id == null ? "" : "Log Out"}</a></li>
                                </ul>
                            </nav>	
                        </div>
                    </div>
                </div>
            </div>

            <main>
                <header class="row tm-welcome-section">


                    <h3 class="col-12 text-center tm-section-title">Welcome ${a.role == 1? "Admin" : "User"} ${a.fullname}</h3>
 
                </header>
                <a href="change_pass" class="tm-register">
                    <i>Change password</i>
                </a><br/>
                <a href="change_profile" class="tm-register">
                    <i>Change profile information</i>
                </a>
            </main>
            <div class="orderTable">
                <h2>Order History</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Date</th>
                    </tr>
                    <c:forEach items="${listOrder}" var="o">
                        <tr>
                            <td>${o.oId}</td>
                            <td>${o.pName}</td>
                            <td>${o.quantity}</td>
                            <td>${o.price}</td>
                            <td>${o.total}</td>
                            <td>${o.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pagination">
                <div class="page-link">
                    <a href="profile?index=${1}">Home</a>
                </div>
                <div class="page-link">
                    <a href="profile?index=${index - 1 > 1?index-1:1}">Pre</a>
                </div>
                <c:forEach begin="${index - 2 > 1? index - 2 : 1}" end="${index + 2 < endP? index + 2: endP}" var="i">
                    <div class="page-link ${i == index ? "page-active":""}">

                        <a href="profile?index=${i}">${i}</a>
                    </div>
                </c:forEach>
                <div class="page-link">

                    <a href="profile?index=${index+1 < endP?index+1:endP}">Next</a>
                </div>
                <div class="page-link">

                    <a href="profile?index=${endP}">End</a>
                </div>
            </div>
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
            .orderTable {
                width: 80%;
                margin: 0 auto;
                text-align: center;
            }

            .orderTable h2 {
                font-size: 24px;
                text-transform: uppercase;
                letter-spacing: 2px;
                font-weight: bold;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
            }

            th {
                background: #319966;
                color: #ffffff;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #A7BEAE;
            }

            th, td {
                border: 1px solid #ccc;
            }
        </style>
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
