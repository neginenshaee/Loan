<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change password</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
    <g:form params="${[id: params.id]}" controller="user" action="changePassword" method="PUT">
        <div class="container">

            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <input type="password" class="custom-input" name="oldPassword" placeholder="Old password" value="${params.oldPassword}">
                        <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
                    </div>
                    <div class="message_error">
                        <g:if test="${flash.message}">
                            <g:renderErrors bean="${flash.message}" as="list" field="oldPassword"/>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <input type="password" class="custom-input" name="password" placeholder="New password" value="${params.password}">
                        <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
                    </div>
                    <div class="message_error">
                        <g:if test="${flash.message}">
                            <g:renderErrors bean="${flash.message}" as="list" field="password"/>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <input type="password" class="custom-input" name="repeatPassword" placeholder="Repeat password" value="${params.repeatPassword}">
                        <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
                    </div>
                    <div class="message_error">
                        <g:if test="${flash.message}">
                            <g:renderErrors bean="${flash.message}" as="list" field="repeatPassword"/>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="custom-button submit">change password</button>
                </div>
            </div>
        </div>
    </g:form>

</body>
</html>