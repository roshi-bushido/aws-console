<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <title>AWS Console</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><g:layoutTitle default="Mulesoft Ple Sales AWS Console"/></title>

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'line-icons.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'application.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'themes/dark-blue.css')}">
    <link rel="stylesheet" href="${resource(dir: 'ui-plugins', file: 'bootstrap/css/bootstrap.min.css')}">
    <link rel="stylesheet" href="${resource(dir: 'ui-plugins', file: 'font-awesome/css/font-awesome.css')}">
    <script type="text/javascript" src="${resource(dir: 'js', file:'jquery-2.1.1.js')}"></script>
    <g:layoutHead/>
</head>

<body>
<div class="wrapper">
    <!--=== Header ===-->
    <div class="header">
        <!-- Topbar -->
        <div class="topbar">
            <div class="container">
                <!-- Topbar Navigation -->
                <ul class="loginbar pull-right">
                    <li><a href="#">Help</a></li>
                    <li class="topbar-devider"></li>
                    <g:if test="${session['user'] == null}">
                        <li><g:link controller="login" action="index">Login</g:link></li>
                    </g:if>
                    <g:if test="${session['user'] != null}">
                        <li><a href="#">Hey ${session['user'].username}</a></li>
                        <li class="topbar-devider"></li>
                        <li><g:link controller="logout" action="index" >Logout</g:link></li>
                    </g:if>

                </ul>
                <!-- End Topbar Navigation -->
            </div>
        </div>
        <!-- End Topbar -->

        <!-- Navbar -->
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="fa fa-bars"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">
                        <img id="logo-header" src="${resource(dir: 'images', file: 'custom/logo.png')}" alt="Logo">
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-responsive-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <g:link controller="user" action="myInstances" class="dropdown-toggle" >My Instances</g:link>
                        </li>
                        <li>
                            <g:link controller="user" action="myWorkshops" class="dropdown-toggle" >My Workshops</g:link>
                        </li>
                        <li>
                            <g:link controller="user" action="myDashboard" class="dropdown-toggle" >Dashboard</g:link>
                        </li>
                        <g:if test="${session['user'] != null && session['user'].isAdmin()}">
                            <li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administration</a>
                            </li>
                        </g:if>
                    </ul>
                </div><!--/navbar-collapse-->
            </div>
        </div>
        <!-- End Navbar -->
    </div>
    <!--=== End Header ===-->


    <g:layoutBody/>


    <!--=== Footer ===-->
    <div class="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-4 md-margin-bottom-40">
                    <!-- About -->
                    <div class="headline"><h2>About</h2></div>
                    <p class="margin-bottom-25 md-margin-bottom-40">Unify is an incredibly beautiful responsive Bootstrap Template for corporate and creative professionals.</p>
                    <!-- End About -->

                    <!-- Monthly Newsletter -->
                    <div class="headline"><h2>Monthly Newsletter</h2></div>
                    <p>Subscribe to our newsletter and stay up to date with the latest news and deals!</p>
                    <form class="footer-subsribe">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Email Address">
                            <span class="input-group-btn">
                                <button class="btn-u" type="button">Subscribe</button>
                            </span>
                        </div>
                    </form>
                    <!-- End Monthly Newsletter -->
                </div><!--/col-md-4-->

                <div class="col-md-4 md-margin-bottom-40">
                    <!-- Recent Blogs -->
                    <div class="posts">
                        <div class="headline"><h2>Recent Blog Entries</h2></div>
                        <dl class="dl-horizontal">
                            <dt><a href="#">${resource(dir: 'images', file:'sliders/elastislide/6.jpg')}</a></dt>
                            <dd>
                                <p><a href="#">Anim moon officia Unify is an incredibly beautiful responsive Bootstrap Template</a></p>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt><a href="#">${resource(dir: 'images', file: 'sliders/elastislide/10.jpg')}</a></dt>
                            <dd>
                                <p><a href="#">Anim moon officia Unify is an incredibly beautiful responsive Bootstrap Template</a></p>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt><a href="#">${resource(dir: 'images', file: 'sliders/elastislide/11.jpg')}</a></dt>
                            <dd>
                                <p><a href="#">Anim moon officia Unify is an incredibly beautiful responsive Bootstrap Template</a></p>
                            </dd>
                        </dl>
                    </div>
                    <!-- End Recent Blogs -->
                </div><!--/col-md-4-->

                <div class="col-md-4">
                    <!-- Contact Us -->
                    <div class="headline"><h2>Contact Us</h2></div>
                    <address class="md-margin-bottom-40">
                        25, Lorem Lis Street, Orange <br />
                        California, US <br />
                        Phone: 800 123 3456 <br />
                        Fax: 800 123 3456 <br />
                        Email: <a href="mailto:info@anybiz.com" class="">info@anybiz.com</a>
                    </address>
                </div><!--/col-md-4-->
            </div>
        </div>
    </div><!--/footer-->
<!--=== End Footer ===-->

<!--=== Copyright ===-->
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <p>2015 &copy; Mulesoft. ALL Rights Reserved.
                        <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a>
                    </p>
                </div>
                <div class="col-md-6">
                    <a href="/">
                        <img class="pull-right" id="logo-footer" src="${resource(dir: 'images', file:  'custom/logo_footer.png')}" alt="">
                    </a>
                </div>
            </div>
        </div>
    </div><!--/copyright-->
<!--=== End Copyright ===-->
</div><!--/wrapper-->

<script type="text/javascript" src="${resource(dir: 'ui-plugins', file:'jquery-migrate-1.2.1.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'ui-plugins', file:'bootstrap/js/bootstrap.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file:'app.js')}"></script>

<!--[if lt IE 9]>
    <script type="text/javascript" src="${resource(dir: 'js', file:'plugins/respond.js')}"></script>
<![endif]-->

</body>
</html>