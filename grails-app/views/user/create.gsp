<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User information</title>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<g:form controller="user" action="save" method="post">
    <header>
        <g:render template="/sharedTemplates/header"/>
    </header>
    <div class="container">

        <div class="form-row">
            <div class="col">
                <div class="form-group">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name">
                </div>
            </div>
        </div>

        <div class="form-group">
            <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>

        <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="Username">
        </div>

        <div class="form-group">
            <input type="password" class="form-control" aria-describedby="passwordHelp" name="password" placeholder="Password">
            <small id="passwordHelp" class="form-text text-muted">
                Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
            </small>
        </div>

        <div class="form-group">
            <input type="text" class="form-control" name="country" placeholder="Country">
        </div>

        <div class="form-group">
            <textarea  type="text" class="form-control" name="address" placeholder="Address" rows="3"></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </div>

    <footer>
        <g:render template="/sharedTemplates/footer"/>
    </footer>
</g:form>
</body>
</html>