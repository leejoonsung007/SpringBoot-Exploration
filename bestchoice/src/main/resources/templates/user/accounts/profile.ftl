<!DOCTYPE html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/static/js/bootstrap3/bootstrap.js"></script>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">


    <!-- Main Style Sheet -->
    <link rel="stylesheet" href="/static/css/bootstrap3/bootstrap.css">
    <!-- Modernizr -->
    <script src="/static/js/vendor/modernizr-2.6.2.min.js"></script>
    <!-- Respond.js for IE 8 or less only -->
    <!--[if (lt IE 9) & (!IEMobile)]>
    <script src="js/vendor/respond.src.js"></script>
    <![endif]-->

    <style>
        .content {
            margin-top: 115px;
        }

        @media (min-device-width: 375px) and (max-device-width: 667px) and (-webkit-min-device-pixel-ratio: 2) {
            .content {
                margin-top: 20px;
            }

        }
    </style>
</head>

<body>
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
                <a class="navbar-brand" href="/index">
                    <img src="/static/img/logo.png" alt="BestChoice'" width="120" style="margin-top:-9px">
                </a>
            </div>

            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav pull-left">
                    <li class="active"><a href='#'>
                        <span class="glyphicon glyphicon-home"></span> Home
                        </a></li>
                    <li><a href='#'>
                        <span class="glyphicon glyphicon-search"></span> Search
                        </a></li>
                    <li><a href='#'>
                        <span class="glyphicon glyphicon-sort-by-order"></span> Rank
                        </a></li>
                    <li><a href='#'>
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

<main role="main">
    <div class="row">
        <div class="col-md-2"></div>

        <div class="col-md-3 content" style="margin-left: 20px">

            <div id="imageuse">
                <#--<a {% if user == current_user or current_user.is_administrator() %}-->
                   <#--href="{{ url_for('operation.change_avatar') }}"{% endif %}>-->
                <a>

                    <img style="width: 300px; position: relative;" id="image" src="/static/avatars/default_avatar.jpg" onclick="EntryUser()">
                </a>
            </div>

            <div>
                <h3>${(loginUser.username)!}</h3>
            </div>

            <div>
                <#if (loginUser.location)??>
                from <a href="http://maps.google.com/?q=${(loginUser.location)!}">${(loginUser.location)!}</a>
                </#if>
            </div>

            <#--<div>-->
                <#--{% if current_user.is_administrator() %}-->
                <#--<p><a href="mailto:{{ user.email }}">{{ user.email }}</a></p>-->
                <#--{% endif %}-->
            <#--</div>-->

            <div>
                <label>Member since: ${(loginUser.member_since)!}</label>
            </div>

            <div>
                <label>Last seen: ${(loginUser.last_seen)!}</label>
            </div>

            <br>

            <#--{% if current_user.is_administrator() %}-->
            <#--<a class="btn btn-danger"-->
               <#--href="{{ url_for('.edit_profile_admin', id=user.id) }}">-->
                <#--Edit Profile [Admin]-->
            <#--</a>-->
            <#--{% else %}-->
            <#--{% if user == current_user %}-->
            <div>
                <a class="btn btn-default" href="/accounts/edit_profile">Edit Profile</a>
            </div>
            <#--{% endif %}-->
            <#--{% endif %}-->

        </div>

        <div class="col-md-1"></div>
        <div class="col-md-4 content" style="margin-left: 20px">

            <h4>Following Schools</h4>
            <div style="width:370px; height:400px;line-height:50px;overflow:auto;overflow-x:hidden; ">
                <#--<table style="margin-left: -5px" class="table table-hover followers">-->

                    <#--{% for follow in follows %}-->
                    <#--<tr>-->
                        <#--<td>-->
                            <#--<a href="{{ url_for('main.school_detail', place_id=follow.school.place_id, official_school_name = follow.school.official_school_name) }}">-->
                                <#--<img style="border-radius: 10px;"-->
                                     <#--src="../../static/img/rank/{{ follow.school.official_school_name }}.jpeg"-->
                                     <#--width="120" height="80"/>-->
                            <#--</a>-->
                        <#--</td>-->
                        <#--<td style="text-align: center">-->
                            <#--<p></p>-->
                            <#--<a href="{{ url_for('main.school_detail', place_id=follow.school.place_id, official_school_name = follow.school.official_school_name) }}">-->
                                <#--<span class="badge"> <h6>{{ follow.school.official_school_name }} </h6> </span>-->
                            <#--</a>-->

                        <#--</td>-->
                    <#--</tr>-->
                    <#--{% endfor %}-->
                <#--</table>-->
            </div>
        </div>
    </div>

</main>


</body>
</html>