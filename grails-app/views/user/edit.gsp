
<!DOCTYPE html>
<html>
<head>
    <title>Edit information</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>
<body>

<g:form params="${[id: params.id]}" controller="user" action="update" method="put" enctype="multipart/form-data">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <img height="110px" width="110px" src="${createLink(action: 'getImage', controller: 'user', id: params.id)}"/>
            </div>
            <div class="col-md-10">

                <div class="inputWithIcon">
                    <input class="custom-input" name="userImage" type="file" />
                    <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
                </div>
                <div class="message_error">
                    <g:if test="${flash.message}">
                        <g:renderErrors bean="${flash.message}" as="list" field="image"/>
                    </g:if>
                </div>
            </div>
        </div>




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
                <button type="submit" class="custom-button edit">Update</button>
            </div>
        </div>

    </div>
</g:form>
</div>
</body>
</html>
