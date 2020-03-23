<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change password</title>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<header>
    <g:render template="/sharedTemplates/header"/>
</header>
    <g:form controller="user" action="changePassword" method="PUT">
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <input type="password" class="form-control" aria-describedby="oldHelp" name="old" placeholder="Password">
                    <small id="oldHelp" class="form-text text-muted">
                        Enter your old password
                    </small>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <input type="password" class="form-control" aria-describedby="newHelp" name="new" placeholder="Password">
                    <small id="newHelp" class="form-text text-muted">
                        Enter new pass
                    </small>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <input type="password" class="form-control" aria-describedby="reHelp" name="re" placeholder="Password">
                    <small id="reHelp" class="form-text text-muted">
                        Enter new pass again
                    </small>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </g:form>
<footer>
    <g:render template="/sharedTemplates/footer"/>
</footer>
</body>
</html>