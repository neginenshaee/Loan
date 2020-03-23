<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change password</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
    <g:form controller="user" action="changePassword" method="PUT">
        <div class="container">

            <div class="custom-wrapper">
                <div class="row">
                    <div class="col">
                        <input type="password" class="form-control custom-input" aria-describedby="oldHelp" name="old" placeholder="Password">
                        <small id="oldHelp" class="form-text text-muted">
                            Enter your old password
                        </small>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <input type="password" class="form-control custom-input" aria-describedby="newHelp" name="new" placeholder="Password">
                        <small id="newHelp" class="form-text text-muted">
                            Enter new pass
                        </small>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <input type="password" class="form-control custom-input" aria-describedby="reHelp" name="re" placeholder="Password">
                        <small id="reHelp" class="form-text text-muted">
                            Enter new pass again
                        </small>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </div>
            </div>
        </div>
    </g:form>

</body>
</html>