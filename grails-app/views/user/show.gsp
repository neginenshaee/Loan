<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <sec:ifAnyGranted roles="ROLE_ADMIN">
        <div class="actions">
            <g:link action="index">
                <button class="custom-button primary" type="button"><g:message code="default.list.label" args="[entityName]" /></button>
            </g:link>

            <g:link resource="${this.user}" action="userloanrequest">
                <button class="custom-button primary" type="button">Show Loan Request List</button>
            </g:link>

            <g:link action="edit" resource="${this.user}">
                <button class="custom-button edit" type="button"><g:message code="default.button.edit.label" default="Edit" /></button>
            </g:link>

            <g:link action="delete" resource="${this.user}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                <button class="custom-button delete" type="button"><g:message code="default.button.delete.label" default="Delete" /></button>
            </g:link>
        %{--        <input class="custom-button delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}"  />--}%
            <g:if test="${this.user.enabled}">
                <input type="button" name="activation" class="custom-button delete" value="Deactivate" onclick="changeStatus(false)">
            </g:if>
            <g:else>
                <input type="button" name="activation" class="custom-button submit" value="Activate"  onclick="changeStatus(true)">
            </g:else>
            <input hidden name="hidid" value="${this.user.id}"/>
        </div>
    </sec:ifAnyGranted>


    <div class="container">
        <div class="show-content">
            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>First name</label>
                        <label class="custom-input">${this.user.firstName}</label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Last name</label>
                        <label class="custom-input">${this.user.lastName}</label>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Username</label>
                        <label class="custom-input">${this.user.username}</label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Status</label>
                        <label class="custom-input">${this.user.status}</label>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Email</label>
                        <label class="custom-input">${this.user.email}</label>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Activated</label>
                        <label class="custom-input">
                            <g:if test="${this.user.enabled}">
                                True
                            </g:if>
                            <g:else>
                                False
                            </g:else>
                        </label>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Country</label>
                        <label class="custom-input">${this.user.country}</label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="inputWithIcon">
                        <label>Address</label>
                        <label class="custom-input" >${this.user.address}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <asset:javascript src="application.js"/>
    </body>

</html>
