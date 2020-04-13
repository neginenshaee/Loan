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
            <div class="row">
                <div class="col-md-12">
                    <div class="inputWithIcon">
                        <input type="email" class="custom-input" name="email" placeholder="Enter email" value="${params.email}">
                        <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
                    </div>
                    <div class="message_error">
                        <g:if test="${flash.message}">
                            <g:renderErrors bean="${flash.message}" as="list" field="email"/>
                        </g:if>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="custom-button submit">Submit</button>
                </div>
            </div>
        </div>
    </div>
</g:form>
</body>
</html>