<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container">

            <sec:ifAnyGranted roles="ROLE_ADMIN">
                <g:link  controller="user" action="create">
                    <button class="custom-button primary" type="button"><g:message code="default.new.label" args="[entityName]"/></button>
                </g:link>
            </sec:ifAnyGranted>

            <h1><g:message code="default.list.label" args="[entityName]" /></h1><hr>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table class="content-table">
                <thead>
                    <tr>
                        <td>Username</td>
                        <td>Firstname</td>
                        <td>Lastname</td>
                        <td>Email</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <g:each in="${users}" status="i" var="user">
                    <tr>
                        <td><a href="/user/show/${user.id}">${user.username}</a></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.status}</td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>
    </body>
</html>