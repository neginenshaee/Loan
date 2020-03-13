<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<g:form controller="user" action="changePassword" method="PUT">
    <header>
        <g:render template="/sharedTemplates/header"/>
    </header>
    <div class="container">

        <div class="form-group">
            <input type="password" class="form-control" aria-describedby="oldHelp" name="old" placeholder="Password">
            <small id="oldHelp" class="form-text text-muted">
                Enter your old password
            </small>
        </div>

        <div class="form-group">
            <input type="password" class="form-control" aria-describedby="newHelp" name="new" placeholder="Password">
            <small id="newHelp" class="form-text text-muted">
                Enter new pass
            </small>
        </div>

        <div class="form-group">
            <input type="password" class="form-control" aria-describedby="reHelp" name="re" placeholder="Password">
            <small id="reHelp" class="form-text text-muted">
                Enter new pass again
            </small>
        </div>


        <button type="submit" class="btn btn-primary">Update</button>
    </div>

    <footer>
        <g:render template="/sharedTemplates/footer"/>
    </footer>
</g:form>
</body>
</html>