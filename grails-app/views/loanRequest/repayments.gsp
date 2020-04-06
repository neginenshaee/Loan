<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'repayment.label', default: 'Repayment')}" />
</head>

<body>
    <g:form controller="loanRequest" action="select" method="POST">
        <g:each in="${request}" var="req">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="about-item text-center">
                            <g:each in="${req}" var="var">
                                ${var.getKey()} : ${var.getValue()} <br>
                            </g:each>
                            <g:link action="select" class="btn btn-primary"
                                    controller="loanRequest"
                                    params="[id: req.id,loan: req.loan]">Select</g:link>

                        </div>
                    </div>
                </div>
            </div>
            <br>
        </g:each>
    </g:form>
</body>
</html>