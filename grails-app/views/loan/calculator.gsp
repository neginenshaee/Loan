<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>amortization calculator</title>
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

    <div class="amortizationGrid">
            <div class="lefGrid">
                <div class="row">
                    <label  for="mortgageamount">
                        <span>Mortgage amount</span>
                    </label>
                    <input type="text" id="mortgageamount" class="amortizationInput" placeholder="$" value="1000">
                </div>
                <div class="row">
                    <label  for="years">
                        <span>Mortgage term in years</span>
                    </label>
                    <input type="text" id="years" class="amortizationInput" value="1">

                </div>
                <div class="row">
                    <span>Or</span>
                    <label  for="month">
                        <span>Term in months</span>
                    </label>
                        <input type="text" id="month" class="amortizationInput" value="12">

                </div>
                <div class="row">
                    <div class="col-md-6">
                    <label  for="interest">
                        <span>Interest rate per year</span>
                    </label>
                    <input type="text" id="interest" class="amortizationInput" placeholder="%" value="5">
                    </div>
                    <div class="col-md-6">
                    <button class="btn amortizationButton" id="calculate">Calculate</button>
                    </div>
                </div>

            </div>

            <div class="rightGrid">
                <p>Monthly Payments</p>
                <p id="monthlyPayment"></p>
                <div>
                    <span>Total Principal Paid</span>
                    <span id="total" ></span>
                </div>
                <hr/>
                <div>
                    <span>Total Interest Paid</span>
                    <span id="totalinterest"></span>
                </div>



                <a class="linktorate">Today's Rate</a>
                <br>
                <a>
                    <span id="calculatorAmortizationLink" class="calculatorAmortizationLink">Show amortization schedule</span>
                </a>

            </div>

    </div>

    <div id="amortizationDiv">
        <div class="grid">
            <div>
                <div>
                    <label for="startDate">Start Date</label>
                    <input id="startDate" class="input-field" type="date">
                </div>
            </div>

            <div>
                <p class="calculatorAcumin">Estimated Payoff Date</p>
                <p id="estimate" class="calculatorAcumin"></p>
            </div>
        </div>

        <div>
            <p class="calculatorAcumin">Amortization Schedule</p>
            <div id="here_table"></div>
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
</div>
</body>
</html>