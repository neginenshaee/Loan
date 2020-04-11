<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loan.label', default: 'Loan')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>
    <div class="container">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1><hr>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <table class="content-table">
            <thead>
            <tr>
                <td>Amount</td>
                <td>Interest</td>
                <td>Month</td>
                <td>Monthly Payments</td>
                <td>Loan Request</td>
                <td>User</td>
            </tr>
            </thead>
            <g:each in="${loans}" status="i" var="loan">
                <tr>
                    <td><a href="/loan/show/${loan.id}"><g:formatNumber  number="${loan.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></a></td>
                    <td>${loan.interest}%</td>
                    <td>${loan.months}</td>
                    <td><g:formatNumber  number="${loan.monthlyPayment}" maxFractionDigits="2" type="currency" currencyCode="USD" /></td>
                    <td><a href="/loanRequest/show/${loan.loanRequest.id}">${loan.loanRequest}</a></td>
                    <td><a href="/user/show/${loan.loanRequest.user.id}">${loan.loanRequest.user}</a></td>
                </tr>
            </g:each>
        </table>

        <div class="pagination">
            <g:paginate total="${loanRequestCount ?: 0}" />
        </div>
    </div>
</body>
</html>