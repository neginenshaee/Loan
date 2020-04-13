<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loan.label', default: 'Loan')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

    <div class="actions">
        <g:link action="index">
            <button class="custom-button primary" type="button"><g:message code="default.list.label" args="[entityName]" /></button>
        </g:link>

    </div>

    <div class="container">
        <div class="show-content">
            <div class="row">
                <div class="col-md-6">
                    <label>Loan</label>
                </div>
                <div class="col-md-6">
                    <label>Requested</label>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label>Mortgage Amount</label>
                    <label type="text" class="custom-input" name="amount"><g:formatNumber  number="${this.loan.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></label>
                </div>
                <div class="col-md-6">
                    <label>Mortgage Amount</label>
                    <label type="text" class="custom-input" name="amount"><g:formatNumber  number="${this.loan.loanRequest.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Mortgage term in years</label>
                    <label type="text" class="custom-input" name="years">${this.loan.months/12}</label>
                </div>
                <div class="col-md-6">
                    <label>Mortgage term in years</label>
                    <label type="text" class="custom-input" name="years">${this.loan.loanRequest.months/12}</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Term in months</label>
                    <label type="text" class="custom-input" name="months">${this.loan.months}</label>
                </div>
                <div class="col-md-6">
                    <label>Term in months</label>
                    <label type="text" class="custom-input" name="months">${this.loan.loanRequest.months}</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Interest rate per year</label>
                    <label type="text" class="custom-input" name="interest">${this.loan.interest}%</label>
                </div>
                <div class="col-md-6">
                    <label>Interest rate per year</label>
                    <label type="text" class="custom-input" name="interest">${this.loan.loanRequest.interest}%</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Monthly Payment</label>
                    <label type="text" class="custom-input" name="status"><g:formatNumber  number="${this.loan.monthlyPayment}" maxFractionDigits="2" type="currency" currencyCode="USD" /></label>
                </div>
            </div>

        </div>


                <g:render template="/loanRequest/amortizationschedule"/>
    </div>

</body>
</html>
