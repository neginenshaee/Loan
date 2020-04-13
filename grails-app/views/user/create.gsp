<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User information</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
    <g:form controller="user" action="save" method="post">
        <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="inputWithIcon">
                            <input type="text" class="custom-input" name="firstName" placeholder="First Name" value="${params.firstName}">
                            <i class="fa fa-id-badge fa-lg fa-fw" aria-hidden="true"></i>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="firstName"/>
                            </g:if>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="inputWithIcon">
                            <input type="text" class="custom-input" name="lastName" placeholder="Last Name" value="${params.lastName}">
                            <i class="fa fa-id-card fa-lg fa-fw" aria-hidden="true"></i>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="lastName"/>
                            </g:if>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="inputWithIcon">
                            <input type="text" class="custom-input" name="username" placeholder="Username" value="${params.username}">
                            <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="username"/>
                            </g:if>
                        </div>
                    </div>
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
                    <div class="col-md-12">
                        <div class="inputWithIcon">
                            <input type="email" class="custom-input" name="email" aria-describedby="emailHelp" placeholder="Enter email" value="${params.email}">
                            <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="email"/>
                            </g:if>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-4">
                        <div class="inputWithIcon">
                            <input type="text" class="custom-input" name="country" placeholder="Country" value="${params.country}">
                            <i class="fa fa-globe fa-lg fa-fw" aria-hidden="true"></i>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="country"/>
                            </g:if>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="inputWithIcon">
                            <textarea  type="text" class="custom-input" name="address" placeholder="Address" rows="3">${params.address}</textarea>
                            <i class="fa fa-address-card fa-lg fa-fw" aria-hidden="true"></i>
                        </div>
                        <div class="message_error">
                            <g:if test="${flash.message}">
                                <g:renderErrors bean="${flash.message}" as="list" field="address"/>
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