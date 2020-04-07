<div class="grid">
    <div class="divStartDate">
        <label class="calculatorAcumin" for="startDate">Start Date</label>
        <input id="startDate" class="amortizationInput" type="date">
    </div>

    <div class="divEstimate">
        <p class="calculatorAcumin">Estimated Payoff Date</p>
        <p id="estimate" class="calculatorAcumin"></p>
    </div>
</div>

<div>
    <p class="calculatorAcumin">Amortization Schedule</p>
    <table class="content-table">
        <thead>
        <tr>
            <td>Payment Date</td>
            <td>Payment</td>
            <td>Principal</td>
            <td>Interest</td>
            <td>Total Interest</td>
            <td>Balance</td>
        </tr>
        </thead>
        <g:each in="${shadowPayments}" status="i" var="payment">
            <tr>
                <td>${payment.paymentDate}</td>
                <td><g:formatNumber  number="${payment.loan.monthlyPayment}" maxFractionDigits="2" type="currency" currencyCode="USD" /></td>
                <td><g:formatNumber  number="${payment.principal}" maxFractionDigits="2" type="currency" currencyCode="USD" /></td>
                <td><g:formatNumber  number="${payment.interest}" maxFractionDigits="2" />%</td>
                <td>Total Interest</td>
                <td><g:formatNumber  number="${payment.balance}" maxFractionDigits="2" type="currency" currencyCode="USD" /></td>
            </tr>
        </g:each>
    </table>
</div>