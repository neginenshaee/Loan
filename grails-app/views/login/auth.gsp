<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code='springSecurity.login.title'/></title>
    <asset:stylesheet src="application.css"/>
</head>

<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
        <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="login100-form validate-form" autocomplete="off">

            <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                <input class="input100" type="text" name="${usernameParameter ?: 'username'}" id="username" autocapitalize="none" placeholder="Username">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                </span>
            </div>

            <div class="wrap-input100 validate-input" data-validate = "Password is required">
                <input class="input100" type="password" name="${passwordParameter ?: 'password'}" placeholder="Password" id="password">
                <span class="focus-input100"></span>
                <span class="symbol-input100">
                    <i class="fa fa-lock" aria-hidden="true"></i>
                </span>
            </div>
            <g:link controller="user" action="forgetpassword">forgot password?</g:link>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" id="submit" value="${message(code: 'springSecurity.login.button')}">
                    Login
                </button>
            </div>
        </form>
        <g:if test='${flash.message}'>
            ${flash.message}
        </g:if>
        </div>

    </div>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
