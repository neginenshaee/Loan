<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Request Loan</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<g:form controller="loanRequest" action="save" method="post">
    <div class="container">
        <div class="amortizationGrid">
            <div class="lefGrid">
                <g:render template="/loanRequest/amortizationinput"/>

                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="custom-button submit">Request</button>
                    </div>
                </div>

            </div>

            <div class="rightGrid" >
                <div id="updateCalculation">
                    <g:render template="/loanRequest/amortizationcalc"/>
                </div>

                <a onclick="calculateSchedule()">
                    <span id="calculatorAmortizationLink" class="calculatorAmortizationLink">Show amortization schedule</span>
                </a>
            </div>
        </div>
        <div id="amortizationDiv" >
            <div id="updateSchedule">
                <g:render template="/loanRequest/amortizationschedule"/>
            </div>
        </div>



    </div>

</g:form>
</body>
</html>