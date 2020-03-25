<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanRequest.label', default: 'LoanRequest')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-loanRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-loanRequest" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="loanRequest" />
        <fieldset class="buttons">
            <div class="row">
                <sec:ifAnyGranted roles="ROLE_USER">
                        <g:form params="${[loanRequest: this.loanRequest.id]}" controller="loanRequest" action="cancel">
                            <g:if test="${this.loanRequest.status.name() == 'CANCELED'}">
                                <button class="btn btn-danger" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >Cancel</button>
                            </g:if>
                            <g:if test="${this.loanRequest.status.name() == 'APPROVED'}">
                                <button class="btn btn-primary" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >Confirm</button>
                            </g:if>
                            <g:link class="edit" action="repayments" resource="${this.loanRequest}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        </g:form>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <g:form params="${[loanRequest: this.loanRequest.id]}" controller="adminLoan" action="approve" method="PUT">
                        <button type="submit" class="btn btn-primary" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >Approve</button>
                    </g:form>
                </sec:ifAnyGranted>
            </div>
        </fieldset>

</div>
</body>
</html>
