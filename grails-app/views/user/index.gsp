<%@ page import="enums.Status" %>
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

            <g:form action="search" controller="adminUser">
                <button type="submit" class="search-submit"><i class="fa fa-search fa-lg"></i></button>

            <table class="content-table">
                <thead>
                    <tr>
                        <td>Username<br><input name="searchusername" value="${params.searchusername}"></td>
                        <td>Firstname<br><input name="searchfirst" value="${params.searchfirst}"></td>
                        <td>Lastname<br><input name="searchlast" value="${params.searchlast}"></td>
                        <td>Email<br><input name="searchemail" value="${params.searchemail}"></td>
                        <td>Status<br><g:select name="searchstatus" noSelection="${['':'Select One']}" from="${enums.UserStatus.values()}" value="${params.searchstatus}"/></td>
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
                <g:paginate params="${params}" total="${userCount ?: 0}" />
            </div>
            </g:form>
        </div>
    </body>
</html>