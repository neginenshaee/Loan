<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanRequest.label')}" />
    <title>User's Loan Request</title>
</head>

<body>
    <div id="list-loanRequest" class="content table table-striped table-hover" role="main">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <f:table collection="${requests}" />

        <div class="pagination">
%{--            <g:paginate total="${requests.size() ?: 0}" max="3" offset="2"/>--}%
        </div>
    </div>
</body>
</html>