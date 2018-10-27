<!DOCTYPE html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/static/js/bootstrap3/bootstrap.js"></script>
<html lang="en">

<style>
    .login-or {
        position: relative;
        font-size: 18px;
        color: #aaa;
        margin-top: 10px;
        margin-bottom: 10px;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    .span-or {
        display: block;
        position: absolute;
        left: 50%;
        top: -2px;
        margin-left: -25px;
        background-color: #fff;
        width: 50px;
        text-align: center;
    }

    .hr-or {
        background-color: #cdcdcd;
        height: 1px;
        margin-top: 0px !important;
        margin-bottom: 0px !important;
    }

    .edit {
        margin-top: 180px;
        width: 100%;
    }

    @media (min-device-width: 375px) and (max-device-width: 667px) and (-webkit-min-device-pixel-ratio: 2) {
        .forgot {
            margin-top: 120px;
        }

    }
</style>

<head>
    <meta charset="UTF-8">
    <title>edit profile</title>
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
</head>
<body>

<main role="main">
    <div class="container edit">
        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col col-md-6">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">
                            Edit Profile
                        </h4>
                    </div>
                    <div class="modal-body">
                        <#--<#if loginUser.role_id == 1>-->
                        <form novalidate="novalidate" id="editprofile" method="post" action="/accounts/edit_profile">
                            <input type="hidden" value="${(loginUser.email)!}" name="email" />

                            <div class="form-group">
                                <label>Name</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" name="username"  class="form-control" id="editname" value="${(loginUser.username)!}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Location</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
                                    <input type="text" name="location" class="form-control" id="editlocation" value="${(loginUser.location)!}" required >
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary" style="width: 127px;text-align: center">
                                Submit
                            </button>
                        </form>
                        <#--<#else>-->
                        <#--{{ wtf.quick_form(form) }}-->
                        <#--</#if>-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>