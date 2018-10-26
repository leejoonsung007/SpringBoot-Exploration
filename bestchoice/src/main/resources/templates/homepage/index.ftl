<!DOCTYPE html>

<!--[if lt IE 7]>
<html class="no-bootstrap lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-bootstrap lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-bootstrap lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Home</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <!-- Main Style Sheet-->
    <link rel="stylesheet" href="/static/css/bootstrap3/bootstrap.css">
    <link rel="stylesheet" href="/static/css/bootstrap3/bootstrap-theme.css">
    <link rel="stylesheet" href="/static/css/home.css">

    <!-- Main JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../static/js/bootstrap3/bootstrap.js"></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGOu1MCWVAvOQ24l6-tOdRpXgKXEDWvYs&language=en&libraries=places">
    </script>
    <script src="/static/js/pagination/jquery.twbsPagination.js"></script>
    <script src="/static/js/home.js"></script>

    <!-- Respond.bootstrap for IE 8 or less only -->
    <!--[if (lt IE 9) & (!IEMobile)]>
    <script src="bootstrap/vendor/respond.min.bootstrap"></script>
    <![endif]-->

</head>

<body class="products">
<!-- navigation bar -->
<header role="banner">
    <nav role="navigation" class="navbar navbar-static-top navbar-default" style="margin-bottom:0px">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="/static/img/logo.png" alt="BestChoice'" width="120" style="margin-top:-9px">
                </a>
            </div>

            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav pull-left">
                    <li class="active"><a href="#">
                        <span class="glyphicon glyphicon-home"></span> Home
                    </a></li>
                    <li><a href="#">
                        <span class="glyphicon glyphicon-search"></span> Search
                    </a></li>
                    <li><a href="#">
                        <span class="glyphicon glyphicon-sort-by-order"></span> Rank
                    </a></li>
                    <li><a href="#">
                        <span class="glyphicon glyphicon-tasks"></span> Compare
                    </a></li>
                </ul>
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown">
                        <#--{% if current_user.is_authenticated %}-->
                        <#if (loginUser.username) ??>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <#--<#if (loginUser.photo) ??>-->
                            <#--<img src="${loginUser.photo}" height="23" width="23">-->
                            <#--{% else %}-->
                            <img class="img-rounded profile-thumbnail"
                                 src="/static/avatars/default_avatar.jpg" height="23"
                                 width="23">
                            <#--{% endif %}-->
                            ${(loginUser.username)!} <b class="caret"></b>
                        </a>

                        <ul class="dropdown-menu">
                            <#--{% if current_user.login_type != 'website' %}-->
                            <#--<li><a href="{{ url_for('operation.user', username=current_user.username) }}">Profile</a>-->
                            <#--</li>-->
                            <#--{#                                    <li><a href="{{ url_for('operation.following', username=current_user.username)}}">Watching List</a></li>#}-->
                            <#--<li><a href="{{ url_for('auth.logout') }}">Log out</a></li>-->
                            <#--{% else %}-->
                            <#--<li><a href="{{ url_for('operation.user', username=current_user.username) }}">Profile</a>-->
                            <#--</li>-->
                            <#--{#                                    <li><a href="{{ url_for('operation.following', username=current_user.username)}}">Watching List</a></li>#}-->
                            <#--<li><a href="{{ url_for('operation.change_password') }}">Change password</a></li>-->
                                <li><a href="/accounts/logout">Log out</a></li></ul>
                    </li>
                    <#else>
                    <li><a href="/accounts/login">Log In</a></li>
                    </#if>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container -->
    </nav>
</header>

<!--[if lte IE 7]>
<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to
    improve your experience.</p>
<![endif]-->


<main role="main">

    <!-- INTRO SECTION -->
    <section id="welcome" class="jumbotron">
        <div class="container">

            <div class="search-bar">
                <form role="form" method="post">
                    <#--{{ form.hidden_tag() }}-->
                    <#--{# {{ wtf.form_errors(fajax_getSchoolDataByLatlngorm, hiddens="only") }}#}-->


                    <div class="row">
                        <!--<div class="form-group rightLine col-lg-2 col-sm-2 col-md-2">
                            <input id="location" type="text" name="location" placeholder="Your City" value="Co.Dublin"
                                   readonly="readonly" >
                            <label for="location" class="location"><i class="fa fa-dot-circle-o"></i></label>
                        </div>-->

                        <div class="form-group col-lg-10  col-sm-10 col-md-10">
                            <#--{{ wtf.form_field(form.search, class='search-text', id = "omg") }}-->
                        </div>

                        <div class="form-group col-lg-2  col-sm-2 col-md-2">
                            <#--{{ wtf.form_field(form.lat, class='search-text', id = "lat", type = "hidden", value="") }}-->
                            <#--{{ wtf.form_field(form.lng, class='search-text', id = "lng", type = "hidden", value="") }}-->
                            <#--{#                             <input type="hidden" name="lat" id="lat" value="">#}-->
                            <#--{#                        <input type="hidden" name="lng" id="lng" value="">#}-->
                            <input type="submit" value="Search" class="submit">
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </section>


    <div class="container">

        <div class="page-header">

            <#if (loginUser.name)??>
            <h4>Welcome to Best Choice</h4>
            <h4>Hello, ${(loginUser.name)}!</h4>
            <h1>Post-Primary Schools
                <small>Sorted by current distance by default</small>
            </h1>
            </#if>
        </div>

        <div class="row">
            <div class="col-sm-4">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="mapholder" class="grid-options" style="height: 400px">

                        </div><!-- Google Map area -->

                        <script>
                            $(function () {

                                initMap();//Execution function

                                //geocodeLocation(latlng);//locate the current count

                                $("#map").on("mouseover", ".marker-container", function () {
                                    $(this).find(".detail-info").show();
                                });

                                $("#map").on("mouseout", ".marker-container", function () {
                                    $(this).find(".detail-info").hide();
                                });
                            });
                        </script>
                        <div class="col-sm-12 centre">
                            <span class="map-guide">Click on map to find your place <img src = '/static/img/locate.png' height="45"width="45"> </span>
                        </div>
                        <div>
                            <span id="address" style="color: #00b3ee;font-family: 'microsoft yahei', Arial, sans-serif;"></span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-grid col-sm-8">
                <div id="loadgif" style="text-align: center; width: 100%;"> 　　
                    <img style="display: inline-block;width:200px; height:200px; top:50%; left:50%;" alt="loading..."
                         src="/static/img/search/loader.gif"/>
                </div>

                <!-- school list-->
                <div id="school-holder" class="row">


                </div><!-- row-->

                <div class="row">

                    <div class="col-md-2"></div>

                    <div id="pagination-row" class="row pagination-wrap col-md-8 mypagination">
                        <nav  aria-label="Page navigation">
                            <ul class="pagination" id="pagination"></ul>
                        </nav>

                    </div><!-- /.row.pagination-wrap -->

                    <div class="col-md-2"></div>

                </div>

                <script>

                    var pos = {'lat':53.3498118, 'lng': -6.2711979};

                    ajax_getSchoolDataByLatlng(pos);

                </script>


            </div><!-- /.products-grid -->

        </div><!-- /.row -->
    </div><!-- /.container -->
</main>
<footer role="contentinfo">
    <div class="container">

        <p class="footer-brand"><a href="#"><img src="../../static/img/logo.png" width="80"
                                                 alt="Bootstrappin'"></a></p>

    </div><!-- /.container -->
</footer>


<!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
<script>
    var _gaq = [['_setAccount', 'UA-XXXXX-X'], ['_trackPageview']];
    (function (d, t) {
        var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
        g.src = ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g, s)
    }(document, 'script'));
</script>

</body>
</html>
