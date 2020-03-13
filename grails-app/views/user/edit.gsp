
<!DOCTYPE html>
<html>
<head>
    <title>Edit information</title>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>
<body>
<g:form resource="${this.user}" controller="user" action="update" method="put">
    <header>
        <g:render template="/sharedTemplates/header"/>
    </header>
    <div class="container">

        <div class="form-row">
            <div class="col">
                <div class="form-group">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name" value="${this.user.firstName}">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"  value="${this.user.lastName}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <input type="text" class="form-control" name="country" placeholder="Country" value="${this.user.country}">
        </div>

        <div class="form-group">
            <textarea  type="text" class="form-control" name="address" placeholder="Address" rows="3" >${this.user.address}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </div>

    <footer>
        <g:render template="/sharedTemplates/footer"/>
    </footer>
</g:form>
</div>
</body>
</html>
