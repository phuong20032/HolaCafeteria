
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Revenue</title>
        <link rel="icon" href="img/simple-house-logo.png"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400" rel="stylesheet" />    
        <link href="css/templatemo-style.css" rel="stylesheet" />
        <link href="css/all.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
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
                                    <li class="tm-nav-li"><a href="${a.role == 1 && a.id!=null ? "manager" : "home"} " class="tm-nav-link">${a.role == 1 && a.id!=null ? "Manager" : "Home"}</a></li>
                                    <li class="tm-nav-li"><a href="${a.role == 1 ? "getorderbydate" : ""} " class="tm-nav-link  ">${a.role == 1 && a.id!=null ? "Statistic detail" : ""}</a></li>
                                    <li class="tm-nav-li"><a href="${a.role == 1 ? "revenue" : "cart"}" class="tm-nav-link active">${a.role == 1 && a.id!=null ? "Revenue statistics" : "Cart"}</a></li>
                                    <li class="tm-nav-li"><a href="${a.id == null ? "login.jsp" : "profile"} " class="tm-nav-link">Profile</a></li>
                                    <li class="tm-nav-li"><a href="logout" class="tm-nav-link">${a.id == null ? "" : "Log Out"}</a></li>
                                </ul>
                            </nav>	
                        </div>
                    </div>
                </div>
            </div>

            <main>


                <div class="tm-container-inner-2 tm-info-section">
                    <div class="row">
                        <!-- FAQ -->
                        <div class="col-12 tm-faq">
                            <h2 class="text-center tm-section-title">Statistics</h2>

                            <div class="tm-accordion">
                                <button class="accordion">1. Shop</button>
                                <div class="panel">
                                    <p>Number of Catergories: ${requestScope.cate}</p>
                                    <p>Number of Products: ${requestScope.prod} </p>
                                    <p>Product's price from ${requestScope.pricemin}đ to ${requestScope.pricemax}đ </p>
                                    <p>Average product's price ~ ${requestScope.avg}đ </p>
                                    <!--                                    <p>Most sold Categories: </p>
                                                                        <p>Least sold Categories: </p>
                                                                        <p>Most sold Products: </p>
                                                                        <p>Least sold Products: </p>-->
                                </div>

                                <button class="accordion">2. Revenue</button>
                                <div class="panel">
                                    <p>Number of Orders:  ${requestScope.numOfOrd}</p>

                                    <p>Total money earned: ${requestScope.totalRevenue}đ</p>
                                    <p>Order has highest earning product: ${requestScope.highestEarningP.getName()} - ${requestScope.highestEarningP.getTotal()/requestScope.highestEarningP.getPrice()}pcs - ${requestScope.highestEarningP.getTotal()}đ </p>
                                    <p>Order has lowest earning product: ${requestScope.lowestEarningP.getName()} - ${requestScope.lowestEarningP.getTotal()/requestScope.lowestEarningP.getPrice()}pcs - ${requestScope.lowestEarningP.getTotal()}đ</p>


                                </div>

                                <button class="accordion">3. Orders</button>
                                <div class="panel">
                                    <p>
                                        Average orders per day: ${requestScope.avgOrd-1} - ${requestScope.avgOrd+1} orders
                                    </p>
                                    <p>Most orders per day:  ${requestScope.mostOrderPerDay.get(0).getNum()} orders 
                                        <a href="orderdetail?date=${requestScope.mostOrderPerDay.get(0).getDate()}"  target="_parent" class="tm-register"  >
                                            <i> click for detail </i>
                                        </a>
                                    </p>
                                    <p>Least orders per day:  ${requestScope.leastOrderPerDay.get(0).getNum()} orders 
                                        <a href="orderdetail?date=${requestScope.leastOrderPerDay.get(0).getDate()}"  target="_parent" class="tm-register"  >
                                            <i> click for detail </i>
                                        </a>
                                    </p>
                                    <p>Most revenue in a order:  ${requestScope.mostRevenuePerDay.get(0).getTotal()} đ 
                                        <a href="orderdetail?date=${requestScope.mostRevenuePerDay.get(0).getDate()}"  target="_parent" class="tm-register"  >
                                            <i> click for detail </i>
                                        </a>
                                    </p>
                                    <p>Least revenue in a order:  ${requestScope.leastRevenuePerDay.get(0).getTotal()} đ 
                                        <a href="orderdetail?date=${requestScope.leastRevenuePerDay.get(0).getDate()}"  target="_parent" class="tm-register"  >
                                            <i> click for detail </i>
                                        </a>
                                    </p>
                                    <p>
                                        <a style="color:green" href="showAllOrder"  target="_parent" class="tm-register"  >
                                            <i> SHOW ALL ORDER </i>
                                        </a>
                                    </p>

                                </div>

                                <button class="accordion">4. Customer</button>
                                <div class="panel">
                                    <p>Customers who buy the most: ${requestScope.customerBuyMost.getFullname()} with ${requestScope.customerBuyMost.getNumOfOrds()} orders </p> 
                                </div>



                            </div>	
                        </div>
                    </div>
                </div>
            </main>

            <script type="text/javascript">

                new Chart(document.getElementById("orderPerDay-bar-chart"), {
                type: 'bar',
                        data: {
                        labels: ["0",<c:forEach var="ord" items="${requestScope.order}">"${ord.getDate()}",</c:forEach>],
                                datasets: [
                                {
                                label: "Orders",
                                        backgroundColor: ["#2F956D",<c:forEach var="ord" items="${requestScope.order}">"#2F956D", </c:forEach>],
                                        data: [0,<c:forEach var="ord" items="${requestScope.order}">${ord.getNum()},</c:forEach>]
                                }
                                ]
                        },
                        options: {
                        legend: {display: false},
                                title: {
                                display: true,
                                        text: 'Average orders per day: ${requestScope.avgOrd-1} - ${requestScope.avgOrd +1} orders'
                                }
                        }
                });
            </script>
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
    </body>
</html>
