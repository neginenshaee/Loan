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
<g:form action="search" controller="loanRequest">
    <button type="submit" class="search-submit"><i class="fa fa-search fa-lg"></i></button>

        <table class="content-table">
            <thead>
            <tr>
                <td>Amount<br><input name="searchamount" placeholder="Greater than" value="${params.searchamount}"></td>
                <td>Interest<br><input name="searchinterest" placeholder="Less than" value="${params.searchinterest}"></td>
                <td>Status<br><g:select name="searchstatus" noSelection="${['':'Select One']}" from="${enums.Status.values()}" value="${params.searchstatus}"/></td>
                <td>Month<br><input name="searchmonths" placeholder="Less than" value="${params.searchmonths}"></td>
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
            <g:paginate params="${params}" total="${loanRequestCount ?: 0}" />
        </div>
</g:form>
    </div>
</body>
</html>