<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>amortization calculator</title>
    <g:javascript library='jquery' />

</head>

<body>
<div class="container">
    <h1 class="amotizationTitle">Amortization Schedule Calculator</h1>

    <div class="caculatorContent">
        <p class="calculatorCalibre">Amortization is paying off a debt over time in equal installments.
        Part of each payment goes toward the loan principal, and part goes toward interest.
        With mortgage amortization, the amount going toward principal starts out small, and gradually grows larger month by month.
        Meanwhile, the amount going toward interest declines month by month for fixed-rate loans.
        </p>

        <p class="calculatorCalibre">
            Your amortization schedule shows how much money you pay in principal and interest over time.
            Use this calculator to see how those payments break down over your loan term.
        </p>
    </div>

    <g:formRemote name="amortizationleft" update="updateMe" url="[controller: 'loanRequest', action:'calculate']">
        <div class="amortizationGrid">
            <div class="lefGrid">
                <g:render template="amortizationinput"/>
            </div>
            <div class="rightGrid">
                <div id="updateCalculation">
                    <g:render template="/loanRequest/amortizationcalc"/>
                </div>
                <div class="divtorate">
                    <a class="linktorate">Today's Rate</a>
                </div>
                <br>
                <a onclick="calculateSchedule()">
                    <span id="calculatorAmortizationLink" class="calculatorAmortizationLink">Show amortization schedule</span>
                </a>
            </div>
        </div>
    </g:formRemote>

    <div id="amortizationDiv" >
        <div id="updateSchedule">
            <g:render template="/loanRequest/amortizationschedule"/>
        </div>
    </div>

    <div>
        <p class="calculatorCalibre">
            Compare rates with confidence. Rates are accurate and available as of the date seen for
        Bankrate customers. Identify yourself as a Bankrate consumer to get the Bankrate.com rate.
        </p>
    </div>
    <hr>
    <div>
        <h3 class="calculatorAcumin">What is an amortization schedule?</h3>
        <p class="calculatorCalibre">An amortization schedule is a table that lists each regular payment on a mortgage over time.
        A portion of each payment is applied toward the principal balance and interest, and the amortization schedule details
        how much will go toward each component of your mortgage payment.
        </p>
        <p class="calculatorCalibre">
            Initially, most of your payment goes toward the interest rather than the principal.
            The schedule will show as the term of your loan progresses, a larger share of your payment goes
            toward paying down the principal until the loan is paid in full at the end of your term.
        </p>
    </div>
    <div>
        <h3 class="calculatorAcumin">How do you calculate amortization?</h3>
        <p class="calculatorCalibre">
            An amortization schedule calculator shows:
        </p>
        <ol class="orderedList calculatorCalibre">
            <li>How much principal and interest are paid in any particular payment.</li>
            <li>How much total principal and interest have been paid at a specified date.</li>
            <li>How much principal you owe on the mortgage at a specified date.</li>
            <li>How much time you will chop off the end of the mortgage by making one or more extra payments.</li>
        </ol>
        <p class="calculatorCalibre">This means you can use the mortgage amortization calculator to:</p>
            <ol class="orderedList calculatorCalibre">
                <li>Determine how much principal you owe now, or will owe at a future date.</li>
                <li>Determine how much extra you would need to pay every month to repay the mortgage in, say, 22 years instead of 30 years.</li>
                <li>See how much interest you have paid over the life of the mortgage, or during a particular year, though this may vary based on when the lender receives your payments.</li>
                <li>Figure how much equity you have.</li>
            </ol>
    </div>

    <div>
        <div>
            <h3 class="calculatorAcumin">How do I calculate monthly mortgage payments?</h3>
            <p class="calculatorCalibre">Here’s a formula to calculate your monthly payments manually: M= P[r(1+r)^n/((1+r)^n)-1)]</p>
            <ul class="calculatorCalibre">
                <li>M = the total monthly mortgage payment.</li>
                <li>P = the principal loan amount.</li>
                <li>r = your monthly interest rate. Lenders provide you an annual rate so you’ll need to divide that figure by 12 (the number of months in a year) to get the monthly rate. If your interest rate is 5 percent, your monthly rate would be 0.004167 (0.05/12=0.004167)</li>
                <li>n = number of payments over the loan’s lifetime. Multiply the number of years in your loan term by 12 (the number of months in a year) to get the number of payments for your loan. For example, a 30-year fixed mortgage would have 360 payments (30x12=360)</li>
            </ul>





        </div>
    </div>
</div>
</body>
</html>