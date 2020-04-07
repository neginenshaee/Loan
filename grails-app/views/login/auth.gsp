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
        <g:if test='${flash.message}'>
            <div class="login_message">${flash.message}</div>
        </g:if>

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
%{--            <p id="remember_me_holder">--}%
%{--                <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>--}%
%{--                <label for="remember_me"><g:message code='springSecurity.login.remember.me.label'/></label>--}%
%{--            </p>--}%
            <g:link >forgot password</g:link>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" id="submit" value="${message(code: 'springSecurity.login.button')}">
                    Login
                </button>
            </div>

        </form>
        </div>
    </div>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
