<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <!-- Main Style Sheet -->
    <link rel="stylesheet" href="/static/css/bootstrap3/bootstrap.css">
    <link rel="stylesheet" href="/static/css/background/demo.css"/>
    <link rel="stylesheet" href="/static/css/background/templatemo-style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/static/js/bootstrap3/bootstrap.js"></script>
    <script src="/static/js/background/modernizr.custom.86080.js"></script>

    <!-- Respond.js for IE 8 or less only -->
    <!--[if (lt IE 9) & (!IEMobile)]>
    <script src="js/vendor/respond.src.js"></script>
    <![endif]-->

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

        .login {
            margin-top: 100px;
        }

        @media (min-device-width: 375px) and (max-device-width: 667px) and (-webkit-min-device-pixel-ratio: 2) {
            .login {
                margin-top: 50px;
            }

        }

    </style>

</head>

<body>

<!-- background area start 需要放在最开始处-->
<div id="particles-js"></div>
<ul class="cb-slideshow">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
</ul>
<!-- background area end-->

<!-- navigation bar -->

<main role="main">

    <div class="login container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col col-md-6 center" style="z-index:9999">
                <div class="modal-content">
                    <div class="modal-header">
                    <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                        <h4 class="modal-title">
                            Login
                        </h4>
                    </div>
                    <div class="modal-body">

                    <#--<div id="errorSignUp">-->
                    <#--</div>-->
                        <div class="form-group">
                            <div class="col-md-15">

                                <div class="form-group">
                                    <div class="col-md-15">
                                        <a href="#" class="btn btn-block"
                                           style="background-color: #5bc0de;color: white">
                                            <span class="icon fa-google-plus-square"></span>    Login with Google
                                        </a>
                                    </div>
                                </div>

                                <div class="login-or">
                                    <hr class="hr-or">
                                    <span class="span-or">or</span>
                                </div>

                                <form role="form" novalidate="novalidate" id="formLogin" method="post" action="/accounts/login">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <div class="input-group" style="width:100%">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" name="username" class="form-control" placeholder="Email Address"
                                                   value="${username!}" id="form-create-account-email" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Password</label>
                                        <div class="input-group" style="width:100%">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                            <input type="password" name="password" class="form-control" placeholder="Password"
                                                   value="${password!}" id="form-create-account-password" required>
                                        </div>
                                    </div>

                                    <input type="hidden" value="${target!}" name="target"/>

                                    <button type="submit" id="btnLogin" class="btn btn-danger"
                                            style="width: 100%; text-align: center">Login
                                    </button>
                                </form>

                                <br>
                                <div class="modal-footer">
                                    <small>Don't have an account? <a class="notSignUp" href="/accounts/register">Sign
                                        up</a></small>
                                </div>

                                <div class="modal-footer">
                                    <small>Forget your password? <a class="notRest" href="#">Reset</a>
                                    </small>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

</main>
</body>

<script src="../../../static/js/background/particles.js"></script>
<script src="../../../static/js/background/app.js"></script>

</html>