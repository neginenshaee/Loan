<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanRequest.label', default: 'LoanRequest')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

    <div class="actions">
        <g:link action="index">
            <button class="custom-button primary" type="button"><g:message code="default.list.label" args="[entityName]" /></button>
        </g:link>

        <g:link action="create">
            <button class="custom-button primary" type="button"><g:message code="default.new.label" args="[entityName]" /></button>
        </g:link>

        <sec:ifAnyGranted roles="ROLE_USER">
            <g:if test="${this.loanRequest.status.name() == 'APPROVED'}">
                <button type="button" class="custom-button submit" data-toggle="modal" data-target="#confirmationModal">Confirm</button>
                <g:link resource="${this.loanRequest}" action="confirm">
                    <g:render template="/sharedTemplates/confirmationpopup"/>
                </g:link>
            </g:if>

            <g:if test="${this.loanRequest.status.name() == 'REQUESTED' || this.loanRequest.status.name() == 'APPROVED'}">
                <button type="button" class="custom-button delete" data-toggle="modal" data-target="#confirmationModal">Cancel</button>
                <g:link resource="${this.loanRequest}" action="cancel">
                    <g:render template="/sharedTemplates/confirmationpopup"/>
                </g:link>
            </g:if>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <g:if test="${this.loanRequest.status.name() == 'REQUESTED'}">
                <button type="button" class="custom-button submit" data-toggle="modal" data-target="#confirmationModal">Approve</button>
                <g:link params="${[id: this.loanRequest.id]}" controller="adminLoanRequest" action="approve">
                    <g:render template="/sharedTemplates/confirmationpopup"/>
                </g:link>
            </g:if>

            <g:if test="${this.loanRequest.status.name() == 'REQUESTED'}">
                <g:link params="${[id: this.loanRequest.id]}" controller="adminLoanRequest" action="reject">
                    <button type="submit" class="custom-button delete" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >Reject</button>
                </g:link>
            </g:if>

            <g:if test="${this.loanRequest.status.name() == 'CONFIRMED'}">
                <g:link params="${[id: this.loanRequest.id]}" controller="loan" action="create">
                    <button type="submit" class="custom-button submit">Create Loan</button>
                </g:link>
            </g:if>
        </sec:ifAnyGranted>
    </div>

    <div class="container">
        <div class="show-content">
            <div class="row">
                <div class="col-md-6">
                    <label>Mortgage Amount</label>
                    <label type="text" class="custom-input" name="amount"><g:formatNumber  number="${this.loanRequest.amount}" maxFractionDigits="0" type="currency" currencyCode="USD" /></label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Mortgage term in years</label>
                    <label type="text" class="custom-input" name="years">${this.loanRequest.months/12}</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Term in months</label>
                    <label type="text" class="custom-input" name="months">${this.loanRequest.months}</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Interest rate per year</label>
                    <label type="text" class="custom-input" name="interest">${this.loanRequest.interest}%</label>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Status</label>
                    <label type="text" class="custom-input" name="status">${this.loanRequest.status}</label>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
