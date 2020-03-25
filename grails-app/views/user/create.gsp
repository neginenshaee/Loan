<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User information</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>


    <g:if test="${flash.message}">
        <div class="message" role="status">
            <g:hasErrors bean="${flash.message}">
                <g:renderErrors bean="${flash.message}" />
            </g:hasErrors>
        </div>
    </g:if>
    <div class="custom-background">
        <g:form controller="user" action="save" method="post">
            <div class="container">
                <div class="custom-wrapper">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control custom-input" name="firstName" placeholder="First Name">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control custom-input" name="lastName" placeholder="Last Name">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control custom-input" name="username" placeholder="Username">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="password" class="form-control custom-input" aria-describedby="passwordHelp" name="password" placeholder="Password">
                                <small id="passwordHelp" class="form-text text-muted">
                                    Your password must be 8-20 characters long, contain letters and numbers.
                                </small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <input type="email" class="form-control custom-input" name="email" aria-describedby="emailHelp" placeholder="Enter email">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" class="form-control custom-input" name="country" placeholder="Country">
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <textarea  type="text" class="form-control custom-input" name="address" placeholder="Address" rows="3"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>





                </div>
            </div>

        </g:form>
    </div>
</body>
</html>