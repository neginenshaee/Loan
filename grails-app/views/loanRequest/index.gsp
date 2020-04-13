<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanRequest.label', default: 'LoanRequest')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>
    <div class="container">
        <g:link action="create">
            <button class="custom-button primary" type="button"><g:message code="default.new.label" args="[entityName]"/></button>
        </g:link>

        <h1><g:message code="default.list.label" args="[entityName]" /></h1><hr>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <table class="content-table">
            <thead>
            <tr>
                <td>Amount</td>
                <td>Interest</td>
                <td>Status</td>
                <td>Month</td>
                <td>User</td>
            </tr>
            </thead>
            <g:each in="${loans}" status="i" var="loan">
                <tr>
                    <td><a href="/loanRequest/show/${loan.id}"><g:formatNumber  number="${loan.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></a></td>
                    <td>${loan.interest}%</td>
                    <td>${loan.status}</td>
                    <td>${loan.months}</td>
                    <td><a href="/user/show/${loan.user.id}">${loan.user}</a></td>
                </tr>
            </g:each>
        </table>

        <div class="pagination">
            <g:paginate action="index" max="5" total="${loanRequestCount ?: 0}" />
        </div>
    </div>
</body>
</html>