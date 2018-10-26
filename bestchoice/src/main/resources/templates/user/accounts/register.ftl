<!DOCTYPE html>
<html lang="en">

<head>
    <title>Registration</title>
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
    <script src="../../static/js/vendor/respond.src.js"></script>
    <![endif]-->
    <style>
        .regiter {
            margin-top: 95px;
            width: 100%;
        }

        @media (min-device-width: 375px) and (max-device-width: 667px) and (-webkit-min-device-pixel-ratio: 2) {
            .regiter {
                margin-top: 60px;
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

<main role="main">
    <div class="regiter container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4" style="z-index:9999">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            Create an account
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form novalidate="novalidate" id="formSignUp" method="post" action="/accounts/register">
                            <div id="errorSignUp">
                            </div>

                            <div class="form-group">
                                <label>Email</label>
                                <div class="input-group" style="width: 100%">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" class="form-control" placeholder="Email address" name="email" id="emailSignUp" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Username</label>
                                <div class="input-group" style="width: 100%">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" class="form-control" placeholder="Username" name="username" id="usernameSignUp" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Password</label>
                                <div class="input-group" style="width: 100%">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="password" class="form-control" placeholder="Create a password" name="password" id="passwordSignUp" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Confirm password</label>
                                <div class="input-group" style="width: 100%">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="password" class="form-control" placeholder="Confirm password" name="confirmPassword" id="passwordConfirmSignUp" required >
                                </div>

                                <br>
                                <button type="submit" id="btnSignUp" class="btn btn-primary" style="width: 127px;">
                                    Register
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</main>
</body>
<script src="/static/js/background/particles.js"></script>
<script src="/static/js/background/app.js"></script>
</html>