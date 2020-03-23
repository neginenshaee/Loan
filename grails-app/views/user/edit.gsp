
<!DOCTYPE html>
<html>
<head>
    <title>Edit information</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>
<body>
<g:form resource="${this.user}" controller="user" action="update" method="put">
    <div class="container">

        <div class="custom-wrapper">
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <input type="text" class="form-control custom-input" name="firstName" placeholder="First Name" value="${this.user.firstName}">
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <input type="text" class="form-control custom-input" name="lastName" placeholder="Last Name"  value="${this.user.lastName}">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <input type="text" class="form-control custom-input" name="country" placeholder="Country" value="${this.user.country}">
                </div>

                <div class="col-md-8">
                    <textarea  type="text" class="form-control custom-input" name="address" placeholder="Address" rows="3" >${this.user.address}</textarea>
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
</div>
</body>
</html>
