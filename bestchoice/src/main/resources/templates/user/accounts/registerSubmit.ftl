<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <!-- Main Style Sheet -->
    <link rel="stylesheet" href="../../../static/css/bootstrap3/bootstrap.css">
    <link rel="stylesheet" href="../../../static/css/background/demo.css"/>
    <link rel="stylesheet" href="../../../static/css/background/templatemo-style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../../static/js/bootstrap3/bootstrap.js"></script>
    <script src="../../../static/js/background/modernizr.custom.86080.js"></script>
    <!-- Respond.js for IE 8 or less only -->
    <!--[if (lt IE 9) & (!IEMobile)]>
    <script src="../../static/js/vendor/respond.src.js"></script>
    <![endif]-->
    <style>
        .unconfirmed {
            margin-top: 200px;
            width: 100%;
        }

        @media (min-device-width: 375px) and (max-device-width: 667px) and (-webkit-min-device-pixel-ratio: 2) {
            .unconfirmed {
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
    <div class="unconfirmed container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col col-md-6" style="z-index:9999">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            Confirmation
                        </h4>
                    </div>

                    <div class="modal-body">
                        <h2> Hello, ${username}!</h2>
                        <h3>You have not confirmed your account yet.</h3>
                        <p>Before you can access this site you need to confirm your account.
                            Check your inbox, you should have received an email with a confirmation link.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>