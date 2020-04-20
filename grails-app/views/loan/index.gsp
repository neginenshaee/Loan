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
<g:form action="search" controller="loan">
    <button type="submit" class="search-submit"><i class="fa fa-search fa-lg"></i></button>

        <table class="content-table">
            <thead>
            <tr>
                <td>Amount<br><input name="searchamount" placeholder="Greater than" value="${params.searchamount}"></td>
                <td>Interest<br><input name="searchinterest" placeholder="Less than" value="${params.searchinterest}"></td>
                <td>Month<br><input name="searchmonths" placeholder="Less than" value="${params.searchmonths}"></td>
                <td>Monthly Payments<br><input name="searchmonthlypayments" placeholder="Greater than" value="${params.searchmonthlypayments}"></td>
                <td>User<br><input name="searchusername" placeholder="Like" value="${params.searchusername}"></td>
            </tr>
            </thead>
            <g:each in="${loans}" status="i" var="loan">
                <tr>
                    <td><a href="/loan/show/${loan.id}"><g:formatNumber  number="${loan.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></a></td>
                    <td>${loan.interest}%</td>
                    <td>${loan.months}</td>
                    <td><g:formatNumber  number="${loan.monthlyPayment}" maxFractionDigits="2" type="currency" currencyCode="USD" /></td>
                    <td><a href="/user/show/${loan.loanRequest.user.id}">${loan.loanRequest.user}</a></td>
                </tr>
            </g:each>
        </table>

        <div class="pagination">
            <g:paginate params="${params}" total="${loansCount ?: 0}" />
        </div>
</g:form>
    </div>
</body>
</html>