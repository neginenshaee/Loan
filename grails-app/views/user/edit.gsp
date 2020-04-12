
<!DOCTYPE html>
<html>
<head>
    <title>Edit information</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>
<body>

<g:form resource="${this.user}" controller="user" action="update" method="put">
    ${flash.message}
    ${this.user.address}
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="inputWithIcon">
                    <input type="text" class="custom-input" name="firstName" placeholder="First Name" value="${this.user.firstName}">
                    <i class="fa fa-id-badge fa-lg fa-fw" aria-hidden="true"></i>
                </div>
%{--                <div class="message_error">--}%
%{--                    <g:if test="${flash.message}">--}%
%{--                        <g:renderErrors bean="${flash.message}" as="list" field="firstName"/>--}%
%{--                    </g:if>--}%
%{--                </div>--}%
            </div>
            <div class="col-md-6">
                <div class="inputWithIcon">
                    <input type="text" class="custom-input" name="lastName" placeholder="Last Name" value="${this.user.lastName}">
                    <i class="fa fa-id-card fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <div class="inputWithIcon">
                    <input type="text" class="custom-input" name="country" placeholder="Country" value="${this.user.country}">
                    <i class="fa fa-globe fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
            <div class="col-md-8">
                <div class="inputWithIcon">
                    <textarea  type="text" class="custom-input" name="address" placeholder="Address" rows="3">${this.user.address}</textarea>
                    <i class="fa fa-address-card fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <button type="submit" class="custom-button edit">Update</button>
            </div>
        </div>

    </div>
</g:form>
</div>
</body>
</html>
