<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'repayment.label', default: 'Repayment')}" />
</head>

<body>
<div class="container">
    <g:form controller="repayment" action="save">
        <div class="custom-wrapper">
            <div class="row">
                <div class="col form-group">
                    <input class="form-control custom-input" name="period" placeholder="Period">
                </div>
            </div>
            <div class="row">
                <div class="col form-group">
                    <input class="form-control custom-input" name="interest" placeholder="Interest">
                </div>
            </div>
            <div class="row">
                <div class="col form-group">
                    <input class="form-control custom-input" name="dailyPenalty" placeholder="Daily Penalty">
                </div>
            </div>

            <div class="row">
                <div class="col form-group">
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </div>
        </div>
    </g:form>
</div>
</body>
</html>