<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Request Loan</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<g:form controller="loanRequest" action="save" method="post">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <label>Mortgage Amount</label>
                <div class="inputWithIcon">
                    <input type="text" class="custom-input" name="amount" placeholder="Mortgage Amount" value="165000">
                    <i class="fa fa-dollar-sign fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Mortgage term in years</label>
                <div class="inputWithIcon">
                    <input id="years" type="text" class="custom-input" name="years" placeholder="Mortgage term in years" value="30">
                    <i class="fa fa-calendar-day fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Term in months</label>
                <div class="inputWithIcon">
                    <input id="month" type="text" class="custom-input" name="months" placeholder="Term in months" value="360">
                    <i class="fa fa-calendar-day fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Interest rate per year</label>
                <div class="inputWithIcon">
                    <input type="text" class="custom-input" name="interest" placeholder="Interest rate per year" value="4.5">
                    <i class="fa fa-percent fa-lg fa-fw" aria-hidden="true"></i>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <button type="submit" class="custom-button submit">Request</button>
            </div>
        </div>

    </div>

</g:form>
</body>
</html>