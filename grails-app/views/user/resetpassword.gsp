<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Reset password</title>
    <meta name="layout" content="main"/>
</head>

<body>
<g:form controller="user" params="${[id: params.id]}" action="reset" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="inputWithIcon">
                    <input type="password" class="custom-input" aria-describedby="passwordHelp" name="password" placeholder="Password">
                    <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
                    <small id="passwordHelp" class="form-text text-muted">
                        Your password must be 8-20 characters long, contain letters and numbers.
                    </small>
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
                    <input type="password" class="custom-input" name="repass" placeholder="Repeat Password">
                    <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
                </div>
                <div class="message_error">
                    <g:if test="${flash.message}">
                        <g:renderErrors bean="${flash.message}" as="list" field="repass"/>
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
</g:form>
</body>
</html>