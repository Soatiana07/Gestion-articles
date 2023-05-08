<%@ page import="com.example.springmvc.model.Contenu" %>
<%@ page import="java.util.List" %>
<!--
=========================================================
* Soft UI Dashboard - v1.0.3
=========================================================

* Product Page: https://www.creative-tim.com/product/soft-ui-dashboard
* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://www.creative-tim.com/license)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/assets/img/favicon.png">
    <title>
        Liste contenus a publier
    </title>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/resources/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/resources/assets/css/soft-ui-dashboard.css?v=1.0.3" rel="stylesheet" />
</head>

<body class="">
<div class="container position-sticky z-index-sticky top-0">
    <div class="row">
        <div class="col-12">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg blur blur-rounded top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
                <div class="container-fluid">
                    <a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 " href="<%=request.getContextPath()%>/frontoffice">
                        Contents' website
                    </a>
                    <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon mt-2">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
              </span>
                    </button>
                    <div class="collapse navbar-collapse" id="navigation">
                        <ul class="navbar-nav mx-auto">
                            <li class="nav-item">
                                <a class="nav-link d-flex align-items-center me-2 active" aria-current="page" href="<%=request.getContextPath()%>/frontoffice">
                                    <i class="fa fa-chart-pie opacity-6 text-dark me-1"></i>
                                    Accueil
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-2" href="<%=request.getContextPath()%>/admins/login">
                                    <i class="fas fa-user-circle opacity-6 text-dark me-1"></i>
                                    Se connecter entant qu'admin
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-2" href="<%=request.getContextPath()%>/auteurs/login">
                                    <i class="fas fa-key opacity-6 text-dark me-1"></i>
                                    Se connecter entant qu'auteur
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-2" href="<%=request.getContextPath()%>/admins">
                                    Voir contenus <span style="color: violet;">crees</span>
                                </a>
                            </li>
                        </ul>
<%--                        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">--%>
<%--                            <div class="ms-md-auto pe-md-3 d-flex align-items-center">--%>
<%--                                <div class="input-group">--%>
<%--                                    <span class="input-group-text text-body"><i class="fas fa-search" aria-hidden="true"></i></span>--%>
<%--                                    <input type="text" class="form-control" placeholder="Type here...">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->

    <div class="col-12 mt-4">
    <%
        List<Contenu> liste = (List<Contenu>)request.getAttribute("listeapub");
    %>
    <div class="card mb-4">
        <div class="card-header pb-0 text-left bg-transparent">
            <h3 style="margin-top: 70px;" class="font-weight-bolder text-info text-gradient">Liste contenus a publier</h3>
        </div>
        <div class="card-body p-3">
            <div class="row">
                <% for(int i = 0; i<liste.size(); i++){ %>
                <div class="col-xl-3 col-md-6 mb-xl-0 mb-4">
                    <%--                                <div class="card card-blog card-plain">--%>
                    <div class="position-relative">
                        <a class="d-block shadow-xl border-radius-xl">
                            <img src="${pageContext.request.contextPath}/images/<%= liste.get(i).getVisuel() %>" alt="img-blur-shadow" class="img-fluid shadow border-radius-xl">
                        </a>
                    </div>
                    <div class="card-body px-1 pb-0">
                        <a href="javascript:;">
                            <% if(liste.get(i).getType() == 2){ %>
                            <h5 style="color: #3acaeb;">
                                <%= liste.get(i).getTitre() %>
                            </h5>
                            <% } %>
                            <% if(liste.get(i).getType() == 1){ %>
                            <h5>
                                <%= liste.get(i).getTitre() %>
                            </h5>
                            <% } %>
                        </a>
                        <p class="mb-4 text-sm">
                            Date creation : <%= liste.get(i).getDatecreation() %>
                        </p>
                        <p class="mb-4 text-sm">
                            Date publication : <%= liste.get(i).getDatepublication() %>
                        </p>
                        <p class="mb-4 text-sm">
                            <%= liste.get(i).getDescription() %>
                        </p>
                    </div>
                    <%--                                </div>--%>
                    <form method="post" action="<%=request.getContextPath()%>/publier/<%= liste.get(i).getId() %>" role="form">
                        <div class="d-flex align-items-center justify-content-between">
                            <input type="submit" value="PUBLIER" class="btn btn-outline-primary btn-sm mb-0"/>
                        </div>
                    </form>
                </div>
                <% } %>
                <%
                    int offset = (Integer)request.getAttribute("offset");
                    int limit = (Integer)request.getAttribute("limit");
                %>
                <% if (offset > 0) { %>
                <a style="font-size: 30px;" href="<%=request.getContextPath()%>/apublier?offset=<%= offset - limit %>&limit=<%= limit %>">Previous</a>
                <% } %>
                <% if (liste.size() == limit) { %>
                <a style="font-size: 30px;" href="<%=request.getContextPath()%>/apublier?offset=<%= offset + limit %>&limit=<%= limit %>">Next</a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</div>
<footer class="footer pt-3  ">
    <div class="container-fluid">
        <div class="row align-items-center justify-content-lg-between">
            <div class="col-lg-6 mb-lg-0 mb-4">
                <div class="copyright text-center text-sm text-muted text-lg-start">
                    © <script>
                    document.write(new Date().getFullYear())
                </script>,
                    made with <i class="fa fa-heart"></i> by
                    <a href="https://www.creative-tim.com" class="font-weight-bold" target="_blank">Creative Tim</a>
                    for a better web.
                </div>
            </div>
            <div class="col-lg-6">
                <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com" class="nav-link text-muted" target="_blank">Creative Tim</a>
                    </li>
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com/presentation" class="nav-link text-muted" target="_blank">About Us</a>
                    </li>
                    <li class="nav-item">
                        <a href="https://creative-tim.com/blog" class="nav-link text-muted" target="_blank">Blog</a>
                    </li>
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-muted" target="_blank">License</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</div>
</div>
<div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
        <i class="fa fa-cog py-2"> </i>
    </a>
    <div class="card shadow-lg ">
        <div class="card-header pb-0 pt-3 ">
            <div class="float-start">
                <h5 class="mt-3 mb-0">Soft UI Configurator</h5>
                <p>See our dashboard options.</p>
            </div>
            <div class="float-end mt-4">
                <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
                    <i class="fa fa-close"></i>
                </button>
            </div>
            <!-- End Toggle Button -->
        </div>
        <hr class="horizontal dark my-1">
        <div class="card-body pt-sm-3 pt-0">
            <!-- Sidebar Backgrounds -->
            <div>
                <h6 class="mb-0">Sidebar Colors</h6>
            </div>
            <a href="javascript:void(0)" class="switch-trigger background-color">
                <div class="badge-colors my-2 text-start">
                    <span class="badge filter bg-gradient-primary active" data-color="primary" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-success" data-color="success" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-warning" data-color="warning" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-danger" data-color="danger" onclick="sidebarColor(this)"></span>
                </div>
            </a>
            <!-- Sidenav Type -->
            <div class="mt-3">
                <h6 class="mb-0">Sidenav Type</h6>
                <p class="text-sm">Choose between 2 different sidenav types.</p>
            </div>
            <div class="d-flex">
                <button class="btn bg-gradient-primary w-100 px-3 mb-2 active" data-class="bg-transparent" onclick="sidebarType(this)">Transparent</button>
                <button class="btn bg-gradient-primary w-100 px-3 mb-2 ms-2" data-class="bg-white" onclick="sidebarType(this)">White</button>
            </div>
            <p class="text-sm d-xl-none d-block mt-2">You can change the sidenav type just on desktop view.</p>
            <!-- Navbar Fixed -->
            <hr class="horizontal dark my-sm-4">
            <a class="btn bg-gradient-dark w-100" href="https://www.creative-tim.com/product/soft-ui-dashboard-pro">Free Download</a>
            <a class="btn btn-outline-dark w-100" href="https://www.creative-tim.com/learning-lab/bootstrap/license/soft-ui-dashboard">View documentation</a>
            <div class="w-100 text-center">
                <a class="github-button" href="https://github.com/creativetimofficial/soft-ui-dashboard" data-icon="octicon-star" data-size="large" data-show-count="true" aria-label="Star creativetimofficial/soft-ui-dashboard on GitHub">Star</a>
                <h6 class="mt-3">Thank you for sharing!</h6>
                <a href="https://twitter.com/intent/tweet?text=Check%20Soft%20UI%20Dashboard%20made%20by%20%40CreativeTim%20%23webdesign%20%23dashboard%20%23bootstrap5&amp;url=https%3A%2F%2Fwww.creative-tim.com%2Fproduct%2Fsoft-ui-dashboard" class="btn btn-dark mb-0 me-2" target="_blank">
                    <i class="fab fa-twitter me-1" aria-hidden="true"></i> Tweet
                </a>
                <a href="https://www.facebook.com/sharer/sharer.php?u=https://www.creative-tim.com/product/soft-ui-dashboard" class="btn btn-dark mb-0 me-2" target="_blank">
                    <i class="fab fa-facebook-square me-1" aria-hidden="true"></i> Share
                </a>
            </div>
        </div>
    </div>
</div>
<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/resources/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${pageContext.request.contextPath}/resources/assets/js/soft-ui-dashboard.min.js?v=1.0.3"></script>
</body>

</html>