<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Forget Password</title>
    <meta name="layout" content="main"/>
</head>

<body>
<g:form controller="user" action="sendresetpassword" method="post">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1>First, let's find your account</h1>
                <p>You forgot your password?</p>
                <p>No worries, just enter the email you used to sign up and we'll send you a link to reset it.</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="inputWithIcon">
                    <input type="email" class="custom-input" name="email" placeholder="Enter email" value="${params.email}">
                    <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
                </div>
                <g:if test="${flash.message}">
                    <div class="message_error">${flash.message}"</div>
                </g:if>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <button type="submit" class="custom-button submit">reset</button>
            </div>
        </div>
    </div>
</g:form>
</body>
</html>